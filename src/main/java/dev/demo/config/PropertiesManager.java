package dev.demo.config;

import java.io.File;
import java.nio.file.Paths;

/**
 * Manages properties located via a base filename and an optional relative/absolute directory path.
 * Defaults to "src/main/resources" if no directory path is provided.
 * Appends ".properties" extension if missing from the base filename.
 */
public class PropertiesManager extends PropertiesAbs {
    private static final String DEFAULT_RESOURCES_RELATIVE_PATH = "src/main/resources";
    private static final String PROPERTIES_EXTENSION = ".properties";

    private String baseFileName;
    private String directoryPath;

    /**
     * Constructor using the default resource path "src/main/resources".
     * The ".properties" extension will be appended automatically if missing.
     *
     * @param baseFileName The base name of the properties file (e.g., "secrets").
     */
    public PropertiesManager(String baseFileName) {
        this(baseFileName, DEFAULT_RESOURCES_RELATIVE_PATH);
    }

    /**
     * Constructor allowing a custom directory path.
     * The ".properties" extension will be appended automatically if missing.
     *
     * @param baseFileName  The base name of the properties file (e.g., "secrets").
     * @param directoryPath The path to the directory containing the properties file.
     *                      Can be relative or absolute.
     */
    public PropertiesManager(String baseFileName, String directoryPath) {
        if (baseFileName == null || baseFileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Base filename cannot be null or empty.");
        }
        if (directoryPath == null || directoryPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Directory path cannot be null or empty.");
        }

        this.baseFileName = formatFileName(baseFileName);
        this.directoryPath = directoryPath;

        // Initialize and load properties (delegated to abstract class)
        initializeAndLoad();
    }

    @Override
    protected File getFile() {
        // Resolve the directory path to absolute first for robustness
        String absoluteDirectoryPath = Paths.get(this.directoryPath).toAbsolutePath().normalize().toString();
        // Construct the full file path
        return new File(absoluteDirectoryPath + File.separator + this.baseFileName);
    }

    // Helper method to ensure filename has .properties extension
    private String formatFileName(String name) {
        if (!name.toLowerCase().endsWith(PROPERTIES_EXTENSION)) {
            return name + PROPERTIES_EXTENSION;
        } else {
            return name;
        }
    }
}
