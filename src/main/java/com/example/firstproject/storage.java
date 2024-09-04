package com.example.firstproject;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;


public class storage {
    public TextField storName;
    public TextField WarehouseSize;
    public TextField compartmentNumber;
    public TextField storeName;
    public Label result;
    public ComboBox combo;
    public TextField compSize;
    public Button addCompDetai;

    int noOfCom;
    @FXML
    private VBox textFieldContainer;

    int textFieldCount = 0;

    public void submit(ActionEvent event) {



        ObservableList<String> list = FXCollections.observableArrayList("Compartment No1", "Compartment No2","Compartment No3","Compartment No4","Compartment No5","Compartment No6","Compartment No7","Compartment No8");
        combo.setItems(list);

        if (storeName.getText().isEmpty()) {
            result.setText("please enter the storage name");
            return;
        }
        else if  (Integer.parseInt(compartmentNumber.getText()) > 8) {
            result.setText("Please select no of Compartments less than 9");
            return;
        }
        else if ( WarehouseSize.getText().isEmpty() || Integer.parseInt( WarehouseSize.getText()) > 1000000){
            result.setText("Please enter size below 1000000 Square Feet");
            return;
        }
        else {
            String storName = storeName.getText();
            int size = Integer.parseInt(WarehouseSize.getText());
            noOfCom = Integer.parseInt(compartmentNumber.getText()) ;
         //  this.processTextFields();
            ObservableList<String> compartmentNames = FXCollections.observableArrayList();
            for (int i = 1; i <= noOfCom; i++) {
                compartmentNames.add("Compartment No" + i);
            }
            combo.setItems(compartmentNames);



            String query =  "insert into storage(storName,toatalsize,noOfCom ) values (?, ?, ?)";
            dbConnect db = new dbConnect();
            Connection conDB = db.dbConn();
            try {
//            Statement statement = conDB.createStatement();
//            ResultSet queryOutput = statement.executeUpdate
                PreparedStatement preparedStatement = conDB.prepareStatement(query);
                preparedStatement.setString(1, storName);
                preparedStatement.setInt(2, size);
                preparedStatement.setInt(3, noOfCom);
                preparedStatement.executeUpdate();

                result.setText("Data Successfully Stored");
            }       catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }}


    public void processTextFields() {
        // This method will be called when another button (e.g., "Process") is clicked to work with the generated text fields.

        // Loop through the generated text fields and retrieve their values.


    }

    public void select(ActionEvent actionEvent) {
        String s = combo.getSelectionModel().getSelectedItem().toString();
    }

    public void addCompartmentDetails(ActionEvent actionEvent) {

        Window owner = addCompDetai.getScene().getWindow();

        String storName = storeName.getText();
        int size = Integer.parseInt(compSize.getText());
        String comparmentName = combo.getSelectionModel().getSelectedItem().toString();

        if ( Integer.parseInt( WarehouseSize.getText()) < size){
            result.setText("your compartment Size is more than warehouse size");
            return;
        }

        String query =  "insert into compartments(storName,Compartments_created, compartment_size) values (?,?,?)";
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();

        try {
//            Statement statement = conDB.createStatement();
//            ResultSet queryOutput = statement.executeUpdate
            PreparedStatement preparedStatement = conDB.prepareStatement(query);
            preparedStatement.setString(1, storName);
            preparedStatement.setString(2, comparmentName);
            preparedStatement.setInt(3, size);
            preparedStatement.executeUpdate();
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Compartmentment Details successfully stored!",
                    "Thank you" );

    }       catch (SQLException e) {
        throw new RuntimeException(e);
    }

        }

    static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void backtoDashboard(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        Scene scene = new Scene(blah,800,500);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}