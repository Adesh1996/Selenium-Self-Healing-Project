package framework.base;

import framework.dom.*;
import framework.driver.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
	protected WebDriver d;
	String prev;

	@BeforeMethod
	public void setUp() {
		d = new ChromeDriver();
		d.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		prev = DomSnapshotUtil.capture(d);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		String cur = DomSnapshotUtil.capture(d);
		DomDiffUtil.generateDiff("page", prev, cur);
		DomSnapshotUtil.save("page", cur);
		d.quit();
	}

	protected void initPage(Object p) {
		HealingPageFactory.initElements(d, p);
	}
}