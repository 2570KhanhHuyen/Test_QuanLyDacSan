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

public class TimkiemVungMien {
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
    public void testTimKiemVungMien_TheoTenChinhXac() throws InterruptedException {
        System.out.println("TC107 ‑ Người dùng có thể tìm kiếm vùng miền hợp lệ theo tên vùng miền ");
        String tenVungmien = "Miền Trung";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(tenVungmien);
        Thread.sleep(1000);
        int soKetQua = page.demSoKetQuaTheoTen(tenVungmien);
        Assert.assertEquals(soKetQua, 1, "Không có kết quả phù hợp với tên vùng miền cần tìm: " + tenVungmien);
    }
    @Test
    public void testTimKiemVungMien_TheoTuKhoaTonTai() throws InterruptedException {
        System.out.println("TC108 – Người dùng có thể tìm kiếm vùng miền theo từ khóa phù hợp");
        String tuKhoa = "Miền";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(tuKhoa);
        Thread.sleep(1000);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có nhóm sản phẩm nào chứa từ khóa cần tìm kiếm: " + tuKhoa);
        int soKetQua = page.demSoKetQuaTheoTen(tuKhoa);
        System.out.println("Số kết quả tìm được là: " + soKetQua);
        Assert.assertTrue(soKetQua > 0, "Không có kết quả vùng miền nào hiển thị phù hợp với từ khóa: " + tuKhoa);
    }
    @Test
    public void testTimKiemVungMien_TheoTuKhoaKhongTonTai() throws InterruptedException {
        System.out.println("TC109 – Người dùng có thể tìm kiếm vùng miền với từ khóa không tồn tại");
        String tuKhoa = "Tây Tạng";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(tuKhoa);
        Thread.sleep(1000);
        boolean hienThiThongBao = page.kiemTraChuaCoDuLieu();
        Assert.assertTrue(hienThiThongBao, "Hệ thống không hiển thị thông báo 'Chưa có dữ liệu' khi tìm kiếm từ khóa không hợp lệ.");
    }
    @Test
    public void testTimKiemVungMien_VsTKhoaKhongPhanBietChuHoaChuThuong() throws InterruptedException {
        System.out.println("TC110 – Tìm kiếm vùng miền với từ khóa không phân biệt chữ hoa/chữ thường ");
        String tuKhoa = "TAY BAC"; // tây bắc
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(tuKhoa);
        Thread.sleep(1000);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có vùng miền nào chứa từ cần tìm: " + tuKhoa);
    }
    @Test
    public void testTimKiemVungMien_TheoTuKhoaKhongDau_SaiDauCau() throws InterruptedException {
        System.out.println("TC111– Tìm kiếm vùng miền với từ khóa không dấu/sai dấu câu ");
        String tuKhoa = "Miến Nám"; // mien nam
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(tuKhoa);
        Thread.sleep(1000);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có vùng miền nào chứa từ khóa: " + tuKhoa);
        int soKetQua = page.demSoKetQuaTheoTen(tuKhoa);
        System.out.println("Số kết quả vùng miền tìm kiếm được là:" + soKetQua);
        Assert.assertTrue(soKetQua > 0, "Không có kết quả vùng miền nào hiển thị phù hợp với từ khóa: " + tuKhoa);
    }
    @Test
    public void testTimKiemVungMien_VoiChuoiQua255KiTu() throws InterruptedException {
        System.out.println("TC112 – Người dùng có thể tìm kiếm vùng miền với chuỗi >255 kí tự");
        String longInput = "b".repeat(256);
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(longInput);
        Thread.sleep(1000);
        boolean hienThiChuaCoDuLieu = page.kiemTraKetQuaTimKiem_2TH(longInput);
        Assert.assertTrue(hienThiChuaCoDuLieu, "Hệ thống không hiển thị 'Chưa có dữ liệu' hoặc item lên list danh sách");
    }
    @Test
    public void testTimKiemVungMien_voiKyTuDacBiet() {
        System.out.println("TC113 – Người dùng có thể tìm kiếm vùng miền với ký tự đặc biệt");
        String tukhoa = "*@##";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(tukhoa);
        boolean hienThiThongBao = page.kiemTraKetQuaTimKiem_2TH(tukhoa);
        Assert.assertTrue(hienThiThongBao, "Hệ thống không hiển thị item/chưa có dữ liệu ở list danh sách vùng miền");
    }
    @Test
    public void testTimKiemVungMien_LienTuc(){
        System.out.println("TC114 - Người dùng có thể tìm kiếm vùng miền liên tục với các từ khóa khác nhau");
        String[] tukhoa =  {"Miền", "nguyên", "Nang", "BẮC","nam","Yến"};
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        for (String tuKhoa : tukhoa) {
            System.out.println("Đang tìm kiếm với từ khóa: " + tuKhoa);
            page.searchVungMien(tuKhoa);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean ketQuaHopLe = page.kiemTraKetQuaTimKiem(tuKhoa);
            Assert.assertTrue(ketQuaHopLe, "Không có vùng miền nào chứa từ khóa: " + tuKhoa);
        }
    }
    @Test
    public void testTimKiemVungMien_VsKhoangTrang() throws InterruptedException {
        System.out.println("TC115 – Người dùng có thể tìm kiếm vùng miền với từ khóa hợp lệ kèm khoảng trắng");
        String tukhoa = "   Đông";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(tukhoa);
        Thread.sleep(2000);
        boolean hienThi = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(hienThi, "Hệ thống không hiển thị vùng miền cần tìm với từ khóa");
    }
    // FAILED => CHƯA CÓ XỬ LÝ KHOẢNG TRẮNG
    @Test
    public void testKiemTraTimKiemKetHopVsReloadlistVungMien() throws InterruptedException {
        System.out.println("TC116 - Người dùng có thể tìm kiếm vùng miền kết hợp reload list vùng miền");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        String tukhoa = "Miền";
        page.searchVungMien(tukhoa);
        boolean ketquaDung = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(ketquaDung, "Không có vùng miền nào chứa từ khóa: " + tukhoa);
        Thread.sleep(2000);
        page.reloadVungMienList();
        String searchBoxValue = page.getSearchBoxTextVungMien();
        Assert.assertEquals(searchBoxValue, tukhoa,"Ô tìm kiếm không còn giữ lại từ khóa cũ sau khi reload");
        boolean coKetQua = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(coKetQua, "Sau khi reload, danh sách vẫn giữ nguyên kết quả tìm kiếm");
    }
    @Test
    public void testXoaTuKhoaSauTimKiemVungMien(){
        System.out.println("TC117 - Người dùng có thể Xóa từ khóa sau khi tìm kiếm vùng miền");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        Assert.assertTrue(page.waitForListDanhsachToAppear(), "Danh sách vùng miền ban đầu không hiển thị.");
        int initialItemCount = page.getNumberOfNSPItems();
        Assert.assertTrue(initialItemCount > 0, "Danh sách ban đầu trống rỗng, không thể tiếp tục test tìm kiếm.");
        String tukhoa = "Bắc";
        page.searchVungMien(tukhoa);
        boolean ketquadung = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(ketquadung, "Không có vùng miền nào chứa từ khóa: " + tukhoa);
        page.clickXoaTimKiem();
        boolean listHienThiLai = page.waitForListDanhsachToAppear();
        Assert.assertTrue(listHienThiLai, "Sau khi nhấn icon hủy tìm kiếm, danh sách vùng miền ban đầu không hiển thị lại.");
        int afterClearItemCount = page.getNumberOfNSPItems();
        System.out.println("Số item hiển thị lại là: "+ afterClearItemCount );
        Assert.assertEquals(afterClearItemCount, initialItemCount, "Số lượng item sau khi xóa tìm kiếm không khớp với số lượng ban đầu.");
    }
    @Test
    public void testTimKiemVoiMaVungMien_HopLe() throws InterruptedException {
        System.out.println("TC118 – Người dùng có thể tìm kiếm vùng miền với mã vùng miền hợp lệ");
        String maVungMien = "890"; //co the nhap 8,89...
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(maVungMien);
        Thread.sleep(2000);
        boolean hienThiKetQua = page.waitForListDanhsachToAppear();
        Assert.assertTrue(hienThiKetQua, "Hệ thống không hiển thị vùng miền phù hợp với mã vùng miền cần tìm");
    }
    @Test
    public void testTimKiemVoiMaVungMien_KhongHopLe() throws InterruptedException {
        System.out.println("TC119 – Người dùng có thể tìm kiếm vùng miền với mã vùng miền không hợp lệ");
        String maVungmien = "89055";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.searchVungMien(maVungmien);
        Thread.sleep(2000);
        boolean hienThiChuaCoDuLieu = page.kiemTraChuaCoDuLieu();
        Assert.assertTrue(hienThiChuaCoDuLieu, "Hệ thống không hiển thị chưa có dữ liệu với tìm kiếm mã vùng miền không tồn tại");
    }
    @Test
    public void testTimKiemVungMienKetHop_LocTatCa() {
        System.out.println("TC120 - Người dùng có thể tìm kiếm vùng miền kết hợp lọc với trạng thái 'Tất cả'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        String tuKhoa = "Miền";
        String trangThai = "Tất cả";
        page.searchVungMien(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Danh sách không chứa đầy đủ các vùng miền theo từ khóa '" + tuKhoa + "' với trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemVungMienKetHop_LocCacTrangThaiKhacNhau() {
        System.out.println("TC121 - Người dùng có thể tìm kiếm vùng miền kết hợp lọc với trạng thái Nháp/Cần xem xét/Xác nhận/Xóa");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        String tuKhoa = "Bắc";
        String trangThai = "Cần xem xét"; //Cần xem xét/Xác nhận/Xóa
        page.searchVungMien(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, " Danh sách không tồn tại các vùng miền theo từ khóa '" + tuKhoa + "' với trạng thái '" + trangThai + "'");
    }
    @Test
    public void testLocVungmienTheoTrangThai_TatCa() throws InterruptedException {
        System.out.println("TC122 - Người dùng có thể lọc vùng miền theo trạng thái Tất cả");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.locTrangThaiTheoTen("Tất cả");
        Thread.sleep(2000);
        boolean ketQua = page.kiemTraTrangThaiKhiLocTatCa();
        Assert.assertTrue(ketQua,"Không hiển thị đủ 4 trạng thái trong danh sách dù với trạng thái 'Tất cả'");
    }
    @Test
    public void testLocVungmienTheoTrangThai_Nhap() throws InterruptedException {
        System.out.println("TC123 - Người dùng có thể lọc vùng miền theo trạng thái Nháp");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.locTrangThaiTheoTen("Nháp");
        Thread.sleep(2000);
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Nháp");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Nháp'");
    }
    @Test
    public void testLocVungmienTheoTrangThai_Canxemxet() throws InterruptedException {
        System.out.println("TC124 - Người dùng có thể lọc vùng miền theo trạng thái Cần xem xét");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.locTrangThaiTheoTen("Cần xem xét");
        Thread.sleep(2000);
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Cần xem xét");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Cần xem xét'");
    }
    @Test
    public void testLocVungmienTheoTrangThai_Xacnhan() throws InterruptedException {
        System.out.println("TC125 - Người dùng có thể lọc vùng miền theo trạng thái Xác nhận");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.locTrangThaiTheoTen("Xác nhận");
        Thread.sleep(2000);
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Xác nhận");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Xác nhận'");
    }
    @Test
    public void testLocVungmienTheoTrangThai_Xoa() throws InterruptedException {
        System.out.println("TC126 - Người dùng có thể lọc vùng miền theo trạng thái Xóa");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        page.locTrangThaiTheoTen("Xóa");
        Thread.sleep(2000);
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Xóa");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Xóaq'");
    }
    @Test
    public void testLocVoiTuKhoaKhongTonTai_QuaTatCaTrangThai() {
        System.out.println("TC127 - Người dùng có thể lọc với từ khóa không tồn tại qua tất cả các trạng thái");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLVungMienPage page = homePage.goToQLVungMienPage();
        String tuKhoa = "abcfjhjk";
        String[] trangThais = {"Tất cả", "Nháp", "Cần xem xét","Xác nhận","Xóa"};
        page.searchVungMien(tuKhoa);
        for (String trangThai : trangThais) {
            System.out.println(">> Đang kiểm tra trạng thái: " + trangThai);
            page.locTrangThaiTheoTen(trangThai);
            boolean hienThiChuaCoDuLieu = page.kiemTraChuaCoDuLieu();
            Assert.assertTrue(hienThiChuaCoDuLieu,
                    "Không hiển thị 'Chưa có dữ liệu' với từ khóa '" + tuKhoa + "' trong trạng thái '" + trangThai + "'");
        }
    }
}
