package de.whs.mci.verwiebel_aufgabe2;

import java.io.IOException;
import java.net.URL;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.FormatStringConverter;


public class UmrechnerController implements Initializable{

    @FXML
    private ChoiceBox<String> auswahlFlaschenGroesse;
    //private ChoiceBox<Double> auswahlFlaschenGroesse;
    
    @FXML
    private TextField preisProLiterNetto, flaschenPreisNetto, 
            flaschenPreisBrutto, preisProLiterBrutto;
    
    @FXML
    private Button buttonNetto, buttonBrutto;
    
    // Nummernformat für 2 nachkommastellen:
    private static final NumberFormat nf = NumberFormat.getInstance();
    // Nummernformat für auswahlFlaschen:
    private static final NumberFormat flaschenNF = NumberFormat.getInstance(Locale.GERMANY);
    
    // Double Array für ChoiceBox mit Werten:
    private Double[] flaschen = {0.187, 0.25, 0.375, 0.50,
        0.62, 0.7, 0.75, 0.8, 1.0, 1.5};
    // String Array für ChoiceBox ohne Werte:
    private String[] flaschenString = new String[flaschen.length];
    
    // Variablen und Konstanten:
    private static final String startWert = "1,0 l";
    private static final int nachkommastellen = 2;
    private static final int nachkommastellenFlaschenMax = 3;
    private static final int nachkommastellenFlaschenMin = 1;
    private final double teiler = 100.0;
    private static final int abschneiden = 2;
    private static final double mehrWertSteuer = 19.0;
    private double literAngabe;
    private double nettoPreisFlasche;
    private double preisProLiter;
    private double bruttoFlasche;
    private double bruttoLiter;
    private String pfeilOben = "dreieckOben.png";
    private String pfeilUnten = "dreieckUnten.png";
    private int groesseBild = 40;
    private int textLeange = 0;
    private int stopWert = 1000;
    private int zähler = 1;
    private int naechsterWert = 1;
    private int vorherigerWert = 1;
    private int indexStartWert = 0;
    private String speicherPreisNetto;
    private String speicherPreisLiter;
    private RichtungPfeil richtung = RichtungPfeil.RICHTUNG; 
    
    // Eingabe Syntax:
    private static final String INPUT_LEX = "[-\\d,\\.]*";    
    
    // Angaben für die Fehlermeldungen:   
    private final String LEER = "";
    private static final String ERROR_TITLE_NO_NUMBER = "Keine Zahl";
    private static final String ERROR_TEXT_NO_NUMBER = "Bitte geben Sie eine "
            + "Dezimalzahl im deutschen Format ein (Komma als Dezimalzeichen).\n\n"
            + "Es sind nur Ganze Zahlen oder Zahlen mit mindestens 2 "
            + "Nachkommastellen zulässig.\nFormat: 4,50 oder 21";    
    private static final String ERROR_TITLE_PROGRAMM = "Fehler bei Programm "
            + "Ausführung";
    private static final String ERROR_TEXT_PROGRAMM = "Bei der Ausführung des "
            + "Programms ist ein Fehler aufgetreten, bitte geben Sie die Werte"
            + "erneut ein."; 
    
    //Syntax: Mindestens Zwei Nachkommastellen notwendig für erfolgreiche Eingabe
    private static final String INPUT_SYNTAX_WITH_GROUPING = 
            "-?\\d{1,3}(\\.\\d{3})*(,\\d{2})?";    
    private static final String INPUT_SYNTAX_WITHOUT_GROUPING =
            "-?\\d*(,\\d{2})?";
    
    /**
     * enum für die Angabe der Richtung der Berechnung 
     * bzw. Angabe der letzten Rechnung
     * 
    **/    
    enum RichtungPfeil {
        RICHTUNG("Leer");
        
        private String wert;
        
        RichtungPfeil(String richtung) {
            this.wert = wert;
        }
        
        public String oben() {
            return this.wert = "oben";
        }
        
        public String unten() {
            return this.wert = "unten";
        }
    }
    
