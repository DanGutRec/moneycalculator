package sofware.ulpgc.project.ui;

import sofware.ulpgc.project.model.Money;

public interface MoneyDisplay {
    void showAmount(Money money);
    void showCurrency(int currencyIndex);

}
