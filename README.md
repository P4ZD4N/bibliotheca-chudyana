
# 💻 Bibliotheca Chudyana

## 👀 About

I am delighted, that I can present my first big app with usage of Spring Framework. Backend app called "Bibliotheca Chudyana" is library management system for fictional library "Bibliotheca Chudyana", which implements a lot of functionalities, that allows to effectively manage company. Created and designed with a thought of meeting all the requirements of a potential customer, who would be interested in ordering such an application. With Figma, I developed an eye-catching and convenient UI to ensure efficient use of all functionalities and problemless use of the library.

## 🔧 Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring MVC
- Hibernate
- Thymeleaf
- Maven
- JUnit
- Mockito
- MySQL
- SQL
- JavaScript
- HTML
- SCSS
- Docker

## 💡 Features

-  Registration and authentication system, taking into data security. For this purpose, the bcrypt encryption algorithm was used.
-  Possibility to change password and email by each user.
- Displaying all books, which are in the database, searching them according to selected criteria and based on keywords, adding new and updating existing books by users with appropriate roles.
- Possibility to check the most popular categories and check new releases (books, which were added to library in past 7 days)
-  Possibility to check discounts, and also add new or update existing by users with appropriate roles.
- Possibility to add review or rating for each book by authenticated users, and also updating or delete them.
- Possibility to view own profile for authenticated users
- Adding and removing books from wishlist.
- Adding and removing books from cart.
- Placing orders from books in cart.
- Admin panel enabling administrators to: manage books, users, orders, reviews, and discounts.
- Manager panel enabling managers to: manage books, orders, reviews and discounts.
- Employee panel enabling employees to: manage books and orders.

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

That's It! You can start using app with a user-friendly interface built with HTML and CSS, which allows you to interact with all features.

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
