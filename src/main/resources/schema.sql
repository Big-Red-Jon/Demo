CREATE TABLE Customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);
CREATE TABLE Account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(10, 2),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);