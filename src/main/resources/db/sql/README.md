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

### `drop_user_table.sql`
Removes the `users` table and its associated index.

## Table Constraints

The user table includes several constraints:
- Length validation for all name fields
- EGN format validation (exactly 10 digits)
- Unique constraint on EGN field
- Index on EGN for performance

## Usage

Run these scripts in your database management tool or via command line:
```bash
mysql -u username -p database_name < create_user_table.sql
```

## Notes

- The `secondName` field is optional as it may not be applicable to all users
- EGN (ЕГН) is a Bulgarian personal number that must be exactly 10 digits
- All scripts use `IF NOT EXISTS` and `IF EXISTS` clauses for safe execution
