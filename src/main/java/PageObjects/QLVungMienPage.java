package PageObjects;

import constant.constant;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QLVungMienPage extends GeneralPage{
    private final By maVungmien_Sibling = By.xpath("//h6[contains(text(),'Mã vùng miền:')]/following::p[1]");
    private final By Dientich_Sibling = By.xpath("//h6[contains(text(),'Diện tích(km²):')]/following::p[1]");
    private final By Kinhdo_Sibling = By.xpath("//h6[contains(text(),'Kinh độ:')]/following::p[1]");
    private final By Vido_Sibling = By.xpath("//h6[contains(text(),'Vĩ độ:')]/following::p[1]");
    private final By Cacdiembien = By.xpath("//h5[contains(text(),'Các điểm biên')]");
    private final By motaTomtat = By.xpath("//h5[contains(text(),'Mô tả tóm tắt')]");
    private final By txtSearchvungmien = By.xpath("//input[@id='inp-search']");
    private final By btnHuyBoVungMien = By.xpath("//button[@id='btn_cancel_new_02']");
    private By ajaxLoader = By.className("ajax_loader");
    private By noDataMessage = By.xpath("//li[contains(text(),'Chưa có dữ liệu')]");
    private By ulListItems = By.xpath("//ul[@id='ul-list']//div[contains(@class,'simplebar-content')]//h6");
    private By btnReloadVungMienList = By.xpath("//a[@id='btn_refresh_group']//i[@class='mdi mdi-refresh font-size-18 mr-2']");
    private final By txtSearch = By.xpath("//input[@id='inp-search']");
    private final By btnNext = By.xpath("//a[normalize-space()='»' and not(ancestor::li[contains(@class,'disabled')])]");
    private final By itemLocator   = By.xpath("//ul[@id='ul-list']//li");
    private final By spantrangthai = By.xpath("//span[contains(@class,'badge')]");
    private final By first_entityItem = By.xpath("(//li[contains(@class,'entity-item')])[1]");
    private final By entityitem_locator = By.xpath("//li[contains(@class,'entity-item')]");
    private final By tabDacSan = By.xpath("//h6[contains(text(),'Đặc sản')]");
    private final By tabVungmien = By.xpath("//h6[contains(text(),'Vùng miền')]");
    private final By Danhsachcacds = By.xpath("//h5[contains(text(),'Danh sách các đặc sản')]");
    private final By btnCong = By.xpath("//button[normalize-space()='+']");
    private final By IconLuu = By.xpath("//a[@id='a_btn_sav_chronic']//i[contains(@class,'mdi-content-save')]");
    private final By txtTenVungMien = By.xpath("//input[@id='inp_group_phone' and @data-name='name']");
    private final By btnChinhSuaDS = By.xpath("//div[@id='btn_mod_chronic']//i[contains(@class,'mdi-pencil-outline')]");
    private final By btnHuyBoCSDS = By.xpath("//a[@id='a_btn_canc_chronic']");
    private final By txtMaVungMien = By.xpath("//input[@id='inp_group_phone' and @data-name='code01']");
    private final By ddlKieuVungMien = By.xpath("//select[@id='sel_region_typ02']");
    private final By ddlTrangThai = By.xpath("//select[@id='sel_region_stat']");
    private final By txtDienTich = By.xpath("//input[@data-name='fv03']");
    private final By txtKinhDo = By.xpath("//input[@data-name='fv02']");
    private final By txtViDo= By.xpath("//input[@data-name='fv01']");
    private final By txtinputTenDS = By.xpath("//input[@data-name='name01']");
    //Xpath Cac Diem Bien
    private final By txtViDoNho= By.xpath("//input[@data-name='longitude']");
    private final By txtKinhDoNho= By.xpath("//input[@data-name='latitude']");
    // FieldError
    private final By TenVungMien_FieldError = By.xpath("//input[@data-name='name']/following-sibling::div[@class='errMsg']");
    private final By MaVungMien_FieldError = By.xpath("//input[@data-name='code01']/following-sibling::div[@class='errMsg']");
    private final By DienTich_FieldError = By.xpath("//input[@data-name='fv03']/following-sibling::div[@class='errMsg']");
    private final By KinhDo_FieldError = By.xpath("//input[@data-name='fv02']/following-sibling::div[@class='errMsg']");
    private final By ViDo_FieldError = By.xpath("//input[@data-name='fv01']/following-sibling::div[@class='errMsg']");

    public void enterTenVungMien(String value) {constant.WEBDRIVER.findElement(txtTenVungMien).sendKeys(value);}
    public void enterMaVungMien(String value) {constant.WEBDRIVER.findElement(txtMaVungMien).sendKeys(value);}
    public void selectKieuVungMien(String value) {
        WebElement selectElement = constant.WEBDRIVER.findElement(ddlKieuVungMien);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
    }
    public void selectTrangThai(String value) {
        WebElement selectElement = constant.WEBDRIVER.findElement(ddlTrangThai);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
    }
    public void enterDienTich(String value) {constant.WEBDRIVER.findElement(txtDienTich).sendKeys(value);}
    public void enterKinhdo(String value) {constant.WEBDRIVER.findElement(txtKinhDo).sendKeys(value);}
    public void enterVido(String value) {constant.WEBDRIVER.findElement(txtViDo).sendKeys(value);}
    public void enterKinhDoNho(String value) {constant.WEBDRIVER.findElement(txtKinhDoNho).sendKeys(value);}
    public void enterViDoNho(String value) {constant.WEBDRIVER.findElement(txtViDoNho).sendKeys(value);}

    public void clickTimKiemVaChonTen(String tenDacSan) {
        WebDriver driver = constant.WEBDRIVER;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement oTimKiem = wait.until(ExpectedConditions.elementToBeClickable(txtinputTenDS));
        oTimKiem.click();
        By itemXpath = By.xpath("//ul[contains(@class,'ui-autocomplete')]//li[contains(@class,'ui-menu-item') and .//text()='" + tenDacSan + "']");
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(itemXpath));
        Actions actions = new Actions(driver);
        actions.moveToElement(item).click().perform();
    }
    public void GoDSTheoTen(String tenDacSan) {
        WebDriver driver = constant.WEBDRIVER;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String xpath = "//tr[.//span[@class='edit-text' and normalize-space(text())='" + tenDacSan + "']]//button[contains(text(), '-')]";
        try {
            WebElement nutXoa = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            nutXoa.click();
            System.out.println("Đã xóa đặc sản: " + tenDacSan);
        } catch (TimeoutException e) {
            System.out.println("Không tìm thấy đặc sản: " + tenDacSan + " hoặc không thể click nút '-'.");
        }
    }
    public void clickHuyBoCSDS(){
        constant.WEBDRIVER.findElement(btnHuyBoCSDS).click();
    }
    public void clickSaveButton(){
        constant.WEBDRIVER.findElement(IconLuu).click();
    }
    public void clickHuyBoVungMien(){
        constant.WEBDRIVER.findElement(btnHuyBoVungMien).click();
    }
    public void clickChinhSuaDS_VungMien(){
        constant.WEBDRIVER.findElement(btnChinhSuaDS).click();
    }
    public String getTenVungMienFieldErrorr() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(TenVungMien_FieldError));
        return errorElement.getText().trim();
    }
    public String getMaVungMienFieldErrorr() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(MaVungMien_FieldError));
        return errorElement.getText().trim();
    }
    public String getDienTichFieldErrorr() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DienTich_FieldError));
        return errorElement.getText().trim();
    }
    public String getKinhDonFieldErrorr() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(KinhDo_FieldError));
        return errorElement.getText().trim();
    }
    public String getViDoFieldErrorr() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ViDo_FieldError));
        return errorElement.getText().trim();
    }
    public void clickbtnCong() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement BtnCong = wait.until(ExpectedConditions.elementToBeClickable(btnCong));
        BtnCong.click();
    }
    public void clickTabDacSan() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement TabDS = wait.until(ExpectedConditions.elementToBeClickable(tabDacSan));
        TabDS.click();
    }
    public void clickTabVungmien() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement TabVungmien = wait.until(ExpectedConditions.elementToBeClickable(tabVungmien));
        TabVungmien.click();
    }
    public String getSearchBoxTextVungMien() {
        WebElement searchBox = constant.WEBDRIVER.findElement(txtSearch);
        return searchBox.getAttribute("value");
    }
    public void reloadVungMienList() {
        constant.WEBDRIVER.findElement(btnReloadVungMienList).click();
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ajax_loader")));
    }
    public void searchVungMien(String keyword) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(txtSearchvungmien));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(ulListItems),
                    ExpectedConditions.visibilityOfElementLocated(noDataMessage)
            ));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Warning: Neither search results nor 'no data' message appeared within timeout for keyword: " + keyword);
        }
    }
    public void chinhSuaThongTinVungMien(String tenVungMien,String maVungMien,String trangThai,String dienTich,String kinhDo,String viDo) {
        WebDriver driver = constant.WEBDRIVER;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement TenVungmienField = wait.until(ExpectedConditions.elementToBeClickable(txtTenVungMien));
        TenVungmienField.clear();
        TenVungmienField.sendKeys(tenVungMien);

        WebElement MaVungmienField = wait.until(ExpectedConditions.elementToBeClickable(txtMaVungMien));
        MaVungmienField.clear();
        MaVungmienField.sendKeys(maVungMien);

        WebElement DienTichField = wait.until(ExpectedConditions.elementToBeClickable(txtDienTich));
        DienTichField.clear();
        DienTichField.sendKeys(dienTich);

        WebElement KinhdoField = wait.until(ExpectedConditions.elementToBeClickable(txtKinhDo));
        KinhdoField.clear();
        KinhdoField.sendKeys(kinhDo);

        WebElement VidoField = wait.until(ExpectedConditions.elementToBeClickable(txtViDo));
        VidoField.clear();
        VidoField.sendKeys(viDo);

        if (trangThai != null && !trangThai.trim().isEmpty()) {
            WebElement trangThaiDropdown = wait.until(ExpectedConditions.elementToBeClickable(ddlTrangThai));
            Select select = new Select(trangThaiDropdown);
            select.selectByVisibleText(trangThai);
        }
    }
    public boolean kiemTraBangDacSanCoHienThi() {
        try {
            WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
            By bangDacSan = By.xpath("//div[contains(@class,'p-3') and contains(@class,'card')]");
            WebElement bang = wait.until(ExpectedConditions.visibilityOfElementLocated(bangDacSan));
            return bang.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean timVaXacThucChiTietVungMien(String tenVungmien,String maVungmien, String dienTich, String kinhDo, String viDo) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(entityitem_locator));
        boolean daTimThay = false;
        while (true) {
            //Tìm vùng miền trên trang hiện tại
            List<WebElement> ds = constant.WEBDRIVER.findElements(By.xpath("//li[contains(@class,'entity-item')]//h6[contains(@class,'mb-0') and normalize-space(text())='" + tenVungmien + "']"));
            if (!ds.isEmpty()) {
                clickTenByName(tenVungmien);
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
        if (!daTimThay) {throw new NoSuchElementException("Không tìm thấy vùng miền: " + tenVungmien);}
        return isThongTinChiTietHienThiDL(tenVungmien,maVungmien,dienTich,kinhDo,viDo);
    }
    public boolean isThongTinTableDSHienThi(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            WebElement tableTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(Danhsachcacds));
            return tableTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isThongTinChiTietHienThiDL(String tenVungmien,String maVungmien ,String dientich, String kinhdo,String vido) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) constant.WEBDRIVER;
        try {
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);

            WebElement ten = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[normalize-space(text())='" + tenVungmien + "']")));
            WebElement maVungmienElement = wait.until(ExpectedConditions.visibilityOfElementLocated(maVungmien_Sibling));
            WebElement dienTichElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Dientich_Sibling));
            WebElement KinhdoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Kinhdo_Sibling));
            WebElement VidoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Vido_Sibling));
            WebElement Diembien = wait.until(ExpectedConditions.visibilityOfElementLocated(Cacdiembien));
            WebElement moTa = wait.until(ExpectedConditions.visibilityOfElementLocated(motaTomtat));

            boolean maVungmienDung = maVungmienElement.getText().trim().equalsIgnoreCase(maVungmien);
            boolean dienTichDung = dienTichElement.getText().trim().equalsIgnoreCase(dientich);
            boolean kinhdoDung = KinhdoElement.getText().trim().equalsIgnoreCase(kinhdo);
            boolean vidoDung = VidoElement.getText().trim().equalsIgnoreCase(vido);
            if (!maVungmienDung) {System.out.println("Mã vùng miền không đúng. Mong đợi: " + maVungmien + " | Thực tế: " + maVungmienElement.getText());}
            if (!dienTichDung) {System.out.println("Diện tích không đúng. Mong đợi: " + dientich + " | Thực tế: " + dienTichElement.getText());}
            if (!kinhdoDung) {System.out.println("Kinh độ không đúng. Mong đợi: " + kinhdo + " | Thực tế: " + KinhdoElement.getText());}
            if (!vidoDung) {System.out.println("Vĩ độ không đúng. Mong đợi: " + vido + " | Thực tế: " + VidoElement.getText());}
            return ten.isDisplayed() && maVungmienElement.isDisplayed()
                    && dienTichElement.isDisplayed() && KinhdoElement.isDisplayed() && VidoElement.isDisplayed()
                    && Diembien.isDisplayed() && moTa.isDisplayed()
                    && maVungmienDung && dienTichDung && kinhdoDung && vidoDung;
        } catch (Exception e) {
            System.out.println(" Không hiển thị đầy đủ thông tin chi tiết: " + e.getMessage());
            return false;
        }
    }
    // Ham kiem tra cho trang thai Tat ca
    public boolean kiemTraTrangThaiKhiLocTatCa() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        Set<String> thuThap = new HashSet<>();
        Set<String> canCo   = Set.of("Nháp", "Cần xem xét","Xác nhận","Xóa");
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
            // Nếu đủ hết các trạng thái thì kết thúc sớm
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
}