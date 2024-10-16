package dev.imarti.bank.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewSwitcher {
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static Object switchView(View view) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFxml()));
        Parent root = fxmlLoader.load();
        Platform.runLater(root::requestFocus);
        scene.setRoot(root);

        return fxmlLoader.getController();
    }
}
