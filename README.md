-----------------------------------------------------------------------------------------------------------------------------
1. System requirements
-----------------------------------------------------------------------------------------------------------------------------

This project was developed with the following tools, and these are needed to run/improve it.

- Java 1.8 update 92
- Maven 3.3.9
- Eclipse 4.5.2

This project was created based on the following Java technologies:

- Spring Boot v1.4.0
- Spring Framework v4.3.27
- Spring Cloud Consul v1.0.2
- Spring HATEOAS v0.20.0
- Apache Tomcat v8.0.36
- HashiCorp Consul v0.7.0
- Hazelcast v3.6.4

This project supports the following databases:

- MySQL v5.7
- PostgreSQL v9.5

For unitary and integration testings, the following in-memory database was used:

- HSQL Database Engine v2.3.3

-----------------------------------------------------------------------------------------------------------------------------
2. Setup
-----------------------------------------------------------------------------------------------------------------------------

2.1 - Java

- Download and install the Java Development Kit 1.8.0_92.
- Set the JAVA_HOME environment variable pointing to the JDK installation folder (Optional - needed for running Java by command line).
- Set the JAVA_OPTS environment variable with the following value -> -Xms256m -Xmx1024m.

2.2 - Maven

2.2.1 - Create your settings.xml file

- Create a settings.xml file in the folder ${USER_HOME}/.m2 with this block.

```
#!xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
	<profiles>
		<profile>
			<id>personal</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<properties>
			</properties>
		</profile>
	</profiles>
</settings>
```

- The following properties must be set

Property | Value
--- | ---
*orion.consul.host* | **Your Consul server host name**
*orion.consul.port* | **Your Consul server port**
*orion.database.host* | **Your database server host name**
*orion.database.idle* | **Number of idle connections**
*orion.database.max* | **Number of maximum connections**
*orion.database.min* | **Number of minimum connections**
*orion.keystore.path* | **Path for the keystore used by the platform**
*orion.keystore.password* | **Password for the keystore used by the platform**
*orion.keystore.type* | **Type of keystore**
*orion.truststore.path* | **Path for the truststore used by the platform**
*orion.truststore.password* | **Password for the truststore used by the platform**
*orion.truststore.type* | **Type of truststore**

2.2.2.1- Using the Maven wrapper created by Takari (https://github.com/takari/maven-wrapper).

- Open a terminal and go to the root project folder.
- Run the following command: mvnw clean install.
- This will download the Maven 3.3.9 distribution and build all the artifacts.

2.2.2.2 - Using an external installation

- Download and unzip Maven 3.3.9.
- Set the MAVEN_HOME environment variable pointing to the Maven installation folder.
- Open a terminal and go to the root project folder.
- Run the following command: mvn clean install.

2.3 - Eclipse

- Download and install Eclipse 4.5.2
- In Windows -> Preferences -> Java -> Installed JREs, add the JDK 1.8 previously installed.
- In Windows -> Preferences -> Java -> Installed JREs -> Execution Environments, associate the JavaSE-1.8 environment with the 1.8 JRE.
- In Windows -> Preferences -> Maven -> Installations, add the Maven previously installed (if you chose the external installation).

2.4 - Source code

- Clone the Github repository from this URL (https://github.com/montdeo/kudos.git) into your computer.
- Open Eclipse.
- Add the local repository into the IDE.
- Import the platform projects as Maven projects.

2.5 - Artifact generation

2.5.1 - Using the Maven wrapper

- Open a terminal and go to the root project
- Run the following command: mvnw clean install -P\<testing|mysql|postgresql>

2.5.2 - Using the Eclipse IDE

- In Run > Run Configurations, create a new Maven build.
- Select the root project as the base directory
- Goals: clean install
- Profiles: testing|mysql|postgresql

-----------------------------------------------------------------------------------------------------------------------------
3. Database setup
-----------------------------------------------------------------------------------------------------------------------------

- Install the database server you choose (MySQL or PostgreSQL are supported for now).
- Update in your settings.xml file the 'orion.database.*' properties to point to your database host.
- Create the database user and schema using the provided SQL script found in the '/sql' folder.  
- Open a terminal and go to the root project
- Run the following commands: 

   mvnw clean install -P\<mysql|postgresql>

   java -jar \<module>/target/orion-<module>-<version>.jar
   
- The module will start, connect to the database, and create all the needed database elements.

-----------------------------------------------------------------------------------------------------------------------------
4. Testing
-----------------------------------------------------------------------------------------------------------------------------

- Open a terminal and go to the root project
- Run the following commands: 

   mvnw install -Ptesting
   
- This will execute all the unitary and integration tests. 

-----------------------------------------------------------------------------------------------------------------------------
6. Quality Control
-----------------------------------------------------------------------------------------------------------------------------

- In your settings.xml file, add this new property (pointing to the Sonar URL).
	
Property | Value
--- | ---
*sonar.host.url* | **Your Sonar server URL (eg. http://localhost:9000)**
	

- Open a terminal and go to the root project
- Run the following commands: 

   mvnw install -Pquality
   
- This will execute the Sonar Maven plugin and save the analysis into the configured Sonar.

-----------------------------------------------------------------------------------------------------------------------------
7. Integration with Consul
-----------------------------------------------------------------------------------------------------------------------------

- Download Consul v0.7.0.
- Start Consul by executing the following command:

consul agent -bootstrap -ui -config-dir \<ROOT_PROJECT>/files

- This will start the Orion DC with the service checks for all the modules. Consul will check the platform modules' 
  health status by pinging the following URL

   - https://\<mvn.server.address>:\<mvn.mgmt.port>/manage/health

-----------------------------------------------------------------------------------------------------------------------------
8. Known issues
-----------------------------------------------------------------------------------------------------------------------------

8.1 - Get https://\<module.server.host>:\<module.mgmt.port>/manage/health: x509: certificate signed by unknown authority

- Add the SSL certificate to your server's trusted certificate store