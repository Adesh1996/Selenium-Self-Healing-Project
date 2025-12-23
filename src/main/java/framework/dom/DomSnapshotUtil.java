package framework.dom;
import org.openqa.selenium.*;import java.nio.file.*;
public class DomSnapshotUtil{public static String capture(WebDriver d){return(String)((JavascriptExecutor)d).executeScript("return document.documentElement.outerHTML;");}
public static void save(String p,String dom)throws Exception{Files.createDirectories(Paths.get("dom-history"));Files.write(Paths.get("dom-history/"+p+".html"),dom.getBytes());}}