DO $$
BEGIN
 IF NOT EXISTS (
     SELECT FROM information_schema.columns
     WHERE table_name = 'automobile' AND column_name = 'rent_request_id'
 ) THEN
     ALTER TABLE automobile ADD COLUMN rent_request_id BIGINT;

     ALTER TABLE automobile ADD CONSTRAINT fk_automobile_rent_request
         FOREIGN KEY (rent_request_id) REFERENCES rent_request(id);

     COMMENT ON COLUMN automobile.rent_request_id IS 'Referência à solicitação de aluguel associada ao automóvel';
 END IF;
END $$;

UPDATE automobile a
SET rent_request_id = latest_request.id
FROM (
 SELECT DISTINCT ON (automobile_id)
     id,
     automobile_id,
     start_date
 FROM rent_request
 ORDER BY automobile_id, start_date DESC
) AS latest_request
WHERE a.id = latest_request.automobile_id
AND a.rent_request_id IS NULL;
