package dev.demo.config;

import java.time.Duration;

public class FrameworkConstants {
    public static final String CONFIG_FILE = "config.properties";
    public static final String TEST_DATA_PATH = "src/test/resources/testdata/";
    public static final String SCREENSHOT_PATH = "target/screenshots/";
    public static final String REPORT_PATH = "target/reports/";
    public static final int IMPLICIT_WAIT = Integer.parseInt(System.getProperty("implicitWait", "10"));
    public static final int EXPLICIT_WAIT = Integer.parseInt(System.getProperty("explicitWait", "10"));
    public static final int PAGE_LOAD_TIMEOUT = Integer.parseInt(System.getProperty("pageLoadTimeout", "20"));
    public static final String BASE_URL = ConfTest.testProperties.readValue("url.test");

    public static Duration getDuration(int seconds) {
        return Duration.ofSeconds(seconds);
    }
}
