# Spring Batch Processing

## Overview

This project was created to practice batch processing scenarios using Spring Batch. The application reads transaction
records from a CSV file, applies business rules, and stores the results in a relational database.

## Technologies

* Java 21
* Spring Boot 3
* Spring Batch 5
* Spring Data JPA
* PostgreSQL
* Maven
* Lombok

## Features

* Read transactions from CSV files
* Validate transaction data
* Ignore duplicate transactions
* Detect suspicious transactions
* Persist valid transactions to PostgreSQL
* Generate processing statistics at the end of the job

## Getting Started

### Prerequisites

To run this project locally, ensure you have:

* [Java 21](https://docs.oracle.com/en/java/javase/21/)
* [Maven 3.9+](https://maven.apache.org/guides/)
* [Git](https://git-scm.com/doc)
* [Docker](https://docs.docker.com/get-docker/)
* [Docker Compose](https://docs.docker.com/compose/)

### Running the application

Run the following commands on the project root folder:

```
mvn clean package
docker compose up --build
```
