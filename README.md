# craigslist-housing-test-cucumber

## Technologies & tools
Java 11, Cucumber 4, Allure 2, Junit 5, Maven, Lombok

## Run options

#### Run all tests                
mvn test 

#### Run tests with specific tag     
mvn test -Dcucumber.options="--tags @all"

#### Run tests and open allure report 
mvn clean test -Dcucumber.options="--tags @all"; allure serve target/allure-results/

## Parameters

#### Firefox browser
-Dselenide.browser=org.openqa.selenium.firefox.FirefoxDriver

#### Chrome mobile emulation
-Dchromeoptions.mobileEmulation="deviceName=Nexus 5"

#### Headless mode
-Dselenide.headless=true

#### Rerun failing tests
-Dsurefire.rerunFailingTestsCount=2