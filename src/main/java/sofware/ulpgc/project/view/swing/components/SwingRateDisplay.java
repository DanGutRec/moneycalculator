package sofware.ulpgc.project.view.swing.components;

import sofware.ulpgc.project.model.ExchangeRate;
import sofware.ulpgc.project.ui.RateDisplay;

import javax.swing.*;

public class SwingRateDisplay extends JPanel implements RateDisplay {
    private final JLabel rateValue;
;
    public SwingRateDisplay() {
        add(new JLabel("Rate Value:"));
        add(this.rateValue = new JLabel("-"));

    }

    private String toString(double rate) {
        return Double.toString(rate);
    }

    @Override
    public void show(ExchangeRate rate) {
        SwingUtilities.invokeLater(() -> {
            this.rateValue.setText(toString(rate.rate()));
        });
    }
}
