# Railway PostgreSQL Database Setup Guide

## Database Connection Details

### For pgAdmin Connection (External Access)
Use the **DATABASE_PUBLIC_URL** for connecting from outside Railway (like your local pgAdmin):

**Host/Address:** `yamabiko.proxy.rlwy.net`
**Port:** `39604`
**Database:** `railway`
**Username:** `postgres`
**Password:** `BokcxluFKfWCwzxhdLOIDUzMWjqjeWtw`
**SSL Mode:** `require`

### For Application Connection (Internal)
Use the **DATABASE_URL** for internal connections within Railway:

**Host/Address:** `${RAILWAY_PRIVATE_DOMAIN}` (Internal Railway domain)
**Port:** `5432`
**Database:** `railway`
**Username:** `postgres`
**Password:** `BokcxluFKfWCwzxhdLOIDUzMWjqjeWtw`

## pgAdmin Connection Steps

1. **Open pgAdmin**
2. **Right-click on "Servers" → "Register" → "Server"**
3. **General Tab:**
   - Name: `Railway PostgreSQL`
4. **Connection Tab:**
   - Host name/address: `yamabiko.proxy.rlwy.net`
   - Port: `39604`
   - Maintenance database: `railway`
   - Username: `postgres`
   - Password: `BokcxluFKfWCwzxhdLOIDUzMWjqjeWtw`
5. **SSL Tab:**
   - SSL mode: `require`
6. **Click "Save"**

## Application Configuration

### Option 1: Use Railway Profile (RECOMMENDED)
Run your application with the Railway profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=railway
```

### Option 2: Set Environment Variables
Set these environment variables before running:
```bash
export DATABASE_PUBLIC_URL="postgresql://postgres:BokcxluFKfWCwzxhdLOIDUzMWjqjeWtw@yamabiko.proxy.rlwy.net:39604/railway"
export POSTGRES_USER="postgres"
export POSTGRES_PASSWORD="BokcxluFKfWCwzxhdLOIDUzMWjqjeWtw"
```

## Test Your Connection

Now you can test if everything works:

1. **Test pgAdmin connection first:**
   - Use the credentials above
   - If it connects, your database is accessible

2. **Test your Spring Boot application:**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=railway
   ```

3. **Check the logs** - you should see successful database connection messages

## Security Notes

- The password is already exposed in your Railway variables
- Consider rotating the password after initial setup
- Use SSL connections (already configured)
- The database is accessible from the internet via Railway's proxy

## Troubleshooting

1. **Connection refused:** Check if the proxy domain and port are correct
2. **SSL error:** Ensure SSL mode is set to `require`
3. **Authentication failed:** Verify username and password
4. **Database doesn't exist:** The database `railway` should be created automatically

## Current Railway Variables (for reference)

```
DATABASE_PUBLIC_URL="postgresql://${{PGUSER}}:${{POSTGRES_PASSWORD}}@${{RAILWAY_TCP_PROXY_DOMAIN}}:${{RAILWAY_TCP_PROXY_PORT}}/${{PGDATABASE}}"
DATABASE_URL="postgresql://${{PGUSER}}:${{POSTGRES_PASSWORD}}@${{RAILWAY_PRIVATE_DOMAIN}}:5432/${{PGDATABASE}}"
PGDATA="/var/lib/postgresql/data/pgdata"
PGDATABASE="${{POSTGRES_DB}}"
PGHOST="${{RAILWAY_PRIVATE_DOMAIN}}"
PGPASSWORD="${{POSTGRES_PASSWORD}}"
PGPORT="5432"
PGUSER="${{POSTGRES_USER}}"
POSTGRES_DB="railway"
POSTGRES_PASSWORD="BokcxluFKfWCwzxhdLOIDUzMWjqjeWtw"
POSTGRES_USER="postgres"
RAILWAY_DEPLOYMENT_DRAINING_SECONDS="60"
SSL_CERT_DAYS="820"
```
