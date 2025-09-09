# 📚 Library Management System

The **Library Management System** is a Java-based application with MySQL integration designed to manage books, users, categories, and borrowing activity in an educational or organizational library. It provides a simple but powerful way for administrators and students to interact with library resources.

---

## ✨ Features

### 👤 User Management
- Admins and Students with role-based permissions  
- Secure password storage (bcrypt hashing)  
- Online/offline status tracking  

### 📖 Book Management
- Add, update, and remove books  
- Track categories and subcategories  
- Monitor available copies and borrowing limits  
- Support for book cover images  

### 🗂️ Category Management
- Parent/child category relationships  
- Automatic count of total books per category  

### 📑 Borrowing System
- Issue and return books with due dates  
- Track borrowing duration and overdue status  
- History of all borrowed/returned books  

### 📊 Analytics (Graph Module)
- Daily record of books, users, issues, and returns  
- Useful for reporting and library usage insights  

---

## 🛠️ Tech Stack
- **Language:** Java (with custom handlers for each module)  
- **Database:** MySQL  
- **Offline Support:** Data is cached locally and synced when online  
- **UI/UX:** Designed for ease of use by both admins and students  

---

This system was built with flexibility and reliability in mind, making it suitable for schools, universities, and small to medium libraries that need a straightforward solution to organize their resources.
