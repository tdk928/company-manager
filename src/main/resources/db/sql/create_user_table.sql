-- Create users table for storing administrative users
-- This table stores user information with authentication and role management

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    second_name VARCHAR(30),
    last_name VARCHAR(30) NOT NULL,
    egn VARCHAR(10) NOT NULL UNIQUE CHECK (egn ~ '^[0-9]{10}$'),
    password VARCHAR(100) NOT NULL DEFAULT '$2a$10$default.hash.for.existing.users',
    role_id BIGINT NOT NULL DEFAULT 4, -- Default to user role (ID 4)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_egn ON users(egn);
CREATE INDEX IF NOT EXISTS idx_users_role_id ON users(role_id);

-- Add foreign key constraint to roles table
ALTER TABLE users ADD CONSTRAINT fk_users_role 
    FOREIGN KEY (role_id) REFERENCES roles(id);

-- Insert default admin user
INSERT INTO users (id, first_name, second_name, last_name, egn, password, role_id) VALUES 
    (1, 'Teodor', 'Danielov', 'Kalev', '9308149045', 'tdk', 1)
ON CONFLICT (id) DO NOTHING;
