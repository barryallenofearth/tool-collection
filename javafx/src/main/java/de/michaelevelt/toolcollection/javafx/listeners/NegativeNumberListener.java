package de.michaelevelt.toolcollection.javafx.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class NegativeNumberListener implements ChangeListener<String> {

    TextField numberTextField;

    public NegativeNumberListener(TextField numberTextField) {
        super();
        this.numberTextField = numberTextField;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue == null) {
            return;
        }
        newValue = newValue.trim();

        boolean isNegative = false;
        if (newValue.length() > 0) {
            if (newValue.substring(0, 1).equals("-")) {
                isNegative = true;
                newValue = newValue.substring(1);
            }
            newValue = newValue.replaceAll("[^\\d]", "");

            if (isNegative) {
                newValue = "-" + newValue;
            }

            numberTextField.setText(newValue);
        }
    }

}
