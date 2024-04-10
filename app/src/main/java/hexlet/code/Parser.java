package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> jsonIntoMap(String filepath) throws Exception {
        Path absolutePath = Paths.get(filepath).toAbsolutePath().normalize();
        String jsonData = Files.readString(absolutePath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, new TypeReference<>() { });
    }
    public static Map<String, Object> ymlIntoMap(String filepath) throws Exception {
        Path absolutePath = Paths.get(filepath).toAbsolutePath().normalize();
        String ymlData = Files.readString(absolutePath);
        YAMLMapper yamlMapper = new YAMLMapper();
        return yamlMapper.readValue(ymlData, new TypeReference<>() { });
    }
}
