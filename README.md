# Mobile Test Automation Lab

This repository is a mobile test automation lab using Java, Appium, TestNG, Maven, and Allure, integrated with GitHub Actions for CI/CD. It supports running tests on both local Android emulators and cloud devices via Sauce Labs.

---

## 📱 Project Overview

This project provides a complete and extensible structure to automate tests for Android applications. It includes local and remote (cloud) execution flows, automated report generation, and integration with GitHub Pages.

---

## 🔧 Technologies Used

- Java 17  
- Maven  
- Appium  
- TestNG  
- Allure Reports  
- GitHub Actions  
- Sauce Labs  

---

## Design Patterns

## Design Patterns Used

This project follows clean architecture principles and applies several design patterns to improve readability, maintainability, and scalability.

### 1. Page Object Pattern
Encapsulates page structure and user interactions, promoting reusability and maintainability in UI tests.

- **Example**: [`CalculatorPage.java`](./src/main/java/com/clarkewerton/page_object/CalculatorPage.java)

### 2. Factory Design Pattern
Responsible for abstracting the creation of driver instances depending on platform and configuration.

- **Example**: [`DriverFactory.java`](./src/main/java/com/clarkewerton/driver/DriverFactory.java)

### 3. Singleton Design Pattern
Ensures a single instance of critical classes like configuration readers or driver managers.

- **Examples**:
  - [`PropertyManager.java`](./src/main/java/com/clarkewerton/utils/PropertyManager.java)
  - [`DriverManager.java`](./src/main/java/com/clarkewerton/driver/DriverManager.java)

### 4. Facade Design Pattern
Simplifies complex UI interactions by exposing higher-level methods in the Page Object classes.

- **Example**: [`CalculatorPage.java`](./src/main/java/com/clarkewerton/page_object/CalculatorPage.java)

### 5. Strategy Design Pattern *(prepared for future use)*
While not fully implemented, the project structure supports adding this pattern to handle different execution strategies or platform-specific logic dynamically.


## 📁 Project Structure

```
mobile-test-automation-lab/ ├── .github/ # GitHub workflows (CI, actions) │ ├── app/ # APKs or app-related files │ ├── docs/ # Documentation and resources │ ├── reports/ # Generated reports (e.g., Allure) │ ├── screenshots/ # Screenshots captured during tests │ ├── src/ │ └── main/ │ └── java/ │ └── com/ │ └── clarkewerton/ │ ├── android/ # Test classes for Android app │ │ └── CalculatorTest.java │ │ │ ├── constants/ # Constants used across the project │ │ └── AppiumConfigConstants.java │ │ │ ├── devicefarm/ # LambdaTest support classes │ │ └── LambdaTestCapabilities.java │ │ │ ├── driver/ # Driver Factory and Driver Manager │ │ ├── DriverFactory.java │ │ └── DriverManager.java │ │ │ ├── enums/ # Enum definitions │ │ └── PlatformType.java │ │ │ ├── page_object/ # Page Object classes for UI interactions │ │ └── CalculatorPage.java │ │ │ ├── test/ # Base test setup │ │ └── BaseTest.java │ │ │ └── utils/ # Utility classes │ ├── CapabilitiesLoader.java │ └── PropertyManager.java │ ├── .gitignore ├── pom.xml # Maven configuration └── README.md

```
---

## 🚀 How to Run Tests Locally

### Prerequisites

- Java 17 installed  
- Maven installed  
- Android SDK and emulator configured  
- Appium installed and running  

### Steps

```bash
# Clone the repository
git clone https://github.com/clark-ewerton/mobile-test-automation-lab.git
cd mobile-test-automation-lab

# Install dependencies
mvn install -DskipTests

# Start Appium server
chmod +x ./scripts/prepareAppiumServer.sh
./scripts/prepareAppiumServer.sh

# Start the emulator
chmod +x ./scripts/start_emulator_2.sh
./scripts/start_emulator_2.sh

# Upload APK to the device
chmod +x ./scripts/uploadAPK.sh
./scripts/uploadAPK.sh

# Run TestNG test suite
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng-local.xml

# (Optional) Kill emulators after test execution
adb -s emulator-5554 emu kill || true
adb -s emulator-5556 emu kill || true

# View Allure report
mvn allure:serve
```

## 🧪 GitHub Actions CI/CD
The workflow file .github/workflows/appium-cicd-test.yml includes three jobs:

run-android-tests-remote: runs tests on a local emulator on GitHub-hosted runners.

run-android-tests-saucelabs: runs tests in Sauce Labs.

deploy-report: generates an Allure report and publishes it to GitHub Pages.

Allure results are stored as artifacts and published after every execution, regardless of test results.

## 📊 Allure Reports
After every test execution, Allure reports are generated and published to:

🔗 https://clark-ewerton.github.io/mobile-test-automation-lab

## 🌟 Contributing
Contributions are welcome!
Feel free to open issues, fork the repository, and submit pull requests.

If you find this project useful, please consider giving it a star to help increase its visibility.

## 📄 License
This project is licensed under the MIT License.
See the LICENSE file for more details.
