package sofware.ulpgc.project.view.swing.components;

import sofware.ulpgc.project.model.Currency;
import sofware.ulpgc.project.model.Money;
import sofware.ulpgc.project.ui.MoneyWidget;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class SwingMoneyDisplay extends JPanel implements MoneyWidget{
    private final JTextField field;
    private final JComboBox<Currency> comboBox;
    private static int moneyDisplayFree=0;
    private SwingMoneyDisplay(List<Currency> currencies) {
        setLayout(new GridLayout(1,2));
        add(this.field = textFieldConstructor());
        add(this.comboBox = comboBoxConstructor(currencies));

    }
    public static SwingMoneyDisplay create(List<Currency> currencies) {
        return new SwingMoneyDisplay(currencies);
    }

    private JComboBox<Currency> comboBoxConstructor(List<Currency> currencies) {
        JComboBox<Currency> comboBox = new JComboBox<>();
        currencies.forEach(comboBox::addItem);
        return  comboBox;
    }

    private JTextField textFieldConstructor() {
        JTextField field = new JTextField();
        field.setEditable(true);
        field.setText("0.0");
        return field;
    }

    @Override
    public void addListenerAmount(ChangeListener listener) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e)  { if(moneyDisplayFree==0) listener.stateChanged(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(moneyDisplayFree==0) listener.stateChanged(null);
            }

            public void changedUpdate(DocumentEvent e) {if(moneyDisplayFree==0) listener.stateChanged(null);}
        });
    }
    @Override
    public void addListenerCurrency(ChangeListener listener) {
        comboBox.addActionListener(e -> {if (moneyDisplayFree==0) listener.stateChanged(null);});
    }

    @Override
    public Money getAmount() {
        return new Money(toDouble(field.getText().trim())
                , (Currency) comboBox.getSelectedItem());
    }

    @Override
    public int getCurrencyIndex() {
        return comboBox.getSelectedIndex();
    }

    private Double toDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;  // Valor por defecto si está vacío
        }
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }}
    @Override
    public void showAmount(Money money) {
        moneyDisplayFree++;
        SwingUtilities.invokeLater(() -> {
            field.setText(toString(money.amount()));
            moneyDisplayFree--;
        });

    }

    @Override
    public void showCurrency(int currencyIndex) {
        moneyDisplayFree++;
        SwingUtilities.invokeLater(() -> {
            comboBox.setSelectedIndex(currencyIndex);
            moneyDisplayFree--;
        });
    }

    private String toString(double amount) {return Double.toString(amount);}

    public static void lockUpdates() {
        moneyDisplayFree++;
    }

    public static void unlockUpdates() {
        moneyDisplayFree--;
        if (moneyDisplayFree < 0) moneyDisplayFree = 0;
    }
}
