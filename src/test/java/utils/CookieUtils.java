package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CookieUtils {
    private static WebDriver driver = AqualityServices.getBrowser().getDriver();
    private static Logger logger = AqualityServices.getLogger();

    public static void addCookie(Cookie cookie) {
        logger.info("Add new cookie");
        driver.manage().addCookie(cookie);
    }

    public static void deleteCookieName(String name) {
        logger.info("Delete cookie");
        driver.manage().deleteCookieNamed(name);
    }

    public static Set<Cookie> getCookies() {
        logger.info("Getting all cookies");
        return driver.manage().getCookies();
    }

    public static boolean containsCookie(String name) {
        for (Cookie cookie : getCookies()) {
            if (cookie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void changeCookieValue(String cookieName, String newValue) {
        logger.info("Changing cookie value");
        driver.manage().deleteCookieNamed(cookieName);
        driver.manage().addCookie(new Cookie(cookieName, newValue));
    }

    public static void deleteAllCookies() {
        logger.info("Delete all cookies");
        driver.manage().deleteAllCookies();
    }
}
