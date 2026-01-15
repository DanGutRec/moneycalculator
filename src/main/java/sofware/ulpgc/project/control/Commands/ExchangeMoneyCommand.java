package sofware.ulpgc.project.control.Commands;

import sofware.ulpgc.project.control.Command;
import sofware.ulpgc.project.io.ExchangeRateImport;
import sofware.ulpgc.project.model.Money;
import sofware.ulpgc.project.ui.MoneyDialog;
import sofware.ulpgc.project.ui.MoneyDisplay;

public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialogFrom;
    private final MoneyDialog moneyDialogTo;
    private final ExchangeRateImport exchangeRateImporter;
    private final MoneyDisplay moneyDisplay;

    public ExchangeMoneyCommand(MoneyDialog moneyDialogFrom, MoneyDialog moneyDialogTo, ExchangeRateImport exchangeRateImporter, MoneyDisplay moneyDisplay) {
        this.moneyDialogFrom = moneyDialogFrom;
        this.moneyDialogTo = moneyDialogTo;
        this.exchangeRateImporter = exchangeRateImporter;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute() {
        moneyDisplay.showAmount(new Money(calculateAmount(getRate()),moneyDialogTo.getAmount().currency()));
    }

    private double getRate() {
        return exchangeRateImporter.importExchangeRate(moneyDialogFrom.getAmount().currency()
                , moneyDialogTo.getAmount().currency()).rate();
    }

    private double calculateAmount(Double rate) {
        return moneyDialogFrom.getAmount().amount()* rate;
    }
}
