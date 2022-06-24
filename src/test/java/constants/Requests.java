package constants;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;

public class Requests {
    private static final String apiUrl = AqualityServices
            .get(ISettingsFile.class)
            .getValue("/api.url")
            .toString();

    public static final String GET_TOKEN = getRequest("/token/get");
    public static final String GET_TESTS = getRequest("/test/get/json");

    private static String getRequest(String req) {
        return apiUrl + req;
    }
}
