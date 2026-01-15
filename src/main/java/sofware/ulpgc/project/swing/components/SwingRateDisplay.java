package sofware.ulpgc.project.swing.components;

import sofware.ulpgc.project.model.ExchangeRate;
import sofware.ulpgc.project.ui.RateDisplay;

import javax.swing.*;

public class SwingRateDisplay extends JPanel implements RateDisplay {
    private final JLabel rateValue;

    public SwingRateDisplay(ExchangeRate exchangeRate) {
        add(new JLabel("Rate Value:"));
        add(this.rateValue = new JLabel(toString(exchangeRate.rate())));
    }

    private String toString(double rate) {
        return Double.toString(rate);
    }

    @Override
    public void show(ExchangeRate rate) {
        this.rateValue.setText(toString(rate.rate()));
    }
}
