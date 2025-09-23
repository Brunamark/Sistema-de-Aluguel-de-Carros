CREATE TABLE contract (
id BIGSERIAL PRIMARY KEY,
credit_quantity INTEGER NOT NULL CHECK (credit_quantity >= 0),
contract_type VARCHAR(50) NOT NULL CHECK (contract_type IN ('SIMPLE', 'CREDIT'))
);