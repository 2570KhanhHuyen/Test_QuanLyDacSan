package common;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Random_ThemMoiDS {
    static Random random = new Random();
    // 1. Random tên đặc sản
    public static String generateRandomTenDacSan() {
        List<String> dacSanList = Arrays.asList(
                "Bánh Pía Sóc Trăng", "Cơm Tấm Sài Gòn", "Lẩu Mắm Cần Thơ", "Bánh Bèo Huế", "Nem Nướng Nha Trang", "Bánh Đa Cua Hải Phòng",
                "Bánh Căn Phan Rang", "Cá Kho Tộ Tiền Giang", "Bún Riêu Cua", "Phở Bò Nam Định", "Chè Bưởi Cần Thơ",
                "Xôi Chè Phú Thọ", "Cá Kho Làng Vũ Đại", "Cao Lầu Hội An", "Bánh Hỏi Lòng Heo", "Gỏi Cá Nam Ô",
                "Bánh Tét Trà Cuôn", "Hủ Tiếu Mỹ Tho", "Lạp Xưởng Cần Đước", "Khô Cá Lóc An Giang", "Rượu Cần Tây Nguyên"
        );
        return dacSanList.get(random.nextInt(dacSanList.size()));
    }
    // 2. Mã đặc sản: viết tắt tên đặc sản + số từ 001 đến 100 (giữ 3 chữ số có padding)
    public static String generateMaDacSan(String tenDacSan) {
        String[] words = tenDacSan.trim().split(" ");
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
    // Random mã đặc sản ko theo tên
    public static String generateMaDacSanWithoutTen() {
        return "DS" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
    // Random đặc sản vùng
    public static String generateDacSanVung() {
        List<String> regions = Arrays.asList("Miền Bắc", "Miền Trung", "Miền Nam", "Miền Tây");
        return regions.get(random.nextInt(regions.size()));
    }
    // Random trạng thái
    public static String generateTrangThai() {
        List<String> statusList = Arrays.asList("Nháp", "Cần kiểm tra", "Xác nhận", "Xóa");
        return statusList.get(random.nextInt(statusList.size()));
    }
    // Test thử
    public static void main(String[] args) {
        String ten = generateRandomTenDacSan();
        String ma = generateMaDacSan(ten);
        String vung = generateDacSanVung();
        String trangThai = generateTrangThai();

        System.out.println("Ten dac san: " + ten);
        System.out.println("Ma dac san: " + ma);
        System.out.println("Đac san vung: " + vung);
        System.out.println("Trang thai: " + trangThai);
    }
    //hàm tạo chuỗi > 255 kí tự
    public static String generateStringOver255Chars() {
        return "K".repeat(300);
    }
}
