
package de.whs.mci.verwiebel_aufgabe_3;

import java.text.ParseException;
import java.util.Locale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * JavaFX App
 * @author Lukas Verwiebe 202021182
 */
public class Umrechner {
    
    // Double Array für ChoiceBox mit Werten:
    protected Double[] flaschen = {0.187, 0.25, 0.375, 0.50,
        0.62, 0.7, 0.75, 0.8, 1.0, 1.5};
    // String Array für ChoiceBox ohne Werte:
    protected String[] flaschenString = new String[flaschen.length];
    
    // Variablen und Konstanten:
    private static final String STARTWERT = "1,0 l";
    private static final int NACHKOMMASTELLEN = 2;
    private static final int NACHKOMMASTELLEN_FLASCHE_MAX = 3;
    private static final int NACHKOMMASTELLEN_FLASCHE_MIN = 1;
    private final double TEILER = 100.0;
    private static final int ABSCHNEIDEN = 2;
    private static final double MEHRWERTSTEUER = 19.0;
    private double literAngabe;
    
    protected double nettoPreisFlasche;    
    protected double preisProLiter;
    protected double bruttoFlasche;
    protected double bruttoLiter;
    
    private int STOPWERT = 1000;
    private int ZAEHLER = 1;
    private int NAECHSTERWERT = 1;
    private int VORHERIGERWERT = 1;   
    
    // Zusätzliche Klassen:
    private Fehlermeldungen meldung = new Fehlermeldungen();
    
    public Umrechner() {
    }
    
    /**
     * Erstellung eines weiteren String Arrays in dem die Formatierten Werte
     * eingefügt werden.
     */
    protected void erstelleArray() {
        try {           
            for(int i = 0; i < flaschen.length; i++) {
                int wert = ZAEHLER;
                String zwischenspeicher = flaschen[i].toString();
            
                for(int j = 0; j < zwischenspeicher.length(); j++) {
                    wert = wert + ZAEHLER;
                    if(zwischenspeicher.charAt(j) == '.') {
                        j = STOPWERT;
                    } 
                }
                String speicher = String.format(Locale.GERMAN, "%,."+ (zwischenspeicher.length() - wert+NAECHSTERWERT) +"f l", flaschen[i]);
                flaschenString[i] = speicher;
            }
        
        } catch(Exception e) {            
            // Fehlermeldung bei Fehlschlag:
            meldung.fehlermeldungOhneFeld(meldung.ERROR_TITLE_PROGRAMM, 
                    meldung.ERROR_TEXT_PROGRAMM);
            return;                                    
        }
    }
    
    /**
     * Berechnung für Preis Pro Lister:
     */
//    protected void berechnePreisProLiter() {        
//        // Berechnung Netto Preis pro Liter:
//        preisProLiter = nettoPreisFlasche / literAngabe;
//                
//        // Berechnung der Brutto Preise:
//        bruttoFlasche =  (nettoPreisFlasche / TEILER) * MEHRWERTSTEUER 
//            + nettoPreisFlasche;
//        bruttoLiter = (preisProLiter / TEILER) * MEHRWERTSTEUER 
//            + preisProLiter;
//        
//    }
    
    /**
     * Berechnung für Literpreis:
     */
//    protected void berechneFlaschenPreis() {
//        // Berechnung Netto Preis pro Flasche: 
//        nettoPreisFlasche =  literAngabe * preisProLiter;
//                
//        // Berechnung der Brutto Preise:
//        bruttoFlasche =  (nettoPreisFlasche / TEILER) * MEHRWERTSTEUER 
//            + nettoPreisFlasche;
//        bruttoLiter = (preisProLiter / TEILER) * MEHRWERTSTEUER 
//            + preisProLiter;
//    }
    
}