    /**
     * 
     * @param arg0
     * @param arg1 
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {            
        // Array mit den String Werten befüllen:
        erstelleArray();
               
        // Einlesen der FlaschenAuswahl:
        ///auswahlFlaschenGroesse.setConverter(new FormatStringConverter<String>(flaschenNF));
        auswahlFlaschenGroesse.getItems().addAll(flaschenString);
	auswahlFlaschenGroesse.setOnAction(this::gibFlaschenGroesse);
        
        // Nachkommastellen der Flaschen Auswahl:
        flaschenNF.setMaximumFractionDigits(nachkommastellenFlaschenMax);
        flaschenNF.setMinimumFractionDigits(nachkommastellenFlaschenMin); 
        
        // Button vor der Eingabe deaktivieren:
        buttonNetto.setDisable(true);
        buttonBrutto.setDisable(true);
        
        // Vorauswahl der ChoiceBox:
        auswahlFlaschenGroesse.setValue(startWert);
        
        // Setzten der möglichen Nachkommastellen der Ergebnisse:
        nf.setMaximumFractionDigits(nachkommastellen);
        nf.setMinimumFractionDigits(nachkommastellen);  
        
        // Bild für Button 1:
        Image img1 = new Image(pfeilUnten);
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(groesseBild);
        view1.setPreserveRatio(true);        
        buttonNetto.setGraphic(view1);
        // Bild für Button 2:
        Image img2 = new Image(pfeilOben);
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(groesseBild);
        view2.setPreserveRatio(true);        
        buttonBrutto.setGraphic(view2);
        
        // Verhindern von Falschen Eingaben für Netto Flaschenpreis:
        this.flaschenPreisNetto.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {
                if (!t.getControlNewText().matches(INPUT_LEX)) {
                    Toolkit.getDefaultToolkit().beep();
                    return null;
                }
                // Freischalten des Button:
                if(flaschenPreisNetto.getText().length() > textLeange) {
                    buttonNetto.setDisable(false);
                }             
                return t;
            }
        }));
        // Prüfung des Eingabefelds beim Verlassen:    
        this.flaschenPreisNetto.focusedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    String eingabe = flaschenPreisNetto.getText();
                    if (!(eingabe.matches(INPUT_SYNTAX_WITH_GROUPING) || (eingabe.matches(INPUT_SYNTAX_WITHOUT_GROUPING)))) {
                        Toolkit.getDefaultToolkit().beep();
                        fehlermeldung(flaschenPreisNetto, ERROR_TITLE_NO_NUMBER, ERROR_TEXT_NO_NUMBER);
                    }
                }
            }
        });
        
        // Verhindern von Falschen Eingaben für Netto Preis pro Liter:
        this.preisProLiterNetto.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {                
                if (!t.getControlNewText().matches(INPUT_LEX)) {
                    Toolkit.getDefaultToolkit().beep();
                    return null;
                }
                // Freischalten des Button:
                if(preisProLiterNetto.getText().length() > textLeange) {
                    buttonBrutto.setDisable(false);
                } 
                return t;
            }
        }));
        // Prüfung des Eingabefelds beim Verlassen:
        this.preisProLiterNetto.focusedProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    String eingabe = preisProLiterNetto.getText();
                    if (!(eingabe.matches(INPUT_SYNTAX_WITH_GROUPING) || (eingabe.matches(INPUT_SYNTAX_WITHOUT_GROUPING)))) {
                        Toolkit.getDefaultToolkit().beep();
                        fehlermeldung(preisProLiterNetto, ERROR_TITLE_NO_NUMBER, ERROR_TEXT_NO_NUMBER);
                    }
                }
            }
        });
        
        } catch(Exception e) {
            // Ergebnisfelder leerren:
            this.flaschenPreisNetto.clear();
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei Fehlschlag:
            fehlermeldung(auswahlFlaschenGroesse, ERROR_TITLE_PROGRAMM, ERROR_TEXT_PROGRAMM);
            return;                                    
        }
    }
    
    /**
     * Erstellung eines weiteren String Arrays in dem die Formatierten Werte
     * eingefügt werden.
     */
    public void erstelleArray() {
        try {           
            for(int i = 0; i < flaschen.length; i++) {
                int wert = zähler;
                String zwischenspeicher = flaschen[i].toString();
            
                for(int j = 0; j < zwischenspeicher.length(); j++) {
                    wert = wert + zähler;
                    if(zwischenspeicher.charAt(j) == '.') {
                        j = stopWert;
                    } 
                }
                String speicher = String.format(Locale.GERMAN, "%,."+ (zwischenspeicher.length() - wert+naechsterWert) +"f l", flaschen[i]);
                flaschenString[i] = speicher;
            }
        
        } catch(Exception e) {
            // Ergebnisfelder leerren:
            this.flaschenPreisNetto.clear();
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei Fehlschlag:
            fehlermeldung(auswahlFlaschenGroesse, ERROR_TITLE_PROGRAMM, ERROR_TEXT_PROGRAMM);
            return;                                    
        }
    }
    
    /**
     * Übergabe des Auswahlwertes aus der ChoiceBox in eine Double Variable für
     * die weiteren Berechnungen.
     * @param event 
     */
    public void gibFlaschenGroesse(ActionEvent event) {
        try {
            // Flaschen Größe aus dem Array Suchen:
            int index = indexStartWert;
            String flaschenAngabe = auswahlFlaschenGroesse.getValue();
            for(int i = 0; i < flaschenString.length; i++) {
                if(flaschenAngabe.equals(flaschenString[i])) {
                    i = stopWert;
                }
                index = index + zähler;
            }
            
            /**
             * Überprüfung ob alle Felder wieder Leer sind Wenn ja dann 
             * keine Fehlermeldung bei Auswahl Flasche.
             * 
             * Wenn Felder nicht leer und einer der Button wurde betätigt dann 
             * Berechnung der jeweiligen Werte Ausführen.
             */            
            if(flaschenPreisNetto.getText().isEmpty() && preisProLiterNetto.getText().isEmpty()) {
                literAngabe = flaschen[index-vorherigerWert];
                
            } else if(flaschenPreisNetto.getText().isEmpty() && preisProLiterNetto.getText().length() > 0) {
                literAngabe = flaschen[index-vorherigerWert];
                berechneMitNettoPreisProLiter();
                
            }  else if(flaschenPreisNetto.getText().length() > 0 && preisProLiterNetto.getText().isEmpty()) {
                literAngabe = flaschen[index-vorherigerWert];
                berechneMitNettoFlaschenPreis();  
                
            } else if(richtung.wert.equals("oben")){
                literAngabe = flaschen[index-vorherigerWert];
                berechneMitNettoPreisProLiter();
                //System.out.println("oben");
           
            } else if(richtung.wert.equals("unten")) {
                literAngabe = flaschen[index-vorherigerWert];
                berechneMitNettoFlaschenPreis();    
                //System.out.println("unten");
           
            } else {
                literAngabe = flaschen[index-vorherigerWert];
            }
       
        }   catch (ParseException e) {            
            // Ergebnisfelder leerren:
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei leerem Feld:
            fehlermeldung(this.preisProLiterNetto, ERROR_TITLE_NO_NUMBER, ERROR_TEXT_NO_NUMBER);
            return;
        }        
    }
    
    /**
     * Wird verwendet um die Felder zu leeren sobald eine neue Zahl eingegeben 
     * wird, in dem Feld preisProLiterNetto.
     */
    public void leereFelderFlaschenpreis() {
        try {            
            if(!preisProLiterNetto.getText().equals(speicherPreisLiter)) {
                this.flaschenPreisNetto.clear(); 
                this.flaschenPreisBrutto.clear();
                this.preisProLiterBrutto.clear();
            }
        } catch(Exception e) {
            // Ergebnisfelder leerren:
            this.flaschenPreisNetto.clear();
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei Fehlschlag:
            fehlermeldung(preisProLiterNetto, ERROR_TITLE_PROGRAMM, ERROR_TEXT_PROGRAMM);
            return;                                    
        }
    }
    
