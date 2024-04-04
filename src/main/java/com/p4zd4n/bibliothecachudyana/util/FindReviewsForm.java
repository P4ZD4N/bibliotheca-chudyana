package com.p4zd4n.bibliothecachudyana.util;

import java.time.LocalDate;

public class FindReviewsForm {

    private Integer id;

    private String username;

    private Integer bookId;

    private Integer rating;

    private LocalDate minAddedDate;

    private LocalDate maxAddedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getMaxAddedDate() {
        return maxAddedDate;
    }

    public void setMaxAddedDate(LocalDate maxAddedDate) {
        this.maxAddedDate = maxAddedDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public LocalDate getMinAddedDate() {
        return minAddedDate;
    }

    public void setMinAddedDate(LocalDate minAddedDate) {
        this.minAddedDate = minAddedDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
