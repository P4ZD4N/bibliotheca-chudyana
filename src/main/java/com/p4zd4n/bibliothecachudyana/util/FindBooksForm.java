package com.p4zd4n.bibliothecachudyana.util;

import com.p4zd4n.bibliothecachudyana.enums.BookStatus;
import jakarta.validation.constraints.Positive;

public class FindBooksForm {

    private String title;

    private String authorName;

    private String authorLastName;

    @Positive(message = "Pole 'Minimalny rok wydania' musi być liczbą dodatnią!")
    private Integer minReleaseYear;

    @Positive(message = "Pole 'Maksymalny rok wydania' musi być liczbą dodatnią!")
    private Integer maxReleaseYear;

    private String category;

    @Positive(message = "Pole 'Cena minimalna' musi być liczbą dodatnią!")
    private Double minPrice;

    @Positive(message = "Pole 'Cena maksymalna' musi być liczbą dodatnią!")
    private Double maxPrice;

    @Positive(message = "Pole 'Minimalna liczba stron' musi być liczbą dodatnią!")
    private Integer minPages;

    @Positive(message = "Pole 'Maksymalna liczba stron' musi być liczbą dodatnią!")
    private Integer maxPages;

    private BookStatus status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public Integer getMinReleaseYear() {
        return minReleaseYear;
    }

    public void setMinReleaseYear(Integer minReleaseYear) {
        this.minReleaseYear = minReleaseYear;
    }

    public Integer getMaxReleaseYear() {
        return maxReleaseYear;
    }

    public void setMaxReleaseYear(Integer maxReleaseYear) {
        this.maxReleaseYear = maxReleaseYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinPages() {
        return minPages;
    }

    public void setMinPages(Integer minPages) {
        this.minPages = minPages;
    }

    public Integer getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(Integer maxPages) {
        this.maxPages = maxPages;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
