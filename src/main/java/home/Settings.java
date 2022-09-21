package home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public final class Settings {

    private enum Setting {
        MEASURE("has_measure", "true"),
        USER("user", "test_user"),
        PASSWORD("password", "test_password"),
        HOST("host", "localhost"),
        PORT("port", "5432"),
        DB_NAME("db_name", "elf");

        private final String name;
        private final String defaultValue;

        private String getName() {
            return name;
        }

        private String getDefaultValue() {
            return defaultValue;
        }

        Setting(String name, String defaultValue){
            this.name = name;
            this.defaultValue = defaultValue;
        }
    }

    private static final String SETTINGS_FILE_NAME = "settings.properties";
    private static final Properties SETTINGS = new Properties();

    public static boolean hasMeasure(){
        return Boolean.parseBoolean(get(Setting.MEASURE.getName()));
    }

    public static String getUserName(){
        return get(Setting.USER.getName());
    }

    public static String getUserPassword(){
        return get(Setting.PASSWORD.getName());
    }

    public static String getHost(){
        return get(Setting.HOST.getName());
    }

    public static String getPort(){
        return get(Setting.PORT.getName());
    }

    public static String getDbName(){
        return get(Setting.DB_NAME.getName());
    }

    private static String get(String key) {
        return SETTINGS.getProperty(key);
    }

    public static void readSettings(){
        readSettings(getSettingsPath());
    }

    // overloaded method for tests
    public static void readSettings(String path) {
        try (var inputStream = new FileInputStream(path)){
            SETTINGS.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Error while read settings from file: " + path, e);
        }
    }

    private static String getSettingsPath() {
        try {
            var file = new File(SETTINGS_FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
                fillWithDefaultSettings();
            }
            return file.getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException("Error while creating settings from file: " + SETTINGS_FILE_NAME, e);
        }
    }

    private static void fillWithDefaultSettings() throws IOException{
        try (var outputStream = new FileOutputStream(SETTINGS_FILE_NAME)){
            SETTINGS.setProperty(Setting.MEASURE.getName(), Setting.MEASURE.getDefaultValue());
            SETTINGS.setProperty(Setting.USER.getName(), Setting.USER.getDefaultValue());
            SETTINGS.setProperty(Setting.PASSWORD.getName(), Setting.PASSWORD.getDefaultValue());
            SETTINGS.setProperty(Setting.HOST.getName(), Setting.HOST.getDefaultValue());
            SETTINGS.setProperty(Setting.PORT.getName(), Setting.PORT.getDefaultValue());
            SETTINGS.setProperty(Setting.DB_NAME.getName(), Setting.DB_NAME.getDefaultValue());
            SETTINGS.store(outputStream, null);
        } catch (IOException e) {
            throw new IllegalStateException("Error while fill default settings: " + SETTINGS_FILE_NAME, e);
        }
    }

    private Settings() {}
}
