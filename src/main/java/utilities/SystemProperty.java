package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SystemProperty {
    private static final String USERDIR = System.getProperty("user.dir");
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String RESOURCES_FOLDER_PATH = USERDIR + FILE_SEPARATOR + "src" + FILE_SEPARATOR + "main" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR;

    public static String getValue(String valueToGet) {
        String javaFilePath = RESOURCES_FOLDER_PATH + FILE_SEPARATOR + "user.properties";
        FileReader reader;
        String value = null;
        try {
            reader = new FileReader(javaFilePath);
            Properties p = new Properties();
            p.load(reader);
            value = p.getProperty(valueToGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
