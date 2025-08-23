-- Create companies table for storing company information
-- This table stores company details with authentication and role management

CREATE TABLE IF NOT EXISTS companies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    eik VARCHAR(9) NOT NULL UNIQUE CHECK (eik ~ '^[0-9]{9}$'),
    address VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL DEFAULT '$2a$12$default.hash.for.existing.companies',
    role_id BIGINT NOT NULL DEFAULT 2, -- Default to company role (ID 2)
    valid_from DATE NOT NULL DEFAULT CURRENT_DATE,
    valid_to DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_companies_eik ON companies(eik);
CREATE INDEX IF NOT EXISTS idx_companies_name ON companies(name);
CREATE INDEX IF NOT EXISTS idx_companies_valid_from ON companies(valid_from);
CREATE INDEX IF NOT EXISTS idx_companies_role_id ON companies(role_id);

-- Add foreign key constraint to roles table
ALTER TABLE companies ADD CONSTRAINT fk_companies_role 
    FOREIGN KEY (role_id) REFERENCES roles(id);
