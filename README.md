# Time-Checker v1.0.0
An scalable pseudo springboot application that runs on K8 cluster

## Higher Level Microservices Architecture
<img alt="load dashboard" src="https://github.com/rasika/time-checker/blob/master/doc/images/architecture.png?raw=true" width="640" />

## Getting Started
### Pre-requisites:
- Java8
- Docker `v20.10.2` or higher
- Kubernetes `v1.19.3` or higher

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

3. Undo RollOut K8 Deployment: You can scale up/down base your need using;
```
kubectl rollout undo deploy time-checker-deployment
```

## Monitoring K8 Deployment
This application is already configured with Prometeus and Grafana using K8 artifacts. You need only to import recommended dashboard(or any dashboard) with few clicks to make this work.
1. Visit http://localhost:31002/login and login with default user credentials `admin/admin`
2. Visit http://localhost:31002/dashboard/import and **load** dashboard with id `13625`([preview](https://grafana.com/grafana/dashboards/13625))
&nbsp;<img alt="load dashboard" src="https://github.com/rasika/time-checker/blob/master/doc/images/load.png?raw=true" width="640" />

3. Select datasources as `promethius` for both dropdowns and **import** dashboard
&nbsp;<img alt="import dashboard" src="https://github.com/rasika/time-checker/blob/master/doc/images/import.png?raw=true" width="640" />

4. Enjoy the dashboard!
![dashboard preview](https://github.com/rasika/time-checker/blob/master/doc/images/dashboard.png?raw=true)

## Tests & Code Coverage
- **JUnit Tests** written for the SpringBoot application functionality can be found in package [src/test/java/com/example/demo/](https://github.com/rasika/time-checker/blob/master/src/test/java/com/example/demo/)
- **Test-coverage** report is available upon command `./gradelw test` and reports will be generated in `build/reports/tests/test/index.html` path.
- **Code quality** checks are assesed with spot-bugs and reports are generated in `build/reports/spotbugs/main.html` path.

## Contributions
Feel free to contribute into this repository. There are areas such as CI/CD improvements that can be done into the build flow to make the deployment flawless.
Git PRs are warmly welcome!

## License
This code is distributed under Apache license 2.0.
