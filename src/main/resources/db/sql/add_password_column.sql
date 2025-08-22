-- Add password column to users table
-- This script adds the password column for user authentication

ALTER TABLE users ADD COLUMN IF NOT EXISTS password VARCHAR(100) NOT NULL DEFAULT 'default_password';

-- Update existing users with a default password (should be changed after first login)
-- Note: In production, you should require users to set their own passwords
UPDATE users SET password = '$2a$10$default.hash.for.existing.users' WHERE password = 'default_password';
