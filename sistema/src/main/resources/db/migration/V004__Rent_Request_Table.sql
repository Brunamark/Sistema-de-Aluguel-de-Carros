CREATE TABLE rent_request (
 id BIGSERIAL PRIMARY KEY,
 user_id BIGINT NOT NULL REFERENCES users(id),
 automobile_id BIGINT NOT NULL REFERENCES automobile(id),
 price FLOAT NOT NULL CHECK (price >= 10),
 start_date DATE NOT NULL,
 end_date DATE NOT NULL,
 executed VARCHAR(50) NOT NULL,
 request_type VARCHAR(50) NOT NULL,
 contract_id BIGINT REFERENCES contract(id)
 );