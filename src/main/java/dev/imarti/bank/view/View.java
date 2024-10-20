package dev.imarti.bank.view;

public enum View {
    LOGIN("login.fxml"),
    REGISTER("register.fxml"),
    ACCOUNT_REGISTER("register_account.fxml"),
    HOME("home.fxml"),
    ACCOUNT("account.fxml"),
    SETTINGS("settings.fxml");

    private final String fxml;

    View(String fxml) {
        this.fxml = fxml;
    }

    public String getFxml() {
        return fxml;
    }
}
