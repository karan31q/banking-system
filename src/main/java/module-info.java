module dev.imarti.bank {
    requires javafx.controls;
    requires javafx.fxml;

    opens dev.imarti.bank to javafx.fxml;
    exports dev.imarti.bank;
}