package de.whs.mci.verwiebel_aufgabe2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * JavaFX App
 * @author Lukas Verwiebe 202021182
 */
public class App extends Application {

    private static final String TITLE = "Preisumrechner";
    private static final String ICON = "index.png";
    private static final String FXMLDatei = "umrechner.fxml";
    private static final int HEIGHT = 350;
    private static final int WIDTH = 500;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(FXMLDatei));
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            Image icon = new Image(ICON);
            
            stage.setTitle(TITLE);
            stage.setResizable(true);            
            stage.getIcons().add(icon);            
            stage.setScene(scene);
            stage.show();
            
	} catch(Exception e) {
            e.printStackTrace();
	}
    }
    
    public static void main(String[] args) {
        launch();
    }

}