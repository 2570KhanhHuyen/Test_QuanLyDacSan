package PageObjects;
import constant.constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage extends GeneralPage {
    WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(20));

    private final By _txtUsername = By.xpath("//input[@id='inp_Username']");
    private final By _txtPassword = By.xpath("//input[@id='inp_Password']");
    private final By _btnLogin = By.xpath("//button[@id='btn_Submit']");
    private final By _lblLoginErrorMessage = By.xpath("//span[@data-notify-text and contains(text(), 'Không thể đăng nhập')]");
    private final By _lblTitleWelcome = By.xpath("//h5[contains(text(),'Chào mừng trở lại!')]");
    private final By _btnForgotPassword = By.xpath("//a[@id='btn_Forgot_Pw']");
    private final By _idLoginPage = By.id("login_page");

    public WebElement getLblLoginErrorMessage() {
        return constant.WEBDRIVER.findElement(_lblLoginErrorMessage);
    }

    public LoginPage open() {
        constant.WEBDRIVER.navigate().to(constant.AGRINOTEV_URL);
        waitUntilLoginPageReady();
        return this;
    }

    public HomePage loginWithValidCredential(String username, String password) {
        waitUntilPageLoaded();
        enterCredentials(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(_lblTitleWelcome));
        return new HomePage();
    }

    public String loginWithInvalidCredential(String username, String password) {
        enterCredentials(username, password);

        int retries = 2;
        while (retries-- > 0) {
            try {
                WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(_lblLoginErrorMessage));
                return error.getText();
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException khi lấy lỗi: thử lại...");
            } catch (WebDriverException e) {
                if (e.getMessage().contains("Node with given id does not belong to the document")) {
                    System.out.println("WebDriverException (DOM reload): thử lại lấy lỗi...");
                } else {
                    throw e;
                }
            }
        }

        throw new RuntimeException("Không thể lấy thông báo lỗi đăng nhập sau khi thử lại.");
    }

    public void clickbtn_ForgotPassword() {
        int retries = 2;
        while (retries-- > 0) {
            try {
                WebElement btnForgot = wait.until(ExpectedConditions.elementToBeClickable(_btnForgotPassword));
                btnForgot.click();
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException khi click 'Quên mật khẩu': thử lại...");
            } catch (WebDriverException e) {
                if (e.getMessage().contains("Node with given id does not belong to the document")) {
                    System.out.println("WebDriverException (DOM reload): thử lại click forgot password...");
                } else {
                    throw e;
                }
            }
        }

        throw new RuntimeException("Không thể click 'Quên mật khẩu' sau khi thử lại.");
    }

    private void enterCredentials(String username, String password) {
        int retries = 2;
        while (retries-- > 0) {
            try {
                WebElement user = wait.until(ExpectedConditions.elementToBeClickable(_txtUsername));
                WebElement pass = wait.until(ExpectedConditions.elementToBeClickable(_txtPassword));
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(_btnLogin));

                user.clear();
                user.sendKeys(username);
                pass.clear();
                pass.sendKeys(password);
                btn.click();
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException khi nhập thông tin: thử lại...");
            } catch (WebDriverException e) {
                if (e.getMessage().contains("Node with given id does not belong to the document")) {
                    System.out.println("WebDriverException (DOM reload): thử lại nhập thông tin...");
                } else {
                    throw e;
                }
            }
        }

        throw new RuntimeException("Thao tác login thất bại sau khi thử lại.");
    }

    public boolean isLoginPageDisplayed() {
        try {
            WebElement loginPage = wait.until(ExpectedConditions.visibilityOfElementLocated(_idLoginPage));
            return loginPage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void waitUntilLoginPageReady() {
        wait.until(ExpectedConditions.presenceOfElementLocated(_idLoginPage));
        wait.until(ExpectedConditions.visibilityOfElementLocated(_txtUsername));
        wait.until(driver -> {
            try {
                WebElement el = constant.WEBDRIVER.findElement(_txtUsername);
                return el.isDisplayed() && el.isEnabled();
            } catch (Exception e) {
                return false;
            }
        });
    }

    private void waitUntilPageLoaded() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }
}