CREATE TABLE Customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(John),
    last_name VARCHAR(Doe)
);
CREATE TABLE Account (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(10, 2),
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);
INSERT INTO Customer (first_name, last_name)
VALUES ('John', 'Doe');
INSERT INTO Account (balance, customer_id)
VALUES (1000.00, 1);
INSERT INTO Account (balance, customer_id)
VALUES (500.00, 1);
INSERT INTO Account (balance, customer_id)
VALUES (10000.00, 1);