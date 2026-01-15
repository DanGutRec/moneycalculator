package sofware.ulpgc.project.application;

import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.model.ExchangeRate;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Currency> currencies = new Webservice.CurrencyImporterAPI().importCurrencies();
        ExchangeRate rate= null;
        currencies.forEach(System.out::println);

    }
}
