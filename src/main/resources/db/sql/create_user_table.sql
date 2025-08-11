-- Create User Table for Admin Users
-- This table stores administrative users with their personal information

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    second_name VARCHAR(30),
    last_name VARCHAR(30) NOT NULL,
    egn VARCHAR(10) NOT NULL UNIQUE,
    role_id INT NOT NULL DEFAULT 1
);

-- Create index on egn for faster lookups
CREATE INDEX idx_users_egn ON users(egn);

-- Create foreign key constraint for role_id
ALTER TABLE users ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id);

-- Insert predefined admin user
INSERT INTO users (id, first_name, second_name, last_name, egn, role_id) 
VALUES (1, 'Teodor', 'Danielov', 'Kalev', '9308149045', 1)
ON CONFLICT (id) DO NOTHING;
