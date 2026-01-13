package sofware.ulpgc.project.application;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import sofware.ulpgc.project.io.JsonParser;
import java.io.IOException;
import java.io.InputStream;

public class CurrencyJsonParser  implements JsonParser {
    @Override
    public JsonElement parse(InputStream urlInput) {
        return getCodes(urlInput);
    }


    private JsonElement getCodes(InputStream inputStream)  {
        return getJsonObject(inputStream).get("supported_codes");
    }

    private JsonObject getJsonObject(InputStream inputStream) {
        return toJsonObject(readAllBytes(inputStream));
    }

    private JsonObject toJsonObject(String currencies) {
        return new Gson().fromJson(currencies, JsonObject.class);
    }

    private String readAllBytes(InputStream inputStream)  {
        try {
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
