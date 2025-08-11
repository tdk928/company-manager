-- Insert Kaufland company
INSERT INTO companies (name, eik, address, email, phone, valid_from) 
VALUES (
    'Kaufland',
    '131129282',
    'ул. Скопие № 1А',
    'info@kaufland.bg',
    '0800  12 220',
    CURRENT_DATE
) ON CONFLICT (eik) DO NOTHING;

-- Verify the insertion
SELECT * FROM companies WHERE eik = '131129282';
