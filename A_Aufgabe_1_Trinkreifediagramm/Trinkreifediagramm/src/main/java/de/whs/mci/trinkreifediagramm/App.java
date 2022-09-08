
package de.whs.mci.trinkreifediagramm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * JavaFX App
 * Es soll in einem Fenster ein Balkendiagramm für die Anzeige der Trinkreife
 * eines Weines erzeugt werden. Vor der Anzeige des Diagramms soll ein weiteres
 * Fenster angezeigt werden in dem der Jahrgang und die Lagerdauer eingegeben
 * werden kann.
 * 
 * @author Lukas Verwiebe 202021182
 */
public class App extends Application {
    
    private static final String TITLE = "Trinkreife";
    private static final String ICON = "weinicon.jpg";
    private static final String FXMLDatei = "eingabe.fxml";
    private static final int HEIGHT = 150;
    private static final int WIDTH = 650;
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
            stage.setResizable(false);            
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