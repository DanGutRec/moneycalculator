package sofware.ulpgc.project.control.Commands;

import sofware.ulpgc.project.control.Command;
import sofware.ulpgc.project.io.ExchangeRateImport;

import sofware.ulpgc.project.model.ExchangeRate;
import sofware.ulpgc.project.model.Money;
import sofware.ulpgc.project.ui.MoneyWidget;
import sofware.ulpgc.project.ui.RateDisplay;

public class SwapCurrenciesCommand implements Command {
    private final MoneyWidget moneyWidgetFrom;
    private final MoneyWidget moneyWidgetTo;
    private final ExchangeRateImport exchangeRateImporter;
    private final RateDisplay rateDisplay;

    public SwapCurrenciesCommand(MoneyWidget moneyWidgetFrom, MoneyWidget moneyWidgetTo, ExchangeRateImport exchangeRateImporter, RateDisplay rateDisplay) {
        this.moneyWidgetFrom = moneyWidgetFrom;
        this.moneyWidgetTo = moneyWidgetTo;
        this.exchangeRateImporter = exchangeRateImporter;
        this.rateDisplay = rateDisplay;
    }

    @Override
    public void execute() {
        int selectedFrom= moneyWidgetFrom.getCurrencyIndex();
        moneyWidgetFrom.showCurrency(moneyWidgetTo.getCurrencyIndex());
        moneyWidgetTo.showCurrency(selectedFrom);

        ExchangeRate rate = getRate();
        this.rateDisplay.show(rate);
        this.moneyWidgetTo.showAmount(calculateRate(rate));
    }

    private Money calculateRate(ExchangeRate rate) {
        return new Money(moneyWidgetFrom.getAmount().amount()*rate.rate(),rate.from());
    }

    private ExchangeRate getRate() {
        return exchangeRateImporter.importExchangeRate(moneyWidgetFrom.getAmount().currency(), moneyWidgetTo.getAmount().currency());
    }
}
