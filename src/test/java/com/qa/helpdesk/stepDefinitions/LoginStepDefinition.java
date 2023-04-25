package com.qa.helpdesk.stepDefinitions;

import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.ProjectConfigData;
import com.qa.helpdesk.locators.DashboardHeaderLocators;
import com.qa.helpdesk.locators.LoginLocators;
import com.qa.helpdesk.locators.TableAndPaginationLocators;
import com.qa.helpdesk.locators.TicketLocators;
import com.qa.helpdesk.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginStepDefinition {

    @Given("Navigate to helpdesk and login")
    public void navigateAndLogin() {
        try {
            Dotenv env = Dotenv.load();
            DriverAction.waitUntilElementClickable(LoginLocators.loginButton, 10);
            if (DriverAction.isExist(LoginLocators.loginButton)) {
                DriverAction.click(LoginLocators.loginButton, "Login SSO");
                DriverAction.waitUntilElementClickable(LoginLocators.loginEmail, 20);
                DriverAction.typeText(LoginLocators.loginEmail, env.get("EMAIL"));
                DriverAction.click(LoginLocators.submitLoginForm);
                DriverAction.waitUntilElementClickable(LoginLocators.loginPswd, 10);
                DriverAction.typeText(LoginLocators.loginPswd, "Password", "Password entered successfully", env.get("PSWD"));
                DriverAction.click(LoginLocators.submitLoginForm, "Submit");
                DriverAction.waitUntilElementClickable(LoginLocators.rejectPrompt, 5);
                if (DriverAction.isExist(LoginLocators.microsoftLoginPrompt)) {
                    DriverAction.click(LoginLocators.rejectPrompt, "Prompt");
                    DriverAction.waitSec(1);
                }
            } else
                GemTestReporter.addTestStep("Login button", "Login button not found", STATUS.FAIL, DriverAction.takeSnapShot());

            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 10);
            }
            if(DriverAction.isExist(LoginLocators.errorModal)) {
                GemTestReporter.addTestStep("Portal health","Portal is not up and running. Error encountered.",STATUS.ERR,DriverAction.takeSnapShot());
                throw new Exception("Portal is not accessible at the moment");
            }
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 45);
            }

            if (DriverAction.isExist(LoginLocators.loginButton)) {
                DriverAction.waitUntilElementClickable(LoginLocators.loginButton, 20);
                DriverAction.click(LoginLocators.loginButton, "Login SSO");
            }
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(DashboardHeaderLocators.headerButtons("logout")));
            DriverAction.waitUntilElementClickable(DashboardHeaderLocators.headerButtons("logout"), 10);

        } catch (Exception e) {
            if(DriverAction.isExist(LoginLocators.errorModal)) {
                GemTestReporter.addTestStep("Portal health","Portal is not up and running. Error encountered.",STATUS.ERR,DriverAction.takeSnapShot());
            }
            GemTestReporter.addTestStep("Exception Occurred", "Login was unsuccessful", STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @Given("Verify if login is successful")
    public void verifyIfLoginIsSuccessful() {
        try {
            String actualUrl = DriverAction.getCurrentURL();
            String expectedUrl = ProjectConfigData.getProperty("dashboardUrl");
            if (actualUrl.contains(expectedUrl)) {
                GemTestReporter.addTestStep("Login to dashboard", "Login is successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Login to dashboard", "Login was unsuccessful \nActual URL: " + actualUrl + " \nExpected URL: " + expectedUrl, STATUS.FAIL, DriverAction.takeSnapShot());
                throw new RuntimeException("Login unsuccessful");
            }

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }

    @And("Verify if the logged in user name is {string}")
    public void checkLoggedInUser(String name) {
        try {
            String actualEmpName = DriverAction.getElementText(LoginLocators.employeeName);
            if (DriverAction.isExist(LoginLocators.employeeName) && DriverAction.getElementText(LoginLocators.employeeName).equals(name)) {
                GemTestReporter.addTestStep("Verify user", "Employee Name matched, Expected: " + name + " Actual: " + actualEmpName + "", STATUS.PASS, DriverAction.takeSnapShot());
            } else
                GemTestReporter.addTestStep("Verify user", "Employee Name not matched, Expected: " + name + " Actual: " + actualEmpName + "", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }

    }


    @And("Switch to view {string}")
    public void switchToView(String view) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(LoginLocators.viewDropdown));
            DriverAction.waitUntilElementClickable(LoginLocators.viewDropdown, 10);
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.click(LoginLocators.viewDropdown, "View dropdown");
            try {
                DriverAction.click(LoginLocators.getView(view), view);
            } catch (Exception e) {
                if (!DriverAction.isExist(LoginLocators.viewListbox)) {
                    DriverAction.click(LoginLocators.viewDropdown, "View dropdown");
                    DriverAction.click(LoginLocators.getView(view), view);
                } else DriverAction.click(LoginLocators.getView(view), view);
            }
            if (DriverAction.isExist(DashboardHeaderLocators.loaderCover)) {
                CommonUtils.waitUntilElementDisappear(DashboardHeaderLocators.loaderCover, 20);
            }
            DriverAction.waitUntilElementClickable(TicketLocators.createTicket, 10);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @And("Login with invalid domain having email {string}")
    public void loginWithInvalidCredentials(String email) {
        try {
            DriverAction.waitUntilElementClickable(LoginLocators.loginButton, 10);
            if (DriverAction.isExist(LoginLocators.loginButton)) {
                DriverAction.click(LoginLocators.loginButton, "SSO Login");
                DriverAction.waitUntilElementClickable(LoginLocators.loginEmail, 20);
                DriverAction.typeText(LoginLocators.loginEmail, email);
                DriverAction.click(LoginLocators.submitLoginForm);

            } else
                GemTestReporter.addTestStep("Login button", "Login button not found", STATUS.FAIL, DriverAction.takeSnapShot());

        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }

    @Then("Verify if login is unsuccessful")
    public void verifyIfLoginIsUnsuccessful() {
        try {
            DriverAction.waitSec(2);
            if (DriverAction.isExist(LoginLocators.loginError)) {
                String error = DriverAction.getElementText(LoginLocators.loginError);
                if (error.contains("incorrect")) {
                    GemTestReporter.addTestStep("Login failure", "Login failed for invalid domain", STATUS.PASS, DriverAction.takeSnapShot());
                } else
                    GemTestReporter.addTestStep("Login failure", "Invalid domain accepted for login", STATUS.FAIL, DriverAction.takeSnapShot());

            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Exception Occurred", "Exception: " + e, STATUS.FAIL);
            throw new RuntimeException(e);
        }
    }
}
