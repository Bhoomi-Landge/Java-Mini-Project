package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addFarmer {
    @FXML
    private TextField storeName;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField mobileNumberField;

    @FXML
    private TextField cropsField;

    @FXML
    private TextField compartmentNoField;

    @FXML
    private TextField tokenNoField;

    @FXML
    private TextField spaceOccupiedField;

    @FXML
    private Button submitButton;

    @FXML
    private Button billButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView farmerImageView;

    @FXML
    private TextField storageDateText;

    @FXML
    private TextField freeStorageText;

    @FXML
    private TextField arrivalDateField;

    @FXML
    private TextField freeDateField;

    public Button submit;

    @FXML
    private void initialize() {
        // You can perform any initialization tasks here.
    }

    @FXML
    private void handleSubmitButtonAction() {
        // Handle the submit button action here.
        // You can access the text field values like this:
        Window owner = submit.getScene().getWindow();

        String storName = storeName.getText();
        String fullName = lastNameField.getText();
        String mobileNumber = mobileNumberField.getText();
        String crops = cropsField.getText();
        int compartmentNo = Integer.parseInt(compartmentNoField.getText());
        int tokenNo = Integer.parseInt(tokenNoField.getText());
        String spaceOccupied = spaceOccupiedField.getText();
        String arrivalDate = arrivalDateField.getText();
        String freeDate = freeDateField.getText();

        //if (!isValidMobileNumber(mobileNumber)) {
           // showAlert(Alert.AlertType.ERROR, owner, "Invalid Mobile Number",
            //        "Please enter a 10-digit mobile number with only digits.");
          //  return;
       // }

        String query =  "insert into farmers(storName,fullName,mobileno,crops,comp_no,token_no,space_occupied,Adate,Ddate) values (?,?,?,?,?,?,?,?,?)";
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();

        try {
//            Statement statement = conDB.createStatement();
//            ResultSet queryOutput = statement.executeUpdate
            PreparedStatement preparedStatement = conDB.prepareStatement(query);
            preparedStatement.setString(1, storName);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, mobileNumber);
            preparedStatement.setString(4, crops);
            preparedStatement.setInt(5, compartmentNo);
            preparedStatement.setInt(6, tokenNo);
            preparedStatement.setString(7, spaceOccupied);
            preparedStatement.setString(8, arrivalDate);
            preparedStatement.setString(9, freeDate);
            preparedStatement.executeUpdate();

            showAlert(Alert.AlertType.CONFIRMATION, owner, "Farmers Details successfully stored!",
                    "Thank you" );

        }       catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }
    ///private boolean isValidMobileNumber(String mobileNumber) {
        // Validate that the mobile number contains only digits and is exactly 10 characters
      //  return mobileNumber.matches("\\d{10}");


   public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void backtoDashboard(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        Scene scene = new Scene(blah,800,500);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}