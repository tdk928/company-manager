-- Insert test data into companies table
-- Run this in pgAdmin or your Railway database

INSERT INTO companies (id, name) VALUES 
(1, 'TechCorp Solutions'),
(2, 'Global Innovations Ltd'),
(3, 'Digital Dynamics Inc'),
(4, 'Future Systems Co'),
(5, 'Smart Technologies Group');

-- Verify the insertions
SELECT * FROM companies;
