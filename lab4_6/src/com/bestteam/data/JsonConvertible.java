package com.bestteam.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Andrew on 12/21/2016.
 */
public abstract class JsonConvertible<T> {
    private ObjectMapper mapper = new ObjectMapper();
    private Class<T> tClass;

    public JsonConvertible(Class<T> tClass) {
        this.tClass = tClass;
    }

    public void save(String filePath) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), this);
    }

    public static <T> T loadFromFile(String filePath, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T obj = mapper.readValue(new File(filePath), tClass);
        return obj;
    }
}
