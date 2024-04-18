package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class FormatterJson {
    public static String formater(ArrayList<HashMap<String, Object>> diff) throws JsonProcessingException {
        var diffInJson = new StringBuilder();
        for (var field : diff) {
            ObjectMapper objectMapper = new ObjectMapper();
            diffInJson.append(objectMapper.writeValueAsString(field))
                    .append("\n");
        }
        return diffInJson.toString();
    }
}
