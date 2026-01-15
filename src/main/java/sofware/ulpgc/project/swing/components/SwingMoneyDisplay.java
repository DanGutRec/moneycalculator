package sofware.ulpgc.project.swing.components;

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

    public SwingMoneyDisplay(List<Currency> currencies) {
        setLayout(new GridLayout(1,2));
        add(this.field = textFieldConstructor());
        add(this.comboBox = comboBoxConstructor(currencies));
    }

    private JComboBox<Currency> comboBoxConstructor(List<Currency> currencies) {
        JComboBox<Currency> comboBox = new JComboBox<>();
        currencies.forEach(comboBox::addItem);
        return  comboBox;
    }

    private JTextField textFieldConstructor() {
        JTextField field = new JTextField();
        field.setEditable(false);
        field.setText("0.0");
        return field;
    }

    @Override
    public void addListenerAmount(ChangeListener listener) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.stateChanged(null);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.stateChanged(null);
            }

            public void changedUpdate(DocumentEvent e) {listener.stateChanged(null);}
        });
    }
    @Override
    public void addListenerCurrency(ChangeListener listener) {
        comboBox.addActionListener(e -> listener.stateChanged(null));
    }

    @Override
    public Money getAmount() {
        return new Money(toDouble(field.getText())
                , (Currency) comboBox.getSelectedItem());
    }

    @Override
    public int getCurrencyIndex() {
        return comboBox.getSelectedIndex();
    }

    private Double toDouble(String value) {return Double.valueOf(value);}
    @Override
    public void showAmount(Money money) {field.setText(toString(money.amount()));}

    @Override
    public void showCurrency(int currencyIndex) {
        comboBox.setSelectedItem(currencyIndex);
    }

    private String toString(double amount) {return Double.toString(amount);}
}
