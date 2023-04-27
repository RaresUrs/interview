### Reference Documentation

This is an aggregator for the TNT Digital API.

- shipment client: This microservice provides shipment data.
- price client: This microservice provides pricing data.
- track client: This microservice provides tracking data.
- aggregation service: This microservice aggregates data from the shipment, price, and track services.

### Prerequisites

To run this project, you need to have the following installed on your machine:

- Java 17
- Maven
- Docker

### Building the service

~~~ 
mvn clean install
~~~

To hit the API, I've attached a Postman collection in the root dir that can be imported directly into your Postman
client
By default, the application runs on port 8081.

### Guides

Completable Futures and WebFlux are two popular Java libraries that enable asynchronous programming.

By using WebFlux, we call each downstream endpoint on separate threads. I created reactive, non-blocking application
that can handle a large number of requests with a
relatively small number of threads.
This is achieved by using an event loop to handle incoming requests and processing
them asynchronously, without blocking the thread.

At the end of the computation, we only then combine the futures and return the aggregate result. Considering this, the
application will respond within 10 seconds for the 99th percentile as part of your SLA.

WebFlux also makes use of the reactive programming paradigm, which emphasizes a non-blocking and event-driven approach
to handling data streams. This allows for efficient use of system resources and reduces the likelihood of blocking
threads, which can lead to performance issues and thread starvation. By using CompletableFuture and other reactive
libraries in combination with WebFlux, we can easily implement asynchronous processing of requests and responses









