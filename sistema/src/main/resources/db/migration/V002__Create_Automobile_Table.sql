-- Create automobile table
CREATE TABLE automobile (
 id BIGSERIAL PRIMARY KEY,
 registration VARCHAR(255) NOT NULL,
 year INTEGER NOT NULL,
 brand VARCHAR(100) NOT NULL,
 registration_plate VARCHAR(20) NOT NULL,
 model VARCHAR(100) NOT NULL
);
