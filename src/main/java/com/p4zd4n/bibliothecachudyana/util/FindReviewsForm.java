package com.p4zd4n.bibliothecachudyana.util;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class FindReviewsForm {

    @Positive(message = "Pole 'ID' musi być liczbą dodatnią!")
    private Integer id;

    private String username;

    private Integer bookId;

    @Min(value = 1, message = "Pole 'ID' musi być liczbą od 1 do 5!")
    @Max(value = 5, message = "Pole 'ID' musi być liczbą od 1 do 5!")
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
