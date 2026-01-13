package sofware.ulpgc.project.application;

import sofware.ulpgc.project.io.CurrencyImport;
import sofware.ulpgc.project.io.ExchangeRateImport;
import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.model.ExchangeRate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Webservice {
    private static final String key = "d85f4cf3fd1c81ad64dd6199";
    private static final String url = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/".replace("YOUR-API-KEY",key);

    public static class CurrencyImporterAPI implements CurrencyImport {
        @Override
        public  List<Currency> importCurrencies() {
            try {
                return importCurrencies(new CurrencyDataFetcher().fetch(url+"codes"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private List<Currency> importCurrencies(InputStream urlInput) throws IOException {
            return new CurrencyJsonParser().parse(urlInput);
        }
    }
    public static class ExchangeRateImporterAPI implements ExchangeRateImport {

        @Override
        public ExchangeRate importExchangeRate(Currency from, Currency to) {
            return null;
        }
    }

}
