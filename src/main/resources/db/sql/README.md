# Database SQL Scripts

This directory contains SQL scripts for managing the database schema.

## Scripts

### `create_user_table.sql`
Creates the `users` table for storing administrative users with the following columns:
- `id` - Auto-incrementing primary key
- `firstName` - Required, maximum 30 characters
- `secondName` - Optional, maximum 30 characters  
- `lastName` - Required, maximum 30 characters
- `egn` - Required, exactly 10 numeric digits, unique

### `create_roles_table.sql`
Creates the `roles` table for storing user roles with the following columns:
- `id` - Auto-incrementing primary key
- `name` - Role name (admin, company, anonymous)
- `description` - Role description

### `create_companies_table.sql`
Creates the `companies` table for storing company information with the following columns:
- `id` - Auto-incrementing primary key
- `name` - Required, maximum 50 characters
- `eik` - Required, exactly 9 numeric digits, unique (Bulgarian company identifier)
- `address` - Required, maximum 50 characters
- `email` - Required, valid email format
- `phone` - Required, company contact phone number
- `valid_from` - Required, automatically set to current date when creating
- `valid_to` - Optional, null when creating (for future use in company lifecycle management)

### `insert_companies.sql`
Inserts multiple companies: Kaufland (EIK: 131129282), Lidl (EIK: 131071587), and Billa (EIK: 130007884)

### `setup_companies_complete.sql`
Complete setup script that creates the companies table and inserts all companies in one go

### `troubleshoot_companies.sql`
Troubleshooting script to identify issues with the companies table setup

## Table Constraints

### Users Table
- Length validation for all name fields
- EGN format validation (exactly 10 digits)
- Unique constraint on EGN field
- Index on EGN for performance

### Companies Table
- Length validation for name and address fields (max 50 characters)
- EIK format validation (exactly 9 digits)
- Email format validation (standard email regex pattern)
- Phone field validation (required, no format restrictions)
- Unique constraint on EIK field
- Indexes on EIK, name, and valid_from for performance
- Automatic valid_from date setting (current date)
- valid_to field for future company lifecycle management

## Usage

Run these scripts in your database management tool or via command line:
```bash
mysql -u username -p database_name < create_user_table.sql
```

## Notes

- The `secondName` field is optional as it may not be applicable to all users
- EGN (ЕГН) is a Bulgarian personal number that must be exactly 10 digits
- EIK (ЕИК) is a Bulgarian company identifier that must be exactly 9 digits
- The `valid_from` field is automatically set to the current date when creating a company
- The `valid_to` field is initially null and can be used for company lifecycle management
- All scripts use `IF NOT EXISTS` and `IF EXISTS` clauses for safe execution

## 🔧 Troubleshooting

If the company insert script doesn't work, follow these steps:

### **Step 1: Run the troubleshooting script**
```bash
psql -U username -d database_name -f troubleshoot_companies.sql
```

### **Step 2: Check the output**
- **Table exists**: Should show `true`
- **Table structure**: Should show all required columns
- **Permissions**: Should show INSERT privileges for your user

### **Step 3: Use the complete setup script**
If there are issues, run the complete setup:
```bash
psql -U username -d database_name -f setup_companies_complete.sql
```

### **Common Issues:**
1. **Table doesn't exist**: Run `create_companies_table.sql` first
2. **Wrong database**: Make sure you're connected to the right database
3. **Permission denied**: Check if your user has INSERT privileges
4. **Syntax errors**: Ensure you're using PostgreSQL (not MySQL)
