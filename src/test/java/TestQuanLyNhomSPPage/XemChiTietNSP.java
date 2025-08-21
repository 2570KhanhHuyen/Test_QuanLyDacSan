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

public class XemChiTietNSP {
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
    public void XemchitietNSPHopLe_QuaTungTrang() {
        System.out.println("TC85 - Người dùng có thể xem chi tiết thông tin nhóm sản phầm thành công qua từng trang");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage qlnspPage = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Bình Định";
        String maNSP = "BĐ-280165765";
        String trangThai = "Hiển thị";
        boolean thongTinHienThiDu = qlnspPage.timVaXacThucChiTietNSP(tenNSP, maNSP,trangThai);
        Assert.assertTrue(thongTinHienThiDu, "Thông tin chi tiết của nhóm sản phẩm không hiển thị đầy đủ");
    }
    @Test
    public void testXemChiTietNSDPTheoTrangThaiKhacnhau(){
        System.out.println("TC86 - Người dùng có thể xem thông tin chi tiết nhóm sản phẩm theo trạng thái 'Hiển thị' hoặc 'Không hiển thị'");
        String tenNSP = "gia vị đặc sản";
        String maNSP = "123";
        String trangThai = "Không hiển thị"; //Hiển thị
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.locTrangThaiTheoTen(trangThai);
        page.clickTenByName(tenNSP);
        boolean hienThi = page.isThongTinChiTietHienThiDL(tenNSP,maNSP,trangThai);
        Assert.assertTrue(hienThi, "Không mở được chi tiết nhóm sản phẩm ở trạng thái " + trangThai);
        boolean dungTrangThai = page.kiemTraTrangThaiChiTiet(trangThai);
        Assert.assertTrue(dungTrangThai, "Trạng thái hiển thị không đúng: " + trangThai);
    }
    @Test
    public void testThuGonThongTinNSP() {
        System.out.println("TC87 - Người dùng có thể thu gọn thông tin chi tiết nhóm sản phẩm");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Bình Định";
        String maNSP = "BĐ-280165765";
        String trangThai = "Hiển thị";
        boolean hienThi = page.timVaXacThucChiTietNSP(tenNSP, maNSP,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết nhóm sản phẩm phải được hiển thị");
        page.clickThuGonThongTinNSP();
        boolean daAn = page.isThongTinChiTietNSPAn(tenNSP);
        Assert.assertTrue(daAn, "Thông tin nhóm sản phẩm chưa được thu gọn sau khi click");
    }
    @Test
    public void tessHienThiLaiTTNSP() {
        System.out.println("TC88 - Người dùng có thể click hiển thị lại thông tin nhóm sản phẩm sau khi thu ẩn");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Bình Định";
        String maNSP = "BĐ-280165765";
        String trangThai = "Hiển thị";
        boolean hienThi = page.timVaXacThucChiTietNSP(tenNSP, maNSP,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết nhóm sản phẩm phải được hiển thị");
        page.clickThuGonThongTinNSP();
        boolean daAn = page.isThongTinChiTietNSPAn(tenNSP);
        Assert.assertTrue(daAn, "Thông tin nhóm sản phẩm chưa được thu gọn sau khi click");
        page.clickHienThiLaiThongTinNSP();
        boolean hienLai = page.isThongTinChiTietHienThiDL(tenNSP,maNSP,trangThai);
        Assert.assertTrue(hienLai, "Thông tin nhóm sản phẩm chưa được hiển thị lại sau khi click lần hai");
    }
    @Test
    public void testThuGonListNSP() {
        System.out.println("TC89 - Người dùng có thể thu gọn danh sách nhóm sản phẩm");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.clickThuGonList_Danhsach();
        boolean daAn = page.waitForListDanhsachoDisappear();
        Assert.assertTrue(daAn, "List nhóm sản phẩm chưa được thu gọn sau khi click");
    }
    @Test
    public void testHienThiLaiListNSP() throws InterruptedException {
        System.out.println("TC90 - Người dùng có thể click hiển thị lại list thông tin nhóm sản phẩm sau khi thu ẩn");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.clickThuGonList_Danhsach();
        boolean daAn = page.waitForListDanhsachoDisappear();
        Assert.assertTrue(daAn, "List nhóm sản phẩm chưa được thu gọn sau khi click");
        Thread.sleep(2000);
        page.clickHienThiLaiList_Danhsach();
        boolean daHien = page.waitForListDanhsachToAppear();
        Assert.assertTrue(daHien, "List nhóm sản phẩm chưa được hiển thị lại sau khi click");
    }
    @Test
    public void testThuGonThongListDacSanTrongNSP() {
        System.out.println("TC91 - Người dùng có thể thu gọn danh sách các đặc sản khi xem chi tiết nhóm sản phẩm");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Miền Trung";
        String maNSP = "MT009";
        String trangThai = "Hiển thị";
        boolean hienThi = page.timVaXacThucChiTietNSP(tenNSP, maNSP,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết nhóm sản phẩm phải được hiển thị");
        page.clickThuGonListDS();
        boolean daAn = page.IsListDacSanToDisappear();
        Assert.assertTrue(daAn, "Thông tin danh sách đặc sản trong nhóm sản phẩm chưa được thu gọn sau khi click");
    }
    @Test
    public void testHienThiLaiListDacSanTrongNSP() throws InterruptedException {
        System.out.println("TC92 - Người dùng có thể click hiển thị lại danh sách các đạc sản sau khi thu ẩn");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tenNSP = "Đặc sản Miền Trung";
        String maNSP = "MT009";
        String trangThai = "Hiển thị";
        boolean hienThi = page.timVaXacThucChiTietNSP(tenNSP, maNSP,trangThai);
        Assert.assertTrue(hienThi, "Thông tin chi tiết nhóm sản phẩm phải được hiển thị");
        page.clickThuGonListDS();
        boolean daAn = page.IsListDacSanToDisappear();
        Assert.assertTrue(daAn, "Thông tin danh sách đặc sản trong nhóm sản phẩm chưa được thu gọn sau khi click");
        page.clickHienThiLaiListDS();
        Thread.sleep(2000);
        boolean daHien = page.IsListDacSanToAppear();
        Assert.assertTrue(daHien, "Thông tin danh sách các đặc sản chưa được hiển thị lại sau khi click");
    }
}
