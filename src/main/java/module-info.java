module dev.imarti.bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;

    opens dev.imarti.bank to javafx.fxml;
    exports dev.imarti.bank;

    exports dev.imarti.bank.view;
    opens dev.imarti.bank.view to javafx.fxml;

    exports dev.imarti.bank.util;
    opens dev.imarti.bank.util to javafx.fxml;

    exports dev.imarti.bank.register;
    opens dev.imarti.bank.register to javafx.fxml;

    exports dev.imarti.bank.home;
    opens dev.imarti.bank.home to javafx.fxml;

    exports dev.imarti.bank.account;
    opens dev.imarti.bank.account to javafx.fxml;

    exports dev.imarti.bank.deposits;
    opens dev.imarti.bank.deposits to javafx.fxml;

    exports dev.imarti.bank.settings;
    opens dev.imarti.bank.settings to javafx.fxml;
}