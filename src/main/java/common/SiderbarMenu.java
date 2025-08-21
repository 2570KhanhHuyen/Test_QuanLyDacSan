package common;
import constant.constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SiderbarMenu {
    private final WebDriver driver = constant.WEBDRIVER;
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    private final By menuQuanLyDacSan = By.xpath("//span[contains(text(),'Quản lý đặc sản')]");
    private final By subQuanLyThongTinDacSan = By.xpath("//a[contains(text(),'Quản lý thông tin đặc sản')]");
    private final By subQuanLyNhomSanPham = By.xpath("//a[contains(text(),'Quản lý nhóm sản phẩm')]");
    private final By subQuanLyVungMien = By.xpath("//a[contains(text(),'Quản lý vùng miền')]");

    public void clickQuanLyDacSan() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuQuanLyDacSan));
        menu.click();
    }
    public void clickQuanLyThongTinDacSan() {
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(subQuanLyThongTinDacSan));
        subMenu.click();
    }
    public void clickQuanLyNhomSanPham() {
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(subQuanLyNhomSanPham));
        subMenu.click();
    }
    public void clickQuanLyVungMien() {
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(subQuanLyVungMien));
        subMenu.click();
    }
}
