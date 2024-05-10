package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String data, String type) throws Exception {
        var parsedData = new HashMap<String, Object>();
        if (type.equals("json")) {
            parsedData = jsonReader(data);
        } else if (type.equals("yml") || type.equals("yaml")) {
            parsedData = yamlReader(data);
        }
        return parsedData;
    }

    public static HashMap<String, Object> jsonReader(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(data, new TypeReference<>() { });
    }

    public static HashMap<String, Object> yamlReader(String data) throws JsonProcessingException {
        YAMLMapper yamlMapper = new YAMLMapper();
        return yamlMapper.readValue(data, new TypeReference<>() { });
    }
}
