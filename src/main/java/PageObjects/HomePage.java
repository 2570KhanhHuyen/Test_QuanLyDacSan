package PageObjects;
import common.SiderbarMenu;
import constant.constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends GeneralPage {
    private final WebDriver driver = constant.WEBDRIVER;
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    private final By txtWelcomeTitle = By.xpath("//h5[contains(text(),'Chào mừng quay trở lại !')]");
    private final By btnHeaderProfile_Dropdown = By.xpath("//button[@id='page-header-user-dropdown']");
    private final By btnDangXuat = By.xpath("//a[@id='a_disconnect']");

    public String getWelcomeMessage() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(txtWelcomeTitle));
        return element.getText();
    }
    public void ClickHeaderButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nutDropdown = wait.until(ExpectedConditions.elementToBeClickable(btnHeaderProfile_Dropdown));
        nutDropdown.click();
    }
    public void clickDangXuatbtn() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(btnDangXuat));
        editBtn.click();
    }
    public QLTTDSPage goToQLTTDSPage() {
        SiderbarMenu menu = new SiderbarMenu();
        menu.clickQuanLyDacSan();
        menu.clickQuanLyThongTinDacSan();
        return new QLTTDSPage();
    }

    public QLNhomSPPage goToQLNhomSPPage() {
        SiderbarMenu menu = new SiderbarMenu();
        menu.clickQuanLyDacSan();
        menu.clickQuanLyNhomSanPham();
        return new QLNhomSPPage();
    }

    public QLVungMienPage goToQLVungMienPage() {
        SiderbarMenu menu = new SiderbarMenu();
        menu.clickQuanLyDacSan();
        menu.clickQuanLyVungMien();
        return new QLVungMienPage();
    }
}
