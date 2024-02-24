package com.p4zd4n.bibliothecachudyana.service.implementation;

import com.p4zd4n.bibliothecachudyana.dao.BookDAO;
import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
