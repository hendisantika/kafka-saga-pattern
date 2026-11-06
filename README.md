# kafka-saga-pattern

[![Java CI with Maven](https://github.com/hendisantika/kafka-saga-pattern/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/kafka-saga-pattern/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.4-6DB33F?logo=spring)
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-binder-231F20?logo=apachekafka)

A sample implementation of the **Choreography-based Saga Pattern** using **Spring Boot**, **Spring Cloud Stream** and
**Apache Kafka**. An *Order Service* and a *Payment Service* (running in the same application for simplicity)
coordinate a distributed transaction purely through events — no central orchestrator.

## How it works

```
 Client                Order Service                  Kafka                    Payment Service
   │  POST /order/create     │                           │                            │
   │────────────────────────▶│ save order (ORDER_CREATED)│                            │
   │                         │── OrderEvent ────────────▶│ order-events ─────────────▶│ check user credit limit
   │                         │                           │                            │
   │                         │◀──────────── payment-events ◀── PaymentEvent ──────────│ APPROVED / REJECTED
   │                         │ update order status       │                            │
   │                         │  APPROVED → ORDER_COMPLETED                            │
   │                         │  REJECTED → ORDER_CANCELLED (compensation)             │
```

| Function bean (Spring Cloud Stream) | Type                              | Binding              | Topic            |
|-------------------------------------|-----------------------------------|----------------------|------------------|
| `orderEventPublisher`               | `Supplier<Flux<OrderEvent>>`      | `out-0`              | `order-events`   |
| `orderEventProcessor`               | `Function<OrderEvent, PaymentEvent>` | `in-0` → `out-0`  | `order-events` → `payment-events` |
| `paymentEventConsumer`              | `Consumer<PaymentEvent>`          | `in-0`               | `payment-events` |

Demo data (in memory):

- **Products** — `1` = 100, `2` = 200, `3` = 300
- **Users** — `1`..`5`, each with a credit limit of **1000**. Every approved payment reduces the user's balance, so
  repeated orders eventually get **REJECTED** and the order is **CANCELLED**.

## Tech stack

- Java **25**
- Spring Boot **4.1.2** (WebFlux, Data JPA, DevTools)
- Spring Cloud **2025.1.4** — Spring Cloud Stream + Kafka binder
- Spring Kafka
- H2 in-memory database
- springdoc-openapi (Swagger UI)
- Lombok
- Maven (wrapper included)

## Project structure

```
src/main/java/id/my/hendisantika/kafkasagapattern
├── config/                 # OpenAPI configuration
├── model/                  # Shared DTOs, events (OrderEvent, PaymentEvent) and enums
├── orderservice/           # REST API, JPA entity/repository, order event publisher & payment consumer
└── paymentservice/         # Order event processor (credit check) → PaymentEvent
```

## Prerequisites

- JDK 25
- Docker (to run Kafka locally) or an existing Kafka broker on `localhost:9092`

## Getting started

1. **Start Kafka** (KRaft mode, single node):

   ```shell
   docker run -d --name kafka -p 9092:9092 apache/kafka:latest
   ```

2. **Run the application**:

   ```shell
   ./mvnw spring-boot:run
   ```

   The app runs on <http://localhost:8080>.

3. **Build & test**:

   ```shell
   ./mvnw clean package
   ```

## API

| Method | Endpoint        | Description                |
|--------|-----------------|----------------------------|
| POST   | `/order/create` | Create a purchase order    |
| GET    | `/order/all`    | List all purchase orders   |

Create an order:

```shell
curl -X POST http://localhost:8080/order/create \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "productId": 3}'
```

List orders (status changes from `ORDER_CREATED` to `ORDER_COMPLETED` / `ORDER_CANCELLED` once the payment event is
consumed):

```shell
curl http://localhost:8080/order/all
```

API documentation:

- Swagger UI: <http://localhost:8080/swagger-ui>
- OpenAPI docs: <http://localhost:8080/v3/api-doc>

## CI/CD & dependency management

- **GitHub Actions** ([`maven.yml`](.github/workflows/maven.yml)) builds and tests the project with JDK 25 (Temurin) on
  every push and pull request to `main`, and submits the Maven dependency graph on push to `main`.
- **Dependabot** ([`dependabot.yml`](.github/dependabot.yml)) checks Maven dependencies and GitHub Actions daily
  (05:00 Asia/Jakarta). Updates are grouped (Spring Boot, Spring Cloud, Kafka, springdoc, Maven wrapper, test
  dependencies, minor/patch, GitHub Actions), labelled and use conventional commit prefixes.

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
- Link: [s.id/hendisantika](https://s.id/hendisantika)
