package dev.demo.base;

import dev.demo.core.driver.DriverManager;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


public class TestWatcherExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        DriverManager.setTestAsFailed();
    }
}
