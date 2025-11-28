
-- Creating the database for the ATM Banking System
CREATE DATABASE atm_db;

-- Switch to the new database
USE atm_db;

-- ACCOUNTS TABLE

-- Stores customer account information such as:
--  - Account Number (Primary Key)
--  - Account Holder Name
--  - PIN for login authentication
--  - Current Balance
CREATE TABLE accounts (
    account_number VARCHAR(10) PRIMARY KEY,  -- Unique account number
    account_holder VARCHAR(50),              -- Name of the account owner
    pin VARCHAR(10),                         -- PIN used for login
    balance DOUBLE                           -- Current account balance
);

-- TRANSACTIONS TABLE

-- Stores all ATM transactions:
--  - Auto-increment transaction ID
--  - Account Number (Foreign key)
--  - Transaction Type (Deposit / Withdraw)
--  - Transaction Amount
--  - Timestamp of transaction
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique transaction ID
    account_number VARCHAR(10),                      -- Linked account number
    type VARCHAR(10),                                -- Type: Deposit / Withdraw
    amount DOUBLE,                                   -- Transaction amount
    transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Time of transaction
    
    -- Ensures account_number exists in accounts table
    FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);


-- Adding two demo accounts for testing the ATM application
INSERT INTO accounts VALUES ('101','Rama','1234',5000);
INSERT INTO accounts VALUES ('102','Krishna','4321',3000);
