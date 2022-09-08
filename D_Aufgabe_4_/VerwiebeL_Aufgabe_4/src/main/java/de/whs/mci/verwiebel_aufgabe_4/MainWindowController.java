
package de.whs.mci.verwiebel_aufgabe_4;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
/**
 *
 * @author Lukas Verwiebe 202021182
 */
public class MainWindowController implements Initializable{

    private static final String EMPTY = "";
    private static final String INFO_TITLE = "Info";
    private static final String INFO_TEXT = "Praktikum MCI/MCIM/MCIW WiSe 2021\nPraktikumsaufgabe 4: Prototyp Anwendung Weinhandel";
    private static final String QUIT_TITLE = "Programm beenden";
    private static final String QUIT_TEXT = "Wollen Sie das Programm beenden?";
    private static final String ERROR_OPEN_TITLE = "Neues Fenster";
    private static final String ERROR_OPEN_TEXT = "Fenster konnte nicht geöffnet werden:\n";
    protected static List<Wein> weinliste = new ArrayList<Wein>();
    private static final String OPEN_IMAGE_TITLE = "Weindaten öffnen";
    private static final String OPEN_IMAGE_IMAGE_DESCRIPTION = "Weindaten";
    private static final String [] OPEN_IMAGE_IMAGE_EXTENSIONS = {"*.csv"};
    private static final String OPEN_IMAGE_ALL_DESCRIPTION = "Alle Dateien";
    private static final String [] OPEN_IMAGE_ALL_EXTENSIONS = {"*.*"};
    
    @FXML
    private VBox vboxMain;    
    @FXML
    private Menu muWindow;
    @FXML
    private MenuItem delete, liste;    
    @FXML
    private MenuItem miMainWindow;
    
    @FXML    
    private void mnOpenWeinliste() throws IOException {
        Weinliste window;
        window = new Weinliste();
        window.starteWeinliste();
    }
    
    @FXML    
    private void mnOpen() throws IOException {
        SecondaryWindow window;
        window = new SecondaryWindow();
        window.starteWeinEingabe();
    }
    
    @FXML    
    private void mnOpenFile() throws IOException {
        try {              
            File file = new File("C:\\Test\\output.csv");   
            if(!Desktop.isDesktopSupported()) {  
                System.out.println("wird nicht unterstützt");  
                return;  
            }  
            Desktop desktop = Desktop.getDesktop();  
            if(file.exists()) {
                desktop.open(file);
            } else {
                mnOpen();
            } 
            
        } catch(Exception e) {  
            e.printStackTrace();  
        }  
  
    }

    @FXML
    private void mnQuit() {
        if (confirm(QUIT_TITLE, QUIT_TEXT).equals(ButtonType.YES)) {
            Platform.exit();
        }
    }

    @FXML
    private void mnInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFO_TITLE);
        alert.setHeaderText(EMPTY);
        alert.setContentText(INFO_TEXT);
        alert.showAndWait();
    }

    private ButtonType confirm(String title, String text) {
        Toolkit.getDefaultToolkit().beep();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, text, ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setHeaderText(EMPTY);
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.isPresent()) {
            return answer.get();
        } else {
            return null;
        }
    }

    private void showError(String title, String text) {
        Toolkit.getDefaultToolkit().beep();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(EMPTY);
        alert.setContentText(text);
        alert.showAndWait();
    }    
    
    @FXML
    private void ausgabeDateiFileChooser() throws FileNotFoundException, IOException {
        try {
            // FileChosser settings
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(OPEN_IMAGE_TITLE);
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(OPEN_IMAGE_IMAGE_DESCRIPTION,OPEN_IMAGE_IMAGE_EXTENSIONS),
                new FileChooser.ExtensionFilter(OPEN_IMAGE_ALL_DESCRIPTION, OPEN_IMAGE_ALL_EXTENSIONS));
            // show FileChooser dialogue
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile == null) {
                // nothing selectedt
                return;
            }
            
            
            OutputStream out = new FileOutputStream("C:\\Test\\output.csv");
            
            // Schleife zum Durchlaufen der Array:
            for(int i = 0; i < weinliste.size(); i++) {
                // Konvertiert den String in Bytes:
                byte[] dataBytes = weinliste.get(i).ausgabeDateiText().getBytes();            
                // Schreibt die Datenin den output stream:
                out.write(dataBytes);
                out.write('\n');
                System.out.println("Die Daten wurden in die Datei geschrieben.");
            }
            // Schließe den output stream:
            out.close();
            
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    @FXML
    private void ausgabeDatei() throws FileNotFoundException, IOException {
        try {
            OutputStream out = new FileOutputStream("C:\\Test\\output.csv");
            
            // Schleife zum Durchlaufen der Array:
            for(int i = 0; i < weinliste.size(); i++) {
                // Konvertiert den String in Bytes:
                byte[] dataBytes = weinliste.get(i).ausgabeDateiText().getBytes();            
                // Schreibt die Datenin den output stream:
                out.write(dataBytes);
                out.write('\n');
                System.out.println("Die Daten wurden in die Datei geschrieben.");
            }
            // Schließe den output stream:
            out.close();
            
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
}    
