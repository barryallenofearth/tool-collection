package de.michaelevelt.toolcollection.javafx.media;

import de.michaelevelt.toolcollection.javafx.alert.AlertHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileBrowserHelper {

    public static void browse(String dialogueTitle, TextField pathField, FileChooser.ExtensionFilter filter) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(filter);

        fileChooser.setTitle(dialogueTitle);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            pathField.setText(selectedFile.getPath());
        }
    }

    public static void imageLaden(TextField pathField, ImageView imageView, FileChooser.ExtensionFilter filter) {

        if (pathField != null && StringUtils.isNotBlank(pathField.getText())) {
            for (String extension : filter.getExtensions()) {
                if (pathField.getText().endsWith(extension.substring(1))) {
                    File selectedFile = new File(pathField.getText());
                    try {
                        imageView.setImage(new Image(new FileInputStream(new File(selectedFile.getPath()))));
                        return;
                    } catch (FileNotFoundException e) {
                        AlertHandler alert = new AlertHandler("Datei nicht gefunden", "Datei nicht gefunden", Alert.AlertType.ERROR);
                        alert.showAndWait();
                    }
                }
            }
            AlertHandler alert = new AlertHandler("Ungültige Dateiendung", "Die Datei hat nicht das erlaubte Dateiformat", Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }
}
