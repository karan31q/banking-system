package dev.imarti.bank.deposits;

import dev.imarti.bank.home.HomeController;
import dev.imarti.bank.util.Deposits;
import dev.imarti.bank.util.DepositsSchemes;
import dev.imarti.bank.util.FetchUserDetails;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;

public class DepositsController {
    public String alertHeading = "Banking System | Deposits";
    private FetchUserDetails userDetails;
    private ObservableList<DepositsSchemes> depositsSchemesList;
    private ObservableList<Deposits> depositsList;

    @FXML
    protected Text name;

    @FXML
    protected TableColumn<DepositsSchemes, String> depositSchemesID;

    @FXML
    protected TableColumn<DepositsSchemes, Double> depositSchemesAmount;

    @FXML
    protected TableColumn<DepositsSchemes, Float> depositsSchemesInterest;

    @FXML
    protected TableColumn<DepositsSchemes, Float> depositsSchemesMaturity;

    @FXML
    protected TableView<DepositsSchemes> depositsSchemesTable;

    @FXML
    protected TableColumn<Deposits, String> depositsID;

    @FXML
    protected TableColumn<Deposits, Date> depositsDate;

    @FXML
    protected TableColumn<Deposits, Double> depositsAmount;

    @FXML
    protected TableColumn<Deposits, Float> depositsInterest;

    @FXML
    protected TableColumn<Deposits, Date> depositsDueDate;

    @FXML
    protected TableView<Deposits> depositsTable;

    public void setUserDetails(FetchUserDetails userDetails) {
        this.userDetails = userDetails;
        name.setText(userDetails.getName());

        depositsSchemesList = userDetails.getDepositsSchemes();
        depositsSchemesTable.setItems(depositsSchemesList);

        depositsList = userDetails.getDeposits(userDetails.getUserID());
        depositsTable.setItems(depositsList);
    }

    @FXML
    protected void onLogoutClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        HomeController homeController = (HomeController) ViewSwitcher.switchView(View.HOME);
        homeController.setUserDetails(userDetails.getUserID());
    }

    @FXML
    protected void onSecureButtonClick() {
        DepositsSchemes selectedScheme = depositsSchemesTable.getSelectionModel().getSelectedItem();
        if (selectedScheme == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertHeading);
            alert.setHeaderText("Please select a scheme");
            alert.showAndWait();
        } else {
            if (userDetails.getBalance() < selectedScheme.getDepositAmount()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(alertHeading);
                alert.setHeaderText("Insufficient balance, to make a deposit.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(alertHeading);
                alert.setContentText("You're making a deposit for " + selectedScheme.getDepositAmount());
                if (alert.showAndWait().isPresent() && alert.getResult() == ButtonType.OK) {
                    String depositResult = userDetails.makeDeposit(userDetails.getUserID(), selectedScheme.getDepositID());
                    if (depositResult == "deposit successful") {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle(alertHeading);
                        successAlert.setHeaderText("Deposit successful");
                        successAlert.showAndWait();
                        setUserDetails(userDetails);
                    } else if (depositResult == "deposit not successful") {
                        Alert successAlert = new Alert(Alert.AlertType.ERROR);
                        successAlert.setTitle(alertHeading);
                        successAlert.setHeaderText("Deposit Failed");
                        successAlert.showAndWait();
                        setUserDetails(userDetails);
                    } else {
                        Alert successAlert = new Alert(Alert.AlertType.ERROR);
                        successAlert.setTitle(alertHeading);
                        successAlert.setHeaderText("Some error occurred, Please try again!");
                        successAlert.showAndWait();
                        setUserDetails(userDetails);
                    }
                }
            }
        }
    }

    @FXML
    protected void initialize() {
        depositSchemesID.setCellValueFactory(new PropertyValueFactory<>("depositID"));
        depositSchemesAmount.setCellValueFactory(new PropertyValueFactory<>("depositAmount"));
        depositsSchemesInterest.setCellValueFactory(new PropertyValueFactory<>("depositInterest"));
        depositsSchemesMaturity.setCellValueFactory(new PropertyValueFactory<>("depositMaturity"));
        depositsSchemesTable.setItems(depositsSchemesList);

        depositsID.setCellValueFactory(new PropertyValueFactory<>("depositID"));
        depositsDate.setCellValueFactory(new PropertyValueFactory<>("depositDate"));
        depositsAmount.setCellValueFactory(new PropertyValueFactory<>("depositAmount"));
        depositsInterest.setCellValueFactory(new PropertyValueFactory<>("depositInterest"));
        depositsDueDate.setCellValueFactory(new PropertyValueFactory<>("depositDueDate"));
        depositsTable.setItems(depositsList);
    }
}
