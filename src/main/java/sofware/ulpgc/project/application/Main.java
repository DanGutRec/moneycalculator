package sofware.ulpgc.project.application;

import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.model.ExchangeRate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Currency> currencies = new Webservice.CurrencyImporterAPI().importCurrencies();
        ExchangeRate rate= null;
        for (Currency currency : currencies) {
            if (currency!= currencies.get(0)) {
                rate = new Webservice.ExchangeRateImporterAPI().importExchangeRate(currencies.get(0), currency);
                System.out.println(rate.from().getCode() + " " + rate.to().getCode() + " " + rate.rate());
            }
        }

    }
}
