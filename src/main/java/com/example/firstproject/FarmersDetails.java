package com.example.firstproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.sql.*;

public class FarmersDetails {
    public TableView table_userssss;
    public TableColumn fullName;
    public TableColumn storeName;
    public TableColumn compartNo;
    public TableColumn spaceOccu;
    public TableColumn fromDate;
    public TableColumn toDate;
    public TableColumn Compartment_Size;


    public void show(ActionEvent event) {
        table_users.setEditable(true);

        ObservableList<users> list = FXCollections.observableArrayList();
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();

        String query = "select ca.Compartments_created,fa.fullName,fa.storName,fa.comp_no,fa.space_occupied,fa.Adate,fa.Ddate,ca.compartment_size from farmers fa inner join compartments ca on fa.storName=ca.storName group by ca.storName";
        try {
            Statement statement = conDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(query);
            while(queryOutput.next()) {

              //  list.add(new users(queryOutput.getString("fullName"),(queryOutput.getString("space_occupied")),(queryOutput.getString("storName")),(queryOutput.getString("Adate")),(queryOutput.getString("Ddate"))));

                list.add(new users(queryOutput.getString("fullName"),(queryOutput.getString("space_occupied")),(queryOutput.getString("storName")),(queryOutput.getString("Adate")),(queryOutput.getString("Ddate")),(queryOutput.getInt("comp_no")),(queryOutput.getInt("compartment_size"))));
            }
            System.out.println(list);
            fullName.setCellValueFactory(new PropertyValueFactory<users, String>("fullName"));
            storeName.setCellValueFactory(new PropertyValueFactory<users, String>("storeName"));

            spaceOccu.setCellValueFactory(new PropertyValueFactory<users, String>("space_occupied"));

            fromDate.setCellValueFactory(new PropertyValueFactory<users, String>("Adate"));
            toDate.setCellValueFactory(new PropertyValueFactory<users, String>("Ddate"));
            compartNo.setCellValueFactory(new PropertyValueFactory<users, Integer>("comp_no"));
            Compartment_Size.setCellValueFactory(new PropertyValueFactory<users, Integer>("compartment_sizes"));


            table_users.setItems(list);
            table_users.setEditable(true);
            fullName.setCellFactory(TextFieldTableCell.forTableColumn());
            storeName.setCellFactory(TextFieldTableCell.forTableColumn());
            spaceOccu.setCellFactory(TextFieldTableCell.forTableColumn());
            fromDate.setCellFactory(TextFieldTableCell.forTableColumn());
            toDate.setCellFactory(TextFieldTableCell.forTableColumn());
           // compartNo.setCellFactory(TextFieldTableCell.forTableColumn());
           // Compartment_Size.setCellFactory(TextFieldTableCell.forTableColumn());
            compartNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            Compartment_Size.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));



        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteSelectedRow(ActionEvent event) throws SQLException {

        ObservableList<users> list = FXCollections.observableArrayList();
        ObservableList<users> selectedItems = table_users.getSelectionModel().getSelectedItems();
             String a = selectedItems.get(0).getFullName();
           //  int h  = selectedItems.get(0).getid();
        //String a = selectedItems.get(0).getFullName();
        String b = selectedItems.get(0).getStoreName();
        String c= selectedItems.get(0).getSpace_occupied();
        int d = selectedItems.get(0).getComp_no();
        int e = selectedItems.get(0).getCompartment_sizes();
        String f = selectedItems.get(0).getAdate();
        String g = selectedItems.get(0).getDdate();
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();
        String query = "Delete  from farmers WHERE fullName = ?";

        PreparedStatement preparedStatement = conDB.prepareStatement(query);
        preparedStatement.setString(1, a);
        preparedStatement.executeUpdate();

       // String query = ""

        // Check if any rows are selected
        if (!selectedItems.isEmpty()) {
            // Remove selected rows from the data source
            //list.removeAll(selectedItems);
        } else {
            // Handle no row selected
            System.out.println("No rows selected for deletion.");
        }
    }

    private void updateUserDataInDatabase(users editedUser) {
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();

        String query = "UPDATE farmers SET fullName=?, storName=?, space_occupied=?, Adate=?, Ddate=?, comp_no=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = conDB.prepareStatement(query);
            preparedStatement.setString(1, editedUser.getFullName());
            preparedStatement.setString(2, editedUser.getStoreName());
            preparedStatement.setString(3, editedUser.getSpace_occupied());
            preparedStatement.setString(4, editedUser.getAdate());
            preparedStatement.setString(5, editedUser.getDdate());
            preparedStatement.setInt(6, editedUser.getComp_no());
           // preparedStatement.setInt(7, editedUser.getId()); // Assuming you have an 'id' property in the 'users' class

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //    public void start(Stage stage) throws Exception {
//        ObservableList<users> list = FXCollections.observableArrayList();
//        dbConnect db = new dbConnect();
//        Connection conDB = db.dbConn();
//
//        String query = "select * from farmers";
//        try {
//            Statement statement = conDB.createStatement();
//            ResultSet queryOutput = statement.executeQuery(query);
//            while(queryOutput.next()) {
//
//                list.add(new users(queryOutput.getString("fullName"), (queryOutput.getString("storName")), (queryOutput.getString("comp_no")), (queryOutput.getString("space_occupied")), (queryOutput.getString("Adate"))));
//
//            }
//            System.out.println(list);
//            fullName.setCellValueFactory(new PropertyValueFactory<users, String>("fullName"));
//            storeName.setCellValueFactory(new PropertyValueFactory<users, String>("storName"));
//            table_users.setItems(list);
//        }
//
//        catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
    public void backToDash(ActionEvent event) throws IOException {

        Parent blah = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void oneditchange(TableColumn.CellEditEvent cellEditEvent) {
        ObservableList<users> list = FXCollections.observableArrayList();
        ObservableList<users> selectedItems = table_users.getSelectionModel().getSelectedItems();



       // list.get(0).setFullName(cellEditEvent.getNewValue())
        selectedItems.get(0).setFullName(cellEditEvent.getNewValue().toString());
        selectedItems.get(0).setAdate(cellEditEvent.getNewValue().toString());
        selectedItems.get(0).setDdate(cellEditEvent.getNewValue().toString());
       // compartNo.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>());
        int newValue = Integer.parseInt(cellEditEvent.getNewValue().toString());
        selectedItems.get(0).setComp_no(newValue);
        selectedItems.get(0).setSpace_occupied(cellEditEvent.getNewValue().toString());


        System.out.println(selectedItems.get(0).fullName);
        dbConnect db = new dbConnect();
        Connection conDB = db.dbConn();
        String query = "UPDATE farmers SET fullName=?,  space_occupied=?, Adate=?, Ddate=?, comp_no=? WHERE storName =?";
        try {
            PreparedStatement preparedStatement = conDB.prepareStatement(query);
            preparedStatement.setString(1, selectedItems.get(0).fullName);
            preparedStatement.setString(3, selectedItems.get(0).Adate);
            preparedStatement.setString(4, selectedItems.get(0).ddate);
            preparedStatement.setString(2, selectedItems.get(0).space_occupied);
            preparedStatement.setString(6, selectedItems.get(0).storeName);
            preparedStatement.setInt(5, selectedItems.get(0).comp_no);

           //  preparedStatement.setInt(7, editedUser.getId()); // Assuming you have an 'id' property in the 'users' class

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
