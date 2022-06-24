package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import org.apache.http.client.utils.URIBuilder;

public class UrlUtils {
    public static String getAuthorizationUrl() {
        final String url = AqualityServices
                .get(ISettingsFile.class)
                .getValue("/web.url")
                .toString();
        final String login = AqualityServices
                .get(ISettingsFile.class)
                .getValue("/login")
                .toString();
        final String password = AqualityServices
                .get(ISettingsFile.class)
                .getValue("/password")
                .toString();
        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setUserInfo(login, password);

            return builder.build().toURL().toString();
        } catch (Exception e) {
            AqualityServices.getLogger().error(e.getMessage());
            return null;
        }
    }
}