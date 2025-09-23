INSERT INTO rent_request (user_id, automobile_id, contract_id, price, start_date, end_date, executed, request_type) VALUES
-- João's approved request with contract
((SELECT id FROM users WHERE username = 'joao.silva@email.com'),
(SELECT id FROM automobile WHERE registration_plate = 'ABC-1234'),
1,  -- Primeiro contrato
150.00, '2025-10-01', '2025-10-05', 'APPROVED', 'CLIENTS'),  -- CLIENTS em vez de RENTAL

-- Maria's approved request with contract
((SELECT id FROM users WHERE username = 'maria.santos@email.com'),
(SELECT id FROM automobile WHERE registration_plate = 'GHI-9012'),
2,  -- Segundo contrato
180.00, '2025-10-15', '2025-10-20', 'APPROVED', 'CLIENTS');  -- CLIENTS em vez de RENTAL

-- Depois, as outras solicitações sem contratos
INSERT INTO rent_request (user_id, automobile_id, contract_id, price, start_date, end_date, executed, request_type) VALUES
-- João's pending request
((SELECT id FROM users WHERE username = 'joao.silva@email.com'),
(SELECT id FROM automobile WHERE registration_plate = 'DEF-5678'),
1,  -- Sem contrato
200.00, '2025-11-10', '2025-11-15', 'PENDING', 'CLIENTS'),  -- CLIENTS em vez de RENTAL

-- Maria's rejected request
((SELECT id FROM users WHERE username = 'maria.santos@email.com'),
(SELECT id FROM automobile WHERE registration_plate = 'JKL-3456'),
2,  -- Sem contrato
35000.00, '2025-09-25', '2025-09-25', 'REJECTED', 'BANKS'),  -- BANKS em vez de PURCHASE

-- Pedro's evaluated request
((SELECT id FROM users WHERE username = 'pedro.oliveira@email.com'),
(SELECT id FROM automobile WHERE registration_plate = 'MNO-7890'),
1,  -- Sem contrato
220.00, '2025-10-05', '2025-10-10', 'EVALUATED', 'CLIENTS'),  -- CLIENTS em vez de RENTAL

-- Ana's pending request
((SELECT id FROM users WHERE username = 'ana.pereira@email.com'),
(SELECT id FROM automobile WHERE registration_plate = 'PQR-1234'),
2,  -- Sem contrato
28000.00, '2025-09-30', '2025-09-30', 'PENDING', 'BANKS');  -- BANKS em vez de PURCHASE