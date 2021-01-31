package ru.geekbraind.lesson5.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

public class ArturLogger implements HttpLoggingInterceptor.Logger {
    ObjectMapper mapper = new ObjectMapper();


    @Override
    public void log(String message) {
        String trimMessage = message.trim();

        Boolean itIsJson = trimMessage.startsWith("{") && trimMessage.endsWith("}");
        Boolean itIsArray = trimMessage.startsWith("[") && trimMessage.endsWith("]");

        if (itIsArray || itIsJson) {
            try {
                Object value = mapper.readValue(message, Object.class);
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
                Platform.get().log(Platform.INFO, json, null);

            } catch (JsonProcessingException e) {
                Platform.get().log(Platform.WARN, message, e);
            }
        } else {
            Platform.get().log(Platform.INFO, message, null);
        }
    }
}
