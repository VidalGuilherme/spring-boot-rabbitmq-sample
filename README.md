# Spring Boot RabbitMQ Example - Microservice for sending email using Spring Email and the Gmail SMTP server.
This repository contains a simple Microservice for sending email using Spring Email and the Gmail SMTP server. The aim of this repository is to practice and share how you can build a Microservice using Java Spring and RabbitMQ.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
$ git clone git@github.com:VidalGuilherme/spring-boot-rabbitmq-sample.git
```

2. Install dependencies with Maven
3. Run with docker-compose (docker-compose up -d)

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080/

## Database
The project uses PostgresSQL as the database.
Spring boot to automatically load my database schema when I start it up.

```markdown
spring.jpa.generate-ddl=true
```
## RabbitMQ
The project uses RabbitMQ as MQTT broker.
Automatically load exchanges, queues and routingKey when I start it up.

#### To Management Console
http://localhost:15672
```markdown
default user: guest
default password: guest
```

## API Endpoints
The API provides the following endpoints:

### SEND EMAIL
```markdown
POST /send-email

BODY
{
"ownerRef":"12345",
"emailFrom":"*****@gmail.com",
"emailTo":"*****@gmail.com",
"subject":"Subject",
"text":"Email Body"
}
```

### SEND EMAIL QUEUE
```markdown
POST /send-email/queue

BODY
{
"ownerRef":"12345",
"emailFrom":"*****@gmail.com",
"emailTo":"*****@gmail.com",
"subject":"Subject",
"text":"Email Body"
}
```

## Docker

You can run a database for this project with Docker by running the following command:

```bash
$ docker-compose up -d
```

To install Docker locally you can [click here](https://www.docker.com/products/docker-desktop/).