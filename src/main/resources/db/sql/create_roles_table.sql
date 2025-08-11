-- Create Roles Table
-- This table stores user roles in the system

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index on role name for faster lookups
CREATE INDEX idx_roles_name ON roles(name);

-- Insert predefined roles
INSERT INTO roles (name, description) VALUES 
    ('admin', 'Administrator with full system access'),
    ('company', 'Company user with limited access'),
    ('anonymous', 'Anonymous user with minimal access')
ON CONFLICT (name) DO NOTHING;
