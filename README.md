# E-Commerce Website

Full-stack e-commerce application built with Spring Boot, PostgreSQL, JWT authentication and React/Vite.

## Stack

- Java 17
- Spring Boot 3.5.3
- Spring Security + JWT
- PostgreSQL
- React 19
- Vite
- Axios

## Project structure

- `e-commerceWebsite/` - Spring Boot backend
- `frontend/` - React frontend

## 1. PostgreSQL

Create the database:

```sql
CREATE DATABASE "e-commerce";
```

Update `src/main/resources/application.properties` if your PostgreSQL username/password are different.

## 2. Start backend

From the backend folder:

```bash
./mvnw spring-boot:run
```

The API runs on:

`http://localhost:8085`

The application uses `spring.jpa.hibernate.ddl-auto=update`, so the entity tables are created/updated automatically.

## 3. Start frontend

From `frontend`:

```bash
npm install
npm run dev
```

Open the Vite URL shown in the terminal, normally:

`http://localhost:5173`

## Demo accounts

The development initializer creates these accounts when they do not already exist:

| Role | Email | Password |
|---|---|---|
| Super Admin | superadmin@eshop.local | superadmin123 |
| Admin | admin@eshop.local | admin123 |
| Intermediate | intermediate@eshop.local | intermediate123 |

Normal registration always creates a `CUSTOMER` account. Elevated roles cannot be selected from the public registration form.

The initializer also inserts four sample products when the product table is empty.

For production, set:

```properties
app.seed-users=false
app.seed-products=false
```

and use secure credentials/secrets.

## Main API endpoints

### Authentication

- `POST /auth/register`
- `POST /auth/login`

### Products

- `GET /products`
- `GET /products/{id}`
- `POST /products`
- `PUT /products/{id}`
- `DELETE /products/{id}`

### Cart

- `POST /cart/add`
- `GET /cart/{userId}`
- `DELETE /cart/{cartId}`

### Wishlist

- `POST /wishlist/add`
- `GET /wishlist/{userId}`
- `DELETE /wishlist/{wishlistId}`

### Orders

- `POST /orders`
- `GET /orders/user/{userId}`
- `GET /orders/{orderId}`
- `PUT /orders/{orderId}/status?status=CONFIRMED`

## Role access

- `SUPER_ADMIN`: full application access
- `ADMIN`: administration and product management
- `INTERMEDIATE`: product/catalog management
- `CUSTOMER`: shopping, cart, wishlist and orders

JWT authentication is stateless and the React Axios client automatically sends the stored bearer token.

## 2026 Storefront Upgrade

The project now includes a modern ecommerce storefront, Cash on Delivery checkout, payment-aware orders, tracking numbers, estimated delivery dates, and a visual customer order timeline.

### Checkout

The current version uses **Cash on Delivery (COD)** and does not require Razorpay or any payment-gateway account. Customers place an order directly from the cart. The backend calculates the order total from the current product prices, creates the order, reduces stock, clears the cart, and marks payment status as `COD / PENDING`.

### Order tracking

Each order receives a tracking number and estimated delivery date. The customer Orders page displays progression through Confirmed → Shipped → Delivered. Existing admin/intermediate status APIs can move an order through these states.
