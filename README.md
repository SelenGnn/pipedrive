This project includes login to Pipedrive test automation scenario by using Selenium and Cucumber.

Test scenarios are described in Gherkin format in the feature files located here ./src\test\resources\features

## Installation ##

You need to have [Java 8 JDK]

To run the tests locally with Chrome, install ChromeDriver

To install all dependencies, run

```console
./gradlew build --refresh-dependencies
```


Tests will run on Chrome.


## Dependencies
* *[selenium](https://www.selenium.dev/)*
* *[webdrivermanager](https://github.com/bonigarcia/webdrivermanager)*