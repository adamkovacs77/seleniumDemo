package utils;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Utils {

    public static boolean isAttributePresent(WebElement element, String attribute) {
        boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public static void createJsonFile(Map<String, String> fields, String fileName) {
        JSONObject jsonObject = new JSONObject();
        for(Map.Entry<String, String> entry : fields.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }

        try {
            FileWriter file = new FileWriter("src\\outputs\\" + fileName + ".json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
