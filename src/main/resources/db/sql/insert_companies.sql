-- Insert multiple companies into the companies table
-- This script inserts Kaufland, Lidl, and Billa companies

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

-- Verify all insertions
SELECT * FROM companies WHERE eik IN ('131129282', '131071587', '130007884') ORDER BY name;
