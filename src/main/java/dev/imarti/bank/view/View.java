package dev.imarti.bank.view;

public enum View {
    LOGIN("login.fxml"),
    REGISTER("register.fxml"),
    HOME("home.fxml");

    private final String fxml;

    View(String fxml) {
        this.fxml = fxml;
    }

    public String getFxml() {
        return fxml;
    }
}
