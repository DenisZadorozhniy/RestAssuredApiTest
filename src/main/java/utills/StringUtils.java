package utills;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {
    private static final int DEFAULT_RANDOM_STRING_LEN = 10;

    public static String generateRandomString(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public static String generateDefaultRandomString() {
        return generateRandomString(DEFAULT_RANDOM_STRING_LEN);
    }
}
