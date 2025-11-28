# **ATM Banking System – Java + MySQL**

A simple and functional **ATM Banking System** built using **Core Java**, **JDBC**, and **MySQL**.
This project demonstrates basic banking operations such as login, balance inquiry, deposit, withdrawal, and transaction logging—implemented with clean OOP design and database integration.

## 🚀 **Features**

* 🔐 **Secure User Login** (Account Number + PIN)
* 💵 **Check Balance**
* ➕ **Deposit Funds**
* ➖ **Withdraw Funds**
* 📜 **Transaction History** (stored in MySQL)
* 🗄️ **Full JDBC Integration**
* 🧱 **Clean OOP Architecture** (ATM, BankService, BankAccount)

## 📁 **Project Structure**

```
ATM Banking System/
│
├── src/
│   ├── ATM.java
│   ├── BankService.java
│   ├── BankAccount.java
│
├── lib/
│   └── mysql-connector-java-8.0.11.jar
│
├── bin/               # Compiled .class files
├── sql/
│   └── atm_db.sql
└── README.md
```

## 🛠 **Technologies Used**

* Java SE (Core Java)
* JDBC
* MySQL
* MySQL Java Connector (8.0.11)
* Windows CMD / PowerShell

## 🗄 **Database Setup**

Refer sql/atm_db.sql file

## 🔧 **How to Compile & Run**

### **1️⃣ Compile Java Source Files**


```sh
cd src/

javac -d ../bin -cp ../lib/mysql-connector-java-8.0.11.jar *.java
```

### **2️⃣ Run the Application**

```sh
java -cp "../bin;../lib/mysql-connector-java-8.0.11.jar" ATM
```


## 🔑 **Sample Login Credentials**

| Account Number | PIN  | Starting Balance |
| -------------- | ---- | ---------------- |
| 101            | 1234 | 5000             |
| 102            | 4321 | 3000             |


## 🧩 **Class Overview**

### **ATM.java**

Handles:

* User menu
* Input handling
* Triggering banking operations

### **BankService.java**

Responsible for:

* MySQL database connection
* Login authentication
* Deposit / Withdraw logic
* Updating account balance
* Storing transaction records

### **BankAccount.java**

Represents:

* Account number
* Account holder name
* Balance
