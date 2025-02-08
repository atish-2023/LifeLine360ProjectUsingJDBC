
*********************************************************************            SQL                     ******************************************************************

CREATE DATABASE help;
USE help;

-- User Table
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(15) NOT NULL
);



create table admin(id int primary key,email varchar(20),password varchar(13));

insert into admin values(01,'admin123@gmail.com','admin@123');

-- Emergency Table
CREATE TABLE emergency (
    emergency_id INT AUTO_INCREMENT PRIMARY KEY,
    emergency_name VARCHAR(30) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    phone_number VARCHAR(10) NOT NULL UNIQUE CHECK (phone_number REGEXP '^[0-9]{10}$'),
    localdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Hospital Table
CREATE TABLE hospital (
    hospital_id INT AUTO_INCREMENT PRIMARY KEY,
    hospital_name VARCHAR(40) NOT NULL,
    hospital_location VARCHAR(50) NOT NULL,
    phone_number VARCHAR(10) NOT NULL UNIQUE CHECK (phone_number REGEXP '^[0-9]{10}$')
);

INSERT INTO hospital (hospital_name, hospital_location, phone_number) VALUES
('City Care Hospital', 'Pune', '9876543210'),
('Global Health Clinic', 'Mumbai', '9123456789'),
('Sunrise Medical Center', 'Delhi', '9234567890'),
('Metro Hospital', 'Bangalore', '9345678901'),
('Green Life Hospital', 'Chennai', '9456789012'),
('Apex Super Specialty', 'Mumbai', '9567890123'),  
('Harmony Health Hub', 'Delhi', '9678901234'),  
('Elite Care Hospital', 'Pune', '9789012345');  


-- Fire Team Table
CREATE TABLE fire_team (
    fireteam_id INT AUTO_INCREMENT PRIMARY KEY,
    fireteam_name VARCHAR(40) NOT NULL,
    location VARCHAR(50) NOT NULL,
    phone_number VARCHAR(10) NOT NULL UNIQUE CHECK (phone_number REGEXP '^[0-9]{10}$')
);

INSERT INTO fire_team (fireteam_name, location, phone_number) VALUES
('Red Flames Squad', 'Pune', '9876543211'),
('Rapid Response Unit', 'Mumbai', '9123456788'),
('Blaze Fighters', 'Delhi', '9234567899'),
('Inferno Warriors', 'Bangalore', '9345678902'),
('Emergency Fire Brigade', 'Chennai', '9456789013'),
('Rescue Task Force', 'Mumbai', '9567890124'),  
('Firestorm Team', 'Delhi', '9678901235'),  
('Phoenix Fire Squad', 'Pune', '9789012346'); 


-- Police Station Table
CREATE TABLE police_station (
    station_id INT AUTO_INCREMENT PRIMARY KEY,
    station_name VARCHAR(40) NOT NULL,
    location VARCHAR(50) NOT NULL,
    phone_number VARCHAR(10) NOT NULL UNIQUE CHECK (phone_number REGEXP '^[0-9]{10}$')
);

INSERT INTO police_station (station_name, location, phone_number) VALUES
('Central Police Station', 'Pune', '9876543222'),
('City Crime Branch', 'Mumbai', '9123456777'),
('Metro Police HQ', 'Delhi', '9234567888'),
('East Zone Police', 'Bangalore', '9345678899'),
('South Division Police', 'Chennai', '9456789000'),
('North City Police', 'Mumbai', '9567890111'),  
('Old Town Police Station', 'Delhi', '9678901222'),  
('West Region Police', 'Pune', '9789012333');  


select * from hospital;

select * from user;

select * from emergency;

select * from fire_team;



SHOW TABLES FROM help;







