package pages;

import framework.healing.DomHealer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "loginBtn")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String value) {
        try {
            username.sendKeys(value);
        } catch (NoSuchElementException e) {
            System.out.println("SELF HEALING: username");
            WebElement healed =
                    DomHealer.heal(driver, "login.username");
            healed.sendKeys(value);
        }
    }

    public void enterPassword(String value) {
        try {
            password.sendKeys(value);
        } catch (NoSuchElementException e) {
            System.out.println("SELF HEALING: password");
            WebElement healed =
                    DomHealer.heal(driver, "login.password");
            healed.sendKeys(value);
        }
    }

    public void clickLogin() {
        try {
            loginBtn.click();
        } catch (NoSuchElementException e) {
            System.out.println("SELF HEALING: login button");
            WebElement healed =
                    DomHealer.heal(driver, "login.button");
            healed.click();
        }
    }
}
