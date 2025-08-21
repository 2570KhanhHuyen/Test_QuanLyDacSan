package TestQuanLyTTDacSanPage;

import PageObjects.*;
import PageObjects.QLTTDSPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import common.Random_ThemMoiDS;
import constant.constant;

public class ThemmoiDS {
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
    public void ThemmoiDSThanhCong_VoiTatCaTruongBatBuoc() {
        System.out.println("TC10 - Thêm mới đặc sản thành công");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        qlttdsPage.clickThemMoi();

        String tenDacSan = Random_ThemMoiDS.generateRandomTenDacSan();
        String maDacSan = Random_ThemMoiDS.generateMaDacSan(tenDacSan);
        String dacSanVung = Random_ThemMoiDS.generateDacSanVung();
        String trangThai = Random_ThemMoiDS.generateTrangThai();

        qlttdsPage.enterTenDacSan(tenDacSan);
        qlttdsPage.enterMaDacSan(maDacSan);
        qlttdsPage.enterDacSanVung(dacSanVung);
        qlttdsPage.selectTrangThai(trangThai);

        qlttdsPage.hideChatBot();
        qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
        qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

        String actualMsg = qlttdsPage.getSuccessNotification();
        String expectedMsg = "Tạo mới thành công!";
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Tạo mới đặc sản thất bại (không thấy thông báo thành công)");
    }
    @Test
    public void Themmoidacsan_Khongnhapcactruongbatbuoc() {
        System.out.println("TC11 - Người dùng không thể thêm mới đặc sản khi bỏ trống các trường bắt buộc");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        qlttdsPage.clickThemMoi();

        qlttdsPage.hideChatBot();
        qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
        qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

        String actualMsg = qlttdsPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi bỏ trống các trường bắt buộc");
    }
    @Test
    public void Themmoidacsan_BotrongTenDacSan() {
        System.out.println("TC12 - Người dùng không thể thêm mới đặc sản khi bỏ trống trường 'Tên đặc sản' ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        qlttdsPage.clickThemMoi();

        String maDacSan = Random_ThemMoiDS.generateMaDacSanWithoutTen();
        String dacSanVung = Random_ThemMoiDS.generateDacSanVung();
        String trangThai = Random_ThemMoiDS.generateTrangThai();

        qlttdsPage.enterMaDacSan(maDacSan);
        qlttdsPage.enterDacSanVung(dacSanVung);
        qlttdsPage.selectTrangThai(trangThai);

        qlttdsPage.hideChatBot();
        qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
        qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

        String actualMsg = qlttdsPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = qlttdsPage.getTenDacSanFieldError();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Tên đặc sản");
    }
    @Test
    public void Themmoidacsan_BotrongMaDacSan() {
        System.out.println("TC13 - Người dùng không thể thêm mới đặc sản khi bỏ trống trường 'Mã đặc sản'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        qlttdsPage.clickThemMoi();

        String tenDacSan = Random_ThemMoiDS.generateRandomTenDacSan();
        String dacSanVung = Random_ThemMoiDS.generateDacSanVung();
        String trangThai = Random_ThemMoiDS.generateTrangThai();

        qlttdsPage.enterTenDacSan(tenDacSan);
        qlttdsPage.enterDacSanVung(dacSanVung);
        qlttdsPage.selectTrangThai(trangThai);

        qlttdsPage.hideChatBot();
        qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
        qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

        String actualMsg = qlttdsPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = qlttdsPage.getMaDacSanFieldError();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Mã đặc sản");
    }
    @Test
    public void Themmoidacsan_BotrongDacSanVung() {
        System.out.println("TC14 - Người dùng không thể thêm mới đặc sản khi bỏ trống trường 'Đặc sản vùng'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        qlttdsPage.clickThemMoi();

        String tenDacSan = Random_ThemMoiDS.generateRandomTenDacSan();
        String maDacSan = Random_ThemMoiDS.generateMaDacSan(tenDacSan);
        String trangThai = Random_ThemMoiDS.generateTrangThai();

        qlttdsPage.enterTenDacSan(tenDacSan);
        qlttdsPage.enterMaDacSan(maDacSan);
        qlttdsPage.selectTrangThai(trangThai);

        qlttdsPage.hideChatBot();
        qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
        qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

        String actualMsg = qlttdsPage.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");
        String actualFieldError = qlttdsPage.getDacSanVungFieldError();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô Đặc sản vùng");
    }
    @Test
    public void Themmoidacsan_TrungMaDacSan() {
        System.out.println("TC15 - Người dùng không thể thêm mới đặc sản khi trùng mã đặc sản đã có trong hệ thống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        qlttdsPage.clickThemMoi();

        String tenDacSan = Random_ThemMoiDS.generateRandomTenDacSan();
        String maDacSan = "MDS008";
        String dacSanVung = Random_ThemMoiDS.generateDacSanVung();
        String trangThai = Random_ThemMoiDS.generateTrangThai();

        qlttdsPage.enterTenDacSan(tenDacSan);
        qlttdsPage.enterMaDacSan(maDacSan);
        qlttdsPage.enterDacSanVung(dacSanVung);
        qlttdsPage.selectTrangThai(trangThai);

        qlttdsPage.hideChatBot();
        qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
        qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

        String actualMsg = qlttdsPage.getMaQLTrungNotification();
        String expectedMsg = "Mã QL đã tồn tại";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi mã bị trùng");
    }
@Test
public void ThemMoiDacSan_Tendacsanchuakitudacbiet() {
    System.out.println("TC16 - Người dùng có thể thêm mới đặc sản dù tên đặc sản chứa kí tự đặc biệt");

    GeneralPage generalPage = new GeneralPage();
    LoginPage loginPage = new LoginPage();
    loginPage.open();
    HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
    loginPage.isLoginThanhCong();
    QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
    qlttdsPage.clickThemMoi();

    String tenDacSan = "Mien***";
    String maDacSan = Random_ThemMoiDS.generateMaDacSan(tenDacSan);
    String dacSanVung = Random_ThemMoiDS.generateDacSanVung();
    String trangThai = Random_ThemMoiDS.generateTrangThai();

    qlttdsPage.enterTenDacSan(tenDacSan);
    qlttdsPage.enterMaDacSan(maDacSan);
    qlttdsPage.enterDacSanVung(dacSanVung);
    qlttdsPage.selectTrangThai(trangThai);

    qlttdsPage.hideChatBot();
    qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
    qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

    String actualError = generalPage.getSuccessNotification();
    String expectedError = "Tạo mới thành công!";
    Assert.assertEquals(actualError, expectedError, "Lưu không thành công khi nhập tên đặc sản chứa kí tự đặc biệt ");
}
@Test
public void ThemMoiDacSan_Kiemtranhapcactruongbatbuocvuot255kitu() {
    System.out.println("TC17 - Người dùng có thể thêm mới đặc sản khi nhập các trường bắt buộc vượt 255 kí tự ");
    LoginPage loginPage = new LoginPage();
    loginPage.open();
    HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
    loginPage.isLoginThanhCong();
    QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
    qlttdsPage.clickThemMoi();

    String tenDacSan = Random_ThemMoiDS.generateStringOver255Chars();
    String maDacSan = Random_ThemMoiDS.generateStringOver255Chars();
    String dacSanVung = Random_ThemMoiDS.generateStringOver255Chars();
    String trangThai = Random_ThemMoiDS.generateTrangThai();

    qlttdsPage.enterTenDacSan(tenDacSan);
    qlttdsPage.enterMaDacSan(maDacSan);
    qlttdsPage.enterDacSanVung(dacSanVung);
    qlttdsPage.selectTrangThai(trangThai);

    qlttdsPage.hideChatBot();
    qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
    qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

    String actualMsg = qlttdsPage.getSuccessNotification();
    String expectedMsg = "Tạo mới thành công!";
    Assert.assertEquals(actualMsg, expectedMsg, "Không hiển thị thông báo tạo mới thành công");
}
    @Test
    public void ThemmoiDS_TrungTenDacSan() {
        System.out.println("TC18 - Người dùng không thể thêm mới đặc sản khi trùng tên đặc sản đã có trong hệ thống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
        qlttdsPage.clickThemMoi();

        String tenDacSan = "Bánh Pía";
        String maDacSan = Random_ThemMoiDS.generateMaDacSan(tenDacSan);
        String dacSanVung = Random_ThemMoiDS.generateDacSanVung();
        String trangThai = Random_ThemMoiDS.generateTrangThai();

        qlttdsPage.enterTenDacSan(tenDacSan);
        qlttdsPage.enterMaDacSan(maDacSan);
        qlttdsPage.enterDacSanVung(dacSanVung);
        qlttdsPage.selectTrangThai(trangThai);

        qlttdsPage.hideChatBot();
        qlttdsPage.retryClick(() -> qlttdsPage.clickLuu());
        qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupLuu());

        String actualMsg = qlttdsPage.getTenTonTaiNotification();
        String expectedMsg = "Tên đã tồn tại";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi tên đặc sản bị trùng");
    }
@Test
public void HuyBoThaoTacThemDS() {
    System.out.println("TC19 - Người dùng có thể hủy bỏ thao tác thêm mới đặc sản");
    LoginPage loginPage = new LoginPage();
    loginPage.open();
    HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
    loginPage.isLoginThanhCong();
    QLTTDSPage qlttdsPage = homePage.goToQLTTDSPage();
    qlttdsPage.clickThemMoi();

    String tenDacSan = Random_ThemMoiDS.generateRandomTenDacSan();
    String maDacSan = Random_ThemMoiDS.generateMaDacSan(tenDacSan);
    String dacSanVung = Random_ThemMoiDS.generateDacSanVung();
    String trangThai = Random_ThemMoiDS.generateTrangThai();

    qlttdsPage.enterTenDacSan(tenDacSan);
    qlttdsPage.enterMaDacSan(maDacSan);
    qlttdsPage.enterDacSanVung(dacSanVung);
    qlttdsPage.selectTrangThai(trangThai);

    qlttdsPage.hideChatBot();
    qlttdsPage.retryClick(() -> qlttdsPage.clickHuyBo());
    qlttdsPage.retryClick(() -> qlttdsPage.confirmPopupHuybo());
    Assert.assertTrue(qlttdsPage.isThemMoiFormClosed(), "Form thêm đặc sản không biến mất sau khi hủy");
}
}
