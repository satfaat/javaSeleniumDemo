package dev.demo.config;

import java.io.File;

/**
 * Manages properties located via a full, absolute file path.
 * This class assumes the provided path is the exact file to be used.
 */
public class PropertiesManagerFullPath extends PropertiesAbs {

    private final String fullFilePath;

    /**
     * Constructor for managing properties using a full, absolute file path.
     * @param fullFilePath The complete, absolute path to the properties file
     * (e.g., "/etc/myapp/config/secrets.properties").
     */
    public PropertiesManagerFullPath(String fullFilePath) {
        if (fullFilePath == null || fullFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Full file path cannot be null or empty.");
        }
        this.fullFilePath = fullFilePath;

        // Initialize and load properties (delegated to abstract class)
        initializeAndLoad();
    }

    @Override
    protected File getFile() {
        return new File(this.fullFilePath);
    }
}
