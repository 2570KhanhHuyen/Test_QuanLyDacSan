package common;

import constant.constant;
import org.openqa.selenium.By;

public class HeaderComponent {
    private final By btnUser = By.id("page-header-user-dropdown");
    private final By btnLogout = By.id("a_disconnect");
    private final By btnProfile = By.id("a_profile");

    public void openUserMenu() {constant.WEBDRIVER.findElement(btnUser).click();}
    public void clickLogout() {constant.WEBDRIVER.findElement(btnLogout).click();}
    public void clickProfile() {constant.WEBDRIVER.findElement(btnProfile).click();}
}