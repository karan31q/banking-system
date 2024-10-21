package dev.imarti.bank.view;

public enum View {
    ADMIN("admin_home.fxml"),
    ADMIN_TRANSACTIONS("admin_transactions.fxml"),
    LOGIN("login.fxml"),
    REGISTER("register.fxml"),
    ACCOUNT_REGISTER("register_account.fxml"),
    HOME("home.fxml"),
    ACCOUNT("account.fxml"),
    DEPOSITS("deposits.fxml"),
    LOANS("loans.fxml"),
    SETTINGS("settings.fxml");

    private final String fxml;

    View(String fxml) {
        this.fxml = fxml;
    }

    public String getFxml() {
        return fxml;
    }
}
