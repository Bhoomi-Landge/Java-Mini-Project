package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;


public class registration {
    @FXML
    private TextField userName;

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    public void signIn(ActionEvent actionEvent) {
    }

    public void register(ActionEvent actionEvent) throws SQLException, IOException {
        Window owner = submitButton.getScene().getWindow();

        System.out.println(userName.getText());
        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());
        if (userName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String fullName = userName.getText();
        String emailId = emailIdField.getText();
        String password = passwordField.getText();
        // strong password code



        // Define the criteria for a strong password using regular expressions
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        // Create a pattern object
        Pattern pattern = Pattern.compile(passwordPattern);

         if(!pattern.matcher(password).matches()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Your password should be atleast 8 characters long ,should have  1 uppercase and 1 lowercase character ,1 number and a symbol.");
            return;
        }



        String query1 =  "select username from wregistration where username = ? ";


//        databaseConn jdbcDao = new databaseConn();
        String query =  "insert into wregistration(username, Email ,Password ) values (?, ?, ?)";
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();

        PreparedStatement statement = conDB.prepareStatement(query1);
        statement.setString(1, fullName);
        ResultSet queryOutput = statement.executeQuery();
        while(queryOutput.next()) {
            String username = queryOutput.getString("username");
            if (username.equalsIgnoreCase(fullName)) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "already exist user");
                return;

            }
            System.out.println(queryOutput.getString("username"));
        }
        try {
//            Statement statement = conDB.createStatement();
//            ResultSet queryOutput = statement.executeUpdate
            PreparedStatement preparedStatement = conDB.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        }       catch (SQLException e) {
            throw new RuntimeException(e);
        }




        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + userName.getText());

        // on success
        Parent blah = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Scene scene = new Scene(blah,800,500);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    }

