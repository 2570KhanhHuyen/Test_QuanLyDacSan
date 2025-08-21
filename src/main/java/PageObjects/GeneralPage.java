package PageObjects;
import common.HeaderComponent;
import common.SiderbarMenu;
import common.NotificationComponent;
import constant.constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.Normalizer;
import java.time.Duration;
import java.util.List;

public class GeneralPage {
    // Main
    private HeaderComponent header = new HeaderComponent();
    private SiderbarMenu sidebar = new SiderbarMenu();
    private NotificationComponent notify = new NotificationComponent();

    //Xpath chung
    private final By formThemMoi = By.xpath("//div[@class='card-body']");
    private final By menuDots = By.xpath("//i[@class='mdi mdi-dots-vertical']");
    private final By btnLuuDS = By.xpath("//button[@id='btn_edit_entity']");
    private final By btnLuuNSP = By.id("btn_create_entity");
    private final By btnHuyBo = By.xpath("//button[@id='btn_cancel_entity']");
    private final By popupLuuBtn = By.xpath("//button[@id='btn_msgbox_OK']");
    private final By popupQuayLaiBtn = By.xpath("//button[@id='btn_msgbox_NO']");
    private final By popupHuyBoBtn = By.xpath("//button[@id='btn_msgbox_OK']");
    private final By txtSearch = By.xpath("//input[@id='inp_search']");
    private final By btnresize_minimizeList= By.xpath("//a[contains(@class,'btn-resize')]//i[contains(@class,'mdi-window-minimize')]");
    private final By btnresize_maximizeList = By.xpath("//a[contains(@class,'btn-resize')]//i[contains(@class,'mdi-window-maximize')]");
    private By ajaxLoader = By.className("ajax_loader");
    private By noDataMessage = By.xpath("//li[contains(text(),'Chưa có dữ liệu')]");
    private By ulListItems = By.xpath("//ul[@id='ul-list']//div[contains(@class,'simplebar-content')]//h6");
    private final By btnXoa = By.id("btn_del");
    private final By btnEdit = By.id("btn_edit");
    private final By ClearIcon = By.id("clear_icon");
    private final By btnThemMoi = By.xpath("//button[@id='btn_new_entity']");
    private final By itemh6locator = By.xpath("//ul[@id='ul-list']//h6");
    private By nspListContentLocator = By.xpath("//ul[@id='ul-list']//div[@class='simplebar-content']");
    private final By itemLocator   = By.xpath("//ul[@id='ul-list']//li");
    private final By itemNameXpath = By.xpath(".//h6");
    private final By badgeXpath = By.xpath(".//span[contains(@class,'badge')]");
    private final By btnNext = By.xpath("//a[normalize-space()='»' and not(ancestor::li[contains(@class,'disabled')])]");
    private final By btnLocTrangThai = By.xpath("//i[@class='mdi mdi-filter-outline']");
    private final By Dropdown_Container = By.xpath("//div[@role='listbox' or contains(@class,'dropdown')]");
    private final By trangThaiThucTe = By.xpath("//h6[contains(text(),'Trạng thái')]/following-sibling::p");
    private By nspListItemLocator = By.cssSelector("#ul-list .simplebar-content li.entity-item");

    public QLTTDSPage goToQLTTDSPage() {
        sidebar.clickQuanLyDacSan();
        sidebar.clickQuanLyThongTinDacSan();
        return new QLTTDSPage();
    }

    public QLNhomSPPage goToQLNhomSPPage() {
        sidebar.clickQuanLyDacSan();
        sidebar.clickQuanLyNhomSanPham();
        return new QLNhomSPPage();
    }

