create table USERS (
    USERID varchar(64) PRIMARY KEY,
    USERNAME varchar(100) not null,
    PASSWORD varchar(128) not null,
    SALT varchar(32) not null,
    FIRSTNAME varchar(100) not null,
    MIDDLENAME varchar(100),
    LASTNAME varchar(100) not null
);
