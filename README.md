# ZenWave Example

## ZenWave SDK

### Install ZenWave

```shell
jbang alias add --fresh --name=zw release@zenwave360/zenwave-sdk
```

or if you prefer to use the latest **snapshot** versions:

```shell
jbang alias add --fresh --name=zw snapshots@zenwave360/zenwave-sdk
```

### Starting Docker Infrastructure

```shell
cd src/main/docker
./start-all-dependencies.sh
```

#### JDL To AsyncAPI

Generate AsyncAPI definition from JDL entities:

- One channel for each entity update events
- Messages and payloads for each entity Create/Update/Delete events (AVRO and AsyncAPI schema)

https://zenwave360.github.io/zenwave-sdk/plugins/jdl-to-asyncapi/

```shell
jbang zw -p io.zenwave360.sdk.plugins.JDLToAsyncAPIPlugin \
    includeCommands=false \
    specFile=src/main/resources/model/orders-model.jdl \
    schemaFormat=schema \
    annotations=aggregate \
    payloadStyle=stateTransfer \
    targetFile=src/main/resources/model/asyncapi.yml
```

#### API-First Maven Plugins

There are two Maven Plugins for API-First code generation:

- OpenAPI Generator Maven Plugin: https://github.com/ivangsa/spring-boot-mongodb-elasticsearch-kafka-example/blob/main/examples/spring-boot-mongodb-kafka-example/pom.xml#L151
- ZenWave Maven Plugin for AsyncAPI (spring-cloud-streams3 and jsonschema2pojo): https://github.com/ivangsa/spring-boot-mongodb-elasticsearch-kafka-example/blob/main/examples/spring-boot-mongodb-kafka-example/pom.xml#L167

Use the following command to generate OpenAPI and AsyncAPI api-first code:

```shell
mvn clean generate-sources test-compile
```

#### AsyncAPI Command Adapters


```shell
jbang zw -p io.zenwave360.sdk.plugins.SpringCloudStreams3AdaptersPlugin \
    specFile=src/main/resources/model/asyncapi.yml \
    role=provider \
    style=imperative \
    basePackage=io.zenwave360.example \
    modelPackage=io.zenwave360.example.core.domain.events \
    consumerApiPackage=io.zenwave360.example.adapters.commands \
    adaptersPackage=io.zenwave360.example.adapters.commands \
    targetFolder=.
```

#### AsyncAPI Commands Tests


```shell
jbang zw -p io.zenwave360.sdk.plugins.SpringCloudStreams3TestsPlugin \
    specFile=src/main/resources/model/asyncapi.yml \
    role=provider \
    style=imperative \
    basePackage=io.zenwave360.example \
    consumerApiPackage=io.zenwave360.example.adapters.commands \
    modelPackage=io.zenwave360.example.core.domain.events \
    targetFolder=.
```
or

```shell
mvn zenwave-sdk:generate@consumer-tests
```
