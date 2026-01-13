package sofware.ulpgc.project.application;

import sofware.ulpgc.project.model.Currency;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Currency> currencies = new Webservice.CurrencyImporterAPI().importCurrencies();
        for (Currency currency : currencies) {
            System.out.println(currency.getCountry());
        }
    }
}
