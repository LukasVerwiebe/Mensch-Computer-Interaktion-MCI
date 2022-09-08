
package de.whs.mci.verwiebel_aufgabe_3;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;

/**
 * JavaFX App
 * @author Lukas Verwiebe 202021182
 */
public class Fehlermeldungen {
    
    // Angaben für die Fehlermeldungen LEER:   
    private final String LEER = "";
    
    // Fehlermeldungen für den Umrechner:
    protected final String ERROR_TITLE_NO_NUMBER = "Keine Zahl";
    protected final String ERROR_TEXT_NO_NUMBER = "Bitte geben Sie eine "
            + "Dezimalzahl im deutschen Format ein (Komma als Dezimalzeichen).\n\n"
            + "Es sind nur Ganze Zahlen oder Zahlen mit mindestens 2 "
            + "Nachkommastellen zulässig.\nFormat: 4,50 oder 21";
    
    // Fehlermeldung Allgemein:
    protected final String ERROR_TITLE_PROGRAMM = "Fehler bei Programm "
            + "Ausführung";
    protected final String ERROR_TEXT_PROGRAMM = "Bei der Ausführung des "
            + "Programms ist ein Fehler aufgetreten, bitte geben Sie die Werte"
            + "erneut ein.";  
    
    // Fehlermeldungen für das Reifediagramm:
    protected final String ALERT_TITLE = "Unvollständige Eingabe";
    protected final String ALERT_CONTENT_TEXT = "Für die Erstellung des "
            + "Diagramms ist es notwendig, dass die Felder: Jahrgang und "
            + "Lagerdauer befüllt werden.";
    protected final String ERROR_TITLE_JAHRGANG = "Falsche Jahresangabe";
    protected final String ERROR_TEXT_JAHRGANG = "Bitte geben Sie ein Jahr "
            + "ein das 4 Zeichenlang ist.";
    protected final String ERROR_TITLE_LAGERDAUER = "Zu Große Lagerdauer";
    protected final String ERROR_TEXT_LAGERDAUER = "Die Lagerdauer darf "
            + "nicht größer als 3 Zeichen sein.";
    protected final String ERROR_TITLE_JAHR = "Jahrgang liegt in der Zukunft";
    protected final String ERROR_TEXT_JAHR = "Die  Angabe für den Jahrgang "
            + "liegt in der Zukunft. Dies ist nicht zulässig.";
    
    // Meldungstext Programm beenden:
    protected final String TITLE_ALERT = "Programm beenden";
    protected final String HEADER_ALERT = "Sie sind dabei das Programm zu verlassen!";
    protected final String CONTEXT_ALERT = "Die von Ihnen eingegebenen Daten werden nicht gespeichert.\n\n"
            + "Wollen Sie Das Programm ohne zu speichern beenden?";
    
    // Meldungstext Programm Speichern:
    protected final String ERROR_TITLE_SPEICHERN = "Fehlende Werte";
    protected final String ERROR_TEXT_SPEICHERN = "Es wurden nicht alle "
            + "Felder ausgefüllt!";
    
    // Meldungstext für Diagramm Erstellung:
    protected final String TITLE_INFORMATION_ERFOLG = "Diagramm erstellung "
            + "erfolgreich";
    protected final String TEXT_INFORMATION_ERFOLG = "Das Diagramm wurde "
            + "erfolgreich erstellt und steht unter dem Tabreiter Reifediagramm "
            + "zur Verfügung.";
    
    // Meldungstext für falsche Syntax bei Bestellnummer:
    protected final String ERROR_TITLE_BESTELL = "Falsches Eingabeformat";
    protected final String ERROR_TEXT_BESTELL = "Das Eingabeformat der "
            + "Bestellnummer ist nicht korrekt: \n"
            + "Die Nummer muss im Format 2021-BBBB-ZZZ eingegeben werden.\n\n"
            + "Beispiel: 2021-ABZD-341";
    
    // Meldungstext für Alkoholgehalt eingabe:
    protected final String ERROR_TITLE_ALKOHOL = "Flasche Eingabe";
    protected final String ERROR_TEXT_ALKOHOL = "Es darf nur eine Zahl zwischen 7 und 25 eingegeben werden.\n"
            + "Es darf nur das Deutsche Format verwendet werden. Außerdem ist nur eine nachkommastelle zulässig.\n\n"
            + "Es sind nur die Zahlen 0 oder 5 als Nachkommastelle zulässig.\n"
            + "Format Beispiel: 14,5";
    
    public Fehlermeldungen() {        
    }
    
     /**
     * Fehlermeldung mit Focus auf Feld.
     * @param control
     * @param title
     * @param text 
     */
    public void fehlermeldungMitFeld(Control control, String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(LEER);
        alert.setContentText(text);
        alert.showAndWait();
        control.requestFocus();
    }
    
    /**
     * Fehlermeldung Ohne Focus auf ein Feld.
     * @param title
     * @param text 
     */
    public void fehlermeldungOhneFeld(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(LEER);
        alert.setContentText(text);
        alert.showAndWait();
    }
    
    /**
     * Meldung mit Information:
     * @param title
     * @param text 
     */
    public void meldung(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(LEER);
        alert.setContentText(text);
        alert.showAndWait();
    }
    
    
}
