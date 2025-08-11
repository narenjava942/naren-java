flowchart TD

A[Customer Transaction] --> B[Check Transaction Amount]
B -->|> $100| C[Points = 2 × (Amount - 100) + 1 × 50]
B -->|Between $50 and $100| D[Points = 1 × (Amount - 50)]
B -->|≤ $50| E[Points = 0]
C --> F[Store Points in DB]
D --> F
E --> F
F --> G[Aggregate Points by Month & Customer]
G --> H[Return via REST API]


Project structure 

src
└── main
├── java
│   └── com.example.rewards
│       ├── controller  # REST API endpoints
│       ├── service     # Business logic
│       ├── repository  # Database access
│       ├── model       # Entities
│       └── RewardsProgramApplication.java
└── resources
├── data.sql        # Sample data
├── schema.sql      # Table definitions
└── application.properties


Features
Calculate reward points from recorded transactions.

Monthly breakdown & total reward points per customer.

RESTful API endpoints for retrieval.

Preloaded in-memory H2 database for demo.

Modular service layer for easy extension.

Tech Stack
Java 17+

Spring Boot 3+

Spring Web

Spring Data JPA

H2 Database (in-memory)

Lombok (reduce boilerplate)

Maven (build tool)

How to Run
Clone Repository

bash
Copy
Edit
git clone https://github.com/your-username/rewards-program.git
cd rewards-program
Build & Run

bash
Copy
Edit
mvn spring-boot:run
Access H2 Console

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Test API

Visit: http://localhost:8080/api/rewards
=======
A[Client Request] --> B[REST Controller: /api/rewards]
B --> C[RewardService]
C --> D[Fetch Customer from H2 DB]
C --> E[Fetch Transactions in Date Range]
E --> F[Calculate Points per Transaction]
F --> G[Group Points by Month]
G --> H[Sum Total Points]
H --> I[Create Response DTO]
I --> J[Send JSON Response to Client]

