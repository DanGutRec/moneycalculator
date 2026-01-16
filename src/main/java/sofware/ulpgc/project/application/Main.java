package sofware.ulpgc.project.application;

import sofware.ulpgc.project.control.CommandFactory;
import sofware.ulpgc.project.control.Commands.ExchangeMoneyCommand;
import sofware.ulpgc.project.control.Commands.SwapCurrenciesCommand;
import sofware.ulpgc.project.io.ExchangeRateImport;
import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.model.ExchangeRate;
import sofware.ulpgc.project.view.swing.MainFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static MainFrame mainFrame;
    public static void main(String[] args) {

        createMainFrame().addCurrencies(getCurrencies()).addExchangeRateImporter(getExchangeRateImporter()).addCommands(createCommands()).initialize().setVisible(true);

    }

    private static ExchangeRateImport getExchangeRateImporter() {
        return new Webservice.ExchangeRateImporterAPI();
    }

    private static List<Currency> getCurrencies() {
        return new Webservice.CurrencyImporterAPI().importCurrencies();
    }

    private static MainFrame createMainFrame() {
        return (mainFrame = new MainFrame(new CommandFactory()));
    }

    private static Map<String, CommandFactory.Builder> createCommands() {
        Map<String, CommandFactory.Builder> commands = new HashMap<>();
        commands.put("swap", ()-> new SwapCurrenciesCommand(mainFrame.getMoneyWidgetFrom(),mainFrame.getMoneyWidgetTo(),mainFrame.getExchangeRate(),mainFrame.getRateDisplay()));
        commands.put("calculate",()-> new ExchangeMoneyCommand(mainFrame.getMoneyWidgetFrom(), mainFrame.getMoneyWidgetTo(),mainFrame.getExchangeRate(),mainFrame.getRateDisplay()));
        commands.put("calculateInverse",()->new ExchangeMoneyCommand(mainFrame.getMoneyWidgetTo(),mainFrame.getMoneyWidgetFrom(),mainFrame.getExchangeRate(),mainFrame.getRateDisplay()));
        return commands;
    }
}
