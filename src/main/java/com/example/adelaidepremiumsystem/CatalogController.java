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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/** The type Catalog controller. */
public class CatalogController implements Initializable {

  /** The Catalogtable. */
  @FXML TableView<Item> catalogtable;

  /** The Column id. */
  @FXML private TableColumn<Item, Integer> columnID;

  /** The Column item. */
  @FXML private TableColumn<Item, String> columnItem;

  /** The Column color. */
  @FXML private TableColumn<Item, String> columnColor;

  /** The Column size. */
  @FXML private TableColumn<Item, String> columnSize;

  /** The Column price. */
  @FXML private TableColumn<Item, Double> columnPrice;

  /** The Column stock. */
  @FXML private TableColumn<Item, Integer> columnStock;

  /** The Column description. */
  @FXML private TableColumn<Item, String> columnDescription;

  /** The Items. */
  ArrayList<Item> items = new ArrayList<>(10);

  /**
   * Initialize.
   *
   * @param url the url
   * @param rb the rb
   */
  @FXML
  public void initialize(URL url, ResourceBundle rb) {

        // generate ten random numbers
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < 11) {
            int num = (int) (Math.random() * 299)+1;
            set.add(num);
        }
        //TODO: fix it

        try {
            int i = 0;
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DATA");

            ObservableList<Item> items = FXCollections.observableArrayList();
            catalogtable.setEditable(true);
            while (rs.next()) {
                i++;
                if(set.contains(i)){
                    int id = rs.getInt("id");
                    String item = rs.getString("item");
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    double price = rs.getDouble("price")*0.85;
                    int stock = rs.getInt("stock");
                    String description = rs.getString("description");
                    items.add(new Item(id, item, color, size, price, stock, description));
                }
            }

            columnID.setCellValueFactory(new PropertyValueFactory<>("itemid"));
            columnItem.setCellValueFactory(new PropertyValueFactory<>("itemitem"));
            columnColor.setCellValueFactory(new PropertyValueFactory<>("itemcolor"));
            columnSize.setCellValueFactory(new PropertyValueFactory<>("itemsize"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("itemprice"));
            columnStock.setCellValueFactory(new PropertyValueFactory<>("itemstock"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
            catalogtable.setItems(items);

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
