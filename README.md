[![Release](https://jitpack.io/v/mstump/gatling-kafka.svg)](https://jitpack.io/#mstump/gatling-kafka)

An unofficial [Gatling](http://gatling.io/) 2.3 stress test plugin
for [Apache Kafka](http://kafka.apache.org/) 1.1.1 protocol.

This plugin supports the Kafka producer API only
and doesn't support the Kafka consumer API.

## Usage

### Cloning this repository

    $ git clone https://github.com/mstump/gatling-kafka.git
    $ cd gatling-kafka

### Creating a jar file

Create a jar file:

    $ ./gradlew assemble

### Incorporating into your Gradle project

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
    
...

	dependencies {
	        implementation 'com.github.mstump:gatling-kafka:master-SNAPSHOT'
	}

```

## License

Apache License, Version 2.0
