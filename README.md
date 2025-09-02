# alert-viewer

This is a demo project to simulate a rest endpoint. It is used to receive alerts triggered by action in the ai-cockpit project. It demonstrate how a third party system can be included in processing.

## How to deploy
Helm chart for application can be found [here](https://hub.docker.com/r/starwitorg/alert-viewer-chart).

## How to build

1) Go to `webclient/app` and install the frontend applications dependencies

    ```bash
    cd webclient/app
    npm install
    ```

2) Build the project with

    ```bash
    mvn clean install -P frontend

3) Start project

    ```bash
    java -jar application/target/application-xxx.jar
    ```

## How to test
Following call will create a test alert:
```bash
    curl http://localhost:8080/api/alerts/test/39.970750433126994/-86.12693383495157
```

## Contact & Contribution

The “KI-Cockpit” (AI Cockpit) project was funded by the Federal Ministry of Labor and Social Affairs.

<img src="foerderlogo.png" alt="BMAS Logo" style="width:33%; height:auto;">

## License

This software is licensed under AGPL and license text can be found at: https://github.com/starwit/alert-viewer/blob/main/LICENSE

More info about Starwit can be found here: https://starwit-technologies.de/


