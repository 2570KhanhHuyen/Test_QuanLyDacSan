package TestQuanLyNhomSPPage;

import PageObjects.*;
import common.Random_ThemMoiNSP;
import constant.constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ThemmoiNSP {
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
    public void ThemmoiNSPThanhCong_VoiTatCaTruongBatBuoc() {
        System.out.println("TC93 - Thêm mới nhóm sản phẩm thành công");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String tenNSP = Random_ThemMoiNSP.generateRandomTenNSP();
        String maNSP = Random_ThemMoiNSP.generateMaNSP(tenNSP);
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterTenNSP(tenNSP);
        qlnspPage.enterMaNSP(maNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getSuccessNotification();
        String expectedMsg = "Tạo mới thành công!";
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Tạo mới nhóm sản phẩm thất bại (không thấy thông báo thành công)");
    }
    @Test
    public void ThemmoiNSP_Khongnhaptatcactruongbatbuoc() {
        System.out.println("TC94 - Người dùng không thể thêm mới nhóm sản phẩm khi bỏ trống tất cả các trường bắt buộc");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi bỏ trống các trường bắt buộc");
    }
    @Test
    public void ThemmoiNSP_BotrongTenNSP() {
        System.out.println("TC95 - Người dùng không thể thêm mới nhóm sản phẩm khi bỏ trống trường 'Tên nhóm sản phẩm'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String maNSP = Random_ThemMoiNSP.generateMaNSPnWithoutTen();
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterMaNSP(maNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = qlnspPage.getTenNSPFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Tên nhóm sản phẩm");
    }
    @Test
    public void ThemmoiNSP_BotrongMaNSP() {
        System.out.println("TC96 - Người dùng không thể thêm mới nhóm sản phẩm khi bỏ trống trường 'Mã nhóm sản phẩm'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String tenNSP = Random_ThemMoiNSP.generateRandomTenNSP();
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterTenNSP(tenNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = qlnspPage.getMaNSPFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Mã nhóm sản phẩm");
    }
    @Test
    public void ThemmoiNSP_TrungMaNSP() {
        System.out.println("TC97 - Người dùng không thể thêm mới nhóm sản phẩm khi trùng mã nhóm sản phẩm đã có trong hệ thống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String tenNSP = Random_ThemMoiNSP.generateRandomTenNSP();
        String maNSP = "DN009";
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterTenNSP(tenNSP);
        qlnspPage.enterMaNSP(maNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getMaQLTrungNotification();
        String expectedMsg = "Mã QL đã tồn tại";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi mã quản lý bị trùng");
    }
    @Test
    public void ThemMoiNSP_Tennhomspchuakitudacbiet() {
        System.out.println("TC98 - Người dùng có thể thêm mới nhóm sản phẩm dù tên nhóm sản phẩm chứa kí tự đặc biệt");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String tenNSP = "Dặc sản Phú Yên 5*";
        String maNSP = Random_ThemMoiNSP.generateMaNSP(tenNSP);
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterTenNSP(tenNSP);
        qlnspPage.enterMaNSP(maNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualError = qlnspPage.getSuccessNotification();
        String expectedError = "Tạo mới thành công!";
        Assert.assertEquals(actualError, expectedError, "Lưu không thành công khi nhập tên đặc sản chứa kí tự đặc biệt ");
    }
    @Test
    public void ThemMoiNSP_Kiemtranhapcactruongbatbuocvuot255kitu() {
        System.out.println("TC99 - Người dùng có thể thêm mới nhóm sản phẩm dù nhập các trường bat bắt buộc vượt 255 ki tự ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String tenNSP = Random_ThemMoiNSP.generateStringOver255Chars();
        String maNSP = Random_ThemMoiNSP.generateStringOver255Chars();
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterTenNSP(tenNSP);
        qlnspPage.enterMaNSP(maNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getSuccessNotification();
        String expectedMsg = "Tạo mới thành công!";
        Assert.assertEquals(actualMsg, expectedMsg, "Không hiển thị thông báo tạo mới thành công");
    }
    @Test
    public void ThemmoiNSP_TrungTenNSP() {
        System.out.println("TC100 - Người dùng không thể thêm mới nhóm sản phẩm với tên nhóm sản phẩm trùng lặp đã tồn tại");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String tenNSP = "Đặc sản Bình Định";
        String maNSP = Random_ThemMoiNSP.generateMaNSP(tenNSP);
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterTenNSP(tenNSP);
        qlnspPage.enterMaNSP(maNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickLuuNSP();
        qlnspPage.confirmPopupLuu();

        String actualMsg = qlnspPage.getTenTonTaiNotification();
        String expectedMsg = "Tên đã tồn tại";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi tên nhóm sản phẩm bị trùng");
    }
    @Test
    public void HuyBoThaoTacThemNSP() {
        System.out.println("TC101 - Người dùng có thể hủy bỏ thao tác thêm nhóm sản phẩm");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        qlnspPage.clickThemMoi();

        String tenNSP = Random_ThemMoiNSP.generateRandomTenNSP();
        String maNSP = Random_ThemMoiNSP.generateMaNSP(tenNSP);
        String trangThai = Random_ThemMoiNSP.generateTrangThai();

        qlnspPage.enterTenNSP(tenNSP);
        qlnspPage.enterMaNSP(maNSP);
        qlnspPage.selectTrangThai(trangThai);

        qlnspPage.hideChatBot();
        qlnspPage.clickHuyBo();
        qlnspPage.confirmPopupHuybo();
        Assert.assertTrue(qlnspPage.isThemMoiFormClosed(), "Form thêm nhóm sản phẩm không biến mất sau khi hủy");
    }
}
