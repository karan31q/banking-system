package dev.imarti.bank.view;

public enum View {
    LOGIN("login.fxml");

    private final String fxml;

    View(String fxml) {
        this.fxml = fxml;
    }

    public String getFxml() {
        return fxml;
    }
}
