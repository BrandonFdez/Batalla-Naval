package com.example.batallanaval;
import com.example.batallanaval.view.BNFirsStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Abre la ventana de inicio
        new BNFirsStage();
    }
}
