ALTER TABLE contract ADD COLUMN rent_request_id BIGINT UNIQUE;
ALTER TABLE contract ADD CONSTRAINT fk_contract_rent_request
 FOREIGN KEY (rent_request_id) REFERENCES rent_request(id);