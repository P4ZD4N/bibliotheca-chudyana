package com.p4zd4n.bibliothecachudyana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "rating")
    @NotNull(message = "Pole 'Ocena' jest wymagane!")
    @Min(value = 1, message = "Ocena musi być większa lub równa 1!")
    @Max(value = 5, message = "Ocena musi być mniejsza lub równa 5!")
    private Integer rating;

    @Column(name = "content")
    @Size(max = 250, message = "Opinia może mieć maksymalnie 250 znaków!")
    private String content;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    public Review() {}

    public Review(Integer rating, String content, LocalDate dateAdded) {
        this.rating = rating;
        this.content = content;
        this.dateAdded = dateAdded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
