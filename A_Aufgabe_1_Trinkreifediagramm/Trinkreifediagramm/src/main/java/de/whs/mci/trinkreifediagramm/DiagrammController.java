
package de.whs.mci.trinkreifediagramm;

import java.io.IOException;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Lukas Verwiebe
 */
public class DiagrammController {    
      
    @FXML
    private Rectangle rectSteigeungsfaehig, rectOptimal, rectUngenießbar, startJahr,
            rectZuFrueh, rectLaesstNach;
    @FXML
    private Label jahrStartRand, jahresAnzeige0, jahresAnzeige1, jahresAnzeige2, 
            jahresAnzeige3, jahresAnzeige4, jahresAnzeige5, jahresAnzeige6,
            jahresAnzeige7, jahresAnzeige8, jahresAnzeige9, jahrEndRand, 
            aktuellesJahr;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static final String FXMLDatei = "eingabe.fxml";
    
    private double groeßeZuFrueh;
    private double groeßeSteigeungsfaehig;
    private double groeßeLaesstNach;   
    private double groeßeOptimal;    
    private double groeßeUngenießbar = 50.0;
    
    private static final int abschlussJahr = 1;    
    private static final int einAchtel = 8;
    private static final int einHalb = 2;
    private static final int einSechzehntel = 16;
    private static final int startDauer = 1;
    private static final int gesamtGroese = 550;
    private static final int jahresBerechnung = 5;
    private static final int rechteckGröße = 50;
    private static final int größeZweiJahre = 100;
    private Calendar calendar = Calendar.getInstance();    
    private final int momentanesJahr = calendar.get(calendar.YEAR);
    
    private int jahrAnfang;
    private int jahrEnde;
    private double startRand;
    private double endRand;
    private int start = 0;
    private int länge;
    
    /**
     * Berechnung für die Position der Farben
     * @param jahr
     * @param dauer 
     */
    public void passeGrößeAn(int jahr, int dauer) {
        // Berechnung des Start Randes und End Randes
        jahrAnfang = momentanesJahr - jahresBerechnung;
        jahrEnde = momentanesJahr + jahresBerechnung;
        startRand = (jahr - jahrAnfang) * rechteckGröße;
        endRand = ((jahr + dauer) - jahrAnfang) * rechteckGröße + größeZweiJahre;
        
        // Festlegeung der Größe des Startjahres
        if(jahr >= jahrAnfang || jahr <= jahrEnde) {
            start = (jahr - jahrAnfang) * rechteckGröße + gesamtGroese;
        } else {
            start = (jahr - jahrAnfang) * rechteckGröße;
        }     
        this.startJahr.setWidth(start);
        
        // Berechnung der dauer ohne Endjahr Gelb
        länge = (startDauer + dauer) * rechteckGröße;
        
        // Berechnung der Farbgröße
        groeßeZuFrueh = länge / einAchtel;        
        groeßeOptimal = länge / einHalb;
        groeßeLaesstNach = länge / einSechzehntel;        
        groeßeSteigeungsfaehig = länge - groeßeZuFrueh - groeßeLaesstNach - groeßeOptimal;
        
        // Setzen der jeweiligen Farbgröße
        rectZuFrueh.setWidth(groeßeZuFrueh);
        rectSteigeungsfaehig.setWidth(groeßeSteigeungsfaehig);
        rectOptimal.setWidth(groeßeOptimal);
        rectLaesstNach.setWidth(groeßeLaesstNach);
        rectUngenießbar.setWidth(groeßeUngenießbar);
    }    
    
    /**
     * Festlegung des Start Jahres und des End Jahres je nach eingabe wird der 
     * Text in einem der Label unterhalb der Rechtecke angezeigt.
     * @param jahr 
     * @param dauer 
     */
    public void setzteJahr(int jahr, int dauer) {       
       aktuellesJahr.setText(Integer.toString(calendar.get(calendar.YEAR)));
       jahrAnfang = momentanesJahr - jahresBerechnung;
       jahrEnde = momentanesJahr + jahresBerechnung;
       
       // Für die das äußere Startjahr
       if(jahr < jahrAnfang && (jahr + dauer+abschlussJahr) >= jahrAnfang) {
           jahrStartRand.setText(Integer.toString(jahr));
       } else if(jahr < jahrAnfang && (jahr + dauer+abschlussJahr) < jahrAnfang) {
           jahrStartRand.setText(Integer.toString(jahr + dauer+abschlussJahr));
       }
       // Für das äußere Endjahr
       if(jahr < jahrEnde && (jahr + dauer+abschlussJahr) > jahrEnde) {
           jahrEndRand.setText(Integer.toString(jahr + dauer+abschlussJahr));
       } else if(jahr > jahrEnde && (jahr + dauer+abschlussJahr) > jahrEnde) {
           jahrEndRand.setText(Integer.toString(jahr + dauer+abschlussJahr));
       }
       // Für die Jahre zwischen dem äußeren End- und Startjahr
       if(jahr >= jahrAnfang && jahr <= jahrEnde) {
           jahrFinden(jahr - jahrAnfang);
           jahrFinden(jahr - jahrAnfang + dauer +abschlussJahr);
       } else if(jahr < jahrAnfang && jahr <= jahrEnde) {
           jahrFinden(jahr - jahrAnfang + dauer +abschlussJahr);
       } else if(jahr >= jahrAnfang && jahr >= jahrEnde) {
           jahrFinden(jahr - jahrAnfang);
       }
    }
    
    public void jahrFinden(int jahresNummer) {
        switch(jahresNummer) {
                case 0: 
                    jahresAnzeige0.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;
                case 1: 
                    jahresAnzeige1.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;
                case 2: 
                    jahresAnzeige2.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;
                case 3: 
                    jahresAnzeige3.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;  
                case 4: 
                    jahresAnzeige4.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;
                case 6: 
                    jahresAnzeige5.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;    
                case 7: 
                    jahresAnzeige6.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;  
                case 8: 
                    jahresAnzeige7.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break; 
                case 9: 
                    jahresAnzeige8.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;
                case 10: 
                    jahresAnzeige9.setText(Integer.toString(jahrAnfang + jahresNummer));
                    break;
        }        
    }  
    
    /**
     * Rücksprung zur erneuten Eingabe. Zu test Zwecken eingebaut.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void switchToEingabe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLDatei));
        root = loader.load();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
