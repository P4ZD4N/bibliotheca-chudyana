package com.p4zd4n.bibliothecachudyana.util;

public class FindBooksForm {

    private String title;

    private String authorName;

    private String authorLastName;

    private String minReleaseYear;

    private String maxReleaseYear;

    private String category;

    private Double minPrice;

    private Double maxPrice;

    private Integer minPages;

    private Integer maxPages;

    private String status;

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

    public String getMinReleaseYear() {
        return minReleaseYear;
    }

    public void setMinReleaseYear(String minReleaseYear) {
        this.minReleaseYear = minReleaseYear;
    }

    public String getMaxReleaseYear() {
        return maxReleaseYear;
    }

    public void setMaxReleaseYear(String maxReleaseYear) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
