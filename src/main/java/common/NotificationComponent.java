package common;
import constant.constant;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NotificationComponent {
    private final By lblAddedSuccessNotification = By.xpath("//span[@data-notify-text and contains(text(), 'Tạo mới thành công')]");
    private final By lblErrorFileSize = By.xpath("//span[normalize-space()='File is too big (473.2MiB). Max filesize: 256MiB.']");
    private final By lblRequiredField = By.xpath("//div[@class='form-group col-12']//div[@class='errMsg'][contains(text(),'Thông tin bắt buộc')]");
    private final By lblFailNotification = By.xpath("//span[@data-notify-text and text()='Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu']");
    private final By lblErrorNotification = By.xpath("//span[@data-notify-text='' and contains(text(),'Vui lòng kiểm tra lại những lỗi dữ liệu!')]");
    private final By lblMaQlErrorNotification = By.xpath("//span[@data-notify-text='' and contains(text(),'Mã QL đã tồn tại')]");
    private final By lblTenTonTaiErrorNotification = By.xpath("//span[@data-notify-text='' and contains(text(),'Tên đã tồn tại')]");
    private final By lblEditSuccessfullNotification = By.xpath("//span[contains(text(),'Cập nhật thành công')]");
    private final By lblXoaThanhCongNotification = By.xpath("//span[contains(text(),'Dữ liệu đã được xóa thành công')]");
    private final By lblKhongTheTruyXuatDulieu = By.xpath("//span[@data-notify-text='' and normalize-space(text())='Có lỗi xảy ra, không thể truy xuất dữ liệu!']");
    //Để assert nội dung ra
    public String getTruyXuatDLErrorNotification() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(lblKhongTheTruyXuatDulieu));
        return notification.getText().trim();
    }
    public String getSuccessNotification() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(lblAddedSuccessNotification));
        return notification.getText().trim();
    }
    public String getErrorNotification() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(lblErrorNotification));
        return notification.getText().trim();
    }
    public String getMaQLTrungErrorNotification() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(lblMaQlErrorNotification));
        return notification.getText().trim();
    }
    public String getTenTrungErrorNotification() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(lblTenTonTaiErrorNotification));
        return notification.getText().trim();
    }
    public String getSuccessDelNotification(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(10));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(lblXoaThanhCongNotification));
        return notification.getText().trim();
    }
    public boolean isThongBaoCapNhatThanhCong() {
        try {
            WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
            WebElement thongBaoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lblEditSuccessfullNotification));
            return thongBaoElement.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    public String getErrorFileSize() {return constant.WEBDRIVER.findElement(lblErrorFileSize).getText();}
    public String getRequiredFieldError() {return constant.WEBDRIVER.findElement(lblRequiredField).getText();}
    public String getErrorMsg() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement errorMsgElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(lblFailNotification)
        );
        return errorMsgElement.getText().trim();
    }
}
