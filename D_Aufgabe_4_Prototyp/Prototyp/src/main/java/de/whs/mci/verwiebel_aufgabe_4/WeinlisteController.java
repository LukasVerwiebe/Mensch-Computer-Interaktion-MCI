
package de.whs.mci.verwiebel_aufgabe_4;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Lukas
 */
public class WeinlisteController implements Initializable{
        
    @FXML
    private TableView<Wein> tableView; 
    @FXML
    private TableColumn<Wein, String> columnEins;
    @FXML
    private TableColumn<Wein, String> columnZwei;
    @FXML
    private TableColumn<Wein, String> columnDrei;
    @FXML
    private TableColumn<Wein, String> columnVier;
    @FXML
    private TableColumn<Wein, String> columnFuenf;
    
    @FXML
    public void zeileEntfernen() {
        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Wein> weindaten = FXCollections.observableArrayList();
        for(int i = 0; i < MainWindowController.weinliste.size(); i++) {
            weindaten.add(MainWindowController.weinliste.get(i));
        }
        tableView.setItems(weindaten);
        columnEins.setCellValueFactory(new PropertyValueFactory<Wein,String>("bestellnummer"));
        columnZwei.setCellValueFactory(new PropertyValueFactory<Wein,String>("jahrgang"));
        columnDrei.setCellValueFactory(new PropertyValueFactory<Wein,String>("weinname"));
        columnVier.setCellValueFactory(new PropertyValueFactory<Wein,String>("farbe"));
        columnFuenf.setCellValueFactory(new PropertyValueFactory<Wein,String>("preisProFlascheBrutto"));
    }
}
