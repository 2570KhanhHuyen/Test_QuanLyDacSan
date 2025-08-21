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

public class TimkiemTTDS {
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
    public void testTimKiemDS_TheoTenChinhXac() {
        System.out.println("TC28 ‑ Tìm kiếm đặc sản hợp lệ theo tên đặc sản");
        String tenDacSan = "Bánh Pía";
        String dacSanVung = "Miền Nam";
        String trangThai = "Cần kiểm tra";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tenDacSan);
        int soKetQua = page.demSoKetQuaTheoTen(tenDacSan);
        Assert.assertEquals(soKetQua, 1, "Phải hiển thị đúng 1 đặc sản: " + tenDacSan);
        page.clickDacSanTrongKetQua(tenDacSan);
        boolean moChiTiet = page.isThongTinChiTietHienThiDL(tenDacSan,dacSanVung,trangThai);
        Assert.assertTrue(moChiTiet, "Không mở được chi tiết thông tin đặc sản đang tìm " + tenDacSan);
    }
    @Test
    public void testTimKiemDS_TheoTuKhoaLienQuan() {
        System.out.println("TC29 – Tìm kiếm đặc sản với từ khóa hợp lệ");
        String tuKhoa = "Mè";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tuKhoa);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có đặc sản nào chứa từ khóa: " + tuKhoa);
    }
    @Test
    public void testTimKiemDS_TheoTuKhoaKhongTonTai() {
        System.out.println("TC30 – Tìm kiếm đặc sản với từ khóa không tồn tại");
        String tuKhoa = "abcxyz123";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tuKhoa);
        boolean hienThiThongBao = page.kiemTraChuaCoDuLieu();
        Assert.assertTrue(hienThiThongBao, "Hệ thống không hiển thị thông báo 'Chưa có dữ liệu' khi tìm kiếm từ khóa không hợp lệ.");
    }
    @Test
    public void testTimKiemDS_TheoTuKhoaKhongPhanBietChuHoaChuThuong() {
        System.out.println("TC31 – Tìm kiếm đặc sản với từ khóa không phân biệt chữ hoa/chữ thường ");
        String tuKhoa = "BÁNH PÍA"; //bánh pía
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tuKhoa);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có đặc sản nào chứa từ khóa: " + tuKhoa);
    }
    @Test
    public void testTimKiemDS_TheoTuKhoaKhongDau_SaiDauCau() {
        System.out.println("TC32– Tìm kiếm đặc sản với từ khóa không dấu/sai dấu câu ");
        String tuKhoa = "banh loc"; //dưa Tậm Quần
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tuKhoa);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có đặc sản nào chứa từ khóa: " + tuKhoa);
    }
    @Test
    public void testTimKiemDS_VoiChuoiQua255KiTu() {
        System.out.println("TC33 – Tìm kiếm với chuỗi dài bất thường (>255 ký tự)");
        String longInput = "z".repeat(300);
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(longInput);
        boolean hienThiChuaCoDuLieu = page.kiemTraKetQuaTimKiem_2TH(longInput);
        Assert.assertTrue(hienThiChuaCoDuLieu, "Hệ thống không hiển thị 'Chưa có dữ liệu' hoặc item lên list danh sách");
    }
    @Test
    public void testTimKiemDS_voiKyTuDacBiet() {
        System.out.println("TC34 – Tìm kiếm đặc sản với ký tự đặc biệt");
        String tuKhoa = "@#%";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(tuKhoa);
        boolean hienThiThongBao = page.kiemTraKetQuaTimKiem_2TH(tuKhoa);
        Assert.assertTrue(hienThiThongBao, "Hệ thống không hiển thị item/chưa có dữ liệu ở list danh sách đặc sản");
    }
    @Test
    public void testTimKiemDSLienTuc(){
        System.out.println("TC35 - Tìm kiếm đặc sản liên tục với các từ khóa khác nhau");
        String[] tukhoa =  {"bánh", "mè", "vịt", "Huế","Cơm","Cà phê"};
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        for (String tuKhoa : tukhoa) {
            System.out.println("Đang tìm kiếm với từ khóa: " + tuKhoa);
            page.search(tuKhoa);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean ketQuaHopLe = page.kiemTraKetQuaTimKiem(tuKhoa);
            Assert.assertTrue(ketQuaHopLe, "Không có đặc sản nào chứa từ khóa: " + tuKhoa);
        }
    }
    @Test
    public void testKiemTraTimKiemKetHopVsReloadlistDS() throws InterruptedException {
        System.out.println("TC36 - Gõ từ khóa vào ô tìm kiếm và reload lại list đặc sản ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tukhoa = "Khô mè";
        page.search(tukhoa);
        boolean ketquaDung = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(ketquaDung, "Không có đặc sản nào chứa từ khóa: " + tukhoa);
        Thread.sleep(2000);
        page.reloadDacSanList();
        String searchBoxValue = page.getSearchBoxText();
        Assert.assertEquals(searchBoxValue, tukhoa,"Ô tìm kiếm không còn giữ lại từ khóa cũ sau khi reload");
        boolean coKetQua = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(coKetQua, "Sau khi reload, danh sách vẫn giữ nguyên kết quả tìm kiếm");
    }
    @Test
    public void testXoaTuKhoaSauKhiTimKiem(){
        System.out.println("TC37 - Xóa từ khóa sau khi tìm kiếm");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page =homePage.goToQLTTDSPage();
        String tukhoa = "bánh";
        page.search(tukhoa);
        boolean ketquadung = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(ketquadung," Không có đặc sản nào chứa từ khóa: " + tukhoa);
        page.retryClick(() -> page.clickXoaTimKiem());
        boolean listHienThiLai = page.waitForListDanhsachToAppear();
        Assert.assertTrue(listHienThiLai,"Sau khi nhấn icon hủy tìm đặc sản, danh sách đặc sản ban đầu không hiển thị lại");
    }
    @Test
    public void testLocDacSanTheoTrangThaiNhap(){
        System.out.println("TC38 - Lọc đặc sản theo trạng thái Nháp");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.locTrangThaiTheoTen("Nháp");
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Nháp");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Nháp'");
    }
    @Test
    public void testLocDacSanTheoTrangThaiXacNhan(){
        System.out.println("TC39 - Lọc đặc sản theo trạng thái Xác nhận");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.locTrangThaiTheoTen("Xác nhận");
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Xác nhận");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Xác nhận'");
    }
    @Test
    public void testLocDacSanTheoTrangThaiCanKiemTra(){
        System.out.println("TC40 - Lọc đặc sản theo trạng thái Cần kiểm tra");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.locTrangThaiTheoTen("Cần kiểm tra");
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Cần kiểm tra");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Cần kiểm tra'");
    }
    @Test
    public void testLocDacSanTheoTrangThaiXoa(){
        System.out.println("TC41 - Lọc đặc sản theo trạng thái Xóa");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.locTrangThaiTheoTen("Xóa");
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Xóa");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Xóa'");
    }
    @Test
    public void testLocDacSanTheoTrangThaiTatCa(){
        System.out.println("TC42 - Lọc đặc sản theo trạng thái Tất cả");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page =homePage.goToQLTTDSPage();
        page.locTrangThaiTheoTen("Tất cả");
        boolean ketQua = page.kiemTraTrangThaiKhiLocTatCa();
        Assert.assertTrue(ketQua,"Không hiển thị đủ các trạng thái trong danh sách 'Tất cả'");
    }
    @Test
    public void testTimKiemKetHop_LocTatCa() {
        System.out.println("TC43 - Tìm kiếm kết hợp lọc với trạng thái 'Tất cả'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "Bánh";
        String trangThai = "Tất cả";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Danh sách không chứa đầy đủ các đặc sản theo từ khóa '" + tuKhoa + "' và trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemKetHop_LocNhap() {
        System.out.println("TC44 - Tìm kiếm kết hợp lọc với trạng thái 'Nháp'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "Bánh";
        String trangThai = "Nháp";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Danh sách không chứa đầy đủ các đặc sản theo từ khóa '" + tuKhoa + "' và trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemKetHop_LocCanKiemTra() {
        System.out.println("TC45 - Tìm kiếm kết hợp lọc với trạng thái 'Cần kiểm tra'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "bánh";
        String trangThai = "Cần kiểm tra";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Danh sách không chứa đầy đủ các đặc sản theo từ khóa '" + tuKhoa + "' và trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemKetHop_LocXacNhan() {
        System.out.println("TC46 - Tìm kiếm kết hợp lọc với trạng thái 'Xác nhận'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "Banh";
        String trangThai = "Xác nhận";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Danh sách không chứa đầy đủ các đặc sản theo từ khóa '" + tuKhoa + "' và trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemKetHop_LocXoa() {
        System.out.println("TC47 - Tìm kiếm kết hợp lọc với trạng thái 'Xóa'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "bánh";
        String trangThai = "Xóa";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Danh sách không chứa đầy đủ các đặc sản theo từ khóa '" + tuKhoa + "' và trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemKetHop_LocVoiTuKhoaDungNhungKhongTonTaiTrangThaiLoc() {
        System.out.println("TC48 - Lọc với đúng từ khóa nhưng từ khóa không có trong trạng thái cần lọc");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "Mắm Nêm";
        String trangThai = "Nháp";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Không hiển thị 'Chưa có dữ liệu' khi từ khóa '" + tuKhoa + "' không tồn tại trong trạng thái '" + trangThai + "'");
    }
    @Test
    public void testChuyenDoiNhanhGiuaCacFilter() {
        System.out.println("TC49 - Kiểm tra chuyển đổi nhanh giữa các filter");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "Bánh";
        page.search(tuKhoa);
        String[] filters = {"Tất cả", "Nháp", "Xác nhận", "Cần kiểm tra", "Xóa"};
        for (String trangThai : filters) {
            System.out.println(">> Kiểm tra lọc với trạng thái: " + trangThai);
            page.locTrangThaiTheoTen(trangThai);
            boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
            Assert.assertTrue(ketQua, "Dữ liệu không khớp sau khi lọc '" + trangThai + "' với từ khóa '" + tuKhoa + "'");
        }
    }
    @Test
    public void testLocVoiTuKhoaKhongTonTai_QuaTatCaTrangThai() {
        System.out.println("TC50 - Lọc với từ khóa không tồn tại qua tất cả các trạng thái");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        String tuKhoa = "Min";
        String[] trangThais = {"Tất cả", "Nháp", "Cần kiểm tra"};
        page.search(tuKhoa);
        for (String trangThai : trangThais) {
            System.out.println(">> Đang kiểm tra trạng thái: " + trangThai);
            page.locTrangThaiTheoTen(trangThai);
            boolean hienThiChuaCoDuLieu = page.kiemTraChuaCoDuLieu();
            Assert.assertTrue(hienThiChuaCoDuLieu,
                    "Không hiển thị 'Chưa có dữ liệu' với từ khóa '" + tuKhoa + "' trong trạng thái '" + trangThai + "'");
        }
    }
    @Test
    public void testTimKiemVoiMaDS_HopLe() throws InterruptedException {
        System.out.println("TC51 – Tìm kiếm đặc sản với mã đặc sản hợp lệ");
        String maDS = "MDS008";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(maDS);
        Thread.sleep(2000);
        boolean hienThiChuaCoDuLieu = page.waitForListDanhsachToAppear();
        Assert.assertTrue(hienThiChuaCoDuLieu, "Hệ thống không hiển thị đặc sản phù hợp với mã đặc sản cần tìm");
    }
    @Test
    public void testTimKiemVoiMaDS_KhongHopLe() throws InterruptedException {
        System.out.println("TC52 – Tìm kiếm đặc sản với mã đặc sản không tồn tại");
        String maDS = "MDS000";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLTTDSPage page = homePage.goToQLTTDSPage();
        page.search(maDS);
        Thread.sleep(2000);
        boolean hienThiChuaCoDuLieu = page.kiemTraChuaCoDuLieu();
        Assert.assertTrue(hienThiChuaCoDuLieu, "Hệ thống không hiển thị Chưa có dữ liệu đối với mã đặc sản không tồn tại");
    }
}
