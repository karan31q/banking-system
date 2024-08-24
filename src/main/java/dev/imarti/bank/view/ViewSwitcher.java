package dev.imarti.bank.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewSwitcher {
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchView(View view) throws IOException {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFxml()));
        scene.setRoot(fxmlLoader.load());
    }
}
