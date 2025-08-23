-- Create roles table for user roles
-- This table stores the different roles available in the system

CREATE TABLE IF NOT EXISTS roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

-- Create index on role name for better performance
CREATE INDEX IF NOT EXISTS idx_roles_name ON roles(name);

-- Insert default roles
INSERT INTO roles (name, description) VALUES 
    ('admin', 'Administrator with full system access'),
    ('company', 'Company user with limited access'),
    ('user', 'Regular user with standard access'),
    ('anonymous', 'Anonymous user with minimal access')
ON CONFLICT (name) DO NOTHING;
