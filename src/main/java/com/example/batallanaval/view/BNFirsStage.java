package com.example.batallanaval.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class BNFirsStage extends Stage {
    public BNFirsStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BNFirsStage.class.getResource("/com/example/batallanaval/First-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 280, 215);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/batallanaval/images/logoBN.png"))));
        setTitle("Batalla Naval");
        setResizable(false);
        setScene(scene);
        show();
    }
}