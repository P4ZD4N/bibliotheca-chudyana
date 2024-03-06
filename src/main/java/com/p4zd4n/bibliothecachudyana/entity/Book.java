package com.p4zd4n.bibliothecachudyana.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author_name")
    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Column(name = "author_last_name")
    private String authorLastName;

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    @Column(name = "release_date")
    private String releaseDate;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "category")
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "add_to_library_date")
    private LocalDate addToLibraryDate;

    public LocalDate getAddToLibraryDate() {
        return addToLibraryDate;
    }

    public void setAddToLibraryDate(LocalDate addToLibraryDate) {
        this.addToLibraryDate = addToLibraryDate;
    }

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Column(name = "price")
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Book() {}

    public Book(String title, String authorName, String authorLastName, String releaseDate, LocalDate addToLibraryDate) {
        this.title = title;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.releaseDate = releaseDate;
        this.addToLibraryDate = addToLibraryDate;
    }
}
