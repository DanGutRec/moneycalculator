package sofware.ulpgc.project.application;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import sofware.ulpgc.project.io.JsonParser;

import java.io.IOException;
import java.io.InputStream;

public class ExchangeRateJsonParse implements JsonParser {
    @Override
    public JsonElement parse(InputStream path) {
        return getRate(path);
    }

    private JsonElement getRate(InputStream path) {
        return getJsonObject(path).get("conversion_rate");
    }

    private JsonObject getJsonObject(InputStream path) {
        return toJsonObject(readAllBytes(path));
    }

    private JsonObject toJsonObject(String bytes) {
        return new Gson().fromJson(bytes, JsonObject.class);
    }

    private String readAllBytes(InputStream path) {
        try {
            return new String(path.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
