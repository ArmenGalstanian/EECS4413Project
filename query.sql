use project;

create table Items(
id int primary key not null,
name varchar(30) not null,
price float not null);

select * from Items;

insert into Items (id, name, price) values (1, 'A', 15.5);
insert into Items (id, name, price) values (2, 'B', 16.5);
insert into Items (id, name, price) values (3, 'C', 17.5);