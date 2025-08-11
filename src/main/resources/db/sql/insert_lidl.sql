-- Insert Lidl company
INSERT INTO companies (name, eik, address, email, phone, valid_from) 
VALUES (
    'Lidl',
    '131071587',
    'с. Равно поле, ул. "3-ти Март"',
    'info@lidl.bg',
    '+359 876046967',
    CURRENT_DATE
) ON CONFLICT (eik) DO NOTHING;

-- Verify the insertion
SELECT * FROM companies WHERE eik = '131071587';
