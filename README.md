# 🏦 Bank Management System & ATM Machine

<img width="887" height="594" alt="ATM1" src="https://github.com/user-attachments/assets/101e446c-306e-4719-9def-2c5fcb17cb9e" />


A professional **Bank Management System and ATM Machine application built in Java**, converted from a C programming project while preserving the original project logic and functionality.

The system combines **bank account management and ATM operations into one application**, with a graphical user interface (GUI), account authentication, transactions, file handling, and an administrative panel.

---

## 📌 Project Overview

The **Bank Management System & ATM Machine** is designed to simulate basic banking operations in a desktop Java application.

The project allows users to:

- Create a new bank account
- Login securely using an account number and PIN
- Check account balance
- Deposit money
- Withdraw money
- Transfer funds to another account
- Generate transaction receipts
- Store account and transaction information
- Block accounts after multiple incorrect PIN attempts
- Allow administrators to manage accounts
- Unblock blocked accounts
- Delete accounts
- View all registered accounts
- Reset stored account records

The project was originally developed in **C programming** and later converted to **Java**, keeping the core banking logic and workflow while adapting the implementation to Java and GUI-based interaction.

---

## ✨ Features

### 👤 Customer Features

#### 🆕 Create Account
Users can create a new bank account by providing:

- Full Name
- 10-digit Account Number
- 4-digit PIN

Each newly created account starts with a balance of:

```text
Rs. 50,000.00
```

The system also checks whether the account number already exists.

---

<img width="878" height="591" alt="image" src="https://github.com/user-attachments/assets/11072e87-68f9-4542-9370-1981381d7a90" />


### 🔐 Secure Login

Customers can login using:

- 10-digit Account Number
- 4-digit PIN

The system provides limited PIN attempts.

After **3 incorrect PIN attempts**, the account is automatically blocked.

```text
PIN_RETRY = 3
```

---

### 💰 Balance Inquiry

Logged-in customers can check their current account balance at any time.

---

### 💵 Deposit Money

Customers can deposit money into their account.

The system:

1. Validates the amount
2. Updates the account balance
3. Saves the updated account
4. Records the transaction
5. Generates a receipt

---

### 💸 Withdraw Money

Customers can withdraw money from their account.

The system checks:

- Amount must be greater than zero
- Sufficient balance must be available

If the requested amount exceeds the available balance, the transaction is rejected.

---

### 🔄 Fund Transfer

Customers can transfer money to another registered account.

The system validates:

- Target account number
- Target account existence
- Target account status
- Available balance
- Same-account transfer prevention

The sender's balance is reduced and the receiver's balance is increased.

---

### 🧾 Transaction Receipt

The system generates a receipt after supported transactions.

Receipt information includes:

- Account holder
- Account number
- Transaction type
- Transaction amount
- Remaining balance
- Date and time

---

## 👨‍💼 Admin Panel

The project includes a dedicated administrative section.

### Admin Features

- 🔓 Unblock Account
- 🗑️ Delete Account
- 📋 List All Accounts
- 🧹 Reset All Accounts

<img width="890" height="601" alt="image" src="https://github.com/user-attachments/assets/2598dbc6-cc37-4d20-ab80-89a06de228b4" />


The admin panel is protected by an administrator PIN.

> **Default Admin PIN:** `9999`

For a real banking application, credentials should never be hard-coded. This value is included only because this is an academic/demo project.

---

## 🖥️ Graphical User Interface

Unlike the original C console-based implementation, the Java version uses a **desktop GUI**.

The interface provides screens for:

- Welcome screen
- Main menu
- Account creation
- ATM login
- ATM dashboard
- Admin login
- Admin panel
- Account information
- Transaction messages
- Error handling

The GUI is implemented using Java Swing.

---
<img width="891" height="592" alt="image" src="https://github.com/user-attachments/assets/9b068b14-ff70-4bda-920d-240403eb7fbe" />


## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| ☕ Java | Main programming language |
| 🖥️ Java Swing | Graphical User Interface |
| 📁 Java File I/O | Account and transaction storage |
| 🧱 Java Classes | Object-oriented data modeling |
| 📋 ArrayList | Managing account records |
| 🕒 LocalDateTime | Transaction date and time |
| 🔐 Exception Handling | Error and invalid-input handling |
| 🧩 Object-Oriented Programming | Project structure and account management |

---

## 🧠 Java Concepts Used

This project demonstrates several important Java programming concepts:

### Classes & Objects

The banking account is represented using a Java class.

```java
class Account {
    String name;
    String account;
    String pin;
    double balance;
    boolean blocked;
}
```

---

### Methods

The project is divided into logical Java methods such as:

```text
createAccount()
login()
atmMenu()
checkBalance()
depositMoney()
withdrawMoney()
fundTransfer()
saveTransaction()
generateReceipt()
updateAccount()
unblockAccount()
deleteAccount()
listAllAccounts()
clearAllAccounts()
```

---

### Encapsulation of Data

Account information is managed through Java objects rather than C structures.

---

### Collections

`ArrayList` is used to manage multiple bank accounts in memory.

---

### File Handling

Java file I/O is used to store account and transaction information.

The project uses files such as:

```text
accounts.dat
transactions.txt
receipt.txt
```

---

### Exception Handling

`try-catch` blocks are used to handle problems such as:

- Invalid numeric input
- File errors
- Data loading errors
- Receipt generation errors

---

### Date & Time

Java's `LocalDateTime` is used to record transaction date and time.

---

### GUI Event Handling

Swing buttons use event listeners to perform actions when users interact with the application.

---

## 📂 Project Structure

The project is intentionally maintained as a **single Java source file**.

```text
Bank-Management-System/
│
├── BankATM.java
├── README.md
│
├── accounts.dat
├── transactions.txt
└── receipt.txt
```

### Main Source File

```text
BankATM.java
```

The complete application logic is contained inside this file.

---

## ⚙️ Requirements

Before running the project, make sure you have:

- Java Development Kit (JDK) 17 or newer
- Windows, Linux, or macOS
- VS Code, IntelliJ IDEA, Eclipse, or another Java IDE

You can verify your Java installation with:

```bash
java -version
```

and:

```bash
javac -version
```

---

## 🚀 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY.git
```

Navigate into the project folder:

```bash
cd YOUR-REPOSITORY
```

---

### 2. Compile the Project

Because the application is contained in one Java file:

```bash
javac BankATM.java
```

---

### 3. Run the Application

```bash
java BankATM
```

The Java Swing GUI will open.

---

## 🔄 Application Workflow

```text
                    ┌─────────────────────┐
                    │      Welcome        │
                    │       Screen        │
                    └──────────┬──────────┘
                               │
              ┌────────────────┼────────────────┐
              │                │                │
              ▼                ▼                ▼
       Create Account      ATM Login       Admin Panel
              │                │                │
              ▼                ▼                ▼
        Account Saved      PIN Check       Admin Login
                               │                │
                               ▼                ▼
                         ATM Dashboard      Admin Menu
                               │                │
              ┌────────────────┼────────────┐   │
              │                │            │   │
              ▼                ▼            ▼   ▼
          Balance          Deposit      Withdraw
              │                │            │
              └────────────────┼────────────┘
                               │
                               ▼
                         Fund Transfer
                               │
                               ▼
                       Transaction Record
                               │
                               ▼
                           Receipt
```

---

## 🔐 Account Security Logic

The project uses a simple PIN-based authentication system.

### Login Process

```text
Enter Account Number
        ↓
Find Account
        ↓
Is Account Blocked?
    ↓           ↓
   YES          NO
    ↓            ↓
 Reject       Enter PIN
                 ↓
            Correct PIN?
             ↓       ↓
            YES      NO
             ↓       ↓
          Login   Retry PIN
                    ↓
              3 Failed Attempts
                    ↓
             Account Blocked
