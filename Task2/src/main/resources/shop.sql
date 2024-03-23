CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL
);

INSERT INTO Product (name, price) VALUES ('Product 1', 10.99);
INSERT INTO Product (name, price) VALUES ('Product 2', 20.49);
INSERT INTO Product (name, price) VALUES ('Product 3', 15.99);