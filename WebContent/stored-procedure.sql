
use ecommerce;

DELIMITER $$
CREATE PROCEDURE add_product(IN pname varchar(100) , IN pprice decimal(10,2))
INSERT INTO eproduct (name,price) values (pname, pprice) ;
$$ 
DELIMITER;

DELIMITER $$
CREATE PROCEDURE update_product(IN pid INT, IN pname varchar(100) , IN pprice decimal(10,2))
update eproduct set name=pname , price=pprice where id=pid;
$$ 
DELIMITER ;