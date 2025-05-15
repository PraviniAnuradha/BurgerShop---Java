# BurgerShop---Java
Java console app for managing a burger shop’s orders and customers. Built with OOP, ArrayList, HashMap, and enums. Features include placing and updating orders, searching customers and orders, viewing customer histories, and identifying the best customer by total spend.
# 🍔 iHungry Burger Shop – Java Console Application

A simple yet powerful Java-based console application that simulates a burger shop's order management system. Designed using object-oriented principles with a clean and scalable code structure.

---

## 🔧 Technologies Used
- **Java 17+**
- **OOP (Object-Oriented Programming)**
- **Java Collections** – ArrayList, HashMap
- **Enum** for order status handling

---

## 📌 Features

### ✅ Core Functionalities:
- **Place Orders**: Generates unique order IDs and accepts customer details
- **Manage Customers**: Tracks returning customers using phone numbers
- **Search Orders & Customers**: Retrieve orders by ID or list all orders by customer
- **View Orders**: Filter by order status – Preparing, Delivered, or Canceled
- **Update Orders**: Modify the quantity or status of an order
- **Best Customer**: Find the customer with the highest total spend

---

## 🧱 Project Structure
```
BurgerShop.java      // Main controller and menu logic
Customer.java        // Customer class (phone, name)
Order.java           // Order class (ID, customer, quantity, value, status)
OrderStatus.java     // Enum to manage status
```

---

## 🧠 Concepts Demonstrated
- **Clean OOP Design**
- **Encapsulation & Data Handling**
- **Console UI Design**
- **Enum-based Status Management**
- **Real-world application modeling**

---

## 🚀 Getting Started
### 🛠️ Prerequisites
- JDK 17 or later

### ▶️ Run the App
```bash
javac BurgerShop.java
java BurgerShop
```

---

## 📌 Sample Output
```
=== iHungry Burger Shop ===
1. Place Order
2. Search Best Customer
3. Search Order
...
```

---

## 🗃️ Future Improvements
- File-based or database storage
- GUI using JavaFX or Swing
- REST API backend using Spring Boot
- Login/Admin roles

---

## 👤 Author
*Pravini**  
Email: pravinianuradha@gmail.com  
