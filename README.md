# ToDoList-JFX

A sleek and responsive **ToDo List Desktop Application** built using **JavaFX**, **Scene Builder**, and the **MVC (Model-View-Controller)** architecture. This lightweight application allows users to manage their daily tasks efficiently, focusing on productivity and a clean user experience.

## 📌 Project Overview

This is a standalone JavaFX application developed as a personal productivity tool and learning project. It demonstrates strong separation of concerns using MVC, form validation, user input handling, and JavaFX-based GUI design with Scene Builder.

---

## 🎯 Objectives

- Develop a simple and intuitive task management app using JavaFX.
- Implement full CRUD operations for task management.
- Apply MVC pattern for better code organization and maintainability.
- Use Scene Builder for designing responsive and interactive UI.
- Provide basic features like task creation, update, deletion, and completion status.

---

## 🧰 Technologies Used

| Component     | Technology                |
|---------------|----------------------------|
| Language       | Java                      |
| UI Framework   | JavaFX with SceneBuilder  |
| Design Pattern | MVC (Model-View-Controller) |
| Build Tool     | Maven / Manual Setup      |

---

## ✨ Features

### ✅ Task Management
- Add new tasks with descriptions and due dates
- Mark tasks as **completed** or **incomplete**
- Edit task details (description, date, status)
- Delete individual tasks or clear all
- Real-time task list updates
- Also another speaciality I have created is the real time desktop notification instead of just normal alerts.

### 🧭 MVC Architecture
- **Model**: Manages task data (Task class, TaskManager)
- **View**: UI layout designed using Scene Builder (FXML)
- **Controller**: Handles user interactions and links UI with backend logic

### 📁 Database Schemas
- 
- The system uses a relational database to manage book inventory, member information, and borrowing transactions.

### 📚 Item

- **task_name**: INT (Primary Key) — Unique identifier for each task that user adds to the database  
- **status**: VARCHAR(20) — This  suggest  whether the specific task is completed or not by the user with the  value of  1 and 0 which is true or false
---

## 🖼️ Screenshots

![App Interface](C:\Users\LENOVO\ICD114\Standalone application development\images of GItHub SS\todoList SS\interface_image)
![App Interface](C:\Users\LENOVO\ICD114\Standalone application development\images of GItHub SS\todoList SS\DBMS_SS)
![App Interface](C:\Users\LENOVO\ICD114\Standalone application development\images of GItHub SS\todoList SS\RealTime_Desktop)

---

## 📚 References

- [JavaFX Documentation](https://openjfx.io/)
- [JFoenix Material UI Guide](https://github.com/sshahine/JFoenix)
- [Maven Repository](https://mvnrepository.com/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

