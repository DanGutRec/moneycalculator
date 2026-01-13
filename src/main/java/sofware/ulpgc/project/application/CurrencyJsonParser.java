package sofware.ulpgc.project.application;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import sofware.ulpgc.project.io.JsonParser;
import sofware.ulpgc.project.model.Currency;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;

public class CurrencyJsonParser  implements JsonParser {
    @Override
    public List<Currency> parse(InputStream urlInput) {
        return readCurrencies(getCodes(urlInput));
    }
    private ArrayList<Currency> readCurrencies(JsonArray codes) {
        ArrayList<Currency> currencies = new ArrayList<>();
        codes.forEach(code -> currencies.add(toCurrency(code.getAsJsonArray())));
        return currencies;
    }

    private JsonArray getCodes(InputStream inputStream)  {
        return getJsonObject(inputStream).get("supported_codes").getAsJsonArray();
    }

    private JsonObject getJsonObject(InputStream inputStream) {
        return toJsonObject(readAllBytes(inputStream));
    }

    private sofware.ulpgc.project.model.Currency toCurrency(JsonArray code) {
        return Currency.create(getString(code.get(0)),getString(code.get(1)));
    }

    private static String getString(JsonElement code) {
        return code.getAsString();
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
