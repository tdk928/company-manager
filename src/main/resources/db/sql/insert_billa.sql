-- Insert Billa company
INSERT INTO companies (name, eik, address, email, phone, valid_from) 
VALUES (
    'Billa',
    '130007884',
    'бул. БЪЛГАРИЯ № 55',
    'info@billa.bg',
    '+359 2 8188 222',
    CURRENT_DATE
) ON CONFLICT (eik) DO NOTHING;

-- Verify the insertion
SELECT * FROM companies WHERE eik = '130007884';
