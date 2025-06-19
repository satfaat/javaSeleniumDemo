package dev.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProviderUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final CsvMapper csvMapper = new CsvMapper();

    public static <T> T readJson(String filePath, Class<T> valueType) throws IOException {
        return objectMapper.readValue(new File(filePath), valueType);
    }

    public static List<Map<String, String>> readCsv(String filePath) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        return csvMapper.readerFor(Map.class)
                .with(schema)
                .<Map<String, String>>readValues(new File(filePath))
                .readAll();
    }
}
