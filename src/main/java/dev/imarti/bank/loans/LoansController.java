package dev.imarti.bank.loans;

import dev.imarti.bank.home.HomeController;
import dev.imarti.bank.util.*;
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

public class LoansController {
    public String alertHeading = "Banking System | Loans";
    private FetchUserDetails userDetails;
    private ObservableList<LoansSchemes> loansSchemesList;
    private ObservableList<Loans> loansList;

    @FXML
    protected Text name;

    @FXML
    protected TableColumn<LoansSchemes, String> loansSchemesID;

    @FXML
    protected TableColumn<LoansSchemes, Double> loansSchemesAmount;

    @FXML
    protected TableColumn<LoansSchemes, Float> loansSchemesInterest;

    @FXML
    protected TableColumn<LoansSchemes, Float> loansSchemesDuration;

    @FXML
    protected TableView<LoansSchemes> loansSchemesTable;

    @FXML
    protected TableColumn<Loans, String> loansID;

    @FXML
    protected TableColumn<Loans, Date> loansDate;

    @FXML
    protected TableColumn<Loans, Double> loansAmount;

    @FXML
    protected TableColumn<Loans, Float> loansInterest;

    @FXML
    protected TableColumn<Loans, Date> loansDueDate;

    @FXML
    protected TableView<Loans> loansTable;

    public void setUserDetails(FetchUserDetails userDetails) {
        this.userDetails = userDetails;
        name.setText(userDetails.getName());

        loansSchemesList = userDetails.getLoanSchemes();
        loansSchemesTable.setItems(loansSchemesList);

        loansList = userDetails.getLoans(userDetails.getUserID());
        loansTable.setItems(loansList);
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
        LoansSchemes selectedScheme = loansSchemesTable.getSelectionModel().getSelectedItem();
        if (selectedScheme == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertHeading);
            alert.setHeaderText("Please select a scheme");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(alertHeading);
            alert.setContentText("You're taking out a loan " + selectedScheme.getLoanAmount());
            if (alert.showAndWait().isPresent() && alert.getResult() == ButtonType.OK) {
                String loanResult = userDetails.takeLoan(userDetails.getUserID(), selectedScheme.getLoanID());
                if (loanResult == "loan successful") {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle(alertHeading);
                    successAlert.setHeaderText("Loan successful");
                    successAlert.showAndWait();
                    setUserDetails(userDetails);
                } else if (loanResult == "loan not successful") {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle(alertHeading);
                    errorAlert.setHeaderText("Loan Failed");
                    errorAlert.showAndWait();
                    setUserDetails(userDetails);
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle(alertHeading);
                    errorAlert.setHeaderText("Some error occurred, Please try again!");
                    errorAlert.showAndWait();
                    setUserDetails(userDetails);
                }
            }
        }
    }

    @FXML
    protected void initialize() {
        loansSchemesID.setCellValueFactory(new PropertyValueFactory<>("loanID"));
        loansSchemesAmount.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));
        loansSchemesInterest.setCellValueFactory(new PropertyValueFactory<>("loanInterest"));
        loansSchemesDuration.setCellValueFactory(new PropertyValueFactory<>("loanDuration"));
        loansSchemesTable.setItems(loansSchemesList);

        loansID.setCellValueFactory(new PropertyValueFactory<>("loanID"));
        loansDate.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        loansAmount.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));
        loansInterest.setCellValueFactory(new PropertyValueFactory<>("loanInterest"));
        loansDueDate.setCellValueFactory(new PropertyValueFactory<>("loanDueDate"));
        loansTable.setItems(loansList);
    }
}
