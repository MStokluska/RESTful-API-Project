create database if not exists myJavaDB;
use myJavaDB;

create table if not exists Users(
userId varchar(25) unique not null,
name varchar(20) not null,
password varchar(200) not null,
position varchar(10) not null,
primary key(userId)
);

create table if not exists Task(
id int not null auto_increment,
title varchar(20) not null,
description varchar(50) not null,
priority smallint default null,
taskDate date,
status varchar(150),
userId varchar(25),
primary key(id),
foreign key (userId) references Users(userId)
on update cascade
on delete set null
);
insert into Users (userId, name, password, position) values
('Admin', 'Admin', '$2a$10$K7bEe/WhUemZ67zCP.BVxe.HtLsbBp9jFKc77ox9.6yzhY6urMM8C', 'admin');
commit;
