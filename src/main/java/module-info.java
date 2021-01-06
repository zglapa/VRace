module VRace {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    exports sample;
    exports visuals;
    exports handlers;
    opens sample;
}