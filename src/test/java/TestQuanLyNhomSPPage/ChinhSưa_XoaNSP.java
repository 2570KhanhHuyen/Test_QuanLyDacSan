package TestQuanLyNhomSPPage;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.QLNhomSPPage;
import constant.constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChinhSưa_XoaNSP {
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
    public void testChinhSuaNSP_Thanhcong() {
        System.out.println("TC102 ‑ Người dùng có thể chỉnh sửa nhóm sản phẩm thành công");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Ninh Bình";
        String maNSP = "765";
        String trangThai = "Hiển thị";
        qlnspPage.timVaXacThucChiTietNSP(tenNSP,maNSP,trangThai);
        boolean Chitietnsp = qlnspPage.isThongTinChiTietHienThiDL(tenNSP,maNSP,trangThai);
        Assert.assertTrue(Chitietnsp, "Không mở được thông tin chi tiết của nhóm sản phẩm");
        qlnspPage.clickMenuDots();
        qlnspPage.clickChinhSuaButton();
        qlnspPage.chinhSuaThongTinNSP("Đặc sản Ninh Bình","Không hiển thị");
        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();
        boolean capNhatThanhCong = qlnspPage.getCapNhatThanhCong();
        Assert.assertTrue(capNhatThanhCong, "Không hiển thị thông báo 'Cập nhật thành công'");
        boolean hienthidlcapnhat = qlnspPage.isThongTinChiTietHienThiDL("Đặc sản Ninh Bình", "765", "Không hiển thị");
        Assert.assertTrue(hienthidlcapnhat,"Thông tin cập nhật hiển thị không đúng mong đợi");
    }
    @Test
    public void testChinhSuaNSP_BoTrongTenNSP() {
        System.out.println("TC103 ‑ Người dùng không thể chỉnh sửa nhóm sản phẩm không thành công nếu bỏ trống trường 'Tên nhóm sản phẩm' ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Tây Bắc";
        String maNSP = "TB-009";
        String trangThai = "Hiển thị";
        qlnspPage.timVaXacThucChiTietNSP(tenNSP,maNSP,trangThai);
        boolean Chitietnsp = qlnspPage.isThongTinChiTietHienThiDL(tenNSP,maNSP,trangThai);
        Assert.assertTrue(Chitietnsp, "Không mở được thông tin chi tiết của nhóm sản phẩm");
        qlnspPage.clickMenuDots();
        qlnspPage.clickChinhSuaButton();
        qlnspPage.chinhSuaThongTinNSP("","Không hiển thị");
        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi dữ liệu");

        String actualFieldError = qlnspPage.getTenNSPFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Tên nhóm sản phẩm");
    }
    @Test
    public void testHuyBoChinhSuaTTNSP() {
        System.out.println("TC104 ‑ Người dùng có thể hủy bỏ chỉnh sửa thông tin nhóm sản phẩm ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Tây Bắc";
        String maNSP = "TB-009";
        String trangThai = "Hiển thị";
        qlnspPage.timVaXacThucChiTietNSP(tenNSP,maNSP,trangThai);
        boolean Chitietnsp = qlnspPage.isThongTinChiTietHienThiDL(tenNSP,maNSP,trangThai);
        Assert.assertTrue(Chitietnsp, "Không mở được thông tin chi tiết của nhóm sản phẩm");
        qlnspPage.clickMenuDots();
        qlnspPage.clickChinhSuaButton();
        qlnspPage.chinhSuaThongTinNSP("Đặc sản Tây Bắc 5*","Không hiển thị");
        qlnspPage.hideChatBot();
        qlnspPage.clickHuyBo();
        qlnspPage.confirmPopupHuybo();
        boolean Hienthidl = qlnspPage.isThongTinChiTietHienThiDL(tenNSP,maNSP,trangThai);
        Assert.assertTrue(Hienthidl, "Thông tin chi tiết nhóm sản phẩm không hiển thị lại");
    }
    @Test
    public void testXoaNhomSP() {
        System.out.println("TC105 ‑ Người dùng có thể xóa nhóm sản phẩm thành công ");
        String tukhoa = "gagaga";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.search(tukhoa);
        qlnspPage.clickTenByName(tukhoa);
        qlnspPage.clickMenuDots();
        qlnspPage.clickXoaButton();
        qlnspPage.confirmPopupLuu();
        String actualMsg = qlnspPage.getXoaThanhCongNotification();
        String expectedMsg = "Dữ liệu đã được xóa thành công";
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Không hiển thị thông báo xóa thành công");
    }
    @Test
    public void testHuybo_XoaNhomSP() {
        System.out.println("TC106 ‑ Người dùng có thể hủy bỏ thao tác xóa nhóm sản phẩm");
        String tukhoa = "Bánh";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.search(tukhoa);
        qlnspPage.clickTenByName(tukhoa);
        qlnspPage.clickMenuDots();
        qlnspPage.clickXoaButton();
        qlnspPage.confirmPopupQuayLai();
        boolean hienthidulieu = qlnspPage.isThongTinChiTietHienThiDL("Bánh","vo009","Hiển thị");
        Assert.assertTrue(hienthidulieu, "Không hiển thị lại dữ liệu của nhóm sản phẩm");
    }
}
