package sofware.ulpgc.project.view.swing;

import sofware.ulpgc.project.control.CommandFactory;
import sofware.ulpgc.project.io.ExchangeRateImport;
import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.view.swing.components.SwingMoneyDisplay;
import sofware.ulpgc.project.ui.MoneyWidget;
import sofware.ulpgc.project.ui.RateDisplay;
import sofware.ulpgc.project.view.swing.components.SwingRateDisplay;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends JFrame {
    private SwingMoneyDisplay moneyWidgetFrom;
    private  SwingMoneyDisplay moneyWidgetTo;
    private  ExchangeRateImport exchangeRateImport;
    private  SwingRateDisplay rateDisplay;
    private  List<Currency> currencies;
    private final CommandFactory commandFactory;
    public MainFrame(CommandFactory commandFactory)  {
        this.commandFactory = commandFactory;
        createSetUp();

    }
    public MainFrame initialize() {
        add(createHead(), BorderLayout.NORTH);
        add(createMoneyWidges(), BorderLayout.CENTER);
        add(createEast(),BorderLayout.EAST);
        return this;

    }
    private JPanel createEast() {
        JPanel eastPanel = new JPanel(new BorderLayout(0,10));
        JButton button = new JButton("⇄ Swap");
        button.setPreferredSize(new Dimension(30, 30));
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.addActionListener(e -> {commandFactory.getCommand("swap").build().execute();});
        eastPanel.add(button, BorderLayout.CENTER);
        this.rateDisplay=new SwingRateDisplay();
        eastPanel.add(rateDisplay,BorderLayout.SOUTH);
        return eastPanel;
    }

    private void createSetUp() {
        this.setTitle("Money calculator");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout(10,10));
    }

    private JPanel createMoneyWidges() {
        JPanel moneyWidgesPanel = new JPanel(new GridLayout(2,1));
        this.moneyWidgetFrom= SwingMoneyDisplay.create(currencies);
        moneyWidgetFrom.addListenerCurrency(e->commandFactory.getCommand("calculate").build().execute());
        moneyWidgetFrom.addListenerAmount(e->commandFactory.getCommand("calculate").build().execute());
        moneyWidgesPanel.add(moneyWidgetFrom);
        this.moneyWidgetTo= SwingMoneyDisplay.create(currencies);
        moneyWidgetTo.addListenerCurrency(e->commandFactory.getCommand("calculate").build().execute());
        moneyWidgetTo.addListenerAmount(e->commandFactory.getCommand("calculateInverse").build().execute());
        moneyWidgesPanel.add(moneyWidgetTo);
        return moneyWidgesPanel;
    }

    private JLabel createHead() {
        JLabel title = new JLabel("Money Exchange",JLabel.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,16));
        return title;
    }

    public MainFrame addCommands(Map<String, CommandFactory.Builder> commands) {
        commands.forEach(commandFactory::addCommand);
        return this;
    }

    public MoneyWidget getMoneyWidgetFrom() {
        return moneyWidgetFrom;
    }

    public MoneyWidget getMoneyWidgetTo() {
        return  moneyWidgetTo;
    }

    public ExchangeRateImport getExchangeRate() {
        return exchangeRateImport;
    }

    public RateDisplay getRateDisplay() {
        return rateDisplay;
    }

    public MainFrame addCurrencies(List<Currency> currencies) {
        this.currencies=currencies;
        return this;
    }

    public MainFrame addExchangeRateImporter(ExchangeRateImport exchangeRateImporter) {
        this.exchangeRateImport=exchangeRateImporter;
        return this;
    }
}
