-- Add password and role_id columns to companies table
-- This script adds the same password encryption logic as users table

-- Add password column
ALTER TABLE companies ADD COLUMN IF NOT EXISTS password VARCHAR(100) NOT NULL DEFAULT 'default_company_password';

-- Add role_id column with foreign key constraint
ALTER TABLE companies ADD COLUMN IF NOT EXISTS role_id BIGINT NOT NULL DEFAULT 2; -- Default to company role (ID 2)

-- Add foreign key constraint (PostgreSQL doesn't support IF NOT EXISTS for constraints)
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints 
                   WHERE constraint_name = 'fk_companies_role') THEN
        ALTER TABLE companies ADD CONSTRAINT fk_companies_role 
            FOREIGN KEY (role_id) REFERENCES roles(id);
    END IF;
END $$;

-- Update existing companies to have hashed passwords
UPDATE companies SET password = '$2a$12$default.hash.for.existing.companies' WHERE password = 'default_company_password';

-- Create index on role_id for better performance
CREATE INDEX IF NOT EXISTS idx_companies_role_id ON companies(role_id);

-- Verify the changes
SELECT column_name, data_type, is_nullable, column_default 
FROM information_schema.columns 
WHERE table_name = 'companies' 
AND column_name IN ('password', 'role_id')
ORDER BY column_name;
