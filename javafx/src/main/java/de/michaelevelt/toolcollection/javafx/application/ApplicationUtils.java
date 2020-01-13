package de.michaelevelt.toolcollection.javafx.application;

import javafx.stage.Stage;

public interface ApplicationUtils {

	BaseController initStage(Stage stage, String title, int xPixel, int yPixel,BaseController... controllers);
	
}
