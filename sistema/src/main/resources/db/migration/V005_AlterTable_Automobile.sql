ALTER TABLE automobile ADD COLUMN rent_request_id BIGINT;

 ALTER TABLE automobile ADD CONSTRAINT fk_automobile_rent_request
  FOREIGN KEY (rent_request_id) REFERENCES rent_request(id);