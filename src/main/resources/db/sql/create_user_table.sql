-- Create User Table for Admin Users
-- This table stores administrative users with their personal information

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    second_name VARCHAR(30),
    last_name VARCHAR(30) NOT NULL,
    egn VARCHAR(10) NOT NULL UNIQUE
);

-- Create index on egn for faster lookups
CREATE INDEX idx_users_egn ON users(egn);

-- Insert predefined admin user
INSERT INTO users (id, first_name, second_name, last_name, egn) 
VALUES (1, 'Teodor', 'Danielov', 'Kalev', '9308149045')
ON CONFLICT (id) DO NOTHING;
