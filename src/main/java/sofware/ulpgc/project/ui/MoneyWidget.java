package sofware.ulpgc.project.ui;

import javax.swing.event.ChangeListener;

public interface MoneyWidget extends MoneyDisplay, MoneyDialog {
    void addListenerAmount(ChangeListener listener);
    void addListenerCurrency(ChangeListener listener);

}
