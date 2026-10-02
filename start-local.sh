#!/bin/bash
set -e

read -r -p "PostgreSQL username [postgres]: " DB_USERNAME_INPUT
export DB_USERNAME="${DB_USERNAME_INPUT:-postgres}"
read -r -s -p "PostgreSQL password: " DB_PASSWORD
echo
read -r -p "JWT secret (press Enter to generate one): " JWT_SECRET_INPUT
if [ -z "$JWT_SECRET_INPUT" ]; then
  export JWT_SECRET="$(openssl rand -base64 48)"
else
  export JWT_SECRET="$JWT_SECRET_INPUT"
fi

echo
echo "Starting EliteCart backend with Cash on Delivery checkout..."
./mvnw spring-boot:run
