package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
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
    public String getStatusOfBookById(int id) {
        String status;
        Book book = bookDAO.findById(id);
        int quantityInStock = book.getQuantityInStock();

        if (0 > quantityInStock)
            status = "error";
        else if (quantityInStock == 0)
            status = "unavailable";
        else if (10 >= quantityInStock)
            status = "last_items";
        else
            status = "available";

        return status;
    }
}
