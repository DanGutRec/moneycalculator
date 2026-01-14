package sofware.ulpgc.project.io;

import sofware.ulpgc.project.model.Currency;

import java.util.List;
import java.util.Map;

public interface CurrencyImport {
    public Map<String,Currency> importCurrencies();
}
