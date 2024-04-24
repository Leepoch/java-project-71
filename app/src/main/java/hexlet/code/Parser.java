package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filepath, String type) throws Exception {
        Path absolutePath = Paths.get(filepath).toAbsolutePath().normalize();
        String data = Files.readString(absolutePath);
        ObjectMapper objectMapper = new ObjectMapper();
        YAMLMapper yamlMapper = new YAMLMapper();
        var parsedData = new HashMap<String, Object>();
        if (type.equals("json")) {
            parsedData = objectMapper.readValue(data, new TypeReference<>() { });
        } else if (type.equals("yml") || type.equals("yaml")) {
            parsedData = yamlMapper.readValue(data, new TypeReference<>() { });
        }
        return parsedData;
    }

}
