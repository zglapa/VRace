module VRace {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    exports sample;
    exports visuals;
    exports handlers;
    exports logic;
    opens sample;
}