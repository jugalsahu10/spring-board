--DROP TABLE IF EXISTS `orders`;
--
--CREATE TABLE orders (
--    id BIGINT NOT NULL AUTO_INCREMENT,
--    quantity INT NOT NULL,
--    product_id BIGINT,
--    user_id BIGINT,
--    PRIMARY KEY (id),
--    FOREIGN KEY (user_id) REFERENCES users(id),
--    FOREIGN KEY (product_id) REFERENCES product(id)
--);
--
--DROP TABLE IF EXISTS `product`;
--
--CREATE TABLE product (
--    id BIGINT NOT NULL AUTO_INCREMENT,
--    name VARCHAR(255),
--    price DOUBLE,
--    quantity INT,
--    PRIMARY KEY (id)
--);
--
--DROP TABLE IF EXISTS `users`;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    username VARCHAR(255),
    address VARCHAR(255),
    name VARCHAR(255),
    type TINYINT,
    password VARCHAR(255),
    roles VARCHAR(255),
    PRIMARY KEY (id)
);