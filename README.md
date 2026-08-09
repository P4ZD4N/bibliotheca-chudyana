
# 💻 Bibliotheca Chudyana

<p align="center">
  <img src="./images/logo.png" alt="Logo" />
</p>

## 👀 About

Backend web application designed to digitalize library / bookstore operations and make them available 24/7. Enhances user experience by allowing customers to effortlessly browse book collections, manage wishlists or carts and track orders from home, increasing revenues and building customer loyalty.

What sets project apart?

— E-Commerce & Customer Engagement: Features like intuitive shopping cart and wishlist system that allows users to seamlessly plan their purchases. Customers can track available discounts, explore newly added books (within the last 7 days) and popular categories, and interact with the book catalog through review system.

— Role-Based Access Control (RBAC): Management panels tailored to specific staff roles. Administrators have full control over books, users, orders, reviews, and discounts; Managers oversee book catalog and sales data; Employees are granted to handle book and order management.

— Security & Profile Management: Session-based authentication and registration system which use Bcrypt password encryption. Authenticated users can manage their profiles, update credentials and monitor order statuses.

— UI Rendering: Used Spring MVC to implement server-side rendering and deliver fast, SEO-friendly and responsive interface, ensuring easy navigation across the entire book catalog and dashboards.

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
