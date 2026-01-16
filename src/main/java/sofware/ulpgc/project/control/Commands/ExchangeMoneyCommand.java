package sofware.ulpgc.project.control.Commands;

import sofware.ulpgc.project.control.Command;
import sofware.ulpgc.project.io.ExchangeRateImport;
import sofware.ulpgc.project.model.ExchangeRate;
import sofware.ulpgc.project.model.Money;
import sofware.ulpgc.project.ui.MoneyDialog;
import sofware.ulpgc.project.ui.MoneyWidget;
import sofware.ulpgc.project.ui.RateDisplay;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialogFrom;
    private final MoneyWidget moneyDialogTo;
    private final ExchangeRateImport exchangeRateImporter;
    private final RateDisplay rateDisplay;
;

    public ExchangeMoneyCommand(MoneyDialog moneyDialogFrom, MoneyWidget moneyDialogTo, ExchangeRateImport exchangeRateImporter, RateDisplay rateDisplay) {
        this.moneyDialogFrom = moneyDialogFrom;
        this.moneyDialogTo = moneyDialogTo;
        this.exchangeRateImporter = exchangeRateImporter;
        this.rateDisplay = rateDisplay;
    }

    @Override
    public void execute() {
        ExchangeRate rate= getRate();
        moneyDialogTo.showAmount(new Money(calculateAmount(rate.rate()),moneyDialogTo.getAmount().currency()));
        this.rateDisplay.show(rate);
    }

    private ExchangeRate getRate() {
        return exchangeRateImporter.importExchangeRate(moneyDialogFrom.getAmount().currency()
                , moneyDialogTo.getAmount().currency());
    }

    private double calculateAmount(Double rate) {
        return moneyDialogFrom.getAmount().amount()* rate;
    }
}
