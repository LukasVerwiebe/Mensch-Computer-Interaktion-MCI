
package de.whs.mci.verwiebel_aufgabe_3;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * JavaFX App
 * @author Lukas Verwiebe 202021182
 */
public class Wein {
    
    private final String NACHRICHT = "Die Daten wurden erfolgreich gespeichert!";
    private static final NumberFormat nf = NumberFormat.getInstance();
    private static final double MEHRWERTSTEUER = 19.0;
    private static final double MEHRWERTSTEUER_BRUTTO = 1.19;  
    private final double TEILER = 100.0;
    private static final int NACHKOMMASTELLEN = 2;
    private String bestellnummer;
    private String farbe;
    private String weinname;
    private String anbauLand;
    private String anbauRegion;
    private String jahrgang;
    private String lagerdauer;
    private String flaschengroesse;
    private String preisProFlascheBrutto;
    private String preisProLiterBrutto;
    private String preisProLiterNetto;
    private String preisProFlascheNetto;
    private String alkoholgehalt;
    private String beschreibung;
    
    public Wein(String bestellnummer, String farbe, String weinname, 
            String land, String region, String jahrgang, String lagerdauer,
            String flaschengroesse, String preisProFlascheBrutto,
            String alkoholgehalt, String beschreibung) throws ParseException {
        
        this.bestellnummer = bestellnummer;
        this.farbe = farbe;
        this.weinname = weinname;
        this.anbauLand = land;
        this.anbauRegion = region;
        this.jahrgang = jahrgang;
        this.lagerdauer = lagerdauer;
        this.flaschengroesse = flaschengroesse;
        this.preisProFlascheBrutto = preisProFlascheBrutto;
        this.alkoholgehalt = alkoholgehalt;
        this.beschreibung = beschreibung;
        
        // Weitere Preise Ausrechnen lassen:
        berechnungenWeinPreise(this.preisProFlascheBrutto, this.flaschengroesse);
    }
    
    public void Ausgabe() {
        System.out.println("----------"+ NACHRICHT +"----------");
        System.out.println("Gespeicherte Wein Daten:");
        System.out.println("Bestellnummer: " + this.bestellnummer + "\n"+
                "Weinfarbe: " + this.farbe + "\n"+
                "Weinname: " + this.weinname + "\n"+
                "Anbauland: " + this.anbauLand+ "\n"+
                "Anbauregion: " + this.anbauRegion + "\n"+
                "Jahrgang: " + this.jahrgang + "\n"+
                "Lagerdauer: " + this.lagerdauer + "\n"+
                "Flaschengröße: " + this.flaschengroesse + "\n"+
                "Preis pro Flasche Brutto: " + this.preisProFlascheBrutto +"\n"+                
                "Alkoholgehalt: " + this.alkoholgehalt + "\n"+
                "-----Preise----" +"\n"+
                "Preis pro Liter Brutto: "+ this.preisProLiterBrutto +"\n"+
                "Preis pro Liter Netto: "+ this.preisProLiterNetto +"\n"+
                "Preis pro Flasche Netto: "+ this.preisProFlascheNetto +"\n"+
                "---------------" +"\n"+
                "Wein Beschreibung: " + this.beschreibung);        
        System.out.println("-----------------------------------");
    }
    
    
    public void berechnungenWeinPreise(String bruttoPreis, String flasche) throws ParseException {
        double eingabePreis = nf.parse(bruttoPreis).doubleValue();
        double flaschenGroesse = nf.parse(flasche).doubleValue();
        double flascheNetto = 0.0;
        double literBrutto = 0.0;
        double literNetto = 0.0;
        // Setzten der möglichen Nachkommastellen der Ergebnisse:
        nf.setMaximumFractionDigits(NACHKOMMASTELLEN);
        nf.setMinimumFractionDigits(NACHKOMMASTELLEN); 
        
        // Berechnung Preis pro Flasche Netto:
        flascheNetto = eingabePreis / MEHRWERTSTEUER_BRUTTO;
        
        // Berechnung Preis pro Liter Netto:
        literNetto = flascheNetto / flaschenGroesse;
        
        // Berechnung Preis pro Liter Brutto:
        literBrutto = (literNetto / TEILER) * MEHRWERTSTEUER + literNetto;
        
        // Werte für Ausgabe auf Bildschirm:
        this.preisProFlascheNetto = nf.format(flascheNetto);
        this.preisProLiterBrutto = nf.format(literBrutto);
        this.preisProLiterNetto = nf.format(literNetto);
    }
    
}
