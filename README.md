# Selenium Automation Framework 2025

A comprehensive, enterprise-grade test automation framework built with Selenium WebDriver, TestNG, and Maven. Features dual reporting capabilities, cross-browser testing, and flexible configuration management.

## 🚀 Framework Features

- **Cross-Browser Testing** - Chrome, Firefox support
- **Dual Reporting System** - ExtentReports & ReportPortal integration
- **Page Object Model** - Clean, maintainable page structure
- **Retry Mechanism** - Automatic retry for flaky tests
- **Screenshot Management** - Automated capture and attachment
- **Headless Execution** - Headless browser support

## 📁 Project Structure

```
src/
├── main/java/com/practice/web/
│   ├── config/              # Configuration management
│   ├── constants/           # Framework and page constants
│   ├── dataproviders/       # Test data providers
│   ├── driver/             # WebDriver factory and management
│   ├── enums/              # Type-safe enumerations
│   ├── exceptions/         # Custom exception classes
│   ├── listener/           # TestNG listeners
│   ├── loggers/            # Logging implementations
│   ├── pages/              # Page Object Model classes
│   ├── retry/              # Test retry mechanisms
│   └── utils/              # Utility classes
└── test/
    ├── java/               # Test classes
    └── resources/          # Test resources and configuration
```

## 🛠️ Setup & Installation

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox browsers installed
- **ReportPortal Server** (required for REPORT_PORTAL option)

### ReportPortal Setup
ReportPortal installation is **required** if you plan to use `REPORT_PORTAL` reporting:

1. **Docker Installation** (Recommended):
```bash
# Download ReportPortal Docker Compose
curl -LO https://raw.githubusercontent.com/reportportal/reportportal/master/docker-compose.yml

# Start ReportPortal services
docker-compose -p reportportal up -d

# Access ReportPortal UI
# URL: http://localhost:8080
# Default credentials: superadmin/erebus
```

2. **Configuration**:
   - Update `src/test/resources/reportportal.properties` with your ReportPortal server details
   - Create a project in ReportPortal UI
   - Configure API token and project settings

### Installation
```bash
# Clone the repository
git clone <repository-url>
cd SeleniumAutomationFwkPractice2025

# Install dependencies
mvn clean install

# Run sample tests
mvn clean test -Psmoke -DreportType=EXTENT_REPORT -Dheadless=false
```

## ⚙️ Configuration

### Basic Configuration
Update `src/test/resources/config/config.properties`:
```properties
# Application settings
app.url=https://your-app.com
timeout.implicit=10
timeout.explicit=30
```

### Environment Configuration
Framework configuration is handled through:
- **System Properties**: Via Maven `-D` parameters (recommended approach)
- **Configuration Files**: `src/test/resources/config/config.properties`
- **TestNG XML**: Browser and test suite configuration

**Note**: Environment variables are not used for primary configuration in this framework.

## 🧪 Running Tests

### Supported Commands
This framework supports **only** the following Maven command patterns:

```bash
# ExtentReports with headed browser (local development)
mvn clean test -Psmoke -DreportType=EXTENT_REPORT -Dheadless=false

# ReportPortal with headless browser (CI/CD)
mvn clean test -Psmoke -DreportType=REPORT_PORTAL -Dheadless=true
```

### Command Parameters Explained

#### Required Parameters:
- **`clean`** - Cleans previous build artifacts
- **`test`** - Executes the test phase
- **`-Psmoke`** - Activates the Maven smoke profile (defines test suite via TestNG XML)

#### Framework Parameters:
- **`-DreportType`** - Controls which reporting system to use:
  - `EXTENT_REPORT` - Generates local HTML reports (recommended for development)
  - `REPORT_PORTAL` - Sends results to ReportPortal server (requires ReportPortal setup)

- **`-Dheadless`** - Controls browser display mode:
  - `true` - Runs browser in headless mode (no GUI, faster execution, ideal for CI/CD)
  - `false` - Runs browser in headed mode (visible browser window, better for debugging)

#### Browser Selection:
- **Browser type** is configured via TestNG XML files (not Maven parameters)
- Cross-browser testing is handled through the smoke profile configuration
- The framework automatically selects Chrome or Firefox based on XML configuration

### Usage Scenarios:

#### Local Development & Debugging:
```bash
mvn clean test -Psmoke -DreportType=EXTENT_REPORT -Dheadless=false
```
- **Use case**: Writing and debugging tests locally
- **Browser**: Visible browser window for step-by-step observation
- **Reports**: HTML reports generated in `target/extent-reports/`

