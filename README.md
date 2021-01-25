# Time-Checker
An scalable springboot application that runs on K8 cluster

## Getting Started
Pre-requsits:
Java8
Docker `v20.10.2` or higher
Kubernetes `v1.19.3` or higher

### Instructions
1. Clone the repo and navigate into folder;
```
git clone https://github.com/rasika/time-checker
cd time-checker
```

2. Trigger the gradle build;
```
./gradlew clean build
```

3. Deploy your Kubernetes deployment;
```
kubectl create -f deployments/k8-deploy.yaml
```

4.. Invoke the service using following URL;
- To send generic GET request;
```
curl http://localhost:31000
```
- To send as a mobile device;
```
curl -H "User-Agent: Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36" http://localhost:31000
```

## Managing K8 Deployment
1. Scaling Your K8 Deployment: You can scale up/down base you need using;
```
kubectl scale --replicas=3 deploy time-checker-deployment
```

2. RollingOut K8 Deployment: You can scale up/down base you need using;
```
kubectl set image deploy time-checker-deployment time-checker-deployment=time-checker:2.0.0-SNAPSHOT
```

3. Undo RollOut K8 Deployment: You can scale up/down base you need using;
```
kubectl rollout undo deploy time-checker-deployment
```

## License
This code is distributed under Apache license 2.0.
