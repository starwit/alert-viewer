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
