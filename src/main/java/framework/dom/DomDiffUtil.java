package framework.dom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class DomDiffUtil {

    public static void generateDiff(String key, String beforeDom, String afterDom) {

        try {
            Document before = Jsoup.parse(beforeDom);
            Document after = Jsoup.parse(afterDom);

            Set<String> beforeSet = extract(before);
            Set<String> afterSet = extract(after);

            Set<String> added = new HashSet<>(afterSet);
            added.removeAll(beforeSet);

            Set<String> removed = new HashSet<>(beforeSet);
            removed.removeAll(afterSet);

            writeHtmlReport(key, added, removed);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Set<String> extract(Document doc) {
        Set<String> elements = new HashSet<>();

        for (Element el : doc.select("*")) {
            String signature =
                    el.tagName() + "|" +
                    el.id() + "|" +
                    el.className() + "|" +
                    el.attr("name") + "|" +
                    el.attr("placeholder");

            elements.add(signature);
        }
        return elements;
    }

    private static void writeHtmlReport(
            String key,
            Set<String> added,
            Set<String> removed) throws Exception {

        File dir = new File("dom-diff");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, key + "_diff.html");
        FileWriter writer = new FileWriter(file);

        writer.write("<html><head><title>DOM Diff</title></head><body>");
        writer.write("<h2>DOM Diff Report</h2>");
        writer.write("<h3>Key: " + key + "</h3>");

        writer.write("<h3 style='color:green'>Added Elements</h3><ul>");
        for (String a : added) {
            writer.write("<li>" + escape(a) + "</li>");
        }
        writer.write("</ul>");

        writer.write("<h3 style='color:red'>Removed Elements</h3><ul>");
        for (String r : removed) {
            writer.write("<li>" + escape(r) + "</li>");
        }
        writer.write("</ul>");

        writer.write("</body></html>");
        writer.close();

        System.out.println("DOM DIFF GENERATED: dom-diff/" + key + "_diff.html");
    }

    private static String escape(String s) {
        return s.replace("<", "&lt;").replace(">", "&gt;");
    }
}
