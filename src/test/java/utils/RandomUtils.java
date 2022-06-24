package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String randomString(int maxStringLength, int maxWordLength) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxStringLength; i++) {
            result.append(RandomStringUtils.randomAlphabetic(maxWordLength));
            result.append(" ");
        }
        return result.toString().strip();
    }
}