
package de.whs.mci.verwiebel_aufgabe_4;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author Lukas
 */
public class SecondaryWindow {
    
    private static final String FXML_FILE = "WeinEingabeformular.fxml";

    //private final String title;
    private final Stage stage = new Stage();
    
    private static final String TITLE = "Wein aufnehmen";
    private static final String ICON = "index.png";
    private static final String FXMLDatei = "WeinEingabeformular.fxml";
    private static final String TITLE_ALERT = "Programm beenden";
    private static final String HEADER_ALERT = "Sie sind dabei das Programm zu verlassen!";
    private static final String CONTEXT_ALERT = "Wollen Sie das Programm beenden?";
    private static final int HEIGHT = 795;
    private static final int WIDTH = 895;
    private Parent root;
    //private Stage stage;
    private Scene scene;
 
    
    public void starteWeinEingabe() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(FXMLDatei));
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            Image icon = new Image(ICON);
            
            stage.setTitle(TITLE);
            stage.setResizable(true);            
            stage.getIcons().add(icon);            
            stage.setScene(scene);
            stage.show();
    }
}
