CREATE DATABASE book_management
GO

use book_management

create table book(
	book_id varchar(10) primary key,
	book_name varchar(50) not null,
	publisher varchar(50) not null,
	author varchar(50) not null,
	published_year integer not null,
	for_rent bit not null
)
insert into book(book_id, book_name, author, publisher, published_year, for_rent)
values 
	('book1', 'java', 'Robert C.Martin', 'yen express', '1990', 1),
	('book2', 'C#', 'Andrew Troelsen', 'yen express', '1998', 1),
	('book3', 'C', 'Andrew Stellman', 'yen express', '1975', 1),
	('book4', 'Crime and Punishment', 'Fyodor Dostoevsky', 'yen express', '1980', 1),
	('book5', 'Rich dad Poor Dad', 'Robert T. Kiyosaki', 'yen express', '2012', 0)
select b.book_id, b.book_name, b.author, b.publisher, b.published_year 
from book b where for_rent = 1