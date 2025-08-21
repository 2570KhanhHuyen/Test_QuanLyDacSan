package TestQuanLyVungMienPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.QLVungMienPage;
import constant.constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChinhSua_XoaVungMien {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        constant.WEBDRIVER = new ChromeDriver();
        constant.WEBDRIVER.manage().window().maximize();
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        constant.WEBDRIVER.quit();
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
    @Test
    public void testChinhSuaVungMien_Thanhcong() {
        try{
        System.out.println("TC146 ‑ Người dùng có thể chỉnh sửa thông tin vùng miền thành công");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String tenVungMien = "Đà Nẵng";
        String maVungMien = "DN-002";
        String dienTich = "10000";
        String kinhDo = "11";
        String viDo = "9";
        qlvungmienPage.timVaXacThucChiTietVungMien(tenVungMien,maVungMien,dienTich,kinhDo,viDo);
        boolean Chitietvungmien = qlvungmienPage.isThongTinChiTietHienThiDL(tenVungMien,maVungMien,dienTich,kinhDo,viDo);
        Assert.assertTrue(Chitietvungmien, "Không mở được thông tin chi tiết của vùng miền");
        retryClick(() -> qlvungmienPage.clickMenuDots());
        retryClick(() -> qlvungmienPage.clickChinhSuaButton());
        qlvungmienPage.chinhSuaThongTinVungMien("Đà Nẵng","DN-001","Nháp","10000","11","9");
        retryClick(() -> qlvungmienPage.clickLuuNSP());
        retryClick(() -> qlvungmienPage.confirmPopupLuu());
        boolean capNhatThanhCong = qlvungmienPage.getCapNhatThanhCong();
        Assert.assertTrue(capNhatThanhCong, "Không hiển thị thông báo 'Cập nhật thành công'");
        boolean hienthidlcapnhat = qlvungmienPage.isThongTinChiTietHienThiDL("Đà Nẵng","DN-001","10000","11","9");
        Assert.assertTrue(hienthidlcapnhat,"Thông tin cập nhật hiển thị không đúng mong đợi");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC146: " + e.getMessage());
        }
    }
    @Test
    public void testChinhSuaVungMien_BoTrongCacTruongBatBuoc() {
        try{
        System.out.println("TC147 ‑ Người dùng không thể chỉnh sửa vùng miền thành công nếu bỏ trống các trường bắt buộc ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String tenVungMien = "Phú Yên";
        String maVungMien = "057";
        String dienTich = "150";
        String kinhDo = "19";
        String viDo = "13";
        qlvungmienPage.timVaXacThucChiTietVungMien(tenVungMien,maVungMien,dienTich,kinhDo,viDo);
        boolean Chitietvungmien = qlvungmienPage.isThongTinChiTietHienThiDL(tenVungMien,maVungMien,dienTich,kinhDo,viDo);
        Assert.assertTrue(Chitietvungmien, "Không mở được thông tin chi tiết của vùng miền");
        qlvungmienPage.clickMenuDots();
        qlvungmienPage.clickChinhSuaButton();
        qlvungmienPage.chinhSuaThongTinVungMien("","","Nháp","","","");
        qlvungmienPage.clickLuuNSP();
        qlvungmienPage.confirmPopupLuu();

        String actualMsg = qlvungmienPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi dữ liệu");

        String actualFieldError = qlvungmienPage.getTenVungMienFieldErrorr();qlvungmienPage.getMaVungMienFieldErrorr();qlvungmienPage.getDienTichFieldErrorr();
        qlvungmienPage.getKinhDonFieldErrorr();qlvungmienPage.getViDoFieldErrorr();String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới các trường bắt buộc");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC147: " + e.getMessage());
        }
    }
    @Test
    public void testHuyBoChinhSuaTTVungMien() {
        try{
        System.out.println("TC148 ‑ Người dùng có thể hủy bỏ quá trình chỉnh sửa thông tin vùng miền ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String tenVungMien = "Phú Yên";
        String maVungMien = "057";
        String dienTich = "150";
        String kinhDo = "19";
        String viDo = "13";
        qlvungmienPage.timVaXacThucChiTietVungMien(tenVungMien,maVungMien,dienTich,kinhDo,viDo);
        boolean Chitietvungmien = qlvungmienPage.isThongTinChiTietHienThiDL(tenVungMien,maVungMien,dienTich,kinhDo,viDo);
        Assert.assertTrue(Chitietvungmien, "Không mở được thông tin chi tiết của vùng miền");
        qlvungmienPage.clickMenuDots();
        qlvungmienPage.clickChinhSuaButton();
        qlvungmienPage.chinhSuaThongTinVungMien("Hà Nội","059","Nháp","125000","14","12");
        qlvungmienPage.clickHuyBoVungMien();
        qlvungmienPage.confirmPopupHuybo();
        boolean Hienthidl = qlvungmienPage.isThongTinChiTietHienThiDL(tenVungMien,maVungMien,dienTich,kinhDo,viDo);
        Assert.assertTrue(Hienthidl, "Thông tin chi tiết vùng mền không hiển thị lại");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC148: " + e.getMessage());
        }
    }
    @Test
    public void testXoaVungmien() {
        try {
            System.out.println("TC149 ‑ Người dùng có thể xóa vùng miền thành công");
            String tukhoa = "Bắc 555";
            LoginPage loginPage = new LoginPage();
            loginPage.open();
            HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
            loginPage.isLoginThanhCong();
            QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
            qlvungmienPage.searchVungMien(tukhoa);
            retryClick(() -> qlvungmienPage.clickTenByName(tukhoa));
            retryClick(() -> qlvungmienPage.clickMenuDots());
            retryClick(() -> qlvungmienPage.clickXoaButton());
            retryClick(() -> qlvungmienPage.confirmPopupLuu());
            String actualMsg = qlvungmienPage.getXoaThanhCongNotification();
            String expectedMsg = "Dữ liệu đã được xóa thành công";
            Assert.assertTrue(actualMsg.contains(expectedMsg), "Không hiển thị thông báo xóa thành công");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC149: " + e.getMessage());
        }
    }

    @Test
    public void testHuybo_XoaVungMien() {
        try{
        System.out.println("TC150 ‑ Người dùng có thể hủy bỏ thao tác xóa vùng miền");
        String tukhoa = "Nha Trang";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        qlvungmienPage.searchVungMien(tukhoa);
        retryClick(() -> qlvungmienPage.clickTenByName(tukhoa));
        Thread.sleep(2000);
        retryClick(() -> qlvungmienPage.clickMenuDots());
        retryClick(() -> qlvungmienPage.clickXoaButton());
        retryClick(() -> qlvungmienPage.confirmPopupQuayLai());
        boolean hienthidulieu = qlvungmienPage.isThongTinChiTietHienThiDL("Nha Trang","079","-284.28","109.219","12");
        Assert.assertTrue(hienthidulieu, "Không hiển thị lại dữ liệu của vùng miền");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC150: " + e.getMessage());
        }
    }
    @Test
    public void testGanDSvaoDSDacSan(){
        try{
            System.out.println("TC151 - Người dùng có thể gán đặc sản vào vùng miền");
            LoginPage loginPage = new LoginPage();
            loginPage.open();
            HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
            loginPage.isLoginThanhCong();
            QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
            String tenVungMien = "Miền Trung";
            String tenDacSan = "Bánh ép huế";
            retryClick(() -> qlvungmienPage.searchVungMien(tenVungMien));
            retryClick(() -> qlvungmienPage.clickTenByName(tenVungMien));
            retryClick(() -> qlvungmienPage.clickTabDacSan());
            Thread.sleep(2000);
            qlvungmienPage.clickChinhSuaDS_VungMien();
            qlvungmienPage.clickbtnCong();
            qlvungmienPage.clickTimKiemVaChonTen(tenDacSan);
            qlvungmienPage.clickSaveButton();
            boolean capNhatThanhCong = qlvungmienPage.getCapNhatThanhCong();
            Assert.assertTrue(capNhatThanhCong, "Không hiển thị thông báo 'Cập nhật thành công'");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC151: " + e.getMessage());
        }
    }
    @Test
    public void testGoDSkhoiDSDacSan(){
        try{
        System.out.println("TC152 - Người dùng có thể gỡ đặc sản khỏi danh sách đặc sản trong một vùng miền");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String tenVungMien = "Miền Trung";
        String tenDacSan = "Bánh ép huế";
        qlvungmienPage.searchVungMien(tenVungMien);
        qlvungmienPage.clickTenByName(tenVungMien);
        qlvungmienPage.clickTabDacSan();
        Thread.sleep(2000);
        qlvungmienPage.clickChinhSuaDS_VungMien();
        qlvungmienPage.GoDSTheoTen(tenDacSan);
        qlvungmienPage.clickSaveButton();
        boolean capNhatThanhCong = qlvungmienPage.getCapNhatThanhCong();
        Assert.assertTrue(capNhatThanhCong, "Không hiển thị thông báo 'Cập nhật thành công'");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC152: " + e.getMessage());
        }
    }
    @Test
    public void testHuyBoThaoTac_ChinhSuaDSDacSan() throws InterruptedException {
        try{
        System.out.println("TC153 - Người dùng có thể hủy bỏ thao tác chỉnh sửa/gán đặc sản vào danh sách đặc sản trong một vùng miền");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String tenVungMien = "Miền Trung";
        retryClick(() -> qlvungmienPage.searchVungMien(tenVungMien));
        retryClick(() -> qlvungmienPage.clickTenByName(tenVungMien));
        retryClick(() -> qlvungmienPage.clickTabDacSan());
        Thread.sleep(2000);
        qlvungmienPage.clickChinhSuaDS_VungMien();
        qlvungmienPage.clickHuyBoCSDS();
        boolean DanhsachDS_HienThi = qlvungmienPage.kiemTraBangDacSanCoHienThi();
        Assert.assertTrue(DanhsachDS_HienThi, "Danh sách các đặc sản không hiển thị lại");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Lỗi xảy ra trong TC153: " + e.getMessage());
        }
    }
}
