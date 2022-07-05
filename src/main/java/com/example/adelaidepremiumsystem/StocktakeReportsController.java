package com.example.adelaidepremiumsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/** The type Stocktake reports controller. */
public class StocktakeReportsController implements Initializable {

  /** The Stocktaketable. */
  @FXML TableView<Item> stocktaketable;

  /** The Column id. */
  @FXML private TableColumn<Item, Integer> columnID;

  /** The Column stock. */
  @FXML private TableColumn<Item, Integer> columnStock;

  /** The Column description. */
  @FXML private TableColumn<Item, String> columnDescription;

  /**
   * Initialize.
   *
   * @param url the url
   * @param rb the rb
   */
  @FXML
  public void initialize(URL url, ResourceBundle rb) {
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DATA");

            ObservableList<Item> items = FXCollections.observableArrayList();
            stocktaketable.setEditable(true);
            while(rs.next()){
                int id = rs.getInt("id");
                String item = rs.getString("item");
                String color = rs.getString("color");
                String size = rs.getString("size");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                String description = rs.getString("description");
                items.add(new Item(id,item,color,size,price,stock,description));
            }

            columnID.setCellValueFactory(new PropertyValueFactory<>("itemid"));
            columnStock.setCellValueFactory(new PropertyValueFactory<>("itemstock"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
            stocktaketable.setItems(items);

            stmt.close();
            c.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
