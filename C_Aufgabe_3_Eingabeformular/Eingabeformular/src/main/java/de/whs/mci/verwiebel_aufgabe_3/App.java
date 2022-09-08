package de.whs.mci.verwiebel_aufgabe_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

/**
 * JavaFX App
 * @author Lukas Verwiebe 202021182
 */
public class App extends Application {

    private static final String TITLE = "Wein aufnehmen";
    private static final String ICON = "index.png";
    private static final String FXMLDatei = "WeinEingabeformular.fxml";
    private static final String TITLE_ALERT = "Programm beenden";
    private static final String HEADER_ALERT = "Sie sind dabei das Programm zu verlassen!";
    private static final String CONTEXT_ALERT = "Wollen Sie das Programm beenden?";
    private static final int HEIGHT = 795;
    private static final int WIDTH = 895;
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
            
            stage.setOnCloseRequest(event -> {
                event.consume();
                beenden(stage);
            });
            
	} catch(Exception e) {
            e.printStackTrace();
	}
    }
    /**
     * Meldung bei Betätigung von X:
     */
    public void beenden(Stage stage) {        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(TITLE_ALERT);
        alert.setHeaderText(HEADER_ALERT);
        alert.setContentText(CONTEXT_ALERT);
        
        ButtonType ja = new ButtonType("Ja");
        ButtonType nein = new ButtonType("Nein");
        
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ja, nein);
        
        if(alert.showAndWait().get() == ja) {
            System.out.println("Das Programm wurde Erfologreich beendet.");
            stage.close();
        }
    }
    
    public static void main(String[] args) {
        launch();
    }

}