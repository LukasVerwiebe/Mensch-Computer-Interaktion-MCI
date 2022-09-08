package de.whs.mci.verwiebel_aufgabe_4;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX App
 * @author Lukas Verwiebe 202021182
 */
public class WeinEingabeFormularController implements Initializable{
    
    @FXML
    private TabPane scenePane;
    @FXML
    private Tab weindatenTab, reifediagrammTab;
    @FXML
    private TitledPane weindaten, anbaugebiet, reifediagrammEingabe, umrechner,
            alkoholgehalt, weindatenDiagramm;    
    @FXML
    private TextArea beschreibung;    
    @FXML
    private Slider alkoholgehaltSlider;    
    @FXML
    private ChoiceBox<String> auswahlFlaschenGroesse, anbauLand, anbauRegion;       
    @FXML
    private TextField preisProLiterNetto, flaschenPreisNetto, 
            flaschenPreisBrutto, preisProLiterBrutto, jahrgangText, dauerText,
            bestellnummer, weinname, weinfarbe, alkoholGehalt;    
    @FXML
    private Button buttonNetto, buttonBrutto, abbrechen, speichern;
    @FXML
    private Rectangle rectSteigeungsfaehig, rectOptimal, rectUngenießbar, startJahr,
            rectZuFrueh, rectLaesstNach;
    @FXML
    private Label jahrStartRand, jahresAnzeige0, jahresAnzeige1, jahresAnzeige2, 
            jahresAnzeige3, jahresAnzeige4, jahresAnzeige5, jahresAnzeige6,
            jahresAnzeige7, jahresAnzeige8, jahresAnzeige9, jahrEndRand, 
            aktuellesJahr, jahrgang, lagerdauer, alkoholgehaltAnzeige, 
            diagrammBestellnummer, diagrammWeinnname, diagrammWeinFarbe,
            diagrammLand, diagrammRegion, diagrammAlkohol, diagrammFlasche;
    @FXML 
    private CheckBox alkoholnullprozent, slideraktivieren;
    @FXML
    private RadioButton farbeRot, farbeWeiss, farbeRose;
    
    
    // Nummernformat für 2 NACHKOMMASTELLEN:
    private static final NumberFormat nf = NumberFormat.getInstance();
    // Nachkommastellen für Slider:
    private static final NumberFormat nf2 = NumberFormat.getInstance();
    // Nummernformat für auswahlFlaschen:
    private static final NumberFormat flaschenNF = NumberFormat.getInstance(Locale.GERMANY);
    
    // Variablen und Konstanten:
    private Stage stage;
    private final String NACHRICHT_BEENDEN = "Das Programm wurde Erfologreich beendet.";
    private boolean aenderungen = false;
    private ArrayList<TextField> feldliste = new ArrayList();
    private String farbauswahl = "Rot";
    private boolean bWeinname;
    private boolean bWeinFarbe;
    private boolean bBestellnummer;
    private boolean bJahrgangText;
    private boolean bDauerText;
    private boolean bFlaschenPreisNetto;
    private boolean bFlaschenPreisBrutto;
    private boolean bPreisProLiterNetto;
    private boolean bPreisProLiterBrutto;
    private boolean bAlkoholGehalt;
    // Variablen Umrechner:
    private static final String STARTWERT = "0,75 l";
    private static final int NACHKOMMASTELLEN = 2;
    private static final int NACHKOMMASTELLEN_FLASCHE_MAX = 3;
    private static final int NACHKOMMASTELLEN_FLASCHE_MIN = 1;
    private final double TEILER = 100.0;
    private static final int ABSCHNEIDEN = 2;
    private static final double MEHRWERTSTEUER = 19.0;
    private static final double MEHRWERTSTEUER_BRUTTO = 1.19;
    private double literAngabe;    
    private double nettoPreisFlasche;
    private double preisProLiter;
    private double bruttoFlasche;
    private double bruttoLiter;    
    private String pfeilOben = "dreieckOben.png";
    private String pfeilUnten = "dreieckUnten.png";
    private int GROESSEBILD = 40;
    private int TEXTLEANGE = 0;
    private int STOPWERT = 1000;
    private int ZAEHLER = 1;
    private int NAECHSTERWERT = 1;
    private int VORHERIGERWERT = 1;
    private int INDEXSTARTWERT = 0;
    private String speicherPreisNetto;
    private String speicherPreisLiterNetto;
    private String speicherPreisBrutto;
    private String speicherPreisLiterBrutto;
    private double bruttoPreisFlasche;
    private double bruttoPreisProLiter;
    // Variablen Reifediagramm:
    private int jahr;
    private int dauer;
    private static final int ABSCHLUSSJAHR = 1;
    private static final int JAHRESBERECHNUNG = 5;  
    private static final String LAGERDAUERSTART = "3";
    private Calendar calendar = Calendar.getInstance();    
    private final int momentanesJahr = calendar.get(calendar.YEAR); 
    private ArrayList<Label> jahresLabel = new ArrayList();    
    // Variablen Anbaugebiet:
    private final String STARTLAND = "Deutschland";
    private final String STARTREGION = "Keine Region";
    // Variablen Slider:
    private final double STARTALKOHOLGEHALT = 16.0;
    private double alkohol;
    private final String TEXTSTARTWERT = "16,0";
    private static final int NACHKOMMASTELLEN2 = 1;
    // Färbung Speichern:
    private String STYLE_RED = "-fx-background-color:rgba(245, 236, 39, 0.3)";
    //"-fx-background-color:rgba(255, 100, 20, 0.5)";
    private String STYLE_WHITE = "-fx-background-color:rgba(255, 255, 255, 1)";
    
    // Zusätzliche Klassen:
    private Umrechner umrechnen = new Umrechner();
    private Fehlermeldungen meldung = new Fehlermeldungen();
    private RichtungPfeil richtung = RichtungPfeil.RICHTUNG; 
    private Anbaugebiet anbau = new Anbaugebiet();
    private Reifediagramm diagramm = new Reifediagramm();
    private Wein ergebnis;
    
    // Eingabe Syntax:
    private static final String INPUT_LEX_DIAGRAMM = "[+\\d]*";
    private static final String INPUT_LEX = "[-\\d,\\.]*";
    private static final String INPUT_LEX_BESTELL = "[-\\d[aA-zZ]]*";
    //Syntax: Mindestens Zwei Nachkommastellen notwendig für erfolgreiche Eingabe
    private static final String INPUT_SYNTAX_WITH_GROUPING = 
            "-?\\d{1,3}(\\.\\d{3})*(,\\d{2})?";    
    private static final String INPUT_SYNTAX_WITHOUT_GROUPING =
            "-?\\d*(,\\d{2})?";
    // Syntax prüfung Bestellnummer:
    private static final String INPUT_BESTELL = 
            "\\d{4}\\-[A-Z]{4}\\-\\d{3}";
    private static final String INPUT_LEX_ALKOHOL = "[+\\d,]*";
    private static final String INPUT_ALKOHOL = "\\d{1,2}\\,\\d{1}";
    
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
            // TitledPane Einstellung:
            weindaten.setCollapsible(false);
            anbaugebiet.setCollapsible(false);
            reifediagrammEingabe.setCollapsible(false);
            umrechner.setCollapsible(false);
            alkoholgehalt.setCollapsible(false);
            
            // Vorbelegung Bestellnummer:
            bestellnummer.setText(momentanesJahr + "-");
            
            // Vorbelegung Jahrgang:
            jahrgangText.setText(momentanesJahr +"");
            
            // Vorbelegung Lagerdauer:
            dauerText.setText(LAGERDAUERSTART);
        
            // Beschreibung textumbruch:
            beschreibung.setWrapText(true);
            
            // Land und Region setzen:
            anbauLand.setValue(STARTLAND);
            anbauRegion.setValue(STARTREGION);
        
            // Alkoholgehalt Slider:
            alkoholGehalt.setText(TEXTSTARTWERT);
            alkoholgehaltSlider.setDisable(true);
            alkoholgehaltSlider.setValue(STARTALKOHOLGEHALT);
            alkohol = (double) alkoholgehaltSlider.getValue();
            alkoholgehaltSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                    alkohol = (double) alkoholgehaltSlider.getValue();
                    alkoholGehalt.setText(nf2.format(alkoholgehaltSlider.getValue()));
                }                
            });
            
            // Array mit den String Werten befüllen:
            umrechnen.erstelleArray();
            
            // Einlesen der FlaschenAuswahl:
            auswahlFlaschenGroesse.getItems().addAll(umrechnen.flaschenString);
            auswahlFlaschenGroesse.setOnAction(this::gibFlaschenGroesse);
            
            // Einlesen der Länder:
            anbauLand.getItems().addAll(anbau.laender);
            anbauRegion.getItems().addAll(anbau.deutschland);
            anbauLand.setOnAction(this::gibAnbauRegion);
            
            // Nachkommastellen der Flaschen Auswahl:
            flaschenNF.setMaximumFractionDigits(NACHKOMMASTELLEN_FLASCHE_MAX);
            flaschenNF.setMinimumFractionDigits(NACHKOMMASTELLEN_FLASCHE_MIN); 
            
            // Button vor der Eingabe deaktivieren:
            buttonNetto.setDisable(true);
            buttonBrutto.setDisable(true);
                
            // Vorauswahl der ChoiceBox:
            auswahlFlaschenGroesse.setValue(STARTWERT);
            
            // Setzten der möglichen Nachkommastellen der Ergebnisse:
            nf.setMaximumFractionDigits(NACHKOMMASTELLEN);
            nf.setMinimumFractionDigits(NACHKOMMASTELLEN); 
            
            // Setzten der möglichen Nachkommastellen des Sliders:
            nf2.setMaximumFractionDigits(NACHKOMMASTELLEN2);
            nf2.setMinimumFractionDigits(NACHKOMMASTELLEN2); 
            
            // Verhindern von Falschen Eingaben für Netto Flaschenpreis:
            this.flaschenPreisNetto.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {                
                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {
                    if (!t.getControlNewText().matches(INPUT_LEX)) {
                        Toolkit.getDefaultToolkit().beep();
                        return null;
                    }
                    // Freischalten des Button:
                    if(flaschenPreisNetto.getText().length() > TEXTLEANGE) {
                        buttonNetto.setDisable(false);
                    }
                    if(flaschenPreisNetto.getText().length() > 0) {
                            flaschenPreisNetto.setStyle(STYLE_WHITE);
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
                            meldung.fehlermeldungMitFeld(flaschenPreisNetto, 
                                    meldung.ERROR_TITLE_NO_NUMBER, 
                                    meldung.ERROR_TEXT_NO_NUMBER);
                        }
                        
                        try {
                            if(flaschenPreisNetto.getText().length() > 0) {
                                berechneMitNettoFlaschenPreis();
                            } else {
                                preisProLiterNetto.setText("");
                                preisProLiterBrutto.setText("");
                                flaschenPreisBrutto.setText("");
                            }                          
                        } catch(Exception e) {
                            meldung.fehlermeldungOhneFeld(meldung.ERROR_TITLE_PROGRAMM,
                                    meldung.ERROR_TEXT_PROGRAMM);
                        }
                        //Prüfung für Abbrechen:
                        if(flaschenPreisNetto.getText().length() > 0) {
                            aenderungen = true;
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
                    if(preisProLiterNetto.getText().length() > TEXTLEANGE) {
                        buttonBrutto.setDisable(false);
                    } 
                    if(preisProLiterNetto.getText().length() > 0) {
                            preisProLiterNetto.setStyle(STYLE_WHITE);
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
                            meldung.fehlermeldungMitFeld(preisProLiterNetto, 
                                    meldung.ERROR_TITLE_NO_NUMBER, 
                                    meldung.ERROR_TEXT_NO_NUMBER);
                        }
                        
                        try {
                            if(preisProLiterNetto.getText().length() > 0){
                                berechneMitNettoPreisProLiter();
                            } else {
                                preisProLiterBrutto.setText("");
                                flaschenPreisBrutto.setText("");
                                flaschenPreisNetto.setText("");
                            }
                        } catch(Exception e) {
                            meldung.fehlermeldungOhneFeld(meldung.ERROR_TITLE_PROGRAMM,
                                    meldung.ERROR_TEXT_PROGRAMM);
                        }
                        //Prüfung für Abbrechen:
                        if(preisProLiterNetto.getText().length() > 0) {
                            aenderungen = true;
                        }
                    }                    
                }
            });
            // Verhindern von Falschen Eingaben für Brutto Flaschenpreis:
            this.flaschenPreisBrutto.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {
                    if (!t.getControlNewText().matches(INPUT_LEX)) {
                        Toolkit.getDefaultToolkit().beep();
                        return null;
                    }
                    // Freischalten des Button:
                    if(flaschenPreisBrutto.getText().length() > TEXTLEANGE) {
                        buttonNetto.setDisable(false);
                    }
                    if(flaschenPreisBrutto.getText().length() > 0) {
                            flaschenPreisBrutto.setStyle(STYLE_WHITE);
                    }
                    return t;
                }            
            }));
            // Prüfung des Eingabefelds beim Verlassen:    
            this.flaschenPreisBrutto.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        String eingabe = flaschenPreisBrutto.getText();
                        if (!(eingabe.matches(INPUT_SYNTAX_WITH_GROUPING) || (eingabe.matches(INPUT_SYNTAX_WITHOUT_GROUPING)))) {
                            Toolkit.getDefaultToolkit().beep();
                            meldung.fehlermeldungMitFeld(flaschenPreisBrutto, 
                                    meldung.ERROR_TITLE_NO_NUMBER, 
                                    meldung.ERROR_TEXT_NO_NUMBER);
                        }
                        
                        try {
                            if(flaschenPreisBrutto.getText().length() > 0){
                                berechneMitBruttoFlaschenPreis();
                            } else {
                                preisProLiterNetto.setText("");
                                preisProLiterBrutto.setText("");
                                flaschenPreisNetto.setText("");
                            }   
                        } catch(Exception e) {
                            meldung.fehlermeldungOhneFeld(meldung.ERROR_TITLE_PROGRAMM,
                                    meldung.ERROR_TEXT_PROGRAMM);
                        }
                        //Prüfung für Abbrechen:
                        if(flaschenPreisBrutto.getText().length() > 0) {
                            aenderungen = true;
                        }
                    }
                }
            });
        
            // Verhindern von Falschen Eingaben für Brutto Preis pro Liter:
            this.preisProLiterBrutto.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {                
                    if (!t.getControlNewText().matches(INPUT_LEX)) {
                        Toolkit.getDefaultToolkit().beep();
                        return null;
                    }
                    // Freischalten des Button:
                    if(preisProLiterBrutto.getText().length() > TEXTLEANGE) {
                        buttonBrutto.setDisable(false);
                    } 
                    if(preisProLiterBrutto.getText().length() > 0) {
                            preisProLiterBrutto.setStyle(STYLE_WHITE);
                    }
                    return t;
                }
            }));
            // Prüfung des Eingabefelds beim Verlassen:
            this.preisProLiterBrutto.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        String eingabe = preisProLiterBrutto.getText();
                        if (!(eingabe.matches(INPUT_SYNTAX_WITH_GROUPING) || (eingabe.matches(INPUT_SYNTAX_WITHOUT_GROUPING)))) {
                            Toolkit.getDefaultToolkit().beep();
                            meldung.fehlermeldungMitFeld(preisProLiterBrutto, 
                                    meldung.ERROR_TITLE_NO_NUMBER, 
                                    meldung.ERROR_TEXT_NO_NUMBER);
                        }
                        
                        try {
                            if(preisProLiterBrutto.getText().length() > 0){
                               berechneMitBruttoPreisProLiter();
                            } else {
                                preisProLiterNetto.setText("");
                                flaschenPreisBrutto.setText("");
                                flaschenPreisNetto.setText("");
                            }
                            
                        } catch(Exception e) {
                            meldung.fehlermeldungOhneFeld(meldung.ERROR_TITLE_PROGRAMM,
                                    meldung.ERROR_TEXT_PROGRAMM);
                        }
                        //Prüfung für Abbrechen:
                        if(preisProLiterBrutto.getText().length() > 0) {
                            aenderungen = true;
                        }                        
                    }                    
                }
            });
            
            // Verhindern von Falschen Eingaben für jahrgang:
            this.jahrgangText.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {                
                    if (!t.getControlNewText().matches(INPUT_LEX_DIAGRAMM)) {
                        Toolkit.getDefaultToolkit().beep();
                        return null;
                    } 
                    return t;
                }
            }));
            // Prüfung des Eingabefelds beim Verlassen:
            this.jahrgangText.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        // Prüfung ob Jahr in der Zukunft liegt:
                        if(jahrgangText.getText().length() >= 4) {
                        int jahr = Integer.parseInt(jahrgangText.getText());
                        
                            if(jahr > momentanesJahr) {
                                Toolkit.getDefaultToolkit().beep();
                                meldung.fehlermeldungMitFeld(jahrgangText, 
                                        meldung.ERROR_TITLE_JAHR, 
                                        meldung.ERROR_TEXT_JAHR);
                            }
                        }
                        // Syntax prüfen:
                        String eingabe = jahrgangText.getText();
                        if (jahrgangText.getText().length() != 0 && (jahrgangText.getText().length() < 4 || jahrgangText.getText().length() > 4)) {
                            Toolkit.getDefaultToolkit().beep();
                            meldung.fehlermeldungMitFeld(jahrgangText, 
                                    meldung.ERROR_TITLE_JAHRGANG, 
                                    meldung.ERROR_TEXT_JAHRGANG);
                        }
                        //Prüfung für Abbrechen:
                        if(!jahrgangText.getText().equals(momentanesJahr+"")) {
                            aenderungen = true;
                        }
                        
                    }
                }
            });
            
            // Verhindern von Falschen Eingaben für lagerdauer:
            this.dauerText.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {                
                    if (!t.getControlNewText().matches(INPUT_LEX_DIAGRAMM)) {
                        Toolkit.getDefaultToolkit().beep();
                        return null;
                    } 
                    return t;
                }
            }));
            // Prüfung des Eingabefelds beim Verlassen:
            this.dauerText.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        String eingabe = dauerText.getText();
                        if (dauerText.getText().length() != 0 && (dauerText.getText().length() > 3)) {
                            Toolkit.getDefaultToolkit().beep();
                            meldung.fehlermeldungMitFeld(dauerText, 
                                    meldung.ERROR_TITLE_LAGERDAUER, 
                                    meldung.ERROR_TEXT_LAGERDAUER);
                        }
                        //Prüfung für Abbrechen:
                        if(dauerText.getText().length() > 0) {
                            aenderungen = true;
                        }
                    }
                }
            });
            
            // Verhindern von Falschen Eingaben für Bestellnummer:
            this.bestellnummer.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {
                    if (!t.getControlNewText().matches(INPUT_LEX_BESTELL)) {
                        Toolkit.getDefaultToolkit().beep();
                        return null;
                    } 
                    return t;
                }
            }));
            // Prüfung des Eingabefelds beim Verlassen:
            this.bestellnummer.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    // KleinBuchstaben in Großbuchstaben beim wechseln des Feldes Wandeln:
                    if(bestellnummer.getText().length() == 13) {
                        bestellnummer.setText(bestellnummer.getText().toUpperCase());
                    }
                    // Prüfung des Syntax:
                    if (!newValue) {
                        String eingabe = bestellnummer.getText();
                        if (!(eingabe.matches(INPUT_BESTELL))&& eingabe.length() > 0) {
                            Toolkit.getDefaultToolkit().beep();
                            meldung.fehlermeldungMitFeld(bestellnummer, 
                                    meldung.ERROR_TITLE_BESTELL, 
                                    meldung.ERROR_TEXT_BESTELL);
                        }
                        
                        //Prüfung für Abbrechen:
                        if(!bestellnummer.getText().equals(momentanesJahr+"-")) {
                            aenderungen = true;
                        }
                    }
                }
            });
                        
            // Prüfung ob Alkoholgehalt gleich null Prozent:
            this.alkoholnullprozent.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    // Prüfung für Abbrechen:
                    if(newValue) {
                        aenderungen = true;
                    }
                }
            });
            
             //Verhindern von Falschen Eingaben für Alkoholgehalt:
            this.alkoholGehalt.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                @Override
                public TextFormatter.Change apply(TextFormatter.Change t) {                
                    if (!t.getControlNewText().matches(INPUT_LEX_ALKOHOL)) {
                        Toolkit.getDefaultToolkit().beep();
                        return null;
                    } 
                    return t;
                }
            }));
            // Prüfung des Eingabefelds Alkoholgehalt beim Verlassen:    
            this.alkoholGehalt.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {                        
                        String eingabe = alkoholGehalt.getText();
                        if (eingabe.length() > 0) {
                            
                        
                        if(!(eingabe.substring(eingabe.length()-1,eingabe.length()).equals("0") || eingabe.substring(eingabe.length()-1,eingabe.length()).equals("5"))) {
                            Toolkit.getDefaultToolkit().beep();
                            meldung.fehlermeldungMitFeld(alkoholGehalt, 
                                    meldung.ERROR_TITLE_ALKOHOL, 
                                    meldung.ERROR_TEXT_ALKOHOL);
                        } else if(alkoholgehalt.getText().length() >0) {
                            String speicher = eingabe.replace(",", ".");
                            double pruefe = Double.parseDouble(speicher);
                            if(pruefe > 25.0 || pruefe < 7) {
                                Toolkit.getDefaultToolkit().beep();
                                meldung.fehlermeldungMitFeld(alkoholGehalt, 
                                        meldung.ERROR_TITLE_ALKOHOL, 
                                        meldung.ERROR_TEXT_ALKOHOL);
                            } else {
                                try {
                                    alkoholgehaltSlider.setValue(nf2.parse(alkoholGehalt.getText()).doubleValue());
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            } 
                        }  
                        
                        }
                        // Prüfung für Abbrechen
                        if(!alkoholGehalt.getText().equals(STARTALKOHOLGEHALT)) {
                            aenderungen = true;
                        }
                        
                                                
                    }
                }
            });
            
            //Prüfen ob eingabe in Weinbeschreibung:
            this.beschreibung.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    //Prüfung für Abbrechen:
                    if(beschreibung.getText().length() > 0) {
                        aenderungen = true;
                    }
                }
            });
            //Prüfen ob veränderung Slider:
            this.alkoholgehaltSlider.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    alkoholGehalt.setText(nf2.format(alkoholgehaltSlider.getValue()));
                    
                    if(newValue) {
                        aenderungen = true;
                    }
                }
            });
            //Prüfen ob veränderung Land:
            this.anbauLand.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if(!anbauLand.getValue().equals(STARTLAND)) {
                        aenderungen = true;
                    }
                }
            });
            //Prüfen ob veränderung Region:
            this.anbauRegion.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if(!anbauRegion.getValue().equals(STARTREGION)) {
                        aenderungen = true;
                    }
                }
            });
            
            //Prüfen ob veränderung Weinnanme:
            this.weinname.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if(weinname.getText().length() > 0) {
                        aenderungen = true;
                    }
                }                
            });
            //Prüfen ob veränderung Flaschengröße:
            this.auswahlFlaschenGroesse.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if(!auswahlFlaschenGroesse.getValue().equals(STARTWERT)) {
                        aenderungen = true;
                    }
                }
            });
            //Prüfen ob Slider verwendet werden soll:
            this.slideraktivieren.focusedProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                    if(newValue) {
                        aenderungen = true;
                    }
                }
            });
            
            
        } catch(Exception e) {            
            // Fehlermeldung bei Fehlschlag:
//            meldung.fehlermeldungOhneFeld(meldung.ERROR_TITLE_PROGRAMM,
//                    meldung.ERROR_TEXT_PROGRAMM);            
            e.printStackTrace();
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
            // Index aus Array:            
            int index = INDEXSTARTWERT;
            String flaschenAngabe = auswahlFlaschenGroesse.getValue();
            for(int i = 0; i < umrechnen.flaschenString.length; i++) {
                if(flaschenAngabe.equals(umrechnen.flaschenString[i])) {
                    i = STOPWERT;
                }
                index = index + ZAEHLER;
            }
            
            /**
             * Überprüfung ob alle Felder wieder Leer sind Wenn ja dann 
             * keine Fehlermeldung bei Auswahl Flasche.
             * 
             * Wenn Felder nicht leer und einer der Button wurde betätigt dann 
             * Berechnung der jeweiligen Werte Ausführen.
             */            
            if(flaschenPreisNetto.getText().isEmpty() && preisProLiterNetto.getText().isEmpty()) {
                literAngabe = umrechnen.flaschen[index-VORHERIGERWERT];
                
            } else if(flaschenPreisNetto.getText().isEmpty() && preisProLiterNetto.getText().length() > 0) {
                literAngabe = umrechnen.flaschen[index-VORHERIGERWERT];
                berechneMitNettoPreisProLiter();
                
            }  else if(flaschenPreisNetto.getText().length() > 0 && preisProLiterNetto.getText().isEmpty()) {
                literAngabe = umrechnen.flaschen[index-VORHERIGERWERT];
                berechneMitNettoFlaschenPreis();  
                
            } else if(richtung.wert.equals("oben")){
                literAngabe = umrechnen.flaschen[index-VORHERIGERWERT];
                berechneMitNettoPreisProLiter();
           
            } else if(richtung.wert.equals("unten")) {
                literAngabe = umrechnen.flaschen[index-VORHERIGERWERT];
                berechneMitNettoFlaschenPreis();
           
            } else {
                literAngabe = umrechnen.flaschen[index-VORHERIGERWERT];
            }
       
        }   catch (Exception e) {            
            // Ergebnisfelder leerren:
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei leerem Feld:
            meldung.fehlermeldungMitFeld(this.preisProLiterNetto, 
                    meldung.ERROR_TITLE_NO_NUMBER, 
                    meldung.ERROR_TEXT_NO_NUMBER);
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
            nettoPreisFlasche = nf.parse(flaschenPreisNetto.getText()).doubleValue();
            
            // Berechnung Netto Preis pro Liter:
            preisProLiter = nettoPreisFlasche / literAngabe;
                
            // Berechnung der Brutto Preise:
            bruttoFlasche =  (nettoPreisFlasche / TEILER) * MEHRWERTSTEUER 
                + nettoPreisFlasche;
            bruttoLiter = (preisProLiter / TEILER) * MEHRWERTSTEUER 
                + preisProLiter;
                
        } catch (ParseException e) {            
            // Ergebnisfelder leerren:
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei leerem Feld:
            meldung.fehlermeldungMitFeld(this.preisProLiterNetto, 
                    meldung.ERROR_TITLE_NO_NUMBER, 
                    meldung.ERROR_TEXT_NO_NUMBER);
            return;
        }
        // ausgabe Formatieren:
        preisProLiterNetto.setText(nf.format(preisProLiter));
        flaschenPreisBrutto.setText(nf.format(bruttoFlasche));
        preisProLiterBrutto.setText(nf.format(bruttoLiter));  
        // Den aktuell eingetragenen Text holen:
        speicherPreisLiterNetto = preisProLiterNetto.getText();
    }
    
    @FXML
    private void richtungMitNettoFlaschenPreis() throws ParseException {        
        // Festlegung der verwendeten Funktion:
        richtung.unten();   
        // Weinpreise Flaschen Preis Netto:
        if(bFlaschenPreisNetto && flaschenPreisNetto.getText().length() > 0) {
            flaschenPreisNetto.setStyle(STYLE_WHITE);
            bFlaschenPreisNetto = false;
        }
        // Felder Umrechner:
        flaschenPreisBrutto.setText("");
        preisProLiterNetto.setText("");
        preisProLiterBrutto.setText("");
    }
    
    @FXML
    private void berechneMitBruttoFlaschenPreis() throws ParseException {
        try {
            // Den aktuellen Text Speichern:
            speicherPreisBrutto = flaschenPreisBrutto.getText();            
            bruttoPreisFlasche = nf.parse(flaschenPreisBrutto.getText()).doubleValue();            
            nettoPreisFlasche = bruttoPreisFlasche / MEHRWERTSTEUER_BRUTTO;
            flaschenPreisNetto.setText(nf.format(nettoPreisFlasche));
            
            //berechneMitNettoFlaschenPreis();
            nettoPreisFlasche = nf.parse(flaschenPreisNetto.getText()).doubleValue();
            
            // Berechnung Netto Preis pro Liter:
            preisProLiter = nettoPreisFlasche / literAngabe;
                
            // Berechnung der Brutto Preise:
            bruttoFlasche =  (nettoPreisFlasche / TEILER) * MEHRWERTSTEUER 
                + nettoPreisFlasche;
            bruttoLiter = (preisProLiter / TEILER) * MEHRWERTSTEUER 
                + preisProLiter;
                
        } catch (ParseException e) {            
            // Ergebnisfelder leerren:
            this.preisProLiterNetto.clear(); 
            this.flaschenPreisNetto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei leerem Feld:
            meldung.fehlermeldungMitFeld(this.preisProLiterNetto, 
                    meldung.ERROR_TITLE_NO_NUMBER, 
                    meldung.ERROR_TEXT_NO_NUMBER);
            return;
        }
        // ausgabe Formatieren:
        preisProLiterNetto.setText(nf.format(preisProLiter));
        preisProLiterBrutto.setText(nf.format(bruttoLiter));          
    }
    
    @FXML
    private void richtungMitBruttoFlaschenPreis() throws ParseException {        
        // Festlegung der verwendeten Funktion:
        richtung.unten(); 
        // Flaschen Preis Brutto:
        if(bFlaschenPreisBrutto && flaschenPreisBrutto.getText().length() > 0) {
            flaschenPreisBrutto.setStyle(STYLE_WHITE);                       
            bFlaschenPreisBrutto = false;
        }
        // Felder Umrechner:
        flaschenPreisNetto.setText("");
        preisProLiterNetto.setText("");
        preisProLiterBrutto.setText("");
    }
    
    /**
     * 
     * @throws IOException 
     */
    @FXML
    private void berechneMitNettoPreisProLiter() throws ParseException {
        try {
            // Den aktuellen Text Speichern:
            speicherPreisLiterNetto = preisProLiterNetto.getText();            
            preisProLiter = nf.parse(preisProLiterNetto.getText()).doubleValue();
            
            // Berechnung Netto Preis pro Flasche: 
            nettoPreisFlasche =  literAngabe * preisProLiter;
                
            // Berechnung der Brutto Preise:
            bruttoFlasche =  (nettoPreisFlasche / TEILER) * MEHRWERTSTEUER 
                + nettoPreisFlasche;
            bruttoLiter = (preisProLiter / TEILER) * MEHRWERTSTEUER 
                + preisProLiter;
            
        } catch (ParseException e) {
            // Ergebnisfelder leerren:
            this.flaschenPreisNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterBrutto.clear();
            // Fehlermeldung bei leerem Feld;
            meldung.fehlermeldungMitFeld(this.preisProLiterNetto, 
                    meldung.ERROR_TITLE_NO_NUMBER, 
                    meldung.ERROR_TEXT_NO_NUMBER);
            return;
        } 
        // ausgabe Formatieren:
        flaschenPreisNetto.setText(nf.format(nettoPreisFlasche));
        flaschenPreisBrutto.setText(nf.format(bruttoFlasche));
        preisProLiterBrutto.setText(nf.format(bruttoLiter));
        // Den aktuell eingetragenen Text holen:
        speicherPreisNetto = flaschenPreisNetto.getText();
    }
    
    @FXML
    private void richtungMitNettoPreisProLiter() throws ParseException {        
        // Festlegung der verwendeten Funktion:
        richtung.oben(); 
        // Preis pro Liter Netto:
        if(bPreisProLiterNetto && preisProLiterNetto.getText().length() > 0) {
            preisProLiterNetto.setStyle(STYLE_WHITE);                      
            bPreisProLiterNetto = false;
        }
        // Felder Umrechner:
        flaschenPreisNetto.setText("");
        flaschenPreisBrutto.setText("");
        preisProLiterBrutto.setText("");
    }
    
    @FXML
    private void berechneMitBruttoPreisProLiter() throws ParseException {
        try {
            // Den aktuellen Text Speichern:
            speicherPreisLiterBrutto = preisProLiterBrutto.getText();            
            bruttoPreisProLiter = nf.parse(preisProLiterBrutto.getText()).doubleValue();            
            preisProLiter = bruttoPreisProLiter / MEHRWERTSTEUER_BRUTTO;
            preisProLiterNetto.setText(nf.format(preisProLiter));
            
            //berechneMitNettoPreisProLiter();
            preisProLiter = nf.parse(preisProLiterNetto.getText()).doubleValue();
            
            // Berechnung Netto Preis pro Flasche: 
            nettoPreisFlasche =  literAngabe * preisProLiter;
                
            // Berechnung der Brutto Preise:
            bruttoFlasche =  (nettoPreisFlasche / TEILER) * MEHRWERTSTEUER 
                + nettoPreisFlasche;
            bruttoLiter = (preisProLiter / TEILER) * MEHRWERTSTEUER 
                + preisProLiter;
            
        } catch (ParseException e) {
            // Ergebnisfelder leerren:
            this.flaschenPreisNetto.clear(); 
            this.flaschenPreisBrutto.clear();
            this.preisProLiterNetto.clear();
            // Fehlermeldung bei leerem Feld;
            meldung.fehlermeldungMitFeld(this.preisProLiterBrutto, 
                    meldung.ERROR_TITLE_NO_NUMBER, 
                    meldung.ERROR_TEXT_NO_NUMBER);
            return;
        } 
        // ausgabe Formatieren:
        flaschenPreisNetto.setText(nf.format(nettoPreisFlasche));
        flaschenPreisBrutto.setText(nf.format(bruttoFlasche));
        // Den aktuell eingetragenen Text holen:
        speicherPreisNetto = flaschenPreisNetto.getText();
    }
    
    @FXML
    private void richtungMitBruttoPreisProLiter() throws ParseException {        
        // Festlegung der verwendeten Funktion:
        richtung.oben(); 
        // Preis pro Liter Brutto:
        if(bPreisProLiterBrutto && preisProLiterBrutto.getText().length() > 0) {
            preisProLiterBrutto.setStyle(STYLE_WHITE);                         
            bPreisProLiterBrutto = false;
        }
        // Felder Umrechner:
        flaschenPreisNetto.setText("");
        flaschenPreisBrutto.setText("");
        preisProLiterNetto.setText("");
        
    }
    
    
    /**
     * Wird verwendet um die Felder zu leeren sobald eine neue Zahl eingegeben 
     * wird, in dem Feld preisProLiterNetto.
     */
    public void leereFelderFlaschenpreis() {
        try {            
            if(!preisProLiterNetto.getText().equals(speicherPreisLiterNetto)) {
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
            meldung.fehlermeldungMitFeld(preisProLiterNetto, 
                    meldung.ERROR_TITLE_PROGRAMM, 
                    meldung.ERROR_TEXT_PROGRAMM);
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
            meldung.fehlermeldungMitFeld(preisProLiterNetto, 
                    meldung.ERROR_TITLE_PROGRAMM, 
                    meldung.ERROR_TEXT_PROGRAMM);
            return;                                    
        }
    }
        
    /**
     * Funktion zum erstellen des Reifediagramms:
     */
    @FXML
    private void switchToDiagramm(/*ActionEvent event*/) throws IOException {
        try {            
            // Jahresangaben Zurücksetzten:
            jahreZuruecksetzten();
            
            // Versuche den eingabe Wert in einen Integer zu Paresen
            jahr = Integer.parseInt(jahrgangText.getText());
            dauer = Integer.parseInt(dauerText.getText());
            
            // Jahresanzeige:
            setzteJahr(jahr, dauer);
            
            // Lggerdauer und Jahgang anzeigen:
            jahrgang.setText("Jahrgang: "+ jahr);
            lagerdauer.setText("Lagerdauer: "+ dauer + " Jahre");
            
            // Aufruf Diagramm Berechnung:
            diagramm.diagrammBerechnung(jahr, dauer);
            
            // Festlegeung der Größe des Startjahres
            startJahr.setWidth(diagramm.START);
        
            // Setzen der jeweiligen Farbgröße
            rectZuFrueh.setWidth(diagramm.groeßeZuFrueh);
            rectSteigeungsfaehig.setWidth(diagramm.groeßeSteigeungsfaehig);
            rectOptimal.setWidth(diagramm.groeßeOptimal);
            rectLaesstNach.setWidth(diagramm.groeßeLaesstNach);
            rectUngenießbar.setWidth(diagramm.GROESEUNGENIESBAR);
                        
        } catch(NumberFormatException e) {            
            // Ergebnisfelder leerren:
            jahrgangText.clear();
            dauerText.clear();                        
            // Fehlermeldung bei Fehlschlag:
            meldung.fehlermeldungOhneFeld(meldung.ALERT_TITLE, 
                    meldung.ALERT_CONTENT_TEXT);
            
            // Tab Wechseln:
            SelectionModel<Tab> selectionModel = scenePane.getSelectionModel();
            selectionModel.select(0);
            
            return;   
        } catch(Exception e) {
            System.out.println(e);
        }       
    }
    
    /**
     * Festlegung des Start Jahres und des End Jahres je nach eingabe wird der 
     * Text in einem der Label unterhalb der Rechtecke angezeigt.
     * @param jahr 
     * @param dauer 
     */
    public void setzteJahr(int jahr, int dauer) {       
       aktuellesJahr.setText(Integer.toString(calendar.get(calendar.YEAR)));
       
       // Aufruf Klasse Reifediagramm für Jahresberechnung:
       diagramm.jahresBerechnung();
       
       // Für die das äußere Startjahr
       if(jahr < diagramm.jahrAnfang && (jahr + dauer+ABSCHLUSSJAHR) >= diagramm.jahrAnfang) {
           jahrStartRand.setText(Integer.toString(jahr));
       } else if(jahr < diagramm.jahrAnfang && (jahr + dauer+ABSCHLUSSJAHR) < diagramm.jahrAnfang) {
           jahrStartRand.setText(Integer.toString(jahr + dauer+ABSCHLUSSJAHR));
       }
       // Für das äußere Endjahr
       if(jahr < diagramm.jahrEnde && (jahr + dauer+ABSCHLUSSJAHR) > diagramm.jahrEnde) {
           jahrEndRand.setText(Integer.toString(jahr + dauer+ABSCHLUSSJAHR));
       } else if(jahr > diagramm.jahrEnde && (jahr + dauer+ABSCHLUSSJAHR) > diagramm.jahrEnde) {
           jahrEndRand.setText(Integer.toString(jahr + dauer+ABSCHLUSSJAHR));
       }
       // Für die Jahre zwischen dem äußeren End- und Startjahr
       if(jahr >= diagramm.jahrAnfang && jahr <= diagramm.jahrEnde) {
           jahrFinden(jahr - diagramm.jahrAnfang);
           jahrFinden(jahr - diagramm.jahrAnfang + dauer +ABSCHLUSSJAHR);
       } else if(jahr < diagramm.jahrAnfang && jahr <= diagramm.jahrEnde) {
           jahrFinden(jahr - diagramm.jahrAnfang + dauer +ABSCHLUSSJAHR);
       } else if(jahr >= diagramm.jahrAnfang && jahr >= diagramm.jahrEnde) {
           jahrFinden(jahr - diagramm.jahrAnfang);
       }
    }
    /**
     * ArrayList mit Labeln durchsuchen und Text setzten:
     * @param jahresNummer 
     */
    public void jahrFinden(int jahresNummer) {
        // Label der ArrayList hinzufügen:
        jahresLabel.add(jahresAnzeige0);
        jahresLabel.add(jahresAnzeige1);
        jahresLabel.add(jahresAnzeige2);
        jahresLabel.add(jahresAnzeige3);
        jahresLabel.add(jahresAnzeige4);
        jahresLabel.add(aktuellesJahr);
        jahresLabel.add(jahresAnzeige5);
        jahresLabel.add(jahresAnzeige6);
        jahresLabel.add(jahresAnzeige7);
        jahresLabel.add(jahresAnzeige8);
        jahresLabel.add(jahresAnzeige9);
        
        // Label suchen und Text ändern:
        for(int i = 0; i < jahresLabel.size(); i++) {
            if(i == jahresNummer) {
                jahresLabel.get(i).setText(Integer.toString(diagramm.jahrAnfang + jahresNummer));
                i = jahresLabel.size();
            }
        }
    }  
    
    /**
     * Die Jahresangaben müssen zu beginn zurückgesetzt werden anderenfalls 
     * bleiben die Alten Jahresdaten bestehen.
     */
    public void jahreZuruecksetzten() {
        jahrStartRand.setText(null);
        jahrEndRand.setText(null);
        jahresAnzeige0.setText(null);
        jahresAnzeige1.setText(null);
        jahresAnzeige2.setText(null);
        jahresAnzeige3.setText(null);
        jahresAnzeige4.setText(null);
        jahresAnzeige5.setText(null);
        jahresAnzeige6.setText(null);
        jahresAnzeige7.setText(null);
        jahresAnzeige8.setText(null);
        jahresAnzeige9.setText(null);
    }
    
    /**
     * Gibt die passende Anbauregion:
     */
    public void gibAnbauRegion(ActionEvent event) {
        // Feld Region leeren:
        for(int j = 0; j < anbau.region.length; j++) {
            anbauRegion.getItems().removeAll(anbau.region[j]);
        }
        // Feld Region befüllen:
        for(int i = 0; i < anbau.laender.length; i++) {
            if(anbauLand.getValue().equals(anbau.laender[i])) {
                anbauRegion.getItems().addAll(anbau.region[i]);
                anbauRegion.setValue(anbau.region[i][0]);
            }
        }
    }    
    
    public void farbauswahl(ActionEvent event) {
        if(farbeRot.isSelected()) {
            farbauswahl = "Rot";
        } else if(farbeWeiss.isSelected()) {
            farbauswahl = "Weiß";
        } else if(farbeRose.isSelected()) {
            farbauswahl = "Rose";
        }            
    }
    
    /**
     * Button Abbrechen:
     * @param event
     */
    public void beenden(ActionEvent event) {
        if(aenderungen) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(meldung.TITLE_ALERT);
            alert.setHeaderText(meldung.HEADER_ALERT);
            alert.setContentText(meldung.CONTEXT_ALERT);
            
        
            ButtonType ja = new ButtonType("Ja");
            ButtonType nein = new ButtonType("Nein");
        
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ja, nein);
        
            if(alert.showAndWait().get() == ja/*ButtonType.YES*/) {
                stage = (Stage) scenePane.getScene().getWindow();            
                System.out.println(NACHRICHT_BEENDEN);
                stage.close();
            }
        } else {
            stage = (Stage) scenePane.getScene().getWindow(); 
            System.out.println(NACHRICHT_BEENDEN);
            stage.close();
        }
    }
    
    public void nullProzentAlkohol(ActionEvent event) {
        if(alkoholnullprozent.isSelected()) {
            alkoholGehalt.setDisable(true);
            slideraktivieren.setSelected(false);
            alkoholgehaltSlider.setDisable(true);
            slideraktivieren.setDisable(true);            
                     
        } else {
            alkoholGehalt.setDisable(false);            
            slideraktivieren.setDisable(false);
        }
        
    }
    
    public void alkoholSliderAktivieren(ActionEvent event) {
        if(slideraktivieren.isSelected()) {
            alkoholgehaltSlider.setDisable(false);
            alkoholGehalt.setDisable(true);
        } else {
            alkoholgehaltSlider.setDisable(true);
            alkoholGehalt.setDisable(false);
            alkoholgehaltAnzeige.setText("");
        }
    }
    
    
    /**
     * Button Speichern:
     * @param event
     */
    public void speichern(ActionEvent event) {
        try {
            // Felder der ArrayList hinzufügen:
            feldliste.add(bestellnummer);
            feldliste.add(weinname);
            feldliste.add(jahrgangText);
            feldliste.add(dauerText);
            feldliste.add(flaschenPreisNetto);
            feldliste.add(preisProLiterNetto);
            feldliste.add(flaschenPreisBrutto);
            feldliste.add(preisProLiterBrutto);
            feldliste.add(alkoholGehalt);
            
            // Prüfen der Felder und einfärbung der nicht gefüllten:
            for(int i = 0; i < feldliste.size(); i++) {
                if(bestellnummer.getText().length() < 13) {
                    bestellnummer.setStyle(STYLE_RED);
                    bBestellnummer = true;
                }
                
                if(feldliste.get(i).getText().isEmpty()) {
                    feldliste.get(i).setStyle(STYLE_RED);
                    
                    if(i == 0) {
                        bBestellnummer = true;
                    } else if(i == 1) {
                        bWeinname = true;
                    } else if(i == 2) {
                        bJahrgangText = true;
                    } else if(i == 3) {
                        bDauerText = true;
                    } else if(i == 4) {
                        bFlaschenPreisNetto = true;
                    } else if(i == 5) {
                        bPreisProLiterNetto = true;
                    }  else if(i == 6) {
                        bFlaschenPreisBrutto = true;
                    }  else if(i == 7) {
                        bPreisProLiterBrutto = true;
                    } else if(i == 8) {
                        bAlkoholGehalt = true;
                    }
                    
                } else {
                    feldliste.get(i).setStyle(STYLE_WHITE);
                } 
            }
            
            // Prüfen ob eine Fehlermeldung angezeigt werden muss und Focus:
            int zaehler = 0;
            for(int j = 0; j < feldliste.size(); j++) {
                
                if(feldliste.get(j).getText().isEmpty() || bBestellnummer) {
                    Toolkit.getDefaultToolkit().beep();
                    meldung.fehlermeldungMitFeld(feldliste.get(j), 
                        meldung.ERROR_TITLE_SPEICHERN, 
                        meldung.ERROR_TEXT_SPEICHERN);
                    j = j + feldliste.size();
                } else {
                    zaehler = zaehler + 1;
                }
                
                if(zaehler == feldliste.size()) {
                    String sAlkoholgehalt;
                    if(alkoholnullprozent.isSelected()) {
                        sAlkoholgehalt = "0%";
                    } else if(slideraktivieren.isSelected()) {
                        sAlkoholgehalt = alkoholGehalt.getText()+"%";
                    } else {
                        sAlkoholgehalt = alkoholGehalt.getText()+"%";
                    }
                    
                    ergebnis = new Wein(bestellnummer.getText(), 
                            farbauswahl, weinname.getText(), 
                            anbauLand.getValue(), 
                            anbauRegion.getValue(), jahrgangText.getText(),
                            dauerText.getText(), auswahlFlaschenGroesse.getValue(),
                            flaschenPreisBrutto.getText(),
                            sAlkoholgehalt, beschreibung.getText());                    
                    
                    MainWindowController.weinliste.add(ergebnis);
                    System.out.println(ergebnis.toString());
                    startWerte();
                } 
            }   
            
        } catch(Exception e) {            
            // Fehlermeldung bei Fehlschlag:
            meldung.fehlermeldungOhneFeld(meldung.ERROR_TITLE_PROGRAMM, 
                    meldung.ERROR_TEXT_PROGRAMM);            
            return;
        }
    }
    
    public void feldFarbeSetzten() {
        // Bestellnummer:
        if(bBestellnummer && bestellnummer.getText().length() == 13) {
            bestellnummer.setStyle(STYLE_WHITE);                         
            bBestellnummer  = false;
        }
        // Weinname:
        if(bWeinname && weinname.getText().length() > 0) {
            weinname.setStyle(STYLE_WHITE);
            bWeinname = false;
        }
        // Jahrgang:
        if(bJahrgangText && jahrgangText.getText().length() > 0) {
            jahrgangText.setStyle(STYLE_WHITE);                        
            bJahrgangText = false;
        }
        // lagerdauer:
        if(bDauerText && dauerText.getText().length() > 0) {
            dauerText.setStyle(STYLE_WHITE);                        
            bDauerText = false;
        }
        // Alkoholgehalt:
        if(bAlkoholGehalt && alkoholGehalt.getText().length() > 0) {
            alkoholGehalt.setStyle(STYLE_WHITE);                        
            bAlkoholGehalt = false;
        }
        
    }
    
    public void startWerte() {
        // Slider deaktivieren:
        alkoholgehaltSlider.setDisable(true);
        // Vorbelegung Bestellnummer:
        bestellnummer.setText(momentanesJahr + "-");            
        // Vorbelegung Jahrgang:
        jahrgangText.setText(momentanesJahr +"");        
        // Land und Region setzen:
        anbauLand.setValue(STARTLAND);
        anbauRegion.setValue(STARTREGION);        
        // Alkoholgehalt Slider:
        alkoholgehaltSlider.setValue(STARTALKOHOLGEHALT);
        // Vorauswahl der ChoiceBox:
        auswahlFlaschenGroesse.setValue(STARTWERT);
        // Vorauswahl Checkbox Alkoholgehalt:
        alkoholnullprozent.setSelected(false);
        // Vorbelegung Lagerdauer:
        dauerText.setText(LAGERDAUERSTART);
        // Farbe:
        farbeRot.setSelected(true);
        // Weiname:
        weinname.setText("");
        // Beschreibung
        beschreibung.setText("");
        // Slider und Feld aktivieren:        
        alkoholGehalt.setDisable(false);
        alkoholGehalt.setText(TEXTSTARTWERT);
        alkoholgehaltSlider.setDisable(true);
        // Slider deaktivieren:
        //alkoholgehaltSlider.setDisable(true);
        // Checkbox Slider aktivieren?:
        slideraktivieren.setSelected(false);
        alkoholgehaltAnzeige.setText("");
        // Felder Umrechner:
        flaschenPreisNetto.setText("");
        flaschenPreisBrutto.setText("");
        preisProLiterNetto.setText("");
        preisProLiterBrutto.setText("");
        
        // Abbrechen zurücksetzten:
        aenderungen = false;
        
    }
    
}
