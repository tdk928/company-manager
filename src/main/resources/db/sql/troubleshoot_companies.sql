-- Troubleshooting script for companies table
-- Run this first to identify any issues

-- 1. Check if companies table exists
SELECT EXISTS (
    SELECT FROM information_schema.tables 
    WHERE table_schema = 'public' 
    AND table_name = 'companies'
) AS table_exists;

-- 2. If table exists, show its structure
SELECT column_name, data_type, is_nullable, column_default
FROM information_schema.columns 
WHERE table_name = 'companies' 
ORDER BY ordinal_position;

-- 3. Check if table has any data
SELECT COUNT(*) AS total_companies FROM companies;

-- 4. Check if there are any existing companies with these EIKs
SELECT eik, name FROM companies 
WHERE eik IN ('131129282', '131071587', '130007884');

-- 5. Show current user and database
SELECT current_user AS current_user, current_database() AS current_database;

-- 6. Check table permissions
SELECT grantee, privilege_type 
FROM information_schema.role_table_grants 
WHERE table_name = 'companies';
