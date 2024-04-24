package com.p4zd4n.bibliothecachudyana.entity;

import com.p4zd4n.bibliothecachudyana.enums.OrderStatus;
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

    @Column(name = "first_name")
    @NotBlank(message = "Pole 'Imię' jest wymagane!")
    @Size(max = 20, message = "Pole 'Imię' może składać się z maksymalnie 20 znaków!")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Pole 'Naziwsko' jest wymagane!")
    @Size(max = 25, message = "Pole 'Nazwisko' może składać się z maksymalnie 25 znaków!")
    private String lastName;

    @Column(name = "phone_number")
    @NotNull(message = "Pole 'Numer telefonu' jest wymagane!")
    @Size(min = 9, max = 9, message = "Pole 'Numer telefonu' musi składać się z 9 cyfr!")
    @Positive(message = "Pole 'Numer telefonu' musi być liczbą dodatnią!")
    private String phoneNumber;

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
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {}

    public Order(
        String firstName,
        String lastName,
        String phoneNumber,
        OrderStatus status,
        String postalCode,
        String city,
        String street,
        Double totalAmount,
        Integer houseNumber,
        LocalDate orderDate,
        User user
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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
