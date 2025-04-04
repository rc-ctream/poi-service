# 🗺️ POI Backend Service – Technical Task

## Overview

This project implements a simple **POI (Point of Interest)** backend service.  
A *POI* represents a location that is useful or interesting to users — for example, a park, museum, restaurant, or landmark.

The service exposes a RESTful API that allows clients to:

- Retrieve all POIs (optionally filtered by category)
- Browse available categories
- Like or dislike POIs
- View details of a single POI
- *(Optional)* Add comments to a POI

No database or authentication is required — the application stores 20 predefined POIs in memory on startup.

---

## 📦 Requirements

- **Technology Stack**:  
  - Preferred: Java with Spring Boot  
  - Alternatives like Node.js or Python (Flask/Django) are also acceptable

- **Persistence**:  
  - In-memory only (e.g., Java `List`, `Map`, etc.)  
  - No external database

---

## 🧱 POI Data Model

Each POI object consists of the following fields:

```json
{
  "id": "uuid",
  "name": "string",
  "category": "string",
  "likes": 0,
  "dislikes": 0,
  "address": {
    "street": "string",
    "city": "string",
    "postalCode": "string",
    "country": "string",
    "location": {
      "latitude": number,
      "longitude": number
    }
  },
  "comments": [
    {
      "author": "string",
      "message": "string",
      "timestamp": "ISO-8601 timestamp"
    }
  ]
}
```
---

## 🔗 API Endpoints

The following REST endpoints should be implemented:

| Method | Endpoint                      | Description                                                   |
|--------|-------------------------------|---------------------------------------------------------------|
| GET    | `/pois`                       | Returns all POIs, optionally filtered by category             |
|        |                               | Example: `/pois?category=Museum`                              |
| GET    | `/categories`                | Returns a list of all unique POI categories                   |
| GET    | `/pois/{id}`                 | Returns details of a single POI                               |
| POST   | `/pois/{id}/like`            | Increments the like counter                                   |
| POST   | `/pois/{id}/dislike`         | Increments the dislike counter                                |
| POST   | `/pois/{id}/comment` *(optional)* | Adds a comment to the POI

---

## 🚀 Getting Started

1. Clone the repository
2. Open the project in your IDE
3. Run the application:
   - For Maven:
     ```bash
     mvn spring-boot:run
     ```
   - Or execute the main class from your IDE
4. Test the endpoints using Postman, curl, or Swagger (if integrated)

---

## 🧪 Expectations

- Clean, well-structured, and maintainable code
- Layered architecture (Controller → Service → Model)
- REST-compliant use of HTTP methods and status codes
- Proper error handling (e.g., return 404 if POI not found)
- A short README with clear instructions on setup and testing
- Bonus: Unit tests, OpenAPI docs (Swagger), Docker support

---

## 📚 Notes

- The 20 POIs must be initialized on application startup (can be hardcoded)
- The `/pois` endpoint should support optional filtering using a query parameter `category`
- The `/comment` endpoint is optional, but appreciated if included

---

Good luck and have fun! 🚀
