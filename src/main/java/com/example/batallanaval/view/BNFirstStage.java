package com.example.batallanaval.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class BNFirstStage extends Stage {
    public BNFirstStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(BNFirstStage.class.getResource("/com/example/batallanaval/First-view.fxml"));
        if (loader.getLocation() == null) {
            throw new IOException("No se pudo encontrar el archivo FXML: /com/example/batallanaval/First-view.fxml");
        }
        Parent root = loader.load();
        Scene scene = new Scene(root);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/batallanaval/images/logoBN.png"))));
        setTitle("Batalla Naval");
        setResizable(false);
        setScene(scene);
        show();
    }
}