    public QLVungMienPage goToQLVungMienPage() {
        sidebar.clickQuanLyDacSan();
        sidebar.clickQuanLyVungMien();
        return new QLVungMienPage();
    }
    //Notification
    public String getSuccessNotification() {return notify.getSuccessNotification();}
    public String getErrorNotification(){return  notify.getErrorNotification();}
    public String getMaQLTrungNotification(){return notify.getMaQLTrungErrorNotification();}
    public String getTenTonTaiNotification(){return notify.getTenTrungErrorNotification();}
    public String getXoaThanhCongNotification(){return notify.getSuccessDelNotification();}
    public String getTruyXuatdlNotification(){return notify.getTruyXuatDLErrorNotification();}
    public boolean getCapNhatThanhCong(){return notify.isThongBaoCapNhatThanhCong();}

    public boolean isThemMoiFormClosed() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(formThemMoi));
        } catch (Exception e) {
            return false;
        }
    }
    public void clickMenuDots(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(10));
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(menuDots));
        item.click();
    }
    public void clickChinhSuaButton() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(btnEdit));
        editBtn.click();
    }
    public void clickXoaButton() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(btnXoa));
        editBtn.click();
    }
    public void clickThemMoi(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(btnThemMoi));
        button.click();
    }
    public void clickLuu() {
        constant.WEBDRIVER.findElement(btnLuuDS).click();
    }
    public void hideChatBot() {
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript(
                "let chatbot = document.querySelector('.boxchat'); if (chatbot) chatbot.style.display = 'none';"
        );
    }
    public void clickLuuNSP() {
        constant.WEBDRIVER.findElement(btnLuuNSP).click();
    }
    public void clickHuyBo(){
        constant.WEBDRIVER.findElement(btnHuyBo).click();
    }
    public void confirmPopupLuu() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(popupLuuBtn));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(popupLuuBtn));
        btn.click();
    }
    public void confirmPopupHuybo() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(popupHuyBoBtn));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(popupHuyBoBtn));
        btn.click();
    }
    public void confirmPopupQuayLai() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(popupQuayLaiBtn));
        btn.click();
    }
    public void clickXoaTimKiem(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(10));
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(ClearIcon));
        item.click();
    }
    public void clickTenByName(String name) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By dacSanItem = By.xpath("//li[contains(@class,'entity-item')]//h6[contains(@class,'mb-0') and contains(text(),'" + name + "')]");
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(dacSanItem));
        item.click();
    }
    public void locTrangThaiTheoTen(String tenTrangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement filterBtn = wait.until(ExpectedConditions.elementToBeClickable(btnLocTrangThai));
        filterBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(Dropdown_Container));
        By trangThaiOption = By.xpath("//div[normalize-space()='" + tenTrangThai + "']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(trangThaiOption));
        option.click();
    }
    public void clickThuGonList_Danhsach(){
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnresize_minimizeList));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public void clickHienThiLaiList_Danhsach() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement collapseButton = wait.until(ExpectedConditions.presenceOfElementLocated(btnresize_maximizeList));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
        wait.until(ExpectedConditions.elementToBeClickable(collapseButton));
        ((JavascriptExecutor) constant.WEBDRIVER).executeScript("arguments[0].click();", collapseButton);
    }
    public int getNumberOfNSPItems() {
        try {
            WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(nspListItemLocator));
        } catch (Exception e) {
            System.err.println("Không tìm thấy bất kỳ item NSP nào hoặc chờ timeout: " + e.getMessage());
            return 0;
        }
        List<WebElement> items = constant.WEBDRIVER.findElements(nspListItemLocator);
        return items.size();
    }
    public boolean waitForListDanhsachToAppear() {
        try {
            WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER,Duration.ofSeconds(10));
            WebElement listContentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nspListContentLocator));
            return listContentElement.isDisplayed();
        } catch (Exception e) {
            System.err.println("Lỗi khi chờ nội dung danh sách xuất hiện: " + e.getMessage());
            return false;
        }
    }
    public boolean waitForListDanhsachoDisappear() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(nspListContentLocator));
        } catch (Exception e) {
            System.err.println("Lỗi khi chờ nội dung danh sách thu ẩn lại: " + e.getMessage());
            return false;
        }
    }
    public String getSearchBoxText() {
        WebElement searchBox = constant.WEBDRIVER.findElement(txtSearch);
        return searchBox.getAttribute("value");
    }
    // loại bỏ dấu câu
    public String removeVietnameseAccents(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String result = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        result = result.replace('đ', 'd').replace('Đ', 'D');
        return result;
    }
    public int demSoKetQuaTheoTen(String tukhoa) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ulListItems));
        int count = 0;
        String keywordNormalized = removeVietnameseAccents(tukhoa).trim().toLowerCase();
        for (WebElement item : items) {
            String itemTextNormalized = removeVietnameseAccents(item.getText()).trim().toLowerCase();
            if (itemTextNormalized.contains(keywordNormalized)) {
                count++;
            }
        }
        return count;
    }
    public boolean kiemTraTrangThaiChiTiet(String tenTrangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            WebElement pTrangThai = wait.until(ExpectedConditions.visibilityOfElementLocated(trangThaiThucTe));
            String text = pTrangThai.getText().trim();
            System.out.println("Trạng thái hiển thị: " + text);
            return text.equalsIgnoreCase(tenTrangThai.trim());
        } catch (Exception e) {
            return false;
        }
    }
    public void search(String keyword) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(txtSearch));
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
    public boolean kiemTraChuaCoDuLieu() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(5));
        try {
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(noDataMessage));
            return msg.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    //ktra trong truong hop co item hien thi
    public boolean kiemTraKetQuaTimKiem(String tuKhoa) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        String tuKhoaNormalized = removeVietnameseAccents(tuKhoa).trim().toLowerCase();
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(noDataMessage),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(itemh6locator)
            ));
            List<WebElement> noDataElements = constant.WEBDRIVER.findElements(noDataMessage);
            if (!noDataElements.isEmpty() && noDataElements.get(0).isDisplayed()) {
                return false;
            }
            List<WebElement> items = constant.WEBDRIVER.findElements(itemh6locator);
            if (items.isEmpty()) {
                return false;
            }
            for (WebElement item : items) {
                String itemTextNormalized = removeVietnameseAccents(item.getText()).trim().toLowerCase();
                if (itemTextNormalized.contains(tuKhoaNormalized)) {
                    return true;
                }
            }
            return false;
        } catch (TimeoutException e) {
            return false;
        }
    }
    // Cái này kiểm tra đúng trong hai trường hợp (Có item hoặc ko có item)
    public boolean kiemTraKetQuaTimKiem_2TH(String tuKhoa) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        String tuKhoaNormalized = removeVietnameseAccents(tuKhoa).trim().toLowerCase();

        try {
            return wait.until(driver -> {
                List<WebElement> noData = driver.findElements(noDataMessage);
                if (!noData.isEmpty() && noData.get(0).isDisplayed()) {
                    return true;
                }

                List<WebElement> items = driver.findElements(itemh6locator);
                if (!items.isEmpty()) {
                    for (WebElement item : items) {
                        String itemTextNormalized = removeVietnameseAccents(item.getText()).trim().toLowerCase();
                        if (itemTextNormalized.contains(tuKhoaNormalized)) {
                            return true;
                        }
                    }
                }
                return false;
            });
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean kiemTraKetHopTimKiemVaTrangThaiToanTrang(String tuKhoa, String trangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        String tuKhoaNormalized = removeVietnameseAccents(tuKhoa.toLowerCase().trim());
        boolean isTatCa = trangThai.equalsIgnoreCase("Tất cả");
        int page = 0;
        while (true) {
            page++;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
            // Kiểm tra nếu hiển thị "Chưa có dữ liệu"
            try {
                WebElement noDataElement = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(2))
                        .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(text(),'Chưa có dữ liệu')]")));
                if (noDataElement != null && noDataElement.isDisplayed()) {
                    System.out.println("Trang " + page + ": Hiển thị 'Chưa có dữ liệu' – đúng kỳ vọng.");
                    return true;
                }
            } catch (TimeoutException ignored) {
                // Không thấy 'Chưa có dữ liệu' -> tiếp tục kiểm tra bình thường
            }
            boolean daLoad = false;
            for (int retry = 0; retry < 3; retry++) {
                List<WebElement> items = constant.WEBDRIVER.findElements(itemLocator);
                if (!items.isEmpty()) {
                    boolean allMatch = true;
                    for (WebElement item : items) {
                        try {
                            WebElement nameElement = item.findElement(itemNameXpath);
                            String nameText = nameElement.getText().toLowerCase().trim();
                            String nameNormalized = removeVietnameseAccents(nameText);

                            if (!nameNormalized.contains(tuKhoaNormalized)) {
                                System.out.println("Trang " + page + ": '" + nameText + "' không chứa từ khóa '" + tuKhoa + "'");
                                allMatch = false;
                                break;
                            }

                            if (!isTatCa) {
                                WebElement badgeElement = item.findElement(badgeXpath);
                                String badgeText = badgeElement.getText().trim();
                                if (!badgeText.equalsIgnoreCase(trangThai)) {
                                    System.out.println("Trang " + page + ": Trạng thái '" + badgeText + "' không khớp với '" + trangThai + "'");
                                    allMatch = false;
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Trang " + page + ": Lỗi khi đọc item.");
                            return false;
                        }
                    }
                    if (allMatch) {
                        daLoad = true;
                        break;
                    }
                }

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (!daLoad) {
                System.out.println("Trang " + page + ": Dữ liệu không hợp lệ hoặc chưa load kịp.");
                return false;
            }
            List<WebElement> nextBtn = constant.WEBDRIVER.findElements(btnNext);
            if (nextBtn.isEmpty()) break;
            WebElement oldItem = constant.WEBDRIVER.findElements(itemLocator).get(0);
            nextBtn.get(0).click();
            wait.until(ExpectedConditions.stalenessOf(oldItem));
        }
        return true;
    }
    // Ham nay chi dung kiem tra cho cac trang thai rieng
    public boolean kiemTraTrangThaiList_LocQuaTatCaTrang(String tenTrangThai) {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        By spanTrangThai = By.xpath("//span[contains(@class,'badge') and normalize-space(text())='" + tenTrangThai + "']");
        int pageCounter = 0;
        while (true) {
            pageCounter++;
            wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxLoader));
            boolean trangHopLe = false;
            for (int retry = 0; retry < 3; retry++) {
                List<WebElement> allItems = constant.WEBDRIVER.findElements(itemLocator);
                List<WebElement> trangThaiBadges = constant.WEBDRIVER.findElements(spanTrangThai);
                if (!allItems.isEmpty() && allItems.size() == trangThaiBadges.size()) {
                    boolean allMatch = trangThaiBadges.stream()
                            .allMatch(e -> e.getText().trim().equalsIgnoreCase(tenTrangThai));
                    if (allMatch) {
                        trangHopLe = true;
                        break;
                    }
                }
                try { Thread.sleep(2000); } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!trangHopLe) {
                System.out.println(" Trang " + pageCounter + " chưa load đúng trạng thái '" + tenTrangThai + "'");
                return false;
            }
            List<WebElement> nextBtn = constant.WEBDRIVER.findElements(btnNext);
            if (nextBtn.isEmpty()) {
                break;
            }
            WebElement firstItem = constant.WEBDRIVER.findElement(itemLocator);
            nextBtn.get(0).click();
            wait.until(ExpectedConditions.stalenessOf(firstItem));
        }
        return true;
    }
    public boolean isLoginThanhCong() {
        WebDriverWait wait = new WebDriverWait(constant.WEBDRIVER, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h5[contains(text(),'Chào mừng quay trở lại !')]")));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Không tìm thấy thông báo 'Chào mừng quay trở lại' sau khi login.");
            return false;
        }
    }
    public void retryClick(Runnable clickAction) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                clickAction.run();
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementNotInteractableException e) {
                attempts++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        throw new RuntimeException("Không thể tương tác với element sau 3 lần thử.");
    }
}