```

---

## 💾 Data Storage

The application uses local files instead of a database.

### `accounts.dat`

Stores account information such as:

- Name
- Account number
- PIN
- Balance
- Blocked status

### `transactions.txt`

Stores transaction history.

Example:

```text
Account Number | Transaction Type | Amount | Date/Time
```

### `receipt.txt`

Stores the most recently generated transaction receipt.

---

## 🧪 Example Account

For testing purposes, an account can be created using:

```text
Name: Ali Ahmed
Account Number: 1234567890
PIN: 1234
Starting Balance: Rs. 50,000.00
```

After successful login, the user can test:

```text
1. Balance Inquiry
2. Deposit Money
3. Withdraw Money
4. Transfer Funds
5. Logout
```

---

## 🎯 Project Objectives

The main objectives of this project are:

1. To understand Java programming fundamentals.
2. To apply Object-Oriented Programming concepts.
3. To convert an existing C programming project into Java.
4. To implement file handling in Java.
5. To implement GUI-based user interaction.
6. To understand account authentication and validation.
7. To implement banking transactions.
8. To practice exception handling.
9. To understand Java collections.
10. To develop a complete academic-level Java application.

---

## 🔁 C to Java Conversion

This project was originally implemented in C and converted to Java while maintaining the original banking workflow.

| C Concept | Java Equivalent |
|---|---|
| `struct Account` | `Account` Java class |
| `printf()` / `scanf()` | Swing GUI components |
| C functions | Java methods |
| Pointers | Object references |
| `#define` constants | `static final` constants |
| C file handling | Java File I/O |
| `strcmp()` | `String.equals()` |
| C strings | Java `String` |
| `time.h` | `LocalDateTime` |
| Binary/text files | Java object/file streams |
| `switch-case` | Java control flow / event handling |
| Console menus | Swing GUI menus/buttons |

The original C project uses structures, file handling, pointers, functions, loops, switch-case, macros, input validation, string handling, and date/time functionality. :contentReference[oaicite:2]{index=2}

---

## ⚠️ Important Note

This project is an **educational/academic banking simulation**.

It is **not intended for real banking or financial transactions**.

For a production banking system, additional security mechanisms would be required, including:

- Encrypted credentials
- Secure authentication
- Database management
- Role-based access control
- Secure transaction processing
- Audit logging
- Encryption
- Network security
- Secure password/PIN storage
- Input sanitization
- Transaction rollback and consistency mechanisms

---

## 🐛 Known Limitations

Because this is an academic desktop application:

- Data is stored locally in files.
- No real banking server is connected.
- No real payment gateway is implemented.
- No database server is required.
- PIN security is designed for demonstration purposes.
- The application is not intended for production financial use.

---

## 🔮 Future Improvements

Possible future improvements include:

- [ ] MySQL / PostgreSQL database integration
- [ ] Secure password/PIN hashing
- [ ] User registration and profile management
- [ ] Improved transaction history
- [ ] PDF receipt generation
- [ ] Admin authentication improvements
- [ ] Role-based access control
- [ ] Search and filtering for accounts
- [ ] Modern JavaFX interface
- [ ] REST API integration
- [ ] Cloud database support
- [ ] Automated testing
- [ ] Multi-user support
- [ ] Better logging and auditing

---

## 🎓 Academic Information

**Project:** Bank Management System & ATM Machine  
**Language:** Java  
**Original Language:** C  
**Application Type:** Desktop GUI Application  
**Course:** Object-Oriented Programming / Java  
**Institution:** Quaid-e-Awam University of Engineering, Science & Technology (QUEST), Nawabshah

---

## 👨‍💻 Author

**Imdad Ali Naich**

BS Computer Science

Quaid-e-Awam University of Engineering, Science & Technology (QUEST), Nawabshah

---

## ⭐ If You Like This Project

If you find this project useful for learning Java, OOP, file handling, and GUI development, consider giving the repository a ⭐ star.

---

## 📜 License

This project is created for educational and academic purposes.

You are free to study, modify, and improve the source code for learning purposes.
