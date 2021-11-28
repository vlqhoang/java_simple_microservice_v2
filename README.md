# Java Sample Microservices
This project is to demo a simple microservice architecture using Spring Cloud and all services are Docker containerized. 

## How to use
* **With Docker**.
```bash 
cd docker/ && docker compose up
```
* **With Kubernetes**.
  * Refer to ./kubernetes for more information.

## Techs
This project has the following features:

* **Netflix Zuul API Gateway** (intercepting all incoming requests,
  perform logging and distribute requests to services based on **Spring Cloud Route**).
* **Feign + Spring Cloud loadbalancer** as client load balancing (automatically balancing requests
  to all services base on service name registered with Eureka Naming Server).
* **Eureka naming server** (Managing all services info).
* Centralized and distributed tracing server with **Spring could sleuth and Zipkin**.
  Configured to **rabbit-mq server** so that services can send tracing data to Zipkin via queue.
* **Spring cloud config** (Holding configs and distribute them to services).
* **Devtool** live reload.
* Simple service managing mechanism with **Spring Actuator**.
* **Resilience4j** fault tolerance.
* **Dockerized**
* Orchestrated with **Kubernetes**.

## Usage

#### Ports
| Application | Port |
| ------ | ------ |
| Limit Service | 8080, 8081, ...|
| Spring Cloud Config Server | 8888 |
| Currency Exchange Service | 8000, 8001, 8002, ... |
| Currency Calculation Service | 8100, 8101, 8102, ... |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server | 8765 |
| Zipkin Distributed Tracing Server | 9411 |

#### URLs
| Application | URL |
| ------ | ------ |
| Eureka | http://localhost:8761/|
| Zipkin | http://localhost:9411/zipkin/|
| Spring Cloud Config Server | http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev|
| Gateway | http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/100|

License
----
MIT