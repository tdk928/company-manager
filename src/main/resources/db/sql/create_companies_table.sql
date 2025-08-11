-- Create companies table
CREATE TABLE IF NOT EXISTS companies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    eik VARCHAR(9) NOT NULL UNIQUE,
    address VARCHAR(50) NOT NULL,
    valid_from DATE NOT NULL DEFAULT CURRENT_DATE,
    valid_to DATE
);

-- Create index on EIK for faster lookups
CREATE INDEX idx_companies_eik ON companies(eik);

-- Create index on name for search functionality
CREATE INDEX idx_companies_name ON companies(name);

-- Create index on valid_from for date-based queries
CREATE INDEX idx_companies_valid_from ON companies(valid_from);

-- Insert a sample company for testing (optional)
-- INSERT INTO companies (name, eik, address, valid_from) 
-- VALUES ('Sample Company Ltd', '123456789', 'Sample Address 123, Sofia', CURRENT_DATE);
