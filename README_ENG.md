# 🏦 DIO Banking System — Java (Console + Maven)

![Built with Java](https://img.shields.io/badge/Built%20with-Java-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-important?style=for-the-badge&logo=apachemaven)
![Console App](https://img.shields.io/badge/Console-Java%2021-blueviolet?style=for-the-badge)

<a href="https://github.com/Caroline-Teixeira/sudoku_game/blob/main/README.md"><img src="https://raw.githubusercontent.com/yammadev/flag-icons/refs/heads/master/png/BR%402x.png" alt="Português" ></a>

## 📖 Description

A simulated banking system via terminal, developed in **Java 21**, utilizing concepts of **Object-Oriented Programming (OOP)**, **custom exception handling**, **polymorphism**, and **Maven** for dependency management.

The project allows for client registration, account creation, financial operations such as deposits, withdrawals, transfers, simulated investments with daily returns, and transaction history.

## 🎯 Features

- ✅ Client registration with CPF (Brazilian Individual Taxpayer Registry) validation
- ✅ Creation of checking accounts linked to clients
- ✅ Deposits, withdrawals, and transfers between accounts
- ✅ Transaction recording and history
- ✅ Simulated investments (Certificate of Deposit, Real Estate Credit Bill, Direct Treasury Bonds, and Real Estate Funds)
- ✅ Investment history
- ✅ Custom error handling
- ✅ Interactive console menu
- ✅ Modular package organization

## 📂 Project Structure

```
.
├── pom.xml
└── src/
    └── main/
        └── java/
            └── br/
                └── com/
                    └── bank_dio/
                        ├── exception/
                        ├── model/
                        ├── repository/
                        ├── util/
                        └── view/
```

## 🛠️ Technologies Used

- **Java 21**
- **Maven 3.9.6**
- **Custom Exception Handling**
- **Object-Oriented Programming (OOP)**

## 📖 Concepts Applied

### 📌 Inheritance and Polymorphism

The project uses inheritance and polymorphism to differentiate account types:

```java
public abstract class Account { ... }

public class CheckingAccount extends Account { ... }

public class SavingsAccount extends Account { ... }
```

This allows the system to handle accounts generically where possible while maintaining specific behaviors for each type through method overriding or future extensions.

---

### 📌 Custom Error Handling

Custom exceptions were created to handle specific errors:

```java
public class BankException extends RuntimeException { ... }

public class InvalidTransaction extends BankException { ... }
public class DuplicateClient extends BankException { ... }
```

This ensures clear error messages and precise control over system failures.

---

### 📌 Layered Structure (MVC-like)

The project is organized modularly:

- `model/` → Entities (Client, Account, Transaction)
- `repository/` → Storage control (database simulation via lists)
- `view/` → User interface via console (menus)
- `exception/` → Custom exceptions
- `util/` → Validation and formatting utilities

---

## 📖 How to Run

### 🖥️ Via IDE

- Run the `Main.java` class
- Navigate through the console menus.

### 🖥️ Via Terminal

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="br.com.bank_dio.Main"
```

### 📦 Prerequisites:

- Java 21 or higher
- Maven configured in the PATH

---

## 📌 Suggestions for Improvement

- 📊 Generate accumulated investment return reports
- 💾 Persist data via files or a database
- 🖥️ Create a graphical interface (Swing or JavaFX)
- 🖥️ Refactor and enhance existing classes
- 📑 Unit tests with JUnit

---

## 📄 License

This project is licensed under the [MIT](LICENSE) license.

## 👩‍💻 Author

[Caroline 💙](https://github.com/Caroline-Teixeira)

---

📌 *Project developed for the DIO (Digital Innovation One) challenge*