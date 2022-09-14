SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS bdtemp1 ;

CREATE SCHEMA IF NOT EXISTS bdtemp1;

USE bdtemp1;

CREATE TABLE employee(
	id int PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
	rut varchar(30) NOT NULL,
	last_names varchar(30) NOT NULL,
	first_names varchar(30) NOT NULL,
	birth_date date NOT NULL,
    hire_date date NOT NULL,
    category char NOT NULL,
    fixed_monthly_wage int NOT NULL
);

CREATE TABLE overtime_approval
(
     id int PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
     approval_date date NOT NULL,
     details varchar(200),
     employee_rut varchar(30) NOT NULL,
     employee_id int NOT NULL,
     FOREIGN KEY(employee_id) REFERENCES bdtemp1.employee(id)
);

CREATE TABLE absence_justification
(
    id int PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    justification_date date NOT NULL,
    details varchar(200),
    status bool,
    employee_rut varchar(30) NOT NULL,
    employee_id int NOT NULL,
    FOREIGN KEY(employee_id) REFERENCES bdtemp1.employee(id)
);

CREATE TABLE clock
(
    id int PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    clock_date date NOT NULL,
    clock_hour time NOT NULL ,
    employee_id int NOT NULL,
    type varchar(30) NOT NULL,
    FOREIGN KEY(employee_id) REFERENCES bdtemp1.employee(id)
);

CREATE TABLE wage
(
    id int PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    date date NOT NULL,
    employee_id int NOT NULL,
    FOREIGN KEY(employee_id) REFERENCES bdtemp1.employee(id)
);

CREATE TABLE wage_detail
(
    id int PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    type varchar(30) NOT NULL,
    amount bigint NOT NULL,
    wage_id int NOT NULL,
    FOREIGN KEY(wage_id) REFERENCES bdtemp1.wage(id)
);


INSERT INTO employee(rut, last_names, first_names, birth_date, hire_date, category, fixed_monthly_wage)
VALUES ('14.707.441-7', 'Ibanez Bendezu', 'Aaron Andre', '1996-01-29', '2022-09-06', 'A', 1700000),
       ('6.591.049-7', 'Gonzalez Reyes', 'Boris Leonardo', '1952-11-03', '1987-04-22', 'C', 800000),
       ('11.234.123-6', 'Hornbuckle', 'Stephanie', '2000-06-15', '2021-09-03', 'B', 1200000),
       ('12.457.562-3', 'Sargeant', 'Anstice', '1999-06-18', '2018-08-19', 'C', 800000),
       ('21.142.354-k', 'Walling', 'Max', '1986-11-23', '2018-05-12', 'B', 1200000);

INSERT INTO overtime_approval(approval_date, employee_rut, employee_id)
VALUES ('2022-08-17', '14.707.441-7', 1);

INSERT INTO absence_justification(justification_date, employee_rut, employee_id)
VALUES ('2022-08-05', '6.591.049-7', 2);