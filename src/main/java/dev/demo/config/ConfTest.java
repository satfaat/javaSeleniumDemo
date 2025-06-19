package dev.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfTest {

    private static final Logger logger = LoggerFactory.getLogger(ConfTest.class.getName());

    private static final String SECRET = "secret";
    private static final String CONFIG = "test";
    public static PropertiesAbs secretProperties;
    public static PropertiesAbs testProperties;

    private static final String ENV_PROPERTY_SYSTEM = "env"; // System property key, e.g., -Denv=TEST
    private static final String SECRET_PROPERTY_SYSTEM = "secret.properties.path"; // System property key
    private static final String ENV_PROPERTY_CONFIG = "env";

    private ConfTest() {
    }

    static {
        // Read config properties
        testProperties = new PropertiesManager(CONFIG);

        // Read secret properties
        // Step 1: Check for a secret property override
        String systemSecret = System.getProperty(SECRET_PROPERTY_SYSTEM);

        if (systemSecret != null && !systemSecret.trim().isEmpty()) {
            logger.info("Using 'secrets.properties' path from system property: {}", systemSecret);
            secretProperties = new PropertiesManagerFullPath(systemSecret);
        } else {
            // Step 2: If no system property is provided, use the default path.
            logger.info("No 'secret.properties.path' system property found. Defaulting to: {}", SECRET);
            secretProperties = new PropertiesManager(SECRET);
        }
    }

    /**
     * Determine the environment
     */
    private static String getEnvironmentName() {

        String environmentName = System.getProperty(ENV_PROPERTY_SYSTEM);

        if (environmentName != null && !environmentName.trim().isEmpty()) {
            logger.info(
                    "Environment specified via system property: -D{}='{}'. This takes precedence.",
                    ENV_PROPERTY_SYSTEM, environmentName);
            return environmentName;
        } else {
            environmentName = testProperties.readValue(ENV_PROPERTY_CONFIG);
            if (environmentName != null) {
                logger.info("Using environment from properties file: {}", CONFIG);
                return environmentName;
            } else {
                // set env to prod by default
                environmentName = "TEST";
            }
        }
        return environmentName;
    }
}
