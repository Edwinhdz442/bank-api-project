--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables
create table account (
account_num int primary key,
username varchar(255) unique,
password varchar(255)
);