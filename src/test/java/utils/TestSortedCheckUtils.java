package utils;

import models.Test;

import java.util.List;

public class TestSortedCheckUtils {
    public static boolean ifTestsSortedByDate(List<Test> tests) {
        boolean isOrdered = true;
        for (int i = 0; i < tests.size() - 1; i++) {
            if (tests.get(i).getStartTime().before(tests.get(i + 1).getStartTime())) {
                isOrdered = false;
                break;
            }
        }
        return isOrdered;
    }
}
