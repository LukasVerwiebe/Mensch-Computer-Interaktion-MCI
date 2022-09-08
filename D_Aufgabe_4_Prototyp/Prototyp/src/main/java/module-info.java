module de.whs.mci.verwiebel_aufgabe_4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;

    opens de.whs.mci.verwiebel_aufgabe_4 to javafx.fxml;
    exports de.whs.mci.verwiebel_aufgabe_4;
}
