package PageObjects;
import constant.constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage{
    private final By _txtEmailAddress = By.xpath("//input[@id='idLogin']");
    private final By _btnSendInstruction =By.xpath("//button[@id='btn_Forgot_Pw']");
    private final By _lblForgotWelcomePass = By.xpath("//h5[contains(text(),'Quên mật khẩu')]");
    private final By lblFogortPassword = By.xpath("//span[@data-notify-text='' and contains(text(),'Vui lòng kiểm tra email để đổi mật khẩu')]");

    public WebElement getTxtEmailAddress()
    {
        return constant.WEBDRIVER.findElement(_txtEmailAddress);
    }
    public WebElement getBtnSendInstruction()
    {
        return constant.WEBDRIVER.findElement(_btnSendInstruction);
    }
    public WebElement getLblForgotWelcomePass()
    {
        return constant.WEBDRIVER.findElement(_lblForgotWelcomePass);
    }

    public LoginPage forgotPassword(String email)
    {
        this.getTxtEmailAddress().sendKeys(email);
        JavascriptExecutor js = (JavascriptExecutor) constant.WEBDRIVER;
        js.executeScript("arguments[0].scrollIntoView(true);", getBtnSendInstruction());
        getBtnSendInstruction().click();
        return new LoginPage();
    }
    public String getSuccesSendEmail() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(lblFogortPassword));
        return notification.getText().trim();
    }
}
