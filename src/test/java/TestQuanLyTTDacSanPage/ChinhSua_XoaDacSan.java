package TestQuanLyTTDacSanPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.QLTTDSPage;
import constant.constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChinhSua_XoaDacSan {
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
    public void testChinhSuaTTDSthanhcong() {
        System.out.println("TC53 ‑ Người dùng có thể chỉnh sửa đặc sản thành công");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Cao lầu";
        String dacSanVung = "Miền Trung";
        String trangThai = "Nháp";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickMenuDots();
        page.clickChinhSuaButton();
        page.chinhSuaThongTinDacSan("Cao lầu","Miền Trung","Cần kiểm tra");
        page.hideChatBot();
        page.clickLuu();
        page.confirmPopupLuu();
        boolean capNhatThanhCong = page.getCapNhatThanhCong();
        Assert.assertTrue(capNhatThanhCong, "Không hiển thị thông báo 'Cập nhật thành công'");
        boolean hienthidlcapnhat = page.isThongTinChiTietHienThiDL("Cao lầu", "Miền Trung", "Cần kiểm tra");
        Assert.assertTrue(hienthidlcapnhat,"Thông tin cập nhật hiển thị không đúng mong đợi");
    }
    // BUG 06 => CHỈNH SỬA ĐẶC SẢN VÙNG ĐÃ HIỆN THÔNG BÁO CẬP NHẬT THÀNH CÔNG NHƯNG KHÔNG LƯU LẠI DỮ LIỆU CẬP NHẬT
    // Tess lại lần 2: Đã Passed
    @Test
    public void testChinhSuaTTDSkhongthanhcong() {
        System.out.println("TC54 ‑ Người dùng không thể chỉnh sửa đặc sản thành công nếu bỏ trống tất cả các trường bắt buộc");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Vịt quay mắc mật";
        String dacSanVung = "Đông Bắc";
        String trangThai = "Cần kiểm tra";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickMenuDots();
        page.clickChinhSuaButton();
        page.chinhSuaThongTinDacSan("","..","");
        page.hideChatBot();
        page.clickLuu();
        page.confirmPopupLuu();
        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");
    }
    @Test
    public void testChinhSuaTTDS_BoTrongTenDS() {
        System.out.println("TC55 ‑ Người dùng không thể chỉnh sửa đặc sản thành công nếu bỏ trống trường tên đặc sản ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Vịt quay mắc mật";
        String dacSanVung = "Đông Bắc";
        String trangThai = "Cần kiểm tra";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickMenuDots();
        page.clickChinhSuaButton();
        page.chinhSuaThongTinDacSan("","Miền Trung","Xác nhận ");
        page.hideChatBot();
        page.clickLuu();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi dữ liệu");

        String actualFieldError = page.getTenDacSanFieldError();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Tên đặc sản");
    }
    @Test
    public void testChinhSuaTTDS_BoTrongDacSanVung() {
        System.out.println("TC56 ‑ Người dùng không thể chỉnh sửa đặc sản thành công nếu bỏ trống trường đặc sản vùng ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Vịt quay mắc mật";
        String dacSanVung = "Đông Bắc";
        String trangThai = "Cần kiểm tra";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickMenuDots();
        page.clickChinhSuaButton();
        page.chinhSuaThongTinDacSan("Vịt quay mắc mật ",null,"Xác nhận");
        page.hideChatBot();
        page.clickLuu();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi dữ liệu");

        String actualFieldError = page.getDacSanVungFieldError();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Đặc sản vùng");
    }
    @Test
    public void testHuyBoChinhSuaTTDS() {
        System.out.println("TC57 ‑ Người dùng có thể hủy bỏ chỉnh sửa thông tin đặc sản ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Vịt quay mắc mật";
        String dacSanVung = "Đông Bắc";
        String trangThai = "Cần kiểm tra";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickMenuDots();
        page.clickChinhSuaButton();
        page.chinhSuaThongTinDacSan("Vịt quay mắc mật ","Đông Bắc","Xác nhận ");
        page.hideChatBot();
        page.clickHuyBo();
        page.confirmPopupHuybo();
        boolean Hienthidl = page.isThongTinChiTietHienThiDL("Vịt quay mắc mật","Đông Bắc","Cần kiểm tra");
        Assert.assertTrue(Hienthidl, "Thông tin chi tiết đặc sản không hiển thị lại");
    }
    @Test
    public void testChinhSuaNhomDS(){
        System.out.println("TC58 ‑ Người dùng có thể chỉnh sửa thông tin nhóm đặc sản thành công ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Bánh Pía";
        String dacSanVung = "Miền Nam";
        String trangThai = "Cần kiểm tra";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickNutChinhSuaNDS();
        page.chonTuDropdownGoiY("Bánh kẹo");
        page.xoaNhomDacSan("Bánh");
        page.LuuCSNDS();
        boolean Capnhatthanhcong = page.getCapNhatThanhCong();
        Assert.assertTrue(Capnhatthanhcong, "Không hiển thị thông báo 'Cập nhật thành công'");
    }
    @Test
    public void testXoaDs() {
        System.out.println("TC59 ‑ Người dùng có thể xóa đặc sản thành công ");
        String tukhoa = "Bánh dàyy";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tukhoa);
        page.clickDacSanTrongKetQua(tukhoa);
        page.clickMenuDots();
        page.clickXoaButton();
        page.confirmPopupLuu();
        String actualMsg = page.getXoaThanhCongNotification();
        String expectedMsg = "Dữ liệu đã được xóa thành công";
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Không hiển thị thông báo xóa thành công");
    }
    @Test
    public void testHuybo_XoaDS() throws InterruptedException {
        System.out.println("TC60 ‑ Người dùng có thể hủy bỏ thao tác xóa đặc sản");
        String tukhoa = "Bánh Pía";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tukhoa);
        page.clickDacSanTrongKetQua(tukhoa);
        Thread.sleep(2000);
        page.clickMenuDots();
        page.clickXoaButton();
        page.confirmPopupQuayLai();
        boolean hienthidulieu = page.isThongTinChiTietHienThiDL("Bánh Pía","Miền Nam","Cần kiểm tra");
        Assert.assertTrue(hienthidulieu, "Không hiển thị lại dữ liệu của đặc sản");
    }
    @Test
    public void testGanDSvaoNhomDS() {
        System.out.println("TC61 ‑ Người dùng có thể gán đặc sản vào nhóm đặc sản");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Bánh Pía";
        String dacSanVung = "Miền Nam";
        String trangThai = "Cần kiểm tra";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickNutChinhSuaNDS();
        page.chonTuDropdownGoiY("Bánh");
        page.LuuCSNDS();
        boolean Capnhatthanhcong = page.getCapNhatThanhCong();
        Assert.assertTrue(Capnhatthanhcong, "Không hiển thị thông báo 'Cập nhật thành công'");
    }
    @Test
    public void testGoDSkhoiNhomDS() {
        System.out.println("TC62 ‑ Người dùng có thể gỡ nhóm đặc sản ở một đặc sản bất kì");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tenDacSan = "Bánh Pía";
        String dacSanVung = "Miền Nam";
        String trangThai = "Cần kiểm tra";
        page.timVaXacThucChiTietDacSan(tenDacSan,dacSanVung,trangThai);
        boolean Chitietds = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(Chitietds, "Không mở được thông tin chi tiết của đặc sản");
        page.clickNutChinhSuaNDS();
        page.xoaNhomDacSan("Bánh");
        page.LuuCSNDS();
        boolean Capnhatthanhcong = page.getCapNhatThanhCong();
        Assert.assertTrue(Capnhatthanhcong, "Không hiển thị thông báo 'Cập nhật thành công'");
    }
}
