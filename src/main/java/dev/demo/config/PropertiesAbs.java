package dev.demo.config;

import java.io.*;
import java.util.Properties;

/**
 * Abstract base class for managing properties from a file.
 * Subclasses must implement the getFile() method to specify the properties file location.
 */
public abstract class PropertiesAbs {

    protected Properties properties;
    protected File propertiesFile; // This will be set by the concrete subclasses

    public PropertiesAbs() {
        this.properties = new Properties();
    }

    /**
     * Abstract method to be implemented by subclasses to provide the specific
     * File object representing the properties file.
     * @return The File object for the properties file.
     */
    protected abstract File getFile();

    /**
     * Initializes the properties manager by loading the properties from the file.
     * This method should be called by the concrete subclass's constructor
     * after setting the 'propertiesFile' field.
     */
    protected void initializeAndLoad() {
        this.propertiesFile = getFile(); // Get the file from the concrete implementation

        if (this.propertiesFile == null) {
            throw new IllegalStateException("Properties file was not properly set by subclass.");
        }

        // Optional: Provide a warning if the file does not exist before trying to load.
        if (!this.propertiesFile.exists()) {
            System.err.println("Warning: Properties file might not exist at: " + this.propertiesFile.getAbsolutePath());
        }

        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream(propertiesFile)) {
            properties.load(input);
            System.out.println("Successfully loaded properties from: " + propertiesFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error loading properties file from " + propertiesFile.getAbsolutePath() + ": " + e.getMessage());
            throw new RuntimeException("Failed to load properties file: " + propertiesFile.getAbsolutePath(), e);
        }
    }

    /**
     * Reads the value associated with the given key.
     *
     * @param key The key to look up.
     * @return The value associated with the key.
     * @throws RuntimeException if the key is not found in the properties.
     */
    public String readValue(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println("Key '" + key + "' is missing in " + propertiesFile.getAbsolutePath() + ".");
            throw new RuntimeException("Key '" + key + "' is missing in " + propertiesFile.getAbsolutePath());
        }
        System.out.println("Read value for key '" + key + "': " + value);
        return value;
    }

    /**
     * Sets a new value for the given key, adds a comment, and saves the properties to the file.
     *
     * @param key     The key to set.
     * @param value   The value to associate with the key.
     * @param comment An optional comment to add to the properties file when saving.
     */
    public void setValue(String key, String value, String comment) {
        properties.setProperty(key, value);
        System.out.println("Setting value for key '" + key + "': " + value);
        saveToFile(comment);
    }

    /**
     * Sets a new value for the given key and saves the properties to the file.
     *
     * @param key   The key to set.
     * @param value The value to associate with the key.
     */
    public void setValue(String key, String value) {
        setValue(key, value, null);
    }

    /**
     * Deletes a key-value pair from the properties, adds a comment, and saves the changes to the file.
     *
     * @param key     The key to delete.
     * @param comment An optional comment to add to the properties file when saving.
     */
    public void deleteValue(String key, String comment) {
        if (properties.containsKey(key)) {
            properties.remove(key);
            System.out.println("Deleting key '" + key + "'");
            saveToFile(comment);
        } else {
            System.out.println("Attempted to delete non-existent key '" + key + "'. No action taken.");
        }
    }

    /**
     * Deletes a key-value pair from the properties and saves the changes to the file.
     *
     * @param key The key to delete.
     */
    public void deleteValue(String key) {
        deleteValue(key, null);
    }

    /**
     * Saves the current state of the properties to the file.
     *
     * @param comment An optional comment to add to the properties file.
     * @throws RuntimeException if saving the properties file fails.
     */
    private void saveToFile(String comment) {
        try (OutputStream output = new FileOutputStream(propertiesFile)) {
            properties.store(output, comment);
            System.out.println("Successfully saved properties to: " + propertiesFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving properties file to " + propertiesFile.getAbsolutePath() + ": " + e.getMessage());
            throw new RuntimeException("Failed to save properties file: " + propertiesFile.getAbsolutePath(), e);
        }
    }
}
