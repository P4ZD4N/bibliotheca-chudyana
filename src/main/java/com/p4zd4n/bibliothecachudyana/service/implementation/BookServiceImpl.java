package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.enums.BookStatus;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public Book findById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookDAO.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthorName(String authorFirstName) {
        return bookDAO.findByAuthorName(authorFirstName);
    }

    @Override
    public List<Book> findByAuthorLastName(String authorLastName) {
        return bookDAO.findByAuthorLastName(authorLastName);
    }

    @Override
    public List<Book> findByMinReleaseYear(Integer minReleaseYear) {
        return bookDAO.findByMinReleaseYear(minReleaseYear);
    }

    @Override
    public List<Book> findByMaxReleaseYear(Integer maxReleaseYear) {
        return bookDAO.findByMaxReleaseYear(maxReleaseYear);
    }

    @Override
    public List<Book> findByMinAndMaxReleaseYear(Integer minReleaseYear, Integer maxReleaseYear) {
        return bookDAO.findByMinAndMaxReleaseYear(minReleaseYear, maxReleaseYear);
    }

    @Override
    public List<Book> findByCategory(String category) {
        return bookDAO.findByCategory(category);
    }

    @Override
    public List<Book> findByMinPrice(Double minPrice) {
        return bookDAO.findByMinPrice(minPrice);
    }

    @Override
    public List<Book> findByMaxPrice(Double maxPrice) {
        return bookDAO.findByMaxPrice(maxPrice);
    }

    @Override
    public List<Book> findByMinAndMaxPrice(Double minPrice, Double maxPrice) {
        return bookDAO.findByMinAndMaxPrice(minPrice, maxPrice);
    }

    @Override
    public List<Book> findByMinPages(Integer minPages) {
        return bookDAO.findByMinPages(minPages);
    }

    @Override
    public List<Book> findByMaxPages(Integer maxPages) {
        return bookDAO.findByMaxPages(maxPages);
    }

    @Override
    public List<Book> findByMinAndMaxPages(Integer minPages, Integer maxPages) {
        return bookDAO.findByMinAndMaxPages(minPages, maxPages);
    }

    @Override
    public List<Book> findUnavailable() {
        return bookDAO.findUnavailable();
    }

    @Override
    public List<Book> findLastItems() {
        return bookDAO.findLastItems();
    }

    @Override
    public List<Book> findAvailable() {
        return bookDAO.findAvailable();
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public List<Book> getNewReleases() {
        List<Book> allBooks = bookDAO.findAll();
        return getListOfNewReleases(allBooks);
    }

    private List<Book> getListOfNewReleases(List<Book> allBooks) {
        List<Book> newReleases = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        LocalDate dateOneWeekAgo = currentDate.minusWeeks(1);

        for (Book book : allBooks)
            if (book.getAddToLibraryDate().isAfter(dateOneWeekAgo) || book.getAddToLibraryDate().isEqual(dateOneWeekAgo))
                newReleases.add(book);

        return newReleases;
    }

    @Override
    public Map<String, Integer> getTopCategories() {
        List<Book> allBooks = bookDAO.findAll();
        Set<String> categories = getSetOfDistinctCategories(allBooks);
        Map<String, Integer> categoriesWithOccurrences = getMapOfCategoriesWithOccurrences(categories, allBooks);
        return getMapSortedByValues(categoriesWithOccurrences);
    }

    private Set<String> getSetOfDistinctCategories(List<Book> books) {
        Set<String> categories = new HashSet<>();
        for (Book book : books)
            categories.add(book.getCategory());
        return categories;
    }

    private Map<String, Integer> getMapOfCategoriesWithOccurrences(Set<String> categories, List<Book> books) {
        Map<String, Integer> categoriesWithOccurrences = new HashMap<>();
        int frequency = 0;
        for (String category : categories) {
            for (Book book : books)
                if (book.getCategory().equals(category))
                    frequency++;
            categoriesWithOccurrences.put(category, frequency);
            frequency = 0;
        }
        return categoriesWithOccurrences;
    }

    private Map<String, Integer> getMapSortedByValues(Map<String, Integer> map) {
        return map
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    @Override
    public BookStatus getStatusOfBookById(int id) {
        BookStatus status;
        Book book = bookDAO.findById(id);
        int quantityInStock = book.getQuantityInStock();

        if (quantityInStock <= 0)
            status = BookStatus.UNAVAILABLE;
        else if (quantityInStock <= 10)
            status = BookStatus.LAST_ITEMS;
        else
            status = BookStatus.AVAILABLE;

        return status;
    }

    @Override
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void delete(Book book) {
        bookDAO.delete(book);
    }
}
