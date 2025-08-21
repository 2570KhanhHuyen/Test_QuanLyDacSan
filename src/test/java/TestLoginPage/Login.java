package TestLoginPage;

import PageObjects.ForgotPasswordPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import constant.constant;

public class Login {
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
    public void LoginSuccessfully() {
        System.out.println("TC01 - Người dùng có thể đăng nhập thành công với tên đăng nhập và mật khẩu hợp lệ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        String actualMsg = homePage.getWelcomeMessage();
        String expectedMsg = "Chào mừng quay trở lại !";
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Loi chao mung khong duoc hien thi nhu mong doi");
    }
    @Test
    public void Loginwithblank_Username() {
        System.out.println("TC02 - Người dùng không thể đăng nhập vào hệ thống quản lý nông nghiệp với tên đăng nhập để trống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.loginWithInvalidCredential("", constant.PASSWORD);
        String actualErrorMsg = loginPage.getLblLoginErrorMessage().getText();
        String expectedErrorMsg = "Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo lỗi không đúng như mong đợi");
    }
    @Test
    public void LoginwithInvalidPassword(){
        System.out.println("TC03 - Người dùng không thể đăng nhập vào hệ thống quản lý nông nghiệp với mật khẩu không hợp lệ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.loginWithInvalidCredential(constant.USERNAME,constant.INVALID_PASSWORD);
        String actualErrorMsg = loginPage.getLblLoginErrorMessage().getText();
        String expectedErrorMsg = "Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo lỗi không đúng như mong đợi");
    }
    @Test
    public void LoginwithBlank_Password() {
        System.out.println("TC04 - Người dùng không thể đăng nhập vào hệ thống quản lý nông nghiệp với mật khẩu để trống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.loginWithInvalidCredential(constant.USERNAME, "");
        String actualErrorMsg = loginPage.getLblLoginErrorMessage().getText();
        String expectedErrorMsg = "Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo lỗi không đúng như mong đợi");
    }
    @Test
    public void LoginwithInvalidUsername(){
        System.out.println("TC05 - Người dùng không thể đăng nhập vào hệ thống quản lý nông nghiệp với tên đăng nhập không hợp lệ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.loginWithInvalidCredential(constant.INVALID_USERNAME,constant.PASSWORD);
        String actualErrorMsg = loginPage.getLblLoginErrorMessage().getText();
        String expectedErrorMsg = "Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo lỗi không đúng như mong đợi");
    }
    @Test
    public void LoginwithInvalidUsernameandPassword(){
        System.out.println("TC06 -Người dùng không thể đăng nhập vào hệ thống quản lý nông nghiệp với tên đăng nhập và mật khẩu không hợp lệ");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.loginWithInvalidCredential(constant.INVALID_USERNAME,constant.INVALID_PASSWORD);
        String actualErrorMsg = loginPage.getLblLoginErrorMessage().getText();
        String expectedErrorMsg = "Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo lỗi không đúng như mong đợi");
    }
    @Test
    public void LoginwithBlankUsernameandPassword(){
        System.out.println("TC07 - Người dùng không thể đăng nhập vào hệ thống quản lý nông nghiệp với tên đăng nhập và mật khẩu để trống");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.loginWithInvalidCredential("","");
        String actualErrorMsg = loginPage.getLblLoginErrorMessage().getText();
        String expectedErrorMsg = "Không thể đăng nhập, vui lòng kiểm tra lại tên đăng nhập và mật khẩu";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo lỗi không đúng như mong đợi");
    }
    @Test
    public void Logout() {
        System.out.println("TC08 - Người dùng có thể đăng xuất khỏi hệ thống quản lý nông nghiệp");
        LoginPage loginPage = new LoginPage();
        loginPage.open();
        HomePage homePage = loginPage.loginWithValidCredential(constant.USERNAME, constant.PASSWORD);
        String actualMsg = homePage.getWelcomeMessage();
        String expectedMsg = "Chào mừng quay trở lại !";
        Assert.assertTrue(actualMsg.contains(expectedMsg), "Loi chao mung khong duoc hien thi nhu mong doi");
        homePage.ClickHeaderButton();
        homePage.clickDangXuatbtn();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Khong quay ve man hinh dang nhap sau khi dang xuat");
    }
    @Test
    public void ForgotPassword() {
        System.out.println("TC09 - Người dùng có thể sử dụng tính năng 'Quên mật khẩu'");
        LoginPage loginPage = new LoginPage();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
        loginPage.open();
        loginPage.clickbtn_ForgotPassword();
        forgotPasswordPage.forgotPassword("adm");
        String actualErrorMsg = forgotPasswordPage.getSuccesSendEmail();
        String expectedErrorMsg = "Vui lòng kiểm tra email để đổi mật khẩu";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Thông báo thành công không đúng như mong đợi");
    }
}
