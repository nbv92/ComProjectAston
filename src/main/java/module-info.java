module org.example.sort {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sort to javafx.fxml;
    exports org.example.sort;
}