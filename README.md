# 🚀 LinkedIn Microservices Application

This project is a **Spring Boot Microservices-based application** that includes:

## 📦 Services

- ✅ API Gateway
- ✅ User Service
- ✅ Posts Service
- ✅ Connection Service (Neo4j)
- ✅ Discovery Server (Eureka)

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Cloud (Gateway, Eureka)
- Neo4j (Graph DB)
- PostgreSQL
- Maven

---

## 📁 Project Structure

root/
├── api-gateway/
├── user-service/
├── posts-service/
├── connection-service/
├── discovery-server/
├── .gitignore
└── README.md
---

## ▶️ How to Run

1. Start Eureka Server
2. Start all microservices
3. Start API Gateway

---

## 🌐 Endpoints
Example:


GET http://localhost:8080/api/v1/posts/1
GET http://localhost:8080/api/v1/users

---

## 🔗 Neo4j

- Uses Bolt connection (`neo4j+s://`)
- Stores connection graph (LinkedIn-style)

---

## 📌 Notes

- `.idea`, `target`, logs are ignored via `.gitignore`
- Each service runs independently
- Gateway handles routing

---

## 👨‍💻 Author

Suman Mondal