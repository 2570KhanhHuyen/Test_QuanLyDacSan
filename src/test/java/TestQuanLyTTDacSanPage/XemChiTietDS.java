package TestQuanLyTTDacSanPage;

import PageObjects.QLTTDSPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import constant.constant;

public class XemChiTietDS {
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
    public void XemchitietDSHopLe_QuaTungTrang() {
        System.out.println("TC20 - Người dùng có thể xem chi tiết thông tin đặc sản hợp lệ qua từng trang");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        String tenDacSan = "Cà phê Trung Nguyên";
        String dacSanVung = "Tây Nguyên";
        String trangThai = "Xác nhận";
        boolean thongTinHienThiDu = qlttdsPage.timVaXacThucChiTietDacSan(tenDacSan, dacSanVung,trangThai);
        Assert.assertTrue(thongTinHienThiDu, "Thông tin chi tiết của đặc sản không hiển thị đầy đủ");
    }
    @Test
    public void testThuGonThongTinDacSan() {
        System.out.println("TC21 - Người dùng có thể thu gọn thông tin chi tiết đặc sản");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String ten = "Khô mè bà liễu";
        String dacSanVung = "Tây Bắc";
        String trangThai = "Cần kiểm tra";
        boolean hienThi = page.timVaXacThucChiTietDacSan(ten,dacSanVung,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết đặc sản phải được hiển thị");
        page.clickThuGonThongTinDacSan();
        boolean daAn = page.isThongTinChiTietAn(ten);
        Assert.assertTrue(daAn, "Thông tin đặc sản phải được thu gọn sau khi click");
    }
    @Test
    public void tessHienThiLaiTTDS() {
        System.out.println("TC22 - Người dùng có thể cho hiển thị lại thông tin đặc sản sau khi thu gọn");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String ten = "Khô mè bà liễu";
        String dacSanVung = "Tây Bắc";
        String trangThai = "Cần kiểm tra";
        boolean hienThi = page.timVaXacThucChiTietDacSan(ten, dacSanVung,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết đặc sản phải được hiển thị");
        page.clickThuGonThongTinDacSan();
        boolean daAn = page.isThongTinChiTietAn(ten);
        Assert.assertTrue(daAn, "Thông tin đặc sản phải được thu gọn sau khi click");
        page.clickHienThiLaiThongTinDacSan();
        boolean hienLai = page.isThongTinChiTietHienThiDL(ten,dacSanVung,trangThai);
        Assert.assertTrue(hienLai, "Thông tin đặc sản phải được hiển thị lại sau khi click lần hai");
    }
    @Test
    public void testThuGonTTNhomDacSan() {
        System.out.println("TC23 - Người dùng có thể thu gon thông tin nhóm đặc sản khi xem chi tiết thông tin của một đặc sản nào đó");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String ten = "Mè xửng Thiên Hương";
        String dacSanVung = "Miền Trung";
        String trangThai = "Xác nhận";
        boolean hienThi = page.timVaXacThucChiTietDacSan(ten,dacSanVung,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết đặc sản phải được hiển thị");
        page.clickThuGonNhomDacSan();
        boolean daAn = page.isThongTinNhomDacSanDaAn();
        Assert.assertTrue(daAn, "Thông tin nhóm đặc sản phải được thu gọn sau khi click");
    }
    @Test
    public void testHienThiLaiTTNhomDacSan() {
        System.out.println("TC24 - Người dùng có thể cho hiển thị lại thông tin nhóm đặc sản sau khi thu gọn của một đặc sản nào đó");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String ten = "Mè xửng Thiên Hương";
        String dacSanVung = "Miền Trung";
        String trangThai = "Xác nhận";
        boolean hienThi = page.timVaXacThucChiTietDacSan(ten,dacSanVung,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết đặc sản phải được hiển thị");
        page.clickThuGonNhomDacSan();
        boolean daAn = page.isThongTinNhomDacSanDaAn();
        Assert.assertTrue(daAn, "Thông tin nhóm đặc sản phải được thu gọn sau khi click");
        page.clickHienThiLaiThongTinNhomDS();
        boolean daHien = page.isThongTinNhomDacSanDaHienThi();
        Assert.assertTrue(daHien,"Thong tin nhom dac san phai duoc hien thi lai sau khi click" );
    }
    @Test
    public void testXemChiTietDacSanTheoTrangThai() {
        System.out.println("TC25 - Người dùng có thể xem thông tin chi tiết đặc sản theo trạng thái");
        String tenDacSan = "Mắm Nêm Dì Cẩn";
        String dacSanVung = "Miền Trung";
        String trangThai = "Xác nhận";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.locTrangThaiTheoTen(trangThai);
        page.clickTenByName(tenDacSan);
        boolean hienThi = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(hienThi, "Không mở được chi tiết đặc sản ở trạng thái " + trangThai);
        boolean dungTrangThai = page.kiemTraTrangThaiChiTiet(trangThai);
        Assert.assertTrue(dungTrangThai, "Trạng thái hiển thị không đúng: " + trangThai);
    }
    @Test
    public void testThuGonListDS() {
        System.out.println("TC26 - Người dùng có thể thu gọn danh sách đặc sản");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.clickThuGonList_Danhsach();
        boolean daAn = page.waitForListDanhsachoDisappear();
        Assert.assertTrue(daAn, "Thông tin list đặc sản chưa được thu gọn sau khi click");
    }
    @Test
    public void testHienThiLaiListDS() throws InterruptedException {
        System.out.println("TC27 - Người dùng có thể click hiển thị lại list đặc sản sau khi thu ẩn");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.clickThuGonList_Danhsach();
        boolean daAn = page.waitForListDanhsachoDisappear();
        Assert.assertTrue(daAn, "List đặc sản chưa được thu gọn sau khi click");
        Thread.sleep(2000);
        page.clickHienThiLaiList_Danhsach();
        boolean daHien = page.waitForListDanhsachToAppear();
        Assert.assertTrue(daHien, "List đặc sản chưa được hiển thị lại sau khi click");
    }
}