package de.michaelevelt.toolcollection.javafx.application;

public interface FXMLControllerFactory {

	BaseController getNewBaseController();

	String getFXMLFileURL();

	String getWindowTitle();

}
