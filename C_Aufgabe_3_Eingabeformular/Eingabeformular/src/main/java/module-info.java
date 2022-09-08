module de.whs.mci.verwiebel_aufgabe_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens de.whs.mci.verwiebel_aufgabe_3 to javafx.fxml;
    exports de.whs.mci.verwiebel_aufgabe_3;
}
