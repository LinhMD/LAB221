create database product_management
use product_management

create table tbl_users
(
	userID varchar(10) primary key,
	full_name varchar(50) not null,
	password varchar(50) not null,
	status bit not null
)
go

create table tbl_categories
(
	category_id varchar(10) primary key,
	category_name nvarchar(50) not null,
	description nvarchar(200) not null
)
go

create table tbl_products
(
	product_id varchar(10) primary key,
	product_name nvarchar(50) not null,
	unit varchar(50) not null,
	price float not null,
	quantity integer not null,
	category_id varchar(10) foreign key references tbl_categories(category_id)
)

use p0013
select quantity from _food where id = 1

update _food
set quantity = 200
where id = 1

select * from _food
