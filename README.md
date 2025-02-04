
# VW DIGITAL:HUB

### CHALLENGE
I have followed the [documentation](vwdigitalhub_backend_technical_test.pdf) to accomplish the coding task.

### APPROACH
I have not used any framework, I only created the project using IntelliJ's "New Project" 
option since it was not required. I have used clean architecture to distribute 
the application's responsibilities into Domain, Application, and Infrastructure layers, and
DDD.

* In the Infrastructure layer, I only created the class for data input, which is responsible
for sending the user's input to the application layer.

* In the Application layer, I decided to include a validation that, in this case, 
I understood was not part of the domain logic—for example, ensuring that no required lines 
of information are missing before being accepted by the domain logic.

* In the Domain layer, all the application logic is located. This includes the core entities, 
Square and Robot, as well as the necessary services for the requested process.

### TECHNOLOGY STACK
 - Java 17: The application is developed using Java 17.
 - Gradle: Used for build automation.
 - JUnit 5: The testing framework for unit tests.
 - Mockito 5: For mocking dependencies in unit tests.
 - AssertJ: Provides fluent assertions for better readability.
 - Spotless: A code formatter to enforce a consistent style.
    
### BUILD AND TESTS 
First of all, the platform has to be compiled with the next command, and the test are executed as well
``
./gradlew clean build
``

for more info tests, you can execute the next one
```
./gradlew test --info
```

### WORK WITH THE PLATFORM
Use the platform by command line 

* with Gradle:

    ```
    ./gradlew run --args='"5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM"'
    
    ```
* with Java:
    ```
    java -jar ./build/libs/cleaning_robots-1.0.jar "5 5\\n1 2 N\\nLMLMLMLMM\\n3 3 E\\nMMRMMRMRRM"
    ```
    or
    ```
    java -jar ./build/libs/cleaning_robots-1.0.jar "5 5
    1 2 N
    LMLMLMLMM
    3 3 E
    MMRMMRMRRM"
    ```