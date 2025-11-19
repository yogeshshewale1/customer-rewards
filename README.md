# Customer Rewards

This project implements a **Spring Boot REST API** to calculate
**monthly and total reward points** for customers based on their
transactions.

## Business Rules

-   **2 points** for every dollar spent over \$100 in a single
    transaction.
-   **1 point** for every dollar spent between \$50 and \$100 in a
    single transaction.
-   Transactions under \$50 earn **0 points**.

------------------------------------------------------------------------

## Build & Run

**Prerequisites:** - Java 17+ - Maven - Spring Boot 3.1.4 - Mockito
5.14.1

------------------------------------------------------------------------

## Project File and Directory Structure

``` text
customer-rewards/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── rewards/
│   │   │           ├── RewardsApplication.java
│   │   │           ├── config/
│   │   │           │   └── DataLoader.java
│   │   │           ├── controller/
│   │   │           │   └── RewardController.java
│   │   │           ├── service/
│   │   │           │   └── RewardService.java
│   │   │           ├── repository/
│   │   │           │   ├── TransactionRepository.java
│   │   │           │   └── TransactionRepositoryImpl.java
│   │   │           └── model/
│   │   │               ├── RewardResponse.java
│   │   │               └── Transaction.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/
│           └── com/
│               └── rewards/
│                   ├── controller/
│                   │   └── RewardControllerTest.java
│                   └── service/
│                       └── RewardServiceTest.java
└── pom.xml
└── README.md
```

------------------------------------------------------------------------

## Maven Build Commands

``` bash
mvn clean package
mvn spring-boot:run
```

------------------------------------------------------------------------

# API Endpoints

------------------------------------------------------------------------

## **1. Get Rewards for a Single Customer**

-   **URL:** `http://localhost:8080/api/rewards/3`
-   **Method:** `GET`

### ✔ Response Example (Correct JSON)

``` json
{
  "customerId": 1,
  "monthlyPoints": {
    "MARCH": 120
  },
  "totalPoints": 60
}
```

------------------------------------------------------------------------

## **2. Get Rewards for All Customers**

-   **URL:** `http://localhost:8080/api/rewards/all`
-   **Method:** `GET`

### ✔ Response Example (Correct JSON)

``` json
[
  {
    "customerId": 1,
    "monthlyPoints": {
      "JANUARY": 40,
      "FEBRUARY": 25,
      "MARCH": 200
    },
    "totalPoints": 265
  },
  {
    "customerId": 2,
    "monthlyPoints": {
      "JANUARY": 30
    },
    "totalPoints": 30
  },
  {
    "customerId": 3,
    "monthlyPoints": {
      "MARCH": 60
    },
    "totalPoints": 60
  }
]
```

------------------------------------------------------------------------

## Tests

Run:

``` bash
mvn test
```

-   Covers positive, negative, and edge cases\
-   Add more transactions for full coverage

------------------------------------------------------------------------

## Notes

-   Month names derived from `java.time.LocalDate`
-   Handles multiple customers
-   Includes validations & exception handling
-   Easily extendable for new rules
