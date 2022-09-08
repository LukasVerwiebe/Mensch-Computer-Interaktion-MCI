module de.whs.mci.trinkreifediagramm {
    requires javafx.controls;
    requires javafx.fxml;

    opens de.whs.mci.trinkreifediagramm to javafx.fxml;
    exports de.whs.mci.trinkreifediagramm;
}
