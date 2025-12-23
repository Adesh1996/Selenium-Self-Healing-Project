package framework.driver;

import framework.annotation.Heal;
import framework.healing.DomHealer;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import java.lang.reflect.Field;

public class HealingPageFactory {
	public static void initElements(WebDriver d, Object p) {
		PageFactory.initElements(d, p);
		for (Field f : p.getClass().getDeclaredFields()) {
			if (f.isAnnotationPresent(Heal.class)) {
				f.setAccessible(true);
				try {
					((WebElement) f.get(p)).isDisplayed();
				} catch (Exception e) {
					try {
						f.set(p, DomHealer.heal(d, f.getAnnotation(Heal.class).key()));
					} catch (Exception ignored) {
					}
				}
			}
		}
	}
}