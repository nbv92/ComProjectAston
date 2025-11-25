module org.example.sort {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.sort to javafx.fxml;
    exports org.example.sort;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Class;
    opens Class to javafx.fxml;
    exports Interface;
    opens Interface to javafx.fxml;
}