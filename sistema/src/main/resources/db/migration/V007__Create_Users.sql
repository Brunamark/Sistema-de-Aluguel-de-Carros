INSERT INTO users (username, password, role) VALUES
('agent1@carrentals.com', 'senha123', 'AGENT'),
('agent2@carrentals.com', 'agente456', 'AGENT'),
('manager@carrentals.com', 'gerente789', 'AGENT');

INSERT INTO users (username, password, role, name, cpf, rg, address, profession, employer, income_earned) VALUES
('joao.silva@email.com', 'joao123', 'CLIENT',
'João Silva', '123.456.789-00', '12.345.678-9', 'Rua das Flores, 123, São Paulo', 'Engenheiro', 'Construtora ABC', 8000),

('maria.santos@email.com', 'maria456', 'CLIENT',
'Maria Santos', '987.654.321-00', '98.765.432-1', 'Av. Paulista, 1000, São Paulo', 'Médica', 'Hospital XYZ', 12000),

('pedro.oliveira@email.com', 'pedro789', 'CLIENT',
'Pedro Oliveira', '456.789.123-00', '45.678.912-3', 'Rua Augusta, 500, São Paulo', 'Advogado', 'Escritório Legal', 9500),

('ana.pereira@email.com', 'ana12345', 'CLIENT',
'Ana Pereira', '789.123.456-00', '78.912.345-6', 'Rua Oscar Freire, 200, São Paulo', 'Designer', 'Agência Criativa', 6500);