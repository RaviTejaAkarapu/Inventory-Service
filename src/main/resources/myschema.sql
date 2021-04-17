create schema inventorydb;

create index item_index on Item(sku_id);
create table Item (
	sku_id varchar(6) primary key,
	item_name varchar(20),
	manufacturer varchar(20),
	quantity int
);

insert into item (sku_id,item_name,manufacturer,quantity)
values('ABC001','Coke-600ml', 'Coca-cola Co.', '5');
insert into Item (sku_id,item_name,manufacturer,quantity)
values('ABC002','Coke-1.2Ltr', 'Coca-cola Co.', '11');
insert into Item (sku_id,item_name,manufacturer,quantity)
values('ABC003','Measuring Tape', 'Gripper Co.', '2');
insert into Item (sku_id,item_name,manufacturer,quantity)
values('ABC004','Lays-200gms', 'Chips Co.', '13');

--update Products
--set product_name = 'TBT', category= 'Book'
--where product_id = 2;
--
--delete from Users
--where user_id = 1;
--
--select product_name, category from Products;
--select * from Users;








