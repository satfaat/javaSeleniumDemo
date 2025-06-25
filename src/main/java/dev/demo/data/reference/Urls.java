package dev.demo.data.reference;

public enum Urls {
    SAUCEDEMO("https://www.saucedemo.com/"),
    LUFTHANSA("https://www.lufthansa.com/");

    private final String path;

    Urls(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
