# Java Sample Microservices
This project is to demo a simple microservice architecture using Spring
and services are Docker containerized and orchestrated by Kubernetes.

## How to use
#### Note: Make sure you have kubernetes setup and running before start using!.

* **First approach with - plaintext kubectl commands**.
```bash 
cd kubernetes
kubectl create deployment currency-exchange --image=vlqhoang/simple-spring-micro-currency-exchange-service:0.1.0-SNAPSHOT
kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000
kubectl scale --replicas=2 deployment currency-exchange
 
kubectl create deployment currency-conversion --image=vlqhoang/simple-spring-micro-currency-conversion-service:0.1.0-SNAPSHOT
kubectl create configmap currency-conversion --from-literal=CURRENCY_EXCHANGE_URI=http://currency-exchange
kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100

curl http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/100
```

* **Second approach - declarative**
```bash
cd kubernetes/currency-exchange-service/script
kubectl apply -f deployment.yaml

cd kubernetes/currency-conversion-service/script
kubectl apply -f deployment.yaml

curl http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/100
```

## Highlight points
* Kubernetes uses probes to check the health of a microservice.
  * **Readiness probe**
    * This is provided by Spring Actuator (>= 2.3)
    * If readiness probe is not successful -> no traffic will be sent
  * **Liveness probe**
    * This is provided by Spring Actuator (>= 2.3)
    * If liveness prob is not successful, pod is restart.
* To check the probes value, use links provided in [_urls.txt_](./urls.txt).
* These probes can be used to ensure availability and no downtime deployment in kubernetes, based on these probes, Kubernetes can determine whether
other services should send a new request to starting / terminating service (pods). Checkout currency_exchange [_deployment.yaml_](./currency-exchange-service/script/deployment.yaml) file.
* To further enhance the availability of a service - for example, the currency-exchange service get lots of traffic, 
kubernetes can help with **autoscaling**
```bash
kubectl autoscale deployment currency-exchange --min=1 --max=5 --cpu-percent=70
```

