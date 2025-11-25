# Online Meditation and Mindfulness Platform

#Overview

This project is an **Online Meditation and Mindfulness Platform** built
using **Java, JDBC, MySQL, and OOP principles**.\
It helps users practice meditation, track mindfulness habits, and access
guided audio sessions.

------------------------------------------------------------------------

#Features

-   User registration & login
-   Guided meditation sessions
-   Progress tracking dashboard
-   Session history stored in database
-   Clean OOP-based project structure
-   JDBC-based database connectivity

------------------------------------------------------------------------

#Technologies Used

-   **Java**
-   **JDBC**
-   **MySQL**
-   **Object-Oriented Programming**
-   **GitHub** for version control

------------------------------------------------------------------------

#Project Structure

    /src
     ├── database
     │     ├── DBConnection.java
     │     ├── UserDAO.java
     │     └── SessionDAO.java
     ├── models
     │     ├── User.java
     │     └── MeditationSession.java
     ├── services
     │     ├── UserService.java
     │     └── SessionService.java
     └── Main.java

------------------------------------------------------------------------

#Database Schema

### **Users Table**

  Column     Type
  ---------- ----------
  user_id    INT (PK)
  name       VARCHAR
  email      VARCHAR
  password   VARCHAR

### **Sessions Table**

  Column       Type
  ------------ ----------
  session_id   INT (PK)
  user_id      INT (FK)
  duration     INT
  category     VARCHAR
  date         DATE

------------------------------------------------------------------------

##How to Run

1.  Install JDK & MySQL\
2.  Create the database using provided SQL file\
3.  Update DB credentials in `DBConnection.java`\
4.  Run `Main.java`

------------------------------------------------------------------------

#Future Enhancements

-   Add mobile app integration\
-   Notifications & reminders\
-   AI-based meditation recommendations

------------------------------------------------------------------------

#Author

This project is developed for academic purposes focusing on Java, JDBC,
and OOP concepts.
