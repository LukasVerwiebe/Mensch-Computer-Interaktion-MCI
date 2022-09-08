
package de.whs.mci.trinkreifediagramm;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Lukas Verwiebe
 */
public class EingabeController extends DiagrammController{    
        
    @FXML
    private TextField jahrgangText, dauerText;
    
    public int jahrgang;
    public int dauer;    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private static final String ALERT_TITLE = "Format Fehler";
    private static final String ALERT_HEADER_TEXT = "Die Eingabe war nicht "
            + "korrekt!";
    private static final String ALERT_CONTENT_TEXT = "Für die Eingabe dürfen "
            + "nur Ganze Zahlen verwendet werden.";
    private static final String FXMLDatei = "diagramm.fxml";
    
    /**
     * Button Funktion mit dem nach der Eingabe das Fenster gewechselt wird.
     * Davor wird geprüft ob nur Zahlen und kein Text eingegeben wurde, ist
     * dies nicht der Fall wird eine Fehlermeldung in einem Label angezeigt.
     * Es wird dann nicht das Fenster gewechselt bis die Eingabe korrekt 
     * vorgenommen wurde.
     */
    @FXML
    private void switchToDiagramm(ActionEvent event) throws IOException {
        try {
            // Versuche den eingabe Wert in einen Integer zu Paresen
            jahrgang = Integer.parseInt(jahrgangText.getText());
            dauer = Integer.parseInt(dauerText.getText());
            
            // Aufruf der zweiten Stage und Ausführung der Methoden:
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLDatei));
            root = loader.load(); 
            
            // Aufruf der Methoden zum Eintragen der Jahre und Farben:            
            DiagrammController diagrammController = loader.getController();            
            diagrammController.setzteJahr(jahrgang, dauer);           
            diagrammController.passeGrößeAn(jahrgang, dauer);
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch(NumberFormatException e) {
            // Ausgabe einer Warnmeldung           
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(ALERT_TITLE);
            alert.setHeaderText(ALERT_HEADER_TEXT);
            alert.setContentText(ALERT_CONTENT_TEXT);
            
            // Die Textfelder werden geleert
            if(alert.showAndWait().get() == ButtonType.OK) {
		jahrgangText.clear();
                dauerText.clear();
            }
        } catch(Exception e) {
            System.out.println(e);
        }       
    }
    
}
