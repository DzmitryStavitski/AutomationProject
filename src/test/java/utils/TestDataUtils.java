package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import db.models.Attachment;
import db.models.Log;
import db.models.Test;

import java.sql.Timestamp;
import java.util.Random;

public class TestDataUtils {
    public static String getValueFromSettings(String key) {
        return AqualityServices
                .get(ISettingsFile.class)
                .getValue("/" + key)
                .toString();
    }

    public static Test getRandomTest(long projectId) {
        int maxSentenceRandomLength = Integer.parseInt(getValueFromSettings("max.sentence.random.length"));
        int maxWordRandomLength = Integer.parseInt(getValueFromSettings("max.word.random.length"));
        int randomStatus = new Random().nextInt(3) + 1;
        String randomBrowser = new Random().nextBoolean() ? "chrome" : "firefox";
        int randomIsException = new Random().nextInt(2);

        Test test = new Test(RandomUtils.randomString(maxSentenceRandomLength, maxWordRandomLength), randomStatus, RandomUtils.randomString(maxSentenceRandomLength, maxWordRandomLength), projectId,
                (long) 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
                RandomUtils.randomString(maxSentenceRandomLength, maxWordRandomLength), randomBrowser, null);

        Attachment attachment = new Attachment(AqualityServices.getBrowser().getScreenshot(), "image/png", (long) 1);
        Log log = new Log(RandomUtils.randomString(maxSentenceRandomLength, maxWordRandomLength), randomIsException, (long) 1);
        test.addAttachment(attachment);
        test.addLog(log);

        return test;
    }
}
