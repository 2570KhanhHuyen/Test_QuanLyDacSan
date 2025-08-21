package PageObjects;

import constant.constant;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;
public class QLNhomSPPage extends GeneralPage {
/* ====== LOCATORS ======*/
private By ajaxLoader = By.className("ajax_loader");
private By btnReloadNSPList = By.xpath("//a[@id='btn_refresh_entity']//i[@class='mdi mdi-refresh font-size-18 mr-2']");
private By nspListDacSan = By.xpath("//div[@id='table_mat_list']");
private final By btnNext = By.xpath("//a[normalize-space()='»' and not(ancestor::li[contains(@class,'disabled')])]");
private final By itemLocator   = By.xpath("//ul[@id='ul-list']//li");
private final By spantrangthai = By.xpath("//span[contains(@class,'badge')]");
private final By first_entityItem = By.xpath("(//li[contains(@class,'entity-item')])[1]");
private final By entityitem_locator = By.xpath("//li[contains(@class,'entity-item')]");
private final By maNSP_Sibling = By.xpath("//h6[contains(text(),'Mã nhóm sản phẩm:')]/following::p[1]");
private final By trangthai_Sibling = By.xpath("//h6[contains(text(),'Trạng thái:')]/following::p[1]");
private final By motaNSP = By.xpath("//h5[contains(text(),'Mô tả nhóm sản phẩm')]");
private final By btncollapse_NSP = By.xpath("//a[contains(@class,'btn-resize-content')]//i[contains(@class,'mdi-window-minimize')]");
private final By btncollapsehienlai_NSP = By.xpath("//a[contains(@class,'btn-resize-content')]//i[contains(@class,'mdi-window-maximize')]");
private final By btnresizemini_listDS = By.xpath("//a[@data-divtoogle='#table_mat_list' and contains(@class,'btn-resize-content') and contains(@class,'cursor-pointer')]//i[contains(@class,'mdi-window-minimize') and contains(@class,'text-primary')]");
private final By btnresizemax_listDS = By.xpath("//a[@data-divtoogle='#table_mat_list' and contains(@class,'btn-resize-content') and contains(@class,'cursor-pointer')]//i[contains(@class,'mdi-window-maximize') and contains(@class,'text-primary')]");
private final By txtTenNSP = By.id("inp_group_name");
private final By ddlTrangThai = By.id("sel_group_stat");
private final By txtMaNSP = By.id("typ_cat_grp_code");
private final By TenNSP_FieldError = By.xpath("//input[@id='inp_group_name']/following-sibling::div[@class='errMsg']");
private final By MaNSP_FieldError = By.xpath("//input[@id='typ_cat_grp_code']/following-sibling::div[@class='errMsg']");
/*====== METHODS ======*/

    //====Void====
    public void enterTenNSP(String value) {
        constant.WEBDRIVER.findElement(txtTenNSP).sendKeys(value);}
    public void enterMaNSP(String value) {constant.WEBDRIVER.findElement(txtMaNSP).sendKeys(value);}
    public void selectTrangThai(String value) {
        WebElement selectElement = constant.WEBDRIVER.findElement(ddlTrangThai);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
    }
    public String getTenNSPFieldErrorr() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(TenNSP_FieldError));
        return errorElement.getText().trim();
    }
    public String getMaNSPFieldErrorr() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(MaNSP_FieldError));
        return errorElement.getText().trim();
    }
    public void clickThuGonThongTinNSP() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btncollapse_NSP));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void clickHienThiLaiThongTinNSP() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btncollapsehienlai_NSP));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void clickThuGonListDS() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnresizemini_listDS));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void clickHienThiLaiListDS() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnresizemax_listDS));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void chinhSuaThongTinNSP(String TenNSP,String trangThai) {
        WebDriver driver = constant.WEBDRIVER;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement TenDSField = wait.until(ExpectedConditions.elementToBeClickable(txtTenNSP));
        TenDSField.clear();
        TenDSField.sendKeys(TenNSP);

        if (trangThai != null && !trangThai.trim().isEmpty()) {
            WebElement trangThaiDropdown = wait.until(ExpectedConditions.elementToBeClickable(ddlTrangThai));
            Select select = new Select(trangThaiDropdown);
            select.selectByVisibleText(trangThai);
        }
    }
    public boolean IsListDacSanToAppear() {
        try {
            WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(10));
            WebElement listContentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nspListDacSan));
            return listContentElement.isDisplayed();
        } catch (Exception e) {
            System.err.println("Lỗi khi chờ nội dung danh sách Dac San xuất hiện: " + e.getMessage());
            return false;
        }
    }
    public boolean IsListDacSanToDisappear() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(nspListDacSan));
        } catch (Exception e) {
            System.err.println("Lỗi khi chờ nội dung danh sách Dac San thu ẩn lại: " + e.getMessage());
            return false;
        }
    }
    public void reloadNSPList() {
        constant.WEBDRIVER.findElement(btnReloadNSPList).click();
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax_loader")));
    }
    public boolean isThongTinChiTietNSPAn(String tenNSP) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By tenChiTiet = By.xpath("//h4[normalize-space(text())='" + tenNSP + "']");
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(tenChiTiet));
        } catch (TimeoutException e) {
            try {
                WebElement tenElement = constant.WEBDRIVER.findElement(tenChiTiet);
                return !tenElement.isDisplayed();
            } catch (NoSuchElementException ex) {
                return true;
            }
        }
    }
    // Ham kiem tra cho trang thai Tat ca
    public boolean kiemTraTrangThaiKhiLocTatCa() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        Set<String> thuThap = new HashSet<>();
        Set<String> canCo   = Set.of("Hiển thị", "Không hiển thị");
        int pageCounter = 0;
        while (true) {
            pageCounter++;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
            boolean daLoad = false;
            for (int retry = 0; retry < 3; retry++) {
                List<WebElement> items  = constant.WEBDRIVER.findElements(itemLocator);
                List<WebElement> badges = constant.WEBDRIVER.findElements(spantrangthai);
                if (!items.isEmpty() && !badges.isEmpty()) {
                    for (WebElement badge : badges) {
                        String text = badge.getText().trim();
                        if (!text.isEmpty()) {
                            thuThap.add(text);
                        }
                    }
                    daLoad = true;
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!daLoad) {
                System.out.println("Trang " + pageCounter + " không load được dữ liệu.");
                return false;
            }
            // Nếu đủ hết 2 trạng thái thì kết thúc sớm
            if (thuThap.containsAll(canCo)) {
                return true;
            }
            List<WebElement> nextBtns = constant.WEBDRIVER.findElements(btnNext);
            if (nextBtns.isEmpty() || !nextBtns.get(0).isEnabled() || !nextBtns.get(0).isDisplayed()) {
                break;
            }
            WebElement firstItem = constant.WEBDRIVER.findElement(itemLocator);
            nextBtns.get(0).click();
            wait.until(ExpectedConditions.stalenessOf(firstItem));

            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
                wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
            } catch (Exception e) {
                System.out.println("Trang " + (pageCounter + 1) + " không load được dữ liệu sau khi chuyển trang.");
                return false;
            }
        }
        if (!thuThap.containsAll(canCo)) {
            canCo.stream()
                    .filter(tt -> !thuThap.contains(tt))
                    .forEach(tt -> System.out.println("Thiếu trạng thái: " + tt));
            return false;
        }
        return true;
    }
    public boolean timVaXacThucChiTietNSP(String tenNSP,String maNSP, String trangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(entityitem_locator));
        boolean daTimThay = false;
        while (true) {
            //Tìm nhóm sản phẩm trên trang hiện tại
            List<WebElement> ds = constant.WEBDRIVER.findElements(By.xpath("//li[contains(@class,'entity-item')]//h6[contains(@class,'mb-0') and normalize-space(text())='" + tenNSP + "']"));
            if (!ds.isEmpty()) {
                clickTenByName(tenNSP);
                daTimThay = true;
                break;
            }
            List<WebElement> nextBtn = constant.WEBDRIVER.findElements(btnNext);
            if (nextBtn.isEmpty()) break;
            //Lưu 1 phần tử hiện tại để đợi staleness
            WebElement firstItem = constant.WEBDRIVER.findElement(first_entityItem);
            WebElement nextPageBtn = nextBtn.get(0);
            ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", nextPageBtn);
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
            } catch (TimeoutException ignored) {
            }
            nextPageBtn.click();
            wait.until(ExpectedConditions.stalenessOf(firstItem));
        }
        if (!daTimThay) {throw new NoSuchElementException("Không tìm thấy nhóm sản phẩm: " + tenNSP);}
        return isThongTinChiTietHienThiDL(tenNSP,maNSP,trangThai);
    }
    public boolean isThongTinChiTietHienThiDL(String tenNSP, String maNSP, String trangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) constant.WEBDRIVER;
        try {
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);
            WebElement ten = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space(text())='" + tenNSP + "']")));
            WebElement maNSPElement = wait.until(ExpectedConditions.visibilityOfElementLocated(maNSP_Sibling));
            WebElement trangThaiElement = wait.until(ExpectedConditions.visibilityOfElementLocated(trangthai_Sibling));
            WebElement moTa = wait.until(ExpectedConditions.visibilityOfElementLocated(motaNSP));
            boolean maNSPDung = maNSPElement.getText().trim().equalsIgnoreCase(maNSP);
            boolean trangThaiDung = trangThaiElement.getText().trim().equalsIgnoreCase(trangThai);
            if (!maNSPDung) {System.out.println("Mã nhóm sản phẩm không đúng. Mong đợi: " + maNSP + " | Thực tế: " + maNSPElement.getText());}
            if (!trangThaiDung) {System.out.println("Trạng thái không đúng. Mong đợi: " + trangThai + " | Thực tế: " + trangThaiElement.getText());}
            return ten.isDisplayed() && maNSPElement.isDisplayed()
                    && trangThaiElement.isDisplayed() && moTa.isDisplayed()
                    && maNSPDung && trangThaiDung;
        } catch (Exception e) {
            System.out.println(" Không hiển thị đầy đủ thông tin chi tiết: " + e.getMessage());
            return false;
        }
    }
}
