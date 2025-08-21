package TestQuanLyVungMienPage;

import PageObjects.*;
import common.Random_ThemMoiVungMien;
import constant.constant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ThemmoiVungMien {
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
    public void ThemmoiVungMienThanhCong_VoiTatCaTruongBatBuoc() {
        System.out.println("TC133 - Người dùng có thể thêm mới vùng miền thành công");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = Random_ThemMoiVungMien.generateRandomTenVungMien();
        String maVungMien = Random_ThemMoiVungMien.generateMaVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();
        String kinhDoNho = Random_ThemMoiVungMien.generateRandomKinhDoNho();
        String viDoNho = Random_ThemMoiVungMien.generateRandomViDoNho();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.clickbtnCong();
        page.enterKinhDoNho(kinhDoNho);
        page.enterViDoNho(viDoNho);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getSuccessNotification();
        String expectedMsg = "Tạo mới thành công!";
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Tạo mới vùng miền thất bại (không thấy thông báo thành công)");
    }
    @Test
    public void ThemmoiVungMien_Khongnhaptatcactruongbatbuoc() {
        System.out.println("TC134 - Người dùng không thể thêm mới vùng miền khi bỏ trống tất cả các trường bắt buộc");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi bỏ trống các trường bắt buộc");
    }
    @Test
    public void ThemmoiVungMien_BotrongTenVungMien() {
        System.out.println("TC135 - Người dùng không thể thêm mới vùng miền khi bỏ trống trường 'Tên vùng miền'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String maVungMien = Random_ThemMoiVungMien.generateMaVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();

        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = page.getTenVungMienFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô 'Tên vùng miền'");
    }
    @Test
    public void ThemmoiVungMien_BotrongMaVungMien() {
        System.out.println("TC136 - Người dùng không thể thêm mới vùng miền khi bỏ trống trường 'Mã vùng miền'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();
        String tenVungMien = Random_ThemMoiVungMien.generateRandomTenVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();

        page.enterTenVungMien(tenVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = page.getMaVungMienFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô 'Mã vùng miền'");
    }
    @Test
    public void ThemmoiVungMien_BotrongDienTich() {
        System.out.println("TC137 - Người dùng không thể thêm mới vùng miền khi bỏ trống trường 'Diện tích'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = Random_ThemMoiVungMien.generateRandomTenVungMien();
        String maVungMien = Random_ThemMoiVungMien.generateMaVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = page.getDienTichFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô 'Diện tích'");
    }
    @Test
    public void ThemmoiVungMien_BotrongKinhDo() {
        System.out.println("TC138 - Người dùng không thể thêm mới vùng miền khi bỏ trống trường 'Kinh độ'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = Random_ThemMoiVungMien.generateRandomTenVungMien();
        String maVungMien = Random_ThemMoiVungMien.generateMaVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterVido(viDo);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = page.getKinhDonFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô 'Kinh độ'");
    }
    @Test
    public void ThemmoiVungMien_BotrongVido() {
        System.out.println("TC139 - Người dùng không thể thêm mới vùng miền khi bỏ trống trường 'Vĩ độ'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = Random_ThemMoiVungMien.generateRandomTenVungMien();
        String maVungMien = Random_ThemMoiVungMien.generateMaVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getErrorNotification();
        String expectedMsg = "Vui lòng kiểm tra lại những lỗi dữ liệu!";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi tổng");

        String actualFieldError = page.getViDoFieldErrorr();
        String expectedFieldError = "Thông tin bắt buộc";
        Assert.assertEquals(actualFieldError, expectedFieldError, "Không hiển thị thông báo lỗi dưới ô 'Vĩ độ'");
    }
    @Test
    public void ThemmoiVungMien_TrungMaVungMien() {
        System.out.println("TC140 - Người dùng không thể thêm vùng miền khi nhập trùng mã vùng miền đã tồn tại trong hệ thống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = Random_ThemMoiVungMien.generateRandomTenVungMien();
        String maVungMien = "890";
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();
        String kinhDoNho = Random_ThemMoiVungMien.generateRandomKinhDoNho();
        String viDoNho = Random_ThemMoiVungMien.generateRandomViDoNho();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.clickbtnCong();
        page.enterKinhDoNho(kinhDoNho);
        page.enterViDoNho(viDoNho);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getMaQLTrungNotification();
        String expectedMsg = "Mã QL đã tồn tại";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi mã quản lý bị trùng");
    }
    @Test
    public void ThemMoiVungMien_Tenvungmienchuakitudacbiet() {
        System.out.println("TC141 - Người dùng có thể thêm mới vùng miền dù tên vùng miền chứa kí tự đặc biệt");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = "Miền Trung & Tây Nguyên!";
        String maVungMien = Random_ThemMoiVungMien.generateMaVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();
        String kinhDoNho = Random_ThemMoiVungMien.generateRandomKinhDoNho();
        String viDoNho = Random_ThemMoiVungMien.generateRandomViDoNho();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.clickbtnCong();
        page.enterKinhDoNho(kinhDoNho);
        page.enterViDoNho(viDoNho);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualError = page.getSuccessNotification();
        String expectedError = "Tạo mới thành công!";
        Assert.assertEquals(actualError, expectedError, "Lưu không thành công khi nhập tên vùng miền chứa kí tự đặc biệt ");
    }
    @Test
    public void ThemMoiVungMien_Kiemtranhapcactruongbatbuocnhohonhoacbang200kitu() {
        System.out.println("TC142 - Người dùng có thể thêm mới vùng miền khi nhập nhỏ hơn hoặc bằng 200 ki tự vào trường 'Tên Vùng Miền' hoặc 'Mã Vùng Miền' ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();
        String tenVungMien = Random_ThemMoiVungMien.generateString200Chars();
        String maVungMien = Random_ThemMoiVungMien.generateString200Chars();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();
        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getSuccessNotification();
        String expectedMsg = "Tạo mới thành công!";
        Assert.assertEquals(actualMsg, expectedMsg, "Không hiển thị thông báo tạo mới thành công");
    }
    @Test
    public void ThemMoiVungMien_Kiemtranhapcactruongbatbuocvuot200kitu() {
        System.out.println("TC143 - Người dùng không thể thêm mới vùng miền khi nhập vượt 200 ki tự vào trường 'Tên Vùng Miền' và 'Mã Vùng Miền' ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = Random_ThemMoiVungMien.generateStringOver200Chars();
        String maVungMien = Random_ThemMoiVungMien.generateStringOver200Chars();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getTruyXuatdlNotification();
        String expectedMsg = "Có lỗi xảy ra, không thể truy xuất dữ liệu!";
        Assert.assertEquals(actualMsg, expectedMsg, "Không hiển thị thông báo lỗi");
    }
    @Test
    public void ThemmoiVungMien_TrungTenVungMien() {
        System.out.println("TC144 - Người dùng không thể thêm mới vùng miền khi nhập trùng tên vùng miền đã tồn tại trong hệ thống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        String tenVungMien = "Đông Bắc";
        String maVungMien = Random_ThemMoiVungMien.generateMaVungMien();
        String kieuVungMien = Random_ThemMoiVungMien.generateKieuVungMien();
        String trangThai = Random_ThemMoiVungMien.generateTrangThai();
        String dienTich = Random_ThemMoiVungMien.generateRandomDienTich();
        String kinhDo = Random_ThemMoiVungMien.generateRandomKinhDo();
        String viDo = Random_ThemMoiVungMien.generateRandomViDo();
        String kinhDoNho = Random_ThemMoiVungMien.generateRandomKinhDoNho();
        String viDoNho = Random_ThemMoiVungMien.generateRandomViDoNho();

        page.enterTenVungMien(tenVungMien);
        page.enterMaVungMien(maVungMien);
        page.selectKieuVungMien(kieuVungMien);
        page.selectTrangThai(trangThai);
        page.enterDienTich(dienTich);
        page.enterKinhdo(kinhDo);
        page.enterVido(viDo);

        page.clickbtnCong();
        page.enterKinhDoNho(kinhDoNho);
        page.enterViDoNho(viDoNho);

        page.hideChatBot();
        page.clickLuuNSP();
        page.confirmPopupLuu();

        String actualMsg = page.getTenTonTaiNotification();
        String expectedMsg = "Tên đã tồn tại";
        Assert.assertTrue(actualMsg.contains(expectedMsg),  "Không hiển thị thông báo lỗi khi tên vùng miền bị trùng");
    }
    //=> Fail do chưa xử lý trùng tên
    @Test
    public void HuyBoThaoTacThemVungMien() {
        System.out.println("TC145 - Người dùng có thể hủy bỏ thao tác thêm mưới vùng miền khi nhấn button 'Hủy bỏ' trên form thêm mới");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.clickThemMoi();

        page.hideChatBot();
        page.clickHuyBoVungMien();
        page.confirmPopupHuybo();
        Assert.assertTrue(page.isThemMoiFormClosed(), "Form thêm vùng miền không biến mất sau khi 'Hủy bỏ'");
    }
}
