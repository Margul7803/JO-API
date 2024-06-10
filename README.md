# JO-API

JO-API is an API designed to manage event reservations in stadiums, along with associated ticket management.

## Run the project

1. Clone the project locally:

    ```bash
    git clone https://github.com/Margul7803/JO-API.git
    ```

2. Build and run the Dockerized app:

    ```bash
    docker compose up
    ```

## Information

Once the API is Dockerized, you can interact with it via the following route:
[http://localhost:8080](http://localhost:8080)

The app comes pre-seeded with some data.

Credentials to use for authentication at the `/auth/login` endpoint:
- admin@example.com/admin
- user@example.com/user

A Swagger UI is also generated to explore all available endpoints:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)