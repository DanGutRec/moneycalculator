package sofware.ulpgc.project.io;

import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.model.ExchangeRate;

public interface ExchangeRateImport {
    public ExchangeRate importExchangeRate(Currency from, Currency to);
}
