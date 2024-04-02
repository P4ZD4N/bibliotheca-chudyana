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

    @Column(name = "title")
    private String title;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "author_last_name")
    private String authorLastName;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "category")
    private String category;

    @Column(name = "add_to_library_date")
    private LocalDate addToLibraryDate;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    @OneToOne(mappedBy = "book")
    private Discount discount;

    public Book() {}

    public Book(String title, String authorName, String authorLastName, String releaseDate, LocalDate addToLibraryDate) {
        this.title = title;
        this.authorName = authorName;
        this.authorLastName = authorLastName;
        this.releaseDate = releaseDate;
        this.addToLibraryDate = addToLibraryDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getAddToLibraryDate() {
        return addToLibraryDate;
    }

    public void setAddToLibraryDate(LocalDate addToLibraryDate) {
        this.addToLibraryDate = addToLibraryDate;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
