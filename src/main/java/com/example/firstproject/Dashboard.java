package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard {
    @FXML
    public void stor(ActionEvent event) throws IOException {

        Parent blah = FXMLLoader.load(getClass().getResource("storage.fxml"));

        Scene scene = new Scene(blah,800,500);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void addFarmerDetails(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("CustomerDetails.fxml"));

        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void showFarmersDetails(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("farmersDetails.fxml"));

        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void loginpage(ActionEvent event) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}


