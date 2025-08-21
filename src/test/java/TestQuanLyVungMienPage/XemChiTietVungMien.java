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

public class XemChiTietVungMien {
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
    @Test
    public void XemchitietVungMienHopLe_QuaTungTrang() {
        System.out.println("TC128 - Người dùng có thể xem chi tiết thông tin vùng miền thành công qua từng trang");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String tenVungmien = "Miền Trung";
        String maVungmien = "098";
        String dienTich = "151.234";
        String kinhDo = "106";
        String viDo = "17";
        boolean thongTinHienThiDu = qlvungmienPage.timVaXacThucChiTietVungMien(tenVungmien,maVungmien,dienTich,kinhDo,viDo);
        Assert.assertTrue(thongTinHienThiDu, "Thông tin chi tiết của vùng miền không hiển thị đầy đủ");
    }
    @Test
    public void testXemChiTietVungMienPTheoTrangThaiKhacnhau(){
        System.out.println("TC129 - Người dùng có thể xem thông tin chi tiết vùng miền theo trạng thái khác nhau như: Nháp, Cần xem xét, Xác nhận, Xóa");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String trangThai = "Xác nhận"; //Nháp,Cần xem xét, Xác nhận, Xóa
        String tenVungmien = "Tây Bắc";
        String maVungmien = "033";
        String dienTich = "50.576";
        String kinhDo = "222";
        String viDo = "222";
        qlvungmienPage.locTrangThaiTheoTen(trangThai);
        qlvungmienPage.clickTenByName(tenVungmien);
        boolean hienThi = qlvungmienPage.isThongTinChiTietHienThiDL(tenVungmien,maVungmien,dienTich,kinhDo,viDo);
        Assert.assertTrue(hienThi, "Không mở được chi tiết vùng miền ở trạng thái " + trangThai);
    }
    @Test
    public void testThuGonListVungmien() {
        System.out.println("TC130 - Người dùng có thể thu gọn danh sách vùng miền");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        qlvungmienPage.clickThuGonList_Danhsach();
        boolean daAn = qlvungmienPage.waitForListDanhsachoDisappear();
        Assert.assertTrue(daAn, "List vùng miền chưa được thu gọn sau khi click");
    }
    @Test
    public void testHienThiLaiListVungmien() throws InterruptedException {
        System.out.println("TC131 - Người dùng có thể click hiển thị lại list thông tin vùng miền sau khi thu ẩn");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        qlvungmienPage.clickThuGonList_Danhsach();
        boolean daAn = qlvungmienPage.waitForListDanhsachoDisappear();
        Assert.assertTrue(daAn, "List vùng miền chưa được thu gọn sau khi click");
        Thread.sleep(2000);
        qlvungmienPage.clickHienThiLaiList_Danhsach();
        boolean daHien = qlvungmienPage.waitForListDanhsachToAppear();
        Assert.assertTrue(daHien, "List vùng miền chưa được hiển thị lại sau khi click");
    }
    @Test
    public void TestChuyenTabVungMien_DacSan() {
        System.out.println("TC132 - Người dùng có thể chuyển tab 'Vùng miền' sang tab 'Đặc sản' và ngược lại để xem chi tiêt thông tin của từng tab");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage qlvungmienPage = homePage.goToQLVungMienPage();
        String tenVungmien = "Miền Trung";
        String maVungmien = "098";
        String dienTich = "151.234";
        String kinhDo = "106";
        String viDo = "17";
        boolean thongTinHienThiDu = qlvungmienPage.timVaXacThucChiTietVungMien(tenVungmien,maVungmien,dienTich,kinhDo,viDo);
        Assert.assertTrue(thongTinHienThiDu, "Thông tin chi tiết của vùng miền không hiển thị đầy đủ");
        qlvungmienPage.clickTabDacSan();
        boolean thongtinDacSanHienThi = qlvungmienPage.isThongTinTableDSHienThi();
        Assert.assertTrue(thongtinDacSanHienThi, "Thông tin bảng danh sách đặc sản không hiển thị");
        qlvungmienPage.clickTabVungmien();
        boolean thongTinHienThiDulieu = qlvungmienPage.timVaXacThucChiTietVungMien(tenVungmien,maVungmien,dienTich,kinhDo,viDo);
        Assert.assertTrue(thongTinHienThiDulieu, "Thông tin chi tiết của vùng miền không hiển thị đầy đủ");
    }
}