    /**
     * Wird verwendet um die Felder zu leeren sobald eine neue Zahl eingegeben 
     * wird, in dem Feld flaschenPreisNetto.
     */
    public void leereFelderPreisProLiter() {
        try {            
            if(!flaschenPreisNetto.getText().equals(speicherPreisNetto)) {
                this.preisProLiterNetto.clear(); 
                this.flaschenPreisBrutto.clear();
                this.preisProLiterBrutto.clear();
            }
        } catch(Exception e) {
            // Ergebnisfelder leerren:
            this.flaschenPreisNetto.clear();
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei Fehlschlag:
            fehlermeldung(preisProLiterNetto, ERROR_TITLE_PROGRAMM, ERROR_TEXT_PROGRAMM);
            return;                                    
        }
    }
    
    /**
     * 
     * @throws IOException 
     */
    @FXML
    private void berechneMitNettoFlaschenPreis() throws ParseException {
        try {
            // Den aktuellen Text Speichern:
            speicherPreisNetto = flaschenPreisNetto.getText();
            // Festlegung der verwendeten Funktion:
            richtung.unten();
            
            nettoPreisFlasche = nf.parse(flaschenPreisNetto.getText()).doubleValue();          
            
            // Berechnung Netto Preis pro Liter:
            preisProLiter = nettoPreisFlasche / literAngabe;
                
            // Berechnung der Brutto Preise:
            bruttoFlasche =  (nettoPreisFlasche / teiler) * mehrWertSteuer 
                + nettoPreisFlasche;
            bruttoLiter = (preisProLiter / teiler) * mehrWertSteuer 
                + preisProLiter;
                
        } catch (ParseException e) {            
            // Ergebnisfelder leerren:
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei leerem Feld:
            fehlermeldung(this.preisProLiterNetto, ERROR_TITLE_NO_NUMBER, ERROR_TEXT_NO_NUMBER);
            return;
        }
        // Ausgabe Formatieren:
        preisProLiterNetto.setText(nf.format(preisProLiter));
        flaschenPreisBrutto.setText(nf.format(bruttoFlasche));
        preisProLiterBrutto.setText(nf.format(bruttoLiter));  
        // Den aktuell eingetragenen Text holen:
        speicherPreisLiter = preisProLiterNetto.getText();
    }
    
    /**
     * 
     * @throws IOException 
     */
    @FXML
    private void berechneMitNettoPreisProLiter() throws ParseException {
        try {
            // Den aktuellen Text Speichern:
            speicherPreisLiter = preisProLiterNetto.getText();
            // Festlegung der verwendeten Funktion:
            richtung.oben();
            
            preisProLiter = nf.parse(preisProLiterNetto.getText()).doubleValue();
                
            // Berechnung Netto Preis pro Flasche: 
            nettoPreisFlasche =  literAngabe * preisProLiter;
                
            // Berechnung der Brutto Preise:
            bruttoFlasche =  (nettoPreisFlasche / teiler) * mehrWertSteuer 
                + nettoPreisFlasche;
            bruttoLiter = (preisProLiter / teiler) * mehrWertSteuer 
                + preisProLiter;
            
        } catch (ParseException e) {
            // Ergebnisfelder leerren:
            this.flaschenPreisNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei leerem Feld;
            fehlermeldung(this.preisProLiterNetto, ERROR_TITLE_NO_NUMBER, ERROR_TEXT_NO_NUMBER);
            return;
        } 
        // Ausgabe Formatieren:
        flaschenPreisNetto.setText(nf.format(nettoPreisFlasche));
        flaschenPreisBrutto.setText(nf.format(bruttoFlasche));
        preisProLiterBrutto.setText(nf.format(bruttoLiter));
        // Den aktuell eingetragenen Text holen:
        speicherPreisNetto = flaschenPreisNetto.getText();
    }
    
    private void fehlermeldung(Control control, String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(LEER);
        alert.setContentText(text);
        alert.showAndWait();
        control.requestFocus();
    }
    
}
