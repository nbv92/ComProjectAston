module org.example.sort {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.sort to javafx.fxml;
    exports org.example.sort;
    exports org.example.sort.utils;
    opens org.example.sort.utils to javafx.fxml;
}