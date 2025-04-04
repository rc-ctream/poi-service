# Points of Interest (POI) Backend Service

This project provides a backend API service for managing Points of Interest (POIs). It utilizes Java (Spring Boot), PostgreSQL, and Docker for containerization.

## Technology Stack

*   **Java:** 21 (Using Spring Boot framework)
*   **Database:** PostgreSQL 16
*   **Containerization:** Docker & Docker Compose

## Prerequisites

Before you begin, ensure you have the following installed on your system:

*   **Docker:** Docker Engine and Docker Compose (usually included with Docker Desktop installations). [Download Docker](https://www.docker.com/get-started)

## Getting Started & Running the Application

Follow these steps to build the Docker images and run the application and database containers:

1.  **Navigate to Project Directory:**
    Open your terminal or command prompt and change into the project's root directory (the one containing the `docker-compose.yml` file). If your project folder is named `pois`, use:
    ```bash
    cd pois
    ```
    *(Replace `pois` with the actual name of your project directory if it's different)*

2.  **Run with Docker Compose:**
    Execute the following command. This will build the Docker image for the Spring Boot application (if it doesn't exist or if changes are detected) and start both the application (`pois-service`) and the PostgreSQL database (`postgres`) containers.
    ```bash
    docker compose up --build

## Testing the API with Swagger UI

Once the containers are up and the Spring Boot application has fully started:

1.  **Open your web browser.**
2.  **Navigate to the Swagger UI documentation:**
    ```
    http://localhost:8080/swagger-ui/index.html
    ```
    *(Ensure no other application on your host machine is using port 8080 if you encounter connection issues)*

3.  **Explore and Test:**
    You should now see the Swagger UI interface, which provides interactive documentation for the API endpoints. You can:
    *   View available API endpoints.
    *   See details about request parameters and response formats.
    *   Execute API requests directly from the browser to test the functionality.

## Stopping the Application

To stop and remove the containers, networks, and volumes created by Docker Compose:

1.  **Open a terminal** in the same project directory where you ran `docker compose up`.
2.  **Execute:**
    ```bash
    docker compose down
    ```