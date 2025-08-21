package PageObjects;
import constant.constant;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class QLTTDSPage extends GeneralPage {
    // ====== LOCATORS ======
    private final By loader = By.className("ajax_loader");
    private final By itemLocator   = By.xpath("//ul[@id='ul-list']//li");
    private final By tenNhomDacSan = By.xpath("//td[@class='cursor-pointer']");
    private final By btnNext       = By.xpath("//a[normalize-space()='»' and not(ancestor::li[contains(@class,'disabled')])]");
    private final By btnReloadList = By.xpath("//a[@id='btn_refresh_entity']//i[@class='mdi mdi-refresh font-size-18 mr-2']");
    private final By txtTenDacSan = By.xpath("//input[@id='inp_material_name']");
    private final By txtDacSanVung = By.xpath("//input[@id='inp_material_region']");
    private final By ddlTrangThai = By.xpath("//select[@id='sel_land_stat']");
    private final By txtMaDacSan = By.xpath("//input[@id='inp_material_code']");
    private final By ErrorTeNDS = By.xpath("//input[@id='inp_material_name']/following-sibling::div[@class='errMsg']");
    private final By ErrorMaDs = By.xpath("//input[@id='inp_material_code']/following-sibling::div[@class='errMsg']");
    private final By ErrorDacSanVung = By.xpath("//input[@id='inp_material_region']/following-sibling::div[@class='errMsg']");
    // ====== METHODS ======

    public String getTenDacSanFieldError() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorTeNDS));
        return errorElement.getText().trim();
    }
    public String getMaDacSanFieldError(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorMaDs));
        return errorElement.getText().trim();
    }
    public String getDacSanVungFieldError(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ErrorDacSanVung));
        return errorElement.getText().trim();
    }
    public void enterTenDacSan(String value) {constant.WEBDRIVER.findElement(txtTenDacSan).sendKeys(value);}
    public void enterMaDacSan(String value) {constant.WEBDRIVER.findElement(txtMaDacSan).sendKeys(value);}
    public void enterDacSanVung(String value) {constant.WEBDRIVER.findElement(txtDacSanVung).sendKeys(value);}
    public void selectTrangThai(String value) {
        WebElement selectElement = constant.WEBDRIVER.findElement(ddlTrangThai);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
    }
    public void clickDacSanTrongKetQua(String tenChinhXac) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By itemLocator = By.xpath("//ul[@id='ul-list']//div[contains(@class,'simplebar-content')]//h6[normalize-space(text())='" + tenChinhXac + "']");
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(itemLocator));
        item.click();
    }
    public void chinhSuaThongTinDacSan(String TenDS, String DacSanVung, String trangThai) {
        WebDriver driver = constant.WEBDRIVER;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Tên đặc sản
        WebElement TenDSField = wait.until(ExpectedConditions.elementToBeClickable(txtTenDacSan));
        TenDSField.clear();
        TenDSField.sendKeys(TenDS);
        //Đặc sản vùng
        WebElement DacSanVungField = wait.until(ExpectedConditions.elementToBeClickable(txtDacSanVung));
        DacSanVungField.clear();
        DacSanVungField.sendKeys(DacSanVung);
        WebElement menuItemToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class,'ui-autocomplete')]//li[contains(@class,'ui-menu-item') and .//text()='" + DacSanVung + "']")
        ));
        menuItemToSelect.click();
        //Trạng thái
        if (trangThai != null && !trangThai.trim().isEmpty()) {
            WebElement trangThaiDropdown = wait.until(ExpectedConditions.elementToBeClickable(ddlTrangThai));
            Select select = new Select(trangThaiDropdown);
            select.selectByVisibleText(trangThai);
        }
    }
    public void clickNutChinhSuaNDS(){
        WebDriver driver = constant.WEBDRIVER;
        WebElement CSuaNDSBtn = driver.findElement(By.id("btn_add_specialty"));
        CSuaNDSBtn.click();
    }
    public void LuuCSNDS() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement LuuBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='mdi mdi-content-save action-item']")));
        LuuBtn.click();
    }
    public void chonTuDropdownGoiY(String tenGoiY) {
        WebDriver driver = constant.WEBDRIVER;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id("inp_name_member")));
        input.clear();
        input.sendKeys(tenGoiY);

        By listSuggestion = By.id("ui-id-1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(listSuggestion));

        By itemSuggestion = By.xpath("//ul[@id='ui-id-1']//li[normalize-space()='" + tenGoiY + "']");
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(itemSuggestion));
        item.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(listSuggestion));
    }
    public void xoaNhomDacSan(String tenNhom) {
        WebDriver driver = constant.WEBDRIVER;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = "//tr[td//h4[contains(normalize-space(),'" + tenNhom + "')]]//i[contains(@class,'mdi-close')]";
        WebElement nutXoa = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        nutXoa.click();
    }
    public void clickThuGonThongTinDacSan() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By btnCollapse = By.xpath("//a[contains(@class,'btn-resize-content')]//i[contains(@class,'mdi-window-minimize')]");
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnCollapse));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax_loader")));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void clickHienThiLaiThongTinDacSan() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By btnCollapse = By.xpath("//a[contains(@class,'btn-resize-content')]//i[contains(@class,'mdi-window-maximize')]");
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnCollapse));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax_loader")));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void clickThuGonNhomDacSan() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By btnCollapse = By.xpath("//a[contains(@class,'btn-resize_grp')]//i[contains(@class,'cursor-pointer')]");
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnCollapse));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax_loader")));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void clickHienThiLaiThongTinNhomDS() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By btnCollapse = By.xpath("//a[contains(@class,'btn-resize_grp')]//i[contains(@class,'mdi cursor-pointer mdi-window-maximize')]");
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnCollapse));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax_loader")));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void reloadDacSanList() {
        constant.WEBDRIVER.findElement(btnReloadList).click();
        // Chờ loading nếu có
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax_loader")));
    }
    public boolean timVaXacThucChiTietDacSan(String tenDacSan,String dacSanVung, String trangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class,'entity-item')]")));
        boolean daTimThay = false;
        while (true) {
            //Tim dac san tren trang hien tai
            List<WebElement> ds = constant.WEBDRIVER.findElements(By.xpath("//li[contains(@class,'entity-item')]//h6[contains(@class,'mb-0') and normalize-space(text())='" + tenDacSan + "']"));
            if (!ds.isEmpty()) {
                clickTenByName(tenDacSan);
                daTimThay = true;
                break;
            }
            // Xác định nút Next (»), bảo đảm chưa bị disabled
            List<WebElement> nextBtn = constant.WEBDRIVER.findElements(By.xpath("//a[normalize-space()='»' and not(ancestor::li[contains(@class,'disabled')])]"));
            if (nextBtn.isEmpty()) break;
            //Lưu 1 phần tử hiện tại để đợi staleness
            WebElement firstItem = constant.WEBDRIVER.findElement(By.xpath("(//li[contains(@class,'entity-item')])[1]"));
            //Cuộn tới nút Next & chờ loader biến mất
            WebElement nextPageBtn = nextBtn.get(0);
            ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", nextPageBtn);
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.className("ajax_loader")));
            } catch (TimeoutException ignored) {

            }
            //Click & chờ trang mới render
            nextPageBtn.click();
            wait.until(ExpectedConditions.stalenessOf(firstItem));
        }
        if (!daTimThay) {throw new NoSuchElementException("Không tìm thấy đặc sản: " + tenDacSan);}
        return isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
    }
    public boolean isThongTinChiTietHienThiDL(String tenDacSan, String dacSanVung, String trangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) constant.WEBDRIVER;

        try {
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);

            WebElement ten = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h5[normalize-space(text())='" + tenDacSan + "']")));

            WebElement ma = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(text(),'Mã đặc sản:')]")));

            // Tìm <p> kế bên phần tử label
            WebElement dacSanVungElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(text(),'Đặc sản vùng:')]/following::p[1]")));

            WebElement trangThaiElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[contains(text(),'Trạng thái:')]/following::p[1]")));

            WebElement tomTat = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h5[contains(text(),'Thông tin tóm tắt của sản phẩm')]")));

            WebElement moTa = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h5[contains(text(),'Mô tả sản phẩm')]")));

            boolean vungDung = dacSanVungElement.getText().trim().equalsIgnoreCase(dacSanVung);
            boolean trangThaiDung = trangThaiElement.getText().trim().equalsIgnoreCase(trangThai);

            if (!vungDung) {
                System.out.println("Đặc sản vùng không đúng. Mong đợi: " + dacSanVung + " | Thực tế: " + dacSanVungElement.getText());
            }
            if (!trangThaiDung) {
                System.out.println("Trạng thái không đúng. Mong đợi: " + trangThai + " | Thực tế: " + trangThaiElement.getText());
            }

            return ten.isDisplayed() && ma.isDisplayed() && dacSanVungElement.isDisplayed()
                    && trangThaiElement.isDisplayed() && tomTat.isDisplayed() && moTa.isDisplayed()
                    && vungDung && trangThaiDung;

        } catch (Exception e) {
            System.out.println(" Không hiển thị đủ thông tin chi tiết: " + e.getMessage());
            return false;
        }
    }
    public boolean isThongTinChiTietAn(String tenDacSan) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By tenChiTiet = By.xpath("//h5[normalize-space(text())='" + tenDacSan + "']");
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
    public boolean isThongTinNhomDacSanDaAn() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(tenNhomDacSan));
        } catch (TimeoutException e) {
            try {
                WebElement tenElement = constant.WEBDRIVER.findElement(tenNhomDacSan);
                return !tenElement.isDisplayed();
            } catch (NoSuchElementException ex) {
                return true;
            }
        }
    }
    public boolean isThongTinNhomDacSanDaHienThi() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            WebElement tenElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tenNhomDacSan));
            return tenElement.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
    // Ham kiem tra cho trang thai Tat ca
    public boolean kiemTraTrangThaiKhiLocTatCa() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By spanTrangThai = By.xpath("//span[contains(@class,'badge')]");
        Set<String> thuThap = new HashSet<>();
        Set<String> canCo   = Set.of("Nháp", "Cần kiểm tra", "Xác nhận");
        int pageCounter = 0;
        while (true) {
            pageCounter++;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
            // Retry đợi danh sách đặc sản thật sự xuất hiện
            boolean daLoad = false;
            for (int retry = 0; retry < 3; retry++) {
                List<WebElement> items  = constant.WEBDRIVER.findElements(itemLocator);
                List<WebElement> badges = constant.WEBDRIVER.findElements(spanTrangThai);
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
                    Thread.sleep(1000); // Chờ thêm 1 giây
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!daLoad) {
                System.out.println("Trang " + pageCounter + " không load được dữ liệu.");
                return false;
            }
            // Nếu đủ hết 4 trạng thái thì kết thúc sớm
            if (thuThap.containsAll(canCo)) {
                return true;
            }
            // Tìm nút chuyển trang
            List<WebElement> nextBtns = constant.WEBDRIVER.findElements(btnNext);
            if (nextBtns.isEmpty()) {
                break;
            }
            // Click next và đợi trang mới load lại
            WebElement firstItem = constant.WEBDRIVER.findElement(itemLocator);
            nextBtns.get(0).click();
            wait.until(ExpectedConditions.stalenessOf(firstItem));
        }
        if (!thuThap.containsAll(canCo)) {
            canCo.stream()
                    .filter(tt -> !thuThap.contains(tt))
                    .forEach(tt -> System.out.println("Thiếu trạng thái: " + tt));
            return false;
        }
        return true;
    }
}