package com.p4zd4n.bibliothecachudyana.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "house_number")
    @NotNull(message = "Pole 'Numer domu' jest wymagane!")
    @Digits(integer = 3, fraction = 0, message = "Pole 'Numer domu' musi być liczbą całkowitą!")
    @Positive(message = "Pole 'Numer domu' musi być liczbą dodatnią!")
    private Integer houseNumber;

    @Column(name = "street")
    @NotBlank(message = "Pole 'Ulica' jest wymagane!")
    private String street;

    @Column(name = "city")
    @NotBlank(message = "Pole 'Miasto' jest wymagane!")
    private String city;

    @Column(name = "postal_code")
    @NotBlank(message = "Pole 'Kod pocztowy' jest wymagane!")
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message = "Pole 'Kod pocztowy' musi być w formacie XX-XXX!")
    private String postalCode;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {}

    public Order(String status, String postalCode, String city, String street, Double totalAmount, Integer houseNumber, LocalDate orderDate, User user) {
        this.status = status;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.totalAmount = totalAmount;
        this.houseNumber = houseNumber;
        this.orderDate = orderDate;
        this.user = user;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addBook(OrderItem item) {
        this.getItems().add(item);
    }
}
