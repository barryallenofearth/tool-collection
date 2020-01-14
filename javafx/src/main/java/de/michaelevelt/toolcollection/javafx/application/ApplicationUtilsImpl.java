package de.michaelevelt.toolcollection.javafx.application;

import de.michaelevelt.toolcollection.javafx.media.ImageUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class ApplicationUtilsImpl implements ApplicationUtils {

	public static final String WINDOW_TITLE_SEPARATOR = " | ";

	private final String baseTitle;

	private final FXMLControllerFactory fxmlControllerFactory;

	public ApplicationUtilsImpl(String baseTitle, FXMLControllerFactory fxmlControllerFactory) {
		this.baseTitle = baseTitle;
		if (StringUtils.isBlank(baseTitle)) {
			throw new RuntimeException("Bitte geben Sie einen gültigen Basis Fenstertitel ein");
		}

		this.fxmlControllerFactory = fxmlControllerFactory;
	}

	@Override
	public BaseController initStage(int xPixel, int yPixel, byte[] logoImage) throws IOException {
		Stage stage = new Stage();
		Image image = ImageUtils.readImage(logoImage);
		if (image != null) {
			stage.getIcons().add(image);
		}

		String title = fxmlControllerFactory.getWindowTitle();
		if (StringUtils.isNotBlank(title)) {
			title = baseTitle + WINDOW_TITLE_SEPARATOR + title.trim();
		} else {
			title = baseTitle;
		}
		stage.setTitle(title);

		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlControllerFactory.getFXMLFileURL()));
		loader.setControllerFactory(new ControllerFactoryCallback(fxmlControllerFactory));
		Parent root = loader.load();
		Scene scene = new Scene(root, xPixel, yPixel);
		stage.setScene(scene);
		stage.show();

		return loader.getController();

	}

	private static class ControllerFactoryCallback implements Callback<Class<?>, Object> {

		public final FXMLControllerFactory fxmlControllerFactory;

		private ControllerFactoryCallback(FXMLControllerFactory fxmlControllerFactory) {
			this.fxmlControllerFactory = fxmlControllerFactory;
		}

		@Override
		public Object call(Class<?> clazz) {
			BaseController controller = fxmlControllerFactory.getNewBaseController();
			if (clazz.isInstance(controller)) {
				return controller;
			} else {
				throw new ClassCastException(String.format("The expected controller is of type %s, but was expected to be of type %s", controller.getClass().toString(), clazz.toString()));
			}
		}
	}

}
