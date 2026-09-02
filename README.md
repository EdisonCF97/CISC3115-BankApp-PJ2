# BankApp - Bank Account Management System

> A Java-based command-line banking application that simulates account management with password-protected access.

---

## 📖 Project Background & Motivation

This project was my assignment while studying CISC 3115 at Brooklyn College. It simulates a simple banking system where users can log in with their SSN and password to check balances, withdraw, and deposit funds.

**Core Problem**: Managing bank accounts securely requires authentication and data protection.

**My Solution**: Built a menu-driven Java application with password hashing for secure login and `BigDecimal` for precise monetary calculations. The system supports multiple accounts, each protected by SSN/password authentication.

---

## ✨ Features

- **Secure Login**: Users enter their SSN and password; the system hashes the password and matches it against stored credentials
- **Check Balance**: Displays current balance in US currency format (e.g., "$5,000.00")
- **Withdraw**: Users can withdraw money with insufficient funds protection
- **Deposit**: Users can deposit money into their account
- **Pre-loaded Accounts**: The system comes with 5 demo accounts for testing

---

## 🛠️ Tech Stack

- **Java** – core language
- **SHA-256 Password Hashing** – passwords are stored as hashes, not plain text
- **BigDecimal** – used for all monetary values to avoid floating-point precision issues
- **Four classes**:
  - `BankApp` – runs the main menu and user interaction loop
  - `Bank` – manages a collection of accounts (add, search, authenticate)
  - `CheckingAccount` – holds account data and handles withdraw/deposit logic
  - `PasswordUtils` – hashes passwords using SHA-256

---

## 🚀 How to Run

### Requirements
- Java Development Kit (JDK) 11 or higher

### Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/EdisonCF97/CISC3115-BankApp-PJ2.git
