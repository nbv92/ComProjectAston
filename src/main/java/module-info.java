module org.example.sort {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.sort to javafx.fxml;
    exports org.example.sort;
    exports org.example.sort.Controller;
    opens org.example.sort.Controller to javafx.fxml;
    exports org.example.sort.Class;
    opens org.example.sort.Class to javafx.fxml;
    exports org.example.sort.Interface;
    opens org.example.sort.Interface to javafx.fxml;
}