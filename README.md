# Ecommerce Microservice Project

This project implements a scalable and robust backend system for an eCommerce platform using Spring Boot and microservices architecture.

## Core Microservices
- Product service (Manages catalog information 📦)
- Category service (Organizes products efficiently 🗂️)
- Cart service (Manages cart and checkout operations smoothly 🛒)
- Order service (Manges order tracking and delivery)
- User service (Handles authentication and user management 👥)
- Filter service (Implements advanced search using ElasticSearch 🔍)

## Additional Components
- API Gateway: Provides a unified entry point with request routing, load balancing, and security enforcement 🚪
- Cloud Config: Streamlines configuration management ☁️
- Service Registry Microservice: Centralized microservice registration 📡
- Proto for gRPC communication between microservices

## API Documentation:
- Product Service Swagger UI: http://localhost:9191/meesho/product-microservice/swagger-ui/index.html#/
- Category Service Swagger UI: http://localhost:9191/meesho/category-microservice/swagger-ui/index.html#/
- Filter Service Swagger UI: http://localhost:9191/meesho/filter-microservice/swagger-ui/index.html#/
- User Service Swagger UI: http://localhost:9191/meesho/user-microservice/swagger-ui/index.html#/
- Cart Service Swagger UI: http://localhost:9191/meesho/cart-microservice/swagger-ui/index.html#/
- Order Service Swagger UI: http://localhost:9191/meesho/order-microservice/swagger-ui/index.html#/



## Installation
- Java 17 is required.
- Use Maven to build the project:
mvn clean install

## Usage
- This project provides APIs for building an eCommerce shopping website.

## Technologies Used
- Spring Boot
- MySQL
- MongoDB
- Elasticsearch
- gRPC
- Kafka
- Hibernate

## Contributing
- The order management feature is not fully completed.
- Contributions are welcome for improving best practices, features, and functionalities.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For inquiries, you can reach me at [sachin.s.developer@gmail.com](mailto:sachin.s.developer@gmail.com).


