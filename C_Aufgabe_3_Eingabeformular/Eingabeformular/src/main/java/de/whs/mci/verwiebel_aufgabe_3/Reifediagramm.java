
package de.whs.mci.verwiebel_aufgabe_3;

import java.util.Calendar;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

/**
 * JavaFX App
 * @author Lukas Verwiebe 202021182
 */
public class Reifediagramm {
    
    private double startRand;
    private double endRand;
    private int länge;
    private static final int ABSCHLUSSJAHR = 1;    
    private static final int EINACHTEL = 8;
    private static final int EINHALB = 2;
    private static final int EINSCHZEHNTEL = 16;
    private static final int STARTDAUER = 1;
    private static final int GESAMTGROESE = 550;
    private static final int ABZUG = 50;
    private static final int JAHRESBERECHNUNG = 5;
    private static final int RECHTECKGROESE = 50;
    private static final int GROESEZWEIJAHRE = 100;
    private Calendar calendar = Calendar.getInstance();    
    private final int momentanesJahr = calendar.get(calendar.YEAR);

    // Zur Verfügung gestellte Variablen:
    protected int START = 0;
    protected double groeßeZuFrueh;
    protected double groeßeSteigeungsfaehig;
    protected double groeßeLaesstNach;   
    protected double groeßeOptimal;    
    protected double GROESEUNGENIESBAR = 50.0;
    protected int jahrAnfang;
    protected int jahrEnde;
    
    // Benötigte Klassen:
    private Fehlermeldungen fehlermeldung = new Fehlermeldungen();
    
    
    public Reifediagramm() {        
    }
    
    /**
     * Berechnung der Längen für das Reifediagramm:
     */
    protected void diagrammBerechnung(int jahr, int dauer) {
        try {            
            // Berechnung des Start Randes und End Randes
            jahrAnfang = momentanesJahr - JAHRESBERECHNUNG;
            jahrEnde = momentanesJahr + JAHRESBERECHNUNG;
            startRand = (jahr - jahrAnfang) * RECHTECKGROESE;
            endRand = ((jahr + dauer) - jahrAnfang) * RECHTECKGROESE + GROESEZWEIJAHRE;
        
            // Festlegeung der Größe des Startjahres
            if(jahr >= jahrAnfang || jahr <= jahrEnde) {
                START = (jahr - jahrAnfang) * RECHTECKGROESE + GESAMTGROESE -ABZUG;
            } else {
                START = (jahr - jahrAnfang) * RECHTECKGROESE -ABZUG;
            }  
        
            // Berechnung der dauer ohne Endjahr Gelb
            länge = (STARTDAUER + dauer) * RECHTECKGROESE;
        
            // Berechnung der Farbgröße
            groeßeZuFrueh = länge / EINACHTEL;        
            groeßeOptimal = länge / EINHALB;
            groeßeLaesstNach = länge / EINSCHZEHNTEL;        
            groeßeSteigeungsfaehig = länge - groeßeZuFrueh - groeßeLaesstNach - groeßeOptimal;
            
        } catch(NumberFormatException e) { 
            // Fehlermeldung bei Fehlschlag:
            fehlermeldung.fehlermeldungOhneFeld(fehlermeldung.ALERT_TITLE, 
                    fehlermeldung.ALERT_CONTENT_TEXT);
            return;   
        } catch(Exception e) {
            System.out.println(e);
        }       
    }
    
    /**
     * Berechnung des Anfang und Endjahres:
     */
    protected void jahresBerechnung() {       
       jahrAnfang = momentanesJahr - JAHRESBERECHNUNG;
       jahrEnde = momentanesJahr + JAHRESBERECHNUNG;
    }
    
    
}
