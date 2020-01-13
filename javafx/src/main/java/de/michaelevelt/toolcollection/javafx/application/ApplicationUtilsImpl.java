//package de.michaelevelt.toolcollection.javafx.application;
//
//import de.michaelevelt.bloodbowl.gui.factories.FXMLControllerFactory;
//import de.michaelevelt.bloodbowl.gui.frontpanel.FrontPanelApplication;
//import de.michaelevelt.bloodbowl.tabelle.database.entities.ProgramSettings;
//import de.michaelevelt.bloodbowl.tabelle.database.handlers.ProgramSettingsHandler;
//import de.michaelevelt.bloodbowl.tabelle.utils.ImageUtils;
//import de.michaelevelt.bloodbowl.tabelle.utils.StringUtils;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//
//import java.io.IOException;
//
//public class ApplicationUtilsImpl implements ApplicationUtils {
//
//    public static final String WINDOW_TITLE_SEPARATOR = " | ";
//
//
//
//    @Override
//    public BaseController initStage(Stage stage, String title, int xPixel, int yPixel) {
//
//        ProgramSettings programSettings = programSettingsHandler.fetchProgramSettings();
//        Image image = ImageUtils.readImage(programSettings.getLogoImage());
//        if (image != null) {
//            stage.getIcons().add(image);
//        }
//        if (!fxmlControllerFactory.getUrl().equals(FrontPanelApplication.FRONTPANEL_FXML) && StringUtils.isNotBlank(programSettings.getWindowTitle())) {
//            title = programSettings.getWindowTitle().trim() + WINDOW_TITLE_SEPARATOR + title;
//        }
//        stage.setTitle(title);
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlControllerFactory.getUrl()));
//            loader.setControllerFactory(new ControllerFactoryCallback(fxmlControllerFactory));
//            Parent root = loader.load();
//            Scene scene = new Scene(root, xPixel, yPixel);
//            stage.setScene(scene);
//            stage.show();
//
//            return loader.getController();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//
//        return null;
//
//    }
//
//    private static class ControllerFactoryCallback implements Callback<Class<?>, Object> {
//
//        public final FXMLControllerFactory fxmlControllerFactory;
//
//        private ControllerFactoryCallback(FXMLControllerFactory fxmlControllerFactory) {
//            this.fxmlControllerFactory = fxmlControllerFactory;
//        }
//
//        @Override
//        public Object call(Class<?> clazz) {
//            BaseController controller = fxmlControllerFactory.getNewBaseController();
//            if(clazz.isInstance(controller)){
//                return controller;
//            }else{
//                throw new ClassCastException(String.format("The expected controller is of type %s, but was expected to be of type %s",controller.getClass().toString(),clazz.toString()));
//            }
//        }
//    }
//
//}
