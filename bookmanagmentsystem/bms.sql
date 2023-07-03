use onetoonerest;



######create table for Book####

create table Book(

ID int not null primary key auto_increment,
TITLE varchar(255) not null,
PRICE int not null,
PUBLISH_YEAR int not null,
STATUS varchar(50) not null,
CREATED_DATE_TIME datetime not null,
UPDATED_DATE_TIME datetime not null,
AUTHOR_ID int not null
);

######create table for Book####



