-- Create User Table for Admin Users
-- This table stores administrative users with their personal information

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(30) NOT NULL,
    secondName VARCHAR(30),
    lastName VARCHAR(30) NOT NULL,
    egn VARCHAR(10) NOT NULL UNIQUE,
    
    -- Add constraints
    CONSTRAINT chk_firstName_length CHECK (LENGTH(firstName) <= 30),
    CONSTRAINT chk_secondName_length CHECK (LENGTH(secondName) <= 30),
    CONSTRAINT chk_lastName_length CHECK (LENGTH(lastName) <= 30),
    CONSTRAINT chk_egn_length CHECK (LENGTH(egn) = 10),
    CONSTRAINT chk_egn_numeric CHECK (egn REGEXP '^[0-9]{10}$')
);

-- Create index on egn for faster lookups
CREATE INDEX idx_users_egn ON users(egn);

-- Insert sample admin user (optional)
-- INSERT INTO users (firstName, secondName, lastName, egn) VALUES ('Admin', 'User', 'System', '1234567890');
