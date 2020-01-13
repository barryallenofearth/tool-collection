package de.michaelevelt.toolcollection.javafx.alert;

import javafx.scene.control.Alert.AlertType;
import org.apache.logging.log4j.Logger;

public class ErrorMessageHandling {
    public static void showErrorMessage(String message, Exception e, Logger LOGGER) {
        LOGGER.error(message, e);
        AlertHandler alert = new AlertHandler("Ein Fehler ist aufgetreten", message, AlertType.ERROR);
        alert.showAndWait();
    }
}
