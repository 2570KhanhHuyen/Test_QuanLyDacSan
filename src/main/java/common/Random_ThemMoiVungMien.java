package common;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Random_ThemMoiVungMien {
    static Random random = new Random();
    // 1. Random tên vùng miền
    public static String generateRandomTenVungMien() {
        List<String> VungMienList = Arrays.asList(
                "Miền Tây", "Hồ Chí Minh", "Hà Nội", "Cần Thơ", "Huế", "Nha Trang",
                "Quảng Ninh", "Hải Phòng", "Vũng Tàu", "Đà Lạt", "Bình Thuận",
                "Nghệ An", "Thanh Hóa", "Quảng Bình", "Đồng Tháp", "Cà Mau",
                "Tây Nguyên", "Lào Cai", "Đông Bắc Bộ", "Hà Giang", "Tây Bắc Bộ",
                "Sơn La", "Điện Biên", "Thái Nguyên", "Bắc Giang", "Vĩnh Phúc",
                "Ninh Bình", "Nam Định", "Thái Bình", "Quảng Trị", "Quảng Nam",
                "Bình Định", "Khánh Hòa", "Bình Dương", "Đồng Nai", "Long An"
        );
        return VungMienList.get(random.nextInt(VungMienList.size()));
    }
    // 2. Random mã vùng miền theo 3 số ngẫu nhiên
    public static String generateMaVungMien() {
        int randomNumber = random.nextInt(1000); // Từ 0 đến 999
        String numberPart = String.format("%03d", randomNumber); // padding thành 000, 001, ..., 999
        return numberPart;
    }
    // 3. Random kiểu vùng miền
    public static String generateKieuVungMien() {
        List<String> typeRegion = Arrays.asList("Vùng miền", "Tỉnh thành", "Điểm nổi bật");
        return typeRegion.get(random.nextInt(typeRegion.size()));
    }
    // 4. Random thuộc miền (Cái này ko thuôc bắt buộc =>Có thể dùng hoặc không)
    public static String generateThuocMien() {
        List<String> typeRegion = Arrays.asList("Miền Trung", "Miền Nam", "Miền Bắc");
        return typeRegion.get(random.nextInt(typeRegion.size()));
    }
    // 5. Random trạng thái
    public static String generateTrangThai() {
        List<String> statusList = Arrays.asList("Nháp", "Cần xem xét", "Xác nhận", "Xóa");
        return statusList.get(random.nextInt(statusList.size()));
    }
    // 6. Random dien tich
    public static String generateRandomDienTich() {
        // Random số nguyên lớn, không âm (ví dụ: từ 0 đến 1.000.000)
        long randomDienTich = (long) (random.nextDouble() * 1_000_000); // Sử dụng long để có số lớn hơn
        return String.valueOf(randomDienTich);
    }
    // 7. Random kinh do
    public static String generateRandomKinhDo() {
        double randomKinhDo = (random.nextDouble() * 360) - 180;
        randomKinhDo = Math.round(randomKinhDo * 1000.0) / 1000.0;
        return String.valueOf(randomKinhDo);
    }
    // 8. Random vi do
    public static String generateRandomViDo() {
        // Random số thập phân từ -90.0 đến 90.0
        double randomViDo = (random.nextDouble() * 180) - 90; // (0-1) * 180 - 90 = -90 đến 90
        randomViDo = Math.round(randomViDo * 1000.0) / 1000.0;
        return String.valueOf(randomViDo);
    }
    // Các điểm biên
    public static String generateRandomKinhDoNho() {
        // Random số thập phân từ -90.0 đến 90.0
        double randomKinhDo = (random.nextDouble() * 180) - 90; // (0-1) * 180 - 90 = -90 đến 90
        randomKinhDo = Math.round(randomKinhDo * 10.0) / 10.0;
        // Đảm bảo không quá 2 chữ số nguyên
        if (Math.abs(randomKinhDo) > 99.9) { // Nếu vượt quá 2 chữ số nguyên, giới hạn lại
            randomKinhDo = (random.nextDouble() * 20) - 10; // Random trong khoảng nhỏ hơn nữa
            randomKinhDo = Math.round(randomKinhDo * 10.0) / 10.0;
        }
        return String.valueOf(randomKinhDo);
    }
    public static String generateRandomViDoNho() {
        // Random số thập phân từ -45.0 đến 45.0
        double randomViDo = (random.nextDouble() * 90) - 45; // (0-1) * 90 - 45 = -45 đến 45
        randomViDo = Math.round(randomViDo * 10.0) / 10.0;
        // Đảm bảo không quá 2 chữ số nguyên
        if (Math.abs(randomViDo) > 99.9) { // Vĩ độ max 90, nên kiểm tra này có thể không cần thiết nếu phạm vi nhỏ
            randomViDo = (random.nextDouble() * 20) - 10; // Random trong khoảng nhỏ hơn nữa
            randomViDo = Math.round(randomViDo * 10.0) / 10.0;
        }
        return String.valueOf(randomViDo);
    }
    //hàm tạo chuỗi > 200 kí tự
    public static String generateStringOver200Chars() {
        return "w".repeat(201);
    }
    //hàm tạo chuỗi <= 200 kí tự
    public static String generateString200Chars() {
        return "d".repeat(200);
    }
    public static void main(String[] args) {
        System.out.println("Tên vùng miền random: " + generateRandomTenVungMien());
        System.out.println("Mã vùng miền random: " + generateMaVungMien());
        System.out.println("Kiểu vùng miền random: " + generateKieuVungMien());
        System.out.println("Diện tích random: " + generateRandomDienTich());
        System.out.println("Kinh độ random: " + generateRandomKinhDo());
        System.out.println("Vĩ độ random: " + generateRandomViDo());
        System.out.println("Kinh độ nhỏ random: " + generateRandomKinhDoNho());
        System.out.println("Vĩ độ nhỏ random: " + generateRandomViDoNho());
    }
}
