CREATE TABLE users (
 id BIGSERIAL PRIMARY KEY,
 username VARCHAR(255) NOT NULL,
 password VARCHAR(255) NOT NULL,
 role VARCHAR(50) NOT NULL,
 name VARCHAR(255),
 cpf VARCHAR(14),
 rg VARCHAR(20),
 address VARCHAR(255),
 profession VARCHAR(100),
 employer VARCHAR(100),
 income_earned INTEGER
 );