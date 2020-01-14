package de.michaelevelt.toolcollection.javafx.alert;

import de.michaelevelt.toolcollection.javafx.media.ImageUtils;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class AlertHandler {

	private final Alert alert;

	private static final Logger LOGGER = LogManager.getLogger(AlertHandler.class);

	public AlertHandler(String title, String headerText, Alert.AlertType alertType, byte[] logoImage) {

		this.alert = new Alert(alertType);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		if (logoImage != null) {
			try {
				stage.getIcons().add(ImageUtils.readImage(logoImage));
			} catch (IOException e) {
				LOGGER.error("An error occurred while trying to set the icon for the alert dialogue.");
			}
		}
		alert.setTitle(title);
		alert.setHeaderText(headerText);

	}

	public AlertHandler(String title, String headerText, Alert.AlertType alertType) {

		this.alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);

	}

	public Alert getAlert() {
		return alert;
	}

	public void showAndWait() {
		alert.showAndWait();
	}
}