#### Headless Execution:
```bash
mvn clean test -Psmoke -DreportType=REPORT_PORTAL -Dheadless=true
```
- **Use case**: Faster test execution without browser GUI
- **Browser**: Headless mode for resource-efficient execution
- **Reports**: Results sent to ReportPortal dashboard

## 📊 Reporting

### ExtentReports
- **Purpose**: Local development and debugging
- **Output**: HTML reports in `target/extent-reports/`
- **Features**: Screenshots, test steps, execution timeline

### ReportPortal
- **Purpose**: Enterprise team collaboration
- **Output**: Centralized dashboard
- **Features**: Historical trends, analytics, team visibility

### Screenshots
- **Auto-capture**: On test failure
- **Manual capture**: Via `LoggerUtils.attachScreenShot()`
- **Storage**: `target/artifacts/screenshots/`

## 🔧 Framework Parameters

### Supported Maven Parameters
This framework supports **only** these specific parameters:

| Parameter | Values | Required | Description |
|-----------|--------|----------|-------------|
| **`-Psmoke`** | smoke | ✅ **Yes** | Maven profile that activates the test suite |
| **`-DreportType`** | `EXTENT_REPORT`, `REPORT_PORTAL` | ✅ **Yes** | Report generation type |
| **`-Dheadless`** | `true`, `false` | ✅ **Yes** | Browser display mode |

### Parameter Details

#### `-Psmoke` (Maven Profile)
- **Purpose**: Activates the predefined test suite configuration
- **Configuration**: Defined in `pom.xml` and links to TestNG XML files
- **Browser Selection**: Handled via TestNG XML parameters (not Maven)
- **Test Scope**: Determines which tests to execute

#### `-DreportType` (Reporting System)
- **`EXTENT_REPORT`**: 
  - Local HTML reports for development
  - No external dependencies required
  - Reports saved to `target/extent-reports/`
- **`REPORT_PORTAL`**: 
  - Enterprise reporting dashboard
  - **Requires ReportPortal server installation**
  - Real-time test execution monitoring

#### `-Dheadless` (Browser Mode)
- **`true`**: Browser runs without GUI (invisible), faster execution, screenshots still work
- **`false`**: Browser window visible during execution, better for debugging


## 📝 Test Development

### Creating Page Objects
```java
@Component
public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameField;
    
    public LoginPage enterUsername(String username) {
        SeleniumUtils.sendKeys(usernameField, username);
        LoggerUtils.info("Entered username: " + username);
        return this;
    }
}
```

### Writing Test Classes
```java
public class LoginTest extends BaseTest {
    
    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        new LoginPage()
            .enterUsername(username)
            .enterPassword(password)
            .clickLogin()
            .verifySuccessfulLogin();
    }
}
```

### Using Logging
```java
// Info logging
LoggerUtils.info("Starting test execution");

// Error logging with screenshot
LoggerUtils.error("Login failed");
LoggerUtils.attachScreenShot("login_failure", screenshotFile);

// Browser actions
LoggerUtils.browserAction("CLICK", "Login Button");
```

## 🔄 Test Retry Configuration

### Automatic Retry
- **Failed tests**: Automatically retry up to 2 times
- **Configuration**: `RetryAnalyzer.java`
- **Scope**: Applied via `RetryTransformer.java`

### Custom Retry
```java
@Test(retryAnalyzer = RetryAnalyzer.class)
public void flakyTest() {
    // Test implementation
}
```


## 🔍 Troubleshooting

### Common Issues

#### Browser Not Starting
```bash
# Check browser installation
which chrome
which firefox

# Update browser drivers
mvn clean install
```

#### Screenshots Not Capturing
```bash
# Verify screenshot directory
ls -la target/artifacts/screenshots/

# Check headless configuration
mvn test -Dheadless=true -DenableScreenshots=true
```

#### Report Not Generating
```bash
# Verify report type parameter
mvn test -DreportType=EXTENT_REPORT

# Check output directory
ls -la target/extent-reports/
```

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📞 Support

For questions and support:
- Create an issue in the repository
- Check the troubleshooting section
- Review the configuration examples

## 🏗️ Framework Architecture

This framework follows enterprise-grade design patterns:
- **Factory Pattern**: Driver and Logger creation
- **Strategy Pattern**: Multiple reporting implementations
- **Page Object Model**: Clean page structure
- **Builder Pattern**: Configuration management
- **Observer Pattern**: Test lifecycle listeners

Built with ❤️ for reliable, scalable test automation.
