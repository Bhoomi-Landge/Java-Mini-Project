package com.example.firstproject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static com.example.firstproject.registration.showAlert;


public class HelloController {
    public ImageView imageView;
    @FXML
    private Label welcomeText;

    @FXML
    private TextField userName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signIn;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

    }

    // Your other controller logic here.


    public void signIn(ActionEvent actionEvent) throws SQLException, IOException {
        Window owner = signIn.getScene().getWindow();
        System.out.println(userName.getText());
        String username = userName.getText();
        String password = passwordField.getText();
        String query = "select username,Password FROM WRegistration where username = ? and Password=?";
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();

        PreparedStatement statement = conDB.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet queryOutput = statement.executeQuery();

        while(queryOutput.next()) {
            System.out.println(queryOutput.getString("username"));
            String username1 = queryOutput.getString("username");
            String password1 = queryOutput.getString("Password");
            if (username.equalsIgnoreCase(username1) && password.equalsIgnoreCase(password1)) {
                Parent blah = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

                Scene scene = new Scene(blah,800,500);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();

                showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!",
                        "Welcome " + userName.getText());
                return;

            }

        }

        showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Invalid credentials");

    }
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    public void register(ActionEvent event) throws IOException {


        Parent blah = FXMLLoader.load(getClass().getResource("registration.fxml"));

        Scene scene = new Scene(blah, 800,500);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

}