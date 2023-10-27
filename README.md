# ProcessorDataRestAPI
## Description

This project aims to create and develop a Java REST API for fetching and presenting information about available processors in a third-party Web Service. In particular, the developped API retreive and display statistical datails including the minimum, maximum and average calues derived from the CPU time percent historical data points. 

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)

## Prerequisites

These are the software, libraries, or tools that need to be installed or configured before running this project:
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or above
- [Maven 4.0.0](https://maven.apache.org/download.cgi)


## Getting Started
### Install all the necessary prerequisites.
### Configure Java environment variable
#### Windows
1. On windows search bar, search for `Environment variables` 
2. Select `Environment variables` 
3. Select `New User Variable` 
4. Variable Name : `JAVA_HOME`
5. Variable Value : `Path_to_the_JDK` (can be found at C:\Program Files\Java\<JDK-VERSION>)

#### Linux
After installing JDK17 or OpenJDK 17 (or above), you need to configure the Java version and the Java home as follows:
```bash
update-alternatives --config java
# then choose the right version
```
Also, set the JAVA_HOME environment variable as follows:
```bash
#JDK is usually found in usr/lib/jvm/<JDK-VERSION>
export JAVA_HOME=PATH-TO-JDK 
```

### Clone this repository using this command:
```bash 
git clone https://github.com/Cherfalyes/ProcessorDataRestAPI.git
```
### Importing project
In your IDE, import the project as a maven project, then run the `ProcessorDataApplication.java`.

### Generating Javadoc
To generate javadoc, you can either do it from your IDE or using command line.
#### Eclipse
1. click on File -> Export
2. Select Java -> Javadoc
3. Select all the packages you want to create Javadoc for -> finish

#### Linux
Using command lines, go to the project file at the same level as pom.xml and execute the following command:
```bash
mvn javadoc:javadoc
```

### Creating Jar from the project
#### Eclipse
1. Open your pom.xml file
2. Right click -> Run as -> Maven build...
3. Goals: `package` -> Run
#### Linux
Using command lines, go to the project file at the same level as pom.xml and execute the following command:
```bash
mvn generate-sources
```

## Usage
After running the application (either by running it from an IDE as a Java application or from a jar file using `java -jar JAR-NAME.jar` from command line), go to the URL:
```url
http://localhost:8080/ENDPOINT
// Like
http://localhost:8080/processors/CPUprcrProcessorTimePercent/max?history=10
```

This REST API handles three endpoints that provide data about a list of processors retrieved from a webservice. These endpoints are: 
> GET /processors/CPUprcrProcessorTimePercent/max?history={value}

> GET /processors/CPUprcrProcessorTimePercent/min?history={value}

> GET /processors/CPUprcrProcessorTimePercent/avg?history={value}

Where {value} represents the processor data history sample size. 

### Examples
1. This request will return the `maximum CPU Time percent` in a sample of 100 records.
    ```url
    GET /processors/CPUprcrProcessorTimePercent/max?history=100
    ```

2. This request will return the `minimum CPU Time percent` in a sample of 50 records.
    ```url
    GET /processors/CPUprcrProcessorTimePercent/min?history=50
    ```

3. This request will return the `average CPU Time percent` in a sample of 2000 records.
    ```url
    GET /processors/CPUprcrProcessorTimePercent/avg?history=2000
    ```
