package framework.healing;

import org.openqa.selenium.WebElement;

public class SimilarityScorer {

    public static double score(WebElement el, String key) {

        double score = 0.0;
        String token = extract(key);

        // Tag match (30%)
        if ("input".equalsIgnoreCase(el.getTagName())) {
            score += 0.3;
        }

        // Attribute similarity (stronger weights)
        score += strongMatch(el.getAttribute("id"), token, 0.3);
        score += strongMatch(el.getAttribute("name"), token, 0.25);
        score += strongMatch(el.getAttribute("aria-label"), token, 0.25);
        score += weakMatch(el.getAttribute("placeholder"), token, 0.15);

        // Visibility & usability (20%)
        if (el.isDisplayed() && el.isEnabled()) {
            score += 0.2;
        }

        return Math.min(score, 1.0);
    }

    private static double strongMatch(String attr, String token, double weight) {
        if (attr == null) return 0;
        return attr.equalsIgnoreCase(token) ? weight : 0;
    }

    private static double weakMatch(String attr, String token, double weight) {
        if (attr == null) return 0;
        return attr.toLowerCase().contains(token) ? weight : 0;
    }

    private static String extract(String key) {
        return key.substring(key.lastIndexOf('.') + 1).toLowerCase();
    }
}
