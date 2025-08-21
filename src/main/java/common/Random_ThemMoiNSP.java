package common;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Random_ThemMoiNSP {
    static Random random = new Random();
    // 1. Random tên nhóm sản phâm
    public static String generateRandomTenNSP() {
        List<String> NSPList = Arrays.asList(
                "Đặc Sản Huế","Đặc Sản Cần Thơ","Đặc Sản Nha Trang","Đặc Sản Hà Nội","Đặc Sản Miền Bắc",
                "Đặc Sản Bắc Trung Bộ", "Đặc Sản Nam Trung Bộ", "Đặc Sản Đồng Bằng Sông Cửu Long","Đặc Sản Đông Nam Bộ", "Đặc sản Miền Tây",
                "Đặc sản Phú Yên","Đặc sản Quy Nhơn","Đặc sản Lào","Đặc sản Thanh Hóa","Đặc sản Nghệ An","Đặc sản Kon Tum","Đặc sản Quãng Ngãi"
        );
        return NSPList.get(random.nextInt(NSPList.size()));
    }
    // 2. Mã đặc sản: viết tắt tên nhóm sản phẩm + số từ 001 đến 100 (giữ 3 chữ số có padding)
    public static String generateMaNSP(String tenNSP) {
        String[] words = tenNSP.trim().split(" ");
        StringBuilder ma = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                ma.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        int randomNumber = random.nextInt(100) + 1; // Từ 1 đến 100
        String numberPart = String.format("%03d", randomNumber); // padding thành 001, 002, ..., 100
        return ma.toString() + numberPart;
    }
    // Random mã nhóm sản phẩm ko theo tên
    public static String generateMaNSPnWithoutTen() {
        return "NSP" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
    // 3. Random trạng thái
    public static String generateTrangThai() {
        List<String> statusList = Arrays.asList("Hiển thị", "Không hiển thị");
        return statusList.get(random.nextInt(statusList.size()));
    }
    // Test thử
    public static void main(String[] args) {
        String ten = generateRandomTenNSP();
        String ma = generateMaNSP(ten);
        String trangThai = generateTrangThai();

        System.out.println("Ten nhom san pham: " + ten);
        System.out.println("Ma nhom san pham: " + ma);
        System.out.println("Trang thai: " + trangThai);
    }
    //hàm tạo chuỗi > 255 kí tự
    public static String generateStringOver255Chars() {
        return "v".repeat(300);
    }
}
