-- Complete setup script for companies table
-- This script will create the table and insert all companies

-- Step 1: Create companies table (if it doesn't exist)
CREATE TABLE IF NOT EXISTS companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    eik VARCHAR(9) NOT NULL UNIQUE,
    address VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    valid_from DATE NOT NULL DEFAULT CURRENT_DATE,
    valid_to DATE
);

-- Step 2: Create indexes for performance
CREATE INDEX IF NOT EXISTS idx_companies_eik ON companies(eik);
CREATE INDEX IF NOT EXISTS idx_companies_name ON companies(name);
CREATE INDEX IF NOT EXISTS idx_companies_valid_from ON companies(valid_from);

-- Step 3: Clear any existing data (optional - remove this if you want to keep existing data)
-- DELETE FROM companies WHERE eik IN ('131129282', '131071587', '130007884');

-- Step 4: Insert companies
INSERT INTO companies (name, eik, address, email, phone, valid_from) 
VALUES (
    'Kaufland',
    '131129282',
    'ул. Скопие № 1А',
    'info@kaufland.bg',
    '0800  12 220',
    CURRENT_DATE
) ON CONFLICT (eik) DO NOTHING;

INSERT INTO companies (name, eik, address, email, phone, valid_from) 
VALUES (
    'Lidl',
    '131071587',
    'с. Равно поле, ул. "3-ти Март"',
    'info@lidl.bg',
    '+359 876046967',
    CURRENT_DATE
) ON CONFLICT (eik) DO NOTHING;

INSERT INTO companies (name, eik, address, email, phone, valid_from) 
VALUES (
    'Billa',
    '130007884',
    'бул. БЪЛГАРИЯ № 55',
    'info@billa.bg',
    '+359 2 8188 222',
    CURRENT_DATE
) ON CONFLICT (eik) DO NOTHING;

-- Step 5: Verify insertions
SELECT 
    id,
    name,
    eik,
    address,
    email,
    phone,
    valid_from,
    valid_to
FROM companies 
WHERE eik IN ('131129282', '131071587', '130007884') 
ORDER BY name;

-- Step 6: Show total count
SELECT COUNT(*) AS total_companies FROM companies;
