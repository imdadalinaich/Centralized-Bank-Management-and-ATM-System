# 🏦 Bank Management System & ATM Machine

<img width="887" height="594" alt="ATM1" src="https://github.com/user-attachments/assets/101e446c-306e-4719-9def-2c5fcb17cb9e" />

A professional **Bank Management System and ATM Machine application built in Java**, designed to simulate basic banking operations through a desktop graphical user interface.

The system combines **bank account management and ATM operations into one application**, with account authentication, transactions, file handling, receipt generation, and an administrative panel.

---

## 📌 Project Overview

The **Bank Management System & ATM Machine** is a Java-based desktop application designed to simulate a real-world banking environment.

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
- Manage accounts through an admin panel
- Unblock blocked accounts
- Delete accounts
- View registered accounts
- Reset stored account records

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

<img width="878" height="591" alt="Account Creation" src="https://github.com/user-attachments/assets/11072e87-68f9-4542-9370-1981381d7a90" />

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

<img width="890" height="601" alt="Admin Panel" src="https://github.com/user-attachments/assets/2598dbc6-cc37-4d20-ab80-89a06de228b4" />

The admin panel is protected by an administrator PIN.

> **Default Admin PIN:** `9999`

For a real banking application, credentials should never be hard-coded. This value is included only for academic and demonstration purposes.

---

## 🖥️ Graphical User Interface

The application uses a **desktop graphical user interface built with Java Swing**.

The interface provides screens for:

- Welcome Screen
- Main Menu
- Account Creation
- ATM Login
- ATM Dashboard
- Admin Login
- Admin Panel
- Account Information
- Transaction Messages
- Error Handling

The GUI provides an interactive way for users to perform banking operations without relying on a command-line interface.

---

<img width="891" height="592" alt="ATM Dashboard" src="https://github.com/user-attachments/assets/9b068b14-ff70-4bda-920d-240403eb7fbe" />

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
| 🧩 Object-Oriented Programming | Application structure and account management |

---

## 🧠 Java Concepts Used

This project demonstrates several important Java programming concepts.

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

The project uses methods for different banking operations, including:

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

### Encapsulation

Account-related data and operations are organized within Java classes and objects.

---

### Collections

`ArrayList` is used to manage multiple bank account records in memory.

---

### File Handling

Java File I/O is used to store account and transaction information.

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

Swing buttons and components use event handling to perform actions when users interact with the application.

---

## 📂 Project Structure

The project is maintained as a **single Java source file**.

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

The complete application logic is contained in this Java source file.

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
git clone https://github.com/imdadalinaich/Centralized-Bank-Management-and-ATM-System.git
```

Navigate into the project folder:

```bash
cd Centralized-Bank-Management-and-ATM-System
```

---

### 2. Compile the Project

The application is contained in one Java source file:

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

The project uses a PIN-based authentication system.

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

The application uses local files for data storage.

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
3. To implement file handling in Java.
4. To develop a GUI-based banking application.
5. To understand account authentication and validation.
6. To implement banking transactions.
7. To practice exception handling.
8. To understand Java collections.
9. To implement event-driven GUI programming.
10. To develop a complete academic-level Java application.

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
- Secure PIN storage
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
- [ ] Secure PIN hashing
- [ ] User registration and profile management
- [ ] Improved transaction history
- [ ] PDF receipt generation
- [ ] Improved admin authentication
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
**Application Type:** Desktop GUI Application  
**Course:** Object-Oriented Programming / Java  
**Institution:** Quaid-e-Awam University of Engineering, Science & Technology (QUEST), Nawabshah

---

## 👨‍💻 Author

**Imdad Ali Naich**

BS Computer Science

Quaid-e-Awam University of Engineering, Science & Technology (QUEST), Nawabshah

---

## ⭐ Support

If you find this project useful for learning Java, OOP, file handling, and GUI development, consider giving the repository a ⭐ star.

---

## 📜 License

This project is created for educational and academic purposes.

You are free to study, modify, and improve the source code for learning purposes.
