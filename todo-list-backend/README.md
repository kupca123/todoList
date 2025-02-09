# Todo List application

[//]: # (TODO JAL: doplnit popis aplikace)

## 1 Prerequisites
* Java 21
* PostgreSQL database


### 1.1 Docker and Docker Compose

For running this project, you can use Docker and Docker Compose.

1. **Docker**: You can install Docker from the [official website](https://docs.docker.com/get-docker/).
2. **Docker Compose**: You can install Docker Compose from
   the [official website](https://docs.docker.com/compose/install/).

    **OR**
3. **Docker Desktop**: You can install Docker Desktop from the [official website](https://www.docker.com/products/docker-desktop).

### 1.2 Java 21

You will need to have **Java 21** installed on your machine and set up in your IDE.
I use the Amazon Corretto distribution of OpenJDK 21.

You can download Amazon Corretto JDK 21 from [Amazon Corretto website](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html).

## 2 Running the Application
Next steps describe how to run the application using Docker Compose.

### 2.1 Create Postgres container
1. Open command line and navigate to the `docker-local` directory of the root project.
    ``` bash
    cd docker-local
    ```
2. Run the following command to builds, creates, starts, and attaches to container for postgres.
    ``` bash
    docker-compose up
    ```