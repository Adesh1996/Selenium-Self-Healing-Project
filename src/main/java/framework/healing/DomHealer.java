package framework.healing;

import framework.dom.DomDiffUtil;
import framework.dom.DomSnapshotUtil;
import framework.locator.LocatorStore;
import org.openqa.selenium.*;

import java.util.List;

public class DomHealer {

    public static WebElement heal(WebDriver driver, String key) {

        System.out.println("SELF HEALING STARTED for: " + key);

        // Capture DOM BEFORE healing
        String beforeDom = DomSnapshotUtil.capture(driver);

        List<WebElement> inputs = driver.findElements(By.tagName("input"));

        WebElement bestMatch = null;
        double bestScore = 0.0;

        for (WebElement el : inputs) {
            double score = SimilarityScorer.score(el, key);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = el;
            }
        }
        
        double threshold = key.contains("username") || key.contains("password")
                ? 0.5   // adaptive threshold for inputs
                : 0.7;
        
        if (bestScore >= threshold) {

            System.out.println("BEST MATCH SCORE: " + bestScore);

            // Persist healed locator
            LocatorStore.save(key, bestMatch);

            // Capture DOM AFTER healing
            String afterDom = DomSnapshotUtil.capture(driver);

            // Generate DOM diff ONLY when healing succeeds
            DomDiffUtil.generateDiff(key, beforeDom, afterDom);

            return bestMatch;
        }

        throw new NoSuchElementException(
            "Self-healing failed for " + key + " (score < 0.7)"
        );
    }
}
