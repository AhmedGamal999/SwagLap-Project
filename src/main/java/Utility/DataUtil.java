package Utility;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtil {
    private static final String test_data_path = "src/test/resources/test-data/";

    public static String GetStingjasondata(String Filename, String Key) throws FileNotFoundException {
        try {

            FileReader reader = new FileReader(test_data_path + Filename + ".json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(Key).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String GetPropData(String Filename, String Key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(test_data_path + Filename + ".properties"));
        return properties.getProperty(Key);
    }


}
