package com.p4zd4n.bibliothecachudyana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(name = "discount_percentage")
    @NotNull(message = "Pole 'Zniżka' jest wymagane!")
    private Double discountPercentage;

    @Column(name = "start_date")
    @NotNull(message = "Pole 'Start' jest wymagane!")
    private LocalDate startDate;

    @Column(name = "end_date")
    @NotNull(message = "Pole 'Koniec' jest wymagane!")
    private LocalDate endDate;

    public Discount() {}

    public Discount(Book book, Double discountPercentage, LocalDate startDate, LocalDate endDate) {
        this.book = book;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @AssertTrue(message = "Data zakończenia musi być po dacie rozpoczęcia!")
    private boolean isEndDateValid() {
        return endDate == null || startDate == null || endDate.isAfter(startDate) || endDate.isEqual(startDate);
    }
}
