package sofware.ulpgc.project.application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import sofware.ulpgc.project.io.CurrencyImport;
import sofware.ulpgc.project.io.ExchangeRateImport;
import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Webservice {
    private static final String key = "d85f4cf3fd1c81ad64dd6199";
    private static final String url = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/".replace("YOUR-API-KEY",key);

    public static class CurrencyImporterAPI implements CurrencyImport {
        @Override
        public  List<Currency> importCurrencies() {
            try {
                return importCurrencies(new CurrencyCodesDataFetcher().fetch(url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private List<Currency>  importCurrencies(InputStream urlInput) throws IOException {
            return readCurrencies(new CurrencyJsonParser().parse(urlInput).getAsJsonArray());
        }
        private List<Currency>  readCurrencies(JsonArray codes) {
            List<Currency> currencies = new ArrayList<>();
            codes.forEach(code -> currencies.add(
                    toCurrency(code.getAsJsonArray())));
            return currencies;
        }
        private Currency toCurrency(JsonArray code) {
            return Currency.create(getString(code.get(0)),getString(code.get(1)));
        }
        private static String getString(JsonElement code) {
            return code.getAsString();
        }

    }
    public static class ExchangeRateImporterAPI implements ExchangeRateImport {

        @Override
        public ExchangeRate importExchangeRate(Currency from, Currency to) {
            return new ExchangeRate(LocalDate.now(),from,to,calculateRate(from.getCode(),to.getCode()));
        }

        private double calculateRate(String from, String to) {
            return calculateRate(new ExchangeRateFetch().fetch(url, from, to));
        }

        private double calculateRate(InputStream fetch) {
            return new ExchangeRateJsonParse().parse(fetch).getAsDouble();
        }
    }

}
