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

public class TimkiemNSP {
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
    public void testTimKiemTheoTenChinhXac() throws InterruptedException {
        System.out.println("TC63 ‑ Người dùng có thể tìm kiếm nhóm sản phẩm theo tên chính xác");
        String tenNSP = "Đặc sản Bình Định";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(tenNSP);
        Thread.sleep(1000);
        int soKetQua = page.demSoKetQuaTheoTen(tenNSP);
        Assert.assertEquals(soKetQua, 1, "Không có kết quả phù hợp với tên nhóm sản phẩm cần tìm: " + tenNSP);
    }
    @Test
    public void testTimKiemTheoTuKhoaTonTai() throws InterruptedException {
        System.out.println("TC64 – Người dùng có thể tìm kiếm nhóm sản phẩm theo từ khóa phù hợp");
        String tuKhoa = "Tây";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(tuKhoa);
        Thread.sleep(1000);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có nhóm sản phẩm nào chứa từ khóa cần tìm kiếm: " + tuKhoa);
        int soKetQua = page.demSoKetQuaTheoTen(tuKhoa);
        System.out.println("Số kết quả tìm được là: " + soKetQua);
        Assert.assertTrue(soKetQua > 0, "Không có kết quả nhóm sản phẩm nào hiển thị phù hợp với từ khóa: " + tuKhoa);
    }
    @Test
    public void testTimKiemTheoTuKhoaKhongTonTai() throws InterruptedException {
        System.out.println("TC65 – Tìm kiếm nhóm sản phẩm với từ khóa không tồn tại");
        String tuKhoa = "abcdvvg";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(tuKhoa);
        Thread.sleep(1000);
        boolean hienThiThongBao = page.kiemTraChuaCoDuLieu();
        Assert.assertTrue(hienThiThongBao, "Hệ thống không hiển thị thông báo 'Chưa có dữ liệu' khi tìm kiếm từ khóa không hợp lệ.");
    }
    @Test
    public void testTimKiemNSPVsTKhoaKhongPhanBietChuHoaChuThuong() throws InterruptedException {
        System.out.println("TC66 – Tìm kiếm nhóm sản phẩm với từ khóa không phân biệt chữ hoa/chữ thường ");
        String tuKhoa = "đặc sản tây nguyên"; //DAC SAN TAY NGUYEN
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(tuKhoa);
        Thread.sleep(1000);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có nhóm sản phẩm nào chứa từ cần tìm: " + tuKhoa);
    }
    @Test
    public void testTimKiemNSPTheoTuKhoaKhongDau_SaiDauCau() throws InterruptedException {
        System.out.println("TC67– Tìm kiếm nhóm sản phẩm với từ khóa không dấu/sai dấu câu ");
        String tuKhoa = "Tầy Bặc"; //Miến Trùng,Miện Nàm, Binh Dinh....
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(tuKhoa);
        Thread.sleep(1000);
        boolean ketQuaPhuHop = page.kiemTraKetQuaTimKiem(tuKhoa);
        Assert.assertTrue(ketQuaPhuHop, "Không có nhóm sản phẩm nào chứa từ khóa: " + tuKhoa);
        int soKetQua = page.demSoKetQuaTheoTen(tuKhoa);
        System.out.println("So ket qua tim duoc la:" + soKetQua);
        Assert.assertTrue(soKetQua > 0, "Không có kết quả nhóm sản phẩm nào hiển thị phù hợp với từ khóa: " + tuKhoa);
    }
    @Test
    public void testTimKiemVoiChuoiQua255KiTu() throws InterruptedException {
        System.out.println("TC68 – Người dùng có thể tìm kiếm nhóm sản phẩm với chuỗi >255 kí tự");
        String longInput = "a".repeat(300);
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(longInput);
        Thread.sleep(1000);
        boolean hienThiKQ = page.kiemTraKetQuaTimKiem_2TH(longInput);
        Assert.assertTrue(hienThiKQ, "Hệ thống không hiển thị 'Chưa có dữ liệu' hoặc item lên list khi nhập chuỗi >255 ký tự.");
    }
    @Test
    public void testTimKiemNSPvoiKyTuDacBiet() {
        System.out.println("TC69 – Người dùng có thể tìm kiếm nhóm sản phẩm với ký tự đặc biệt");
        String tukhoa = "@#%%";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(tukhoa);
        boolean hienThiKQ = page.kiemTraChuaCoDuLieu();
        Assert.assertTrue(hienThiKQ, "Hệ thống không hiển thị item/chưa có dữ liệu ở list danh sách nhóm sản phẩm");
    }
    @Test
    public void testTimKiemNSPLienTuc(){
        System.out.println("TC70 - Tìm kiếm nhóm sản phẩm liên tục với các từ khóa khác nhau");
        String[] tukhoa =  {"Bình", "bánh", "mùa", "Đà Nẵng","gia vị","Đặc sản"};
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        for (String tuKhoa : tukhoa) {
            System.out.println("Đang tìm kiếm với từ khóa: " + tuKhoa);
            page.search(tuKhoa);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {e.printStackTrace();}
            boolean ketQuaHopLe = page.kiemTraKetQuaTimKiem(tuKhoa);
            Assert.assertTrue(ketQuaHopLe, "Không có nhóm sản phẩm nào chứa từ khóa: " + tuKhoa);
        }
    }
    @Test
    public void testTimKiemNSP_VsKhoangTrang() throws InterruptedException {
        System.out.println("TC71 – Tìm kiếm nhóm sản phẩm với khoảng trắng");
        String tukhoa = "  bánh";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(tukhoa);
        Thread.sleep(2000);
        boolean hienThiChuaCoDuLieu = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(hienThiChuaCoDuLieu, "Hệ thống không hiển thị nhóm sản phẩm cần tìm với từ khóa");
    }
    // FAILED => CHƯA CÓ XỬ LÝ KHOẢNG TRẮNG
    @Test
    public void testKiemTraTimKiemKetHopVsReloadlistNSP() throws InterruptedException {
        System.out.println("TC72 - Tìm kiếm kết hợp reload list nhóm sản phẩm ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tukhoa = "Gia vị";
        page.search(tukhoa);
        Thread.sleep(2000);
        boolean ketquaDung = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(ketquaDung, "Không có nhóm sản phẩm nào chứa từ khóa: " + tukhoa);
        Thread.sleep(2000);
        page.reloadNSPList();
        String searchBoxValue = page.getSearchBoxText();
        Assert.assertEquals(searchBoxValue, tukhoa,"Ô tìm kiếm không còn giữ lại từ khóa cũ sau khi reload");
        boolean coKetQua = page.kiemTraKetQuaTimKiem(tukhoa);
        Assert.assertTrue(coKetQua, "Sau khi reload, danh sách vẫn giữ nguyên kết quả tìm kiếm");
    }
    @Test
    public void testXoaTuKhoaSauTimKiem(){
        System.out.println("TC73 - Xóa từ khóa sau khi tìm kiếm ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        Assert.assertTrue(page.waitForListDanhsachToAppear(), "Danh sách nhóm sản phẩm ban đầu không hiển thị.");
        int initialItemCount = page.getNumberOfNSPItems();
        Assert.assertTrue(initialItemCount > 0, "Danh sách ban đầu trống rỗng, không thể tiếp tục test tìm kiếm.");
        String tukhoa = "Miền Trung";
        page.search(tukhoa);
        page.clickXoaTimKiem();
        boolean listHienThiLai = page.waitForListDanhsachToAppear();
        Assert.assertTrue(listHienThiLai, "Sau khi nhấn icon hủy tìm kiếm, danh sách nhóm sản phẩm ban đầu không hiển thị lại.");
        int afterClearItemCount = page.getNumberOfNSPItems();
        System.out.println("Số item hiển thị lại là: "+ afterClearItemCount );
        Assert.assertEquals(afterClearItemCount, initialItemCount, "Số lượng item sau khi xóa tìm kiếm không khớp với số lượng ban đầu.");
    }
    @Test
    public void testTimKiemVoiMaNSP_HopLe() throws InterruptedException {
        System.out.println("TC74 – Tìm kiếm nhóm sản phẩm với mã nhóm sản phẩm hợp lệ");
        String maNSP = "BĐ-280165765"; //co the nhap BĐ,280...
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(maNSP);
        Thread.sleep(2000);
        boolean hienThiKQ = page.waitForListDanhsachToAppear();
        Assert.assertTrue(hienThiKQ, "Hệ thống không hiển thị nhóm sản phẩm phù hợp với mã sản phẩm cần tìm");
    }
    @Test
    public void testTimKiemVoiMaNSP_KhongHopLe() throws InterruptedException {
        System.out.println("TC75 – Tìm kiếm nhóm sản phẩm với mã nhóm sản phẩm không tồn tại");
        String maNSP = "BĐ-280165777";
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.search(maNSP);
        Thread.sleep(2000);
        boolean hienThiChuaCoDuLieu = page.kiemTraChuaCoDuLieu();
        Assert.assertTrue(hienThiChuaCoDuLieu, "Hệ thống không hiển thị chưa có dữ liệu với tìm kiếm mã nhóm sản phẩm không tồn tại");
    }
    @Test
    public void testTimKiemNSPKetHop_LocTatCa() {
        System.out.println("TC76 - Tìm kiếm nhóm sản phẩm kết hợp lọc với trạng thái 'Tất cả'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tuKhoa = "Đặc sản";
        String trangThai = "Tất cả";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Danh sách không chứa đầy đủ các nhóm sản phẩm theo từ khóa '" + tuKhoa + "' với trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemNSPKetHop_LocHienThi() {
        System.out.println("TC77 - Tìm kiếm nhóm sản phẩm kết hợp lọc với trạng thái 'Hiển thị'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tuKhoa = "Đặc sản";
        String trangThai = "Hiển thị";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, " Danh sách không tồn tại các nhóm sản phẩm theo từ khóa '" + tuKhoa + "' với trạng thái '" + trangThai + "'");
    }
    @Test
    public void testTimKiemNSPKetHop_LocKhongHienThi() {
        System.out.println("TC78 - Tìm kiếm nhóm sản phẩm kết hợp lọc với trạng thái 'Không hiển thị'");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tuKhoa = "Đặc sản";
        String trangThai = "Không hiển thị";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, " Danh sách không tồn tại các nhóm sản phẩm theo từ khóa '" + tuKhoa + "' với trạng thái '" + trangThai + "'");
    }
    @Test
    public void testLocNSPTheoTrangThai_TatCa() throws InterruptedException {
        System.out.println("TC79 - Lọc nhóm sản phẩm theo trạng thái Tất cả");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.locTrangThaiTheoTen("Tất cả");
        Thread.sleep(1000);
        boolean ketQua = page.kiemTraTrangThaiKhiLocTatCa();
        Assert.assertTrue(ketQua,"Không hiển thị đủ các trạng thái 'Hiển thị' và 'Không hiển thị' trong danh sách với trạng thái 'Tất cả'");
    }
    @Test
    public void testLocNSPTheoTrangThai_HienThi() throws InterruptedException {
        System.out.println("TC80 - Lọc nhóm sản phẩm theo trạng thái Hiển thị");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.locTrangThaiTheoTen("Hiển thị");
        Thread.sleep(2000);
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Hiển thị");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Hiển thị'");
    }
    @Test
    public void testLocNSPTheoTrangThai_KhongHienThi() throws InterruptedException {
        System.out.println("TC81 - Lọc nhóm sản phẩm theo trạng thái Không hiển thị");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME,constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        page.locTrangThaiTheoTen("Không hiển thị");
        Thread.sleep(2000);
        boolean ketQua = page.kiemTraTrangThaiList_LocQuaTatCaTrang("Không hiển thị");
        Assert.assertTrue(ketQua, "Danh sách lọc không đúng tất cả ở trạng thái 'Không hiển thị'");
    }
    @Test
    public void testTimKiemKetHop_LocVoiTuKhoaDungNhungKhongTonTaiTrangThaiLoc() {
        System.out.println("TC82 - Lọc với đúng từ khóa nhưng từ khóa không có trong trạng thái cần lọc");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tuKhoa = "theo mùa";
        String trangThai = "Không hiển thị";
        page.search(tuKhoa);
        page.locTrangThaiTheoTen(trangThai);
        boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
        Assert.assertTrue(ketQua, "Không hiển thị 'Chưa có dữ liệu' khi từ khóa '" + tuKhoa + "' không tồn tại trong trạng thái '" + trangThai + "'");
    }
    @Test
    public void testLocVoiTuKhoaKhongTonTai_QuaTatCaTrangThai() {
        System.out.println("TC83 - Lọc với từ khóa không tồn tại qua tất cả các trạng thái");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tuKhoa = "abcfjhjk";
        String[] trangThais = {"Tất cả", "Hiển thị", "Không hiển thị"};
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
    public void testChuyenDoiNhanhGiuaCacFilter() {
        System.out.println("TC84 - Kiểm tra chuyển đổi nhanh giữa các filter khi lọc nhóm sản phẩm qua nhiều trạng thái");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        loginPage.isLoginThanhCong();
        QLNhomSPPage page = homePage.goToQLNhomSPPage();
        String tuKhoa = "Bình";
        page.search(tuKhoa);
        String[] filters = {"Tất cả", "Hiển thị", "Không hiển thị"};
        for (String trangThai : filters) {
            System.out.println(">> Kiểm tra lọc với trạng thái: " + trangThai);
            page.locTrangThaiTheoTen(trangThai);
            boolean ketQua = page.kiemTraKetHopTimKiemVaTrangThaiToanTrang(tuKhoa, trangThai);
            Assert.assertTrue(ketQua, "Dữ liệu không khớp sau khi lọc '" + trangThai + "' với từ khóa '" + tuKhoa + "'");
        }
    }
}
