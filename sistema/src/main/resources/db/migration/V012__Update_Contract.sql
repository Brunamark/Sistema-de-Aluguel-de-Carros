DO $$
BEGIN
 IF NOT EXISTS (
     SELECT FROM information_schema.columns
     WHERE table_name = 'contract' AND column_name = 'rent_request_id'
 ) THEN
     -- Adicionar coluna rent_request_id
     ALTER TABLE contract ADD COLUMN rent_request_id BIGINT UNIQUE;

     -- Adicionar restrição de chave estrangeira
     ALTER TABLE contract ADD CONSTRAINT fk_contract_rent_request
         FOREIGN KEY (rent_request_id) REFERENCES rent_request(id);

     -- Adicionar comentário para documentação
     COMMENT ON COLUMN contract.rent_request_id IS 'Referência à solicitação de aluguel associada ao contrato';
 END IF;
END $$;

-- Atualizar a tabela contract para adicionar referências às solicitações
UPDATE contract
SET rent_request_id = rr.id
FROM rent_request rr
WHERE rr.contract_id = contract.id
AND contract.rent_request_id IS NULL;

-- Verificar se a coluna client_id existe e adicioná-la se necessário
DO $$
BEGIN
 IF NOT EXISTS (
     SELECT FROM information_schema.columns
     WHERE table_name = 'contract' AND column_name = 'client_id'
 ) THEN
     -- Adicionar coluna client_id
     ALTER TABLE contract ADD COLUMN client_id BIGINT;

     -- Adicionar restrição de chave estrangeira
     ALTER TABLE contract ADD CONSTRAINT fk_contract_client
         FOREIGN KEY (client_id) REFERENCES users(id);

     -- Adicionar comentário para documentação
     COMMENT ON COLUMN contract.client_id IS 'Referência ao cliente associado ao contrato';
 END IF;
END $$;

-- Atualizar a tabela contract para adicionar referências aos clientes
UPDATE contract
SET client_id = rent_request.user_id
FROM rent_request
WHERE contract.rent_request_id = rent_request.id
AND contract.client_id IS NULL;

-- Verificar que apenas usuários do tipo CLIENT estão associados aos contratos
DO $$
DECLARE
 invalid_count INTEGER;
BEGIN
 SELECT COUNT(*) INTO invalid_count
 FROM contract c
 JOIN users u ON c.client_id = u.id
 WHERE u.role != 'CLIENT';

 IF invalid_count > 0 THEN
     RAISE EXCEPTION 'Existem contratos associados a usuários que não são do tipo CLIENT';
 END IF;
END $$;