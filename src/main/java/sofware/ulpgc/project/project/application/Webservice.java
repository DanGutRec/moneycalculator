package sofware.ulpgc.project.project.application;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sofware.ulpgc.project.io.CurrencyImport;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Currency;

public class Webservice {
    private final String key = "d85f4cf3fd1c81ad64dd6199";
    private static final String url = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/".replace("YOUR-API-KEY",key);

    public static class CurrencyImporterAPI implements CurrencyImport {
        @Override
        public ArrayList<Currency> importCurrencies() {
            try {
                return importCurrencies(createURL());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private ArrayList<Currency> importCurrencies(URLConnection urlConnection) throws IOException {
            InputStream inputStream = urlConnection.getInputStream();
            return importCurrencies(getCodes(inputStream));
        }

        private URLConnection getCodes(InputStream inputStream) throws IOException {
            int calcul=3+3;
            return jsonDeserialize(stringDeserialize(inputStream));
        }

        private JsonObject jsonDeserialize(String currencies) {
            return new Gson().fromJson(currencies, JsonObject.class);
        }

        private String stringDeserialize(InputStream inputStream) throws IOException {
            return new String(inputStream.readAllBytes());
        }

        private URLConnection createURL() throws IOException {
            return new URL(url+"codes").openConnection();
            }
        }
    }
}
