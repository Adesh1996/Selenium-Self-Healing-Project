package framework.locator;

import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.util.Properties;

public class LocatorStore {

    private static final String FILE = "healed-locators.properties";

    public static void save(String key, WebElement el) {
        try {
            Properties props = new Properties();

            String healedXpath =
                "//*[@id='" + el.getAttribute("id") + "']";

            props.setProperty(key, healedXpath);

            FileWriter fw = new FileWriter(FILE, true);
            props.store(fw, "Healed locator saved");
            fw.close();

            System.out.println("LOCATOR PERSISTED: " + key);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
