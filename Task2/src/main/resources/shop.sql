CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL
);

INSERT INTO Product (name, price) VALUES ('Apple iPhone 13 Pro', 999.00);
INSERT INTO Product (name, price) VALUES ('Samsung Galaxy S22 Ultra', 1199.99);
INSERT INTO Product (name, price) VALUES ('Sony PlayStation 5', 499.99);
INSERT INTO Product (name, price) VALUES ('Bose QuietComfort 45 Headphones', 329.00);
INSERT INTO Product (name, price) VALUES ('Dyson V15 Detect Vacuum Cleaner', 699.99);