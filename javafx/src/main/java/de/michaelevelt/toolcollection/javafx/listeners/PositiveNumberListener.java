package de.michaelevelt.toolcollection.javafx.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class PositiveNumberListener implements ChangeListener<String> {

	TextField numberTextField;

	public PositiveNumberListener(TextField numberTextField) {
		super();
		this.numberTextField = numberTextField;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (newValue == null) {
			return;
		}
		numberTextField.setText(newValue.replaceAll("[^\\d]", ""));
	}

}
