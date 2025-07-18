
# 💻 Bibliotheca Chudyana

<p align="center">
  <img src="./images/logo.png" alt="Logo" />
</p>

## 👀 About

Backend app with sublte frontend elements created to solve problems typical for companies from library/bookstore industries. System offers a lot of features, that allow effective business management and seamless interaction with customers. Architected with great consideration for meeting all the requirements of a potential entrepreneur, who would be interested in digitalization of common business scenarios. Automating processes like book and order management, user registration saves time for both employees and customers. Application also improves customer experience by making it easier to search for books, place orders, or leave reviews, leading to higher satisfaction and convenience. Digitization of such library services is attractive to new customers because of easy access to its resources online. Users can browse the library 24/7, which can increase the number of orders placed. 

## 🔧 Tech Stack

**Backend:** Java 21, Spring Boot 3, Spring Security, Spring MVC, Hibernate, Thymeleaf

**Frontend:** HTML, SCSS, JavaScript

**Testing:** JUnit, Mockito

**Databases:** MySQL, SQL

**Build tools:** Maven

**DevOps:** Docker

**Other tools used during development:** phpMyAdmin, Figma

## 💡 Features

-  Secure user registration and authentication system, which use bcrypt encryption algorithm to ensure data protection.
-  Ability for users to change the email address and password.
- Browsing full catalog of books and searching them based on selected criteria and keywords.
- Adding new books and updating existing ones by users with appropriate roles.
- Viewing most popular categories and newly added books (books, which were added to library in past 7 days)
-  Viewing available discounts, with option to add new or update existing by users with appropriate roles.
- Adding, updating or deleting. review or rating for each book by authenticated users.
- Viewing and managing own profile for authenticated users.
- Adding or removing books from wishlist.
- Adding or removing books from cart.
- Placing orders using items from cart.
- Admin panel enabling administrators to manage books, users, orders, reviews, and discounts.
- Manager panel enabling managers to manage books, orders, reviews and discounts.
- Employee panel enabling employees to manage books and orders.

## 📺 Demo

![](./demo/1.png)
![](./demo/2.png)
![](./demo/3.png)
![](./demo/4.png)
![](./demo/5.png)
![](./demo/6.png)
![](./demo/7.png)
![](./demo/8.png)
![](./demo/9.png)
![](./demo/10.png)
![](./demo/11.png)
![](./demo/12.png)
![](./demo/13.png)
![](./demo/14.png)
![](./demo/15.png)
![](./demo/16.png)
![](./demo/17.png)
![](./demo/18.png)
![](./demo/19.png)
![](./demo/20.png)
![](./demo/21.png)
![](./demo/22.png)

## 🛠️ Pre-requisites

Before running app make sure, that you have the following tools installed on your machine:
- Java 21 (or higher)
- Git
- Docker

## ▶️ Run

Clone the project

```bash
git clone git@github.com:P4ZD4N/bibliotheca-chudyana.git
```

Navigate to the project directory

```bash
cd /path/to/bibliotheca-chudyana
```

Build project without tests

```bash
./mvnw clean package -DskipTests
```

If Maven is installed globally:

```bash
mvn clean package -DskipTests
```

Build and run Docker containers

```bash
docker-compose up --build
```

## ⚡ Usage

Navigate to the following URL in your web browser

```
http://localhost:8080
```

That's It! You can start using app with a user-friendly interface which allows you to interact with all features.

## 💳 Example login data

1. **User**
Login: user
Password: test123
2. **Employee**
Login: employee
Password: test123
3. **Manager**
Login: manager
Password: test123
4. **Admin**
Login: admin
Password: test123
