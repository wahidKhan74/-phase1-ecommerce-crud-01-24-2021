CREATE DATABASE ecommerce;
USE ecommerce;

CREATE TABLE eproduct (ID bigint primary key auto_increment, name varchar(100), 
price decimal(10,2), date_added timestamp default now()) ;

INSERT INTO eproduct (name,price) values ('HP Laptop ABC', 12000);
INSERT INTO eproduct (name,price) values  ('Acer Laptop ABC', 14000);
INSERT INTO eproduct (name,price) values  ('Lenovo Laptop ABC', 12000);

SELECT * from eproduct;