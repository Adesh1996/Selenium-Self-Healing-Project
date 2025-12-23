package tests;

import framework.base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	@Test
	public void login() {
		LoginPage p = new LoginPage(d);

		p.enterUsername("admin");
		p.enterPassword("admin123");
		p.clickLogin();
	}
}