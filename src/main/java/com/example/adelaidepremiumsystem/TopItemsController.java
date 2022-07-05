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
import java.util.*;

/** The type Top items controller. */
public class TopItemsController implements Initializable {

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

  /** The Column stock. */
  @FXML private TableColumn<Item, Integer> columnStock;

  /** The Column description. */
  @FXML private TableColumn<Item, String> columnDescription;

  /** The Map. */
  HashMap<Integer, Integer> map = new HashMap<>();

  /**
   * Initialize.
   *
   * @param url the url
   * @param rb the rb
   */
  @FXML
  public void initialize(URL url, ResourceBundle rb) {

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");

            while (rs.next()) {
                int itemid = rs.getInt("itemid");
                map.put(itemid,0);
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");

            while (rs.next()) {
                int itemid = rs.getInt("itemid");
                int num = map.get(itemid);
                map.put(itemid,num+1);
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        map.forEach((id,q) ->{idList.add(id);quantity.add(q);});


        //sort
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        ArrayList<Integer> topid = new ArrayList<Integer>(); ArrayList<Integer> topq = new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            topid.add(list.get(list.size()-1-i).getKey()); topq.add(list.get(list.size()-1-i).getValue());
        }
        ObservableList<Item> items = FXCollections.observableArrayList();
    for (int i = 0; i < topid.size(); i++) {
      try {
        Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM data");


        while (rs.next()) {
          int itemid = rs.getInt("id");
          String item = rs.getString("item");
          String color = rs.getString("color");
          String size = rs.getString("size");
          String description = rs.getString("description");
          if (topid.get(i) == itemid) {
            items.add(new Item(itemid, item, color, size, topq.get(i), description));
            break;
          }
        }



        stmt.close();
        c.close();

      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
            }
        columnID.setCellValueFactory(new PropertyValueFactory<>("itemid"));
        columnItem.setCellValueFactory(new PropertyValueFactory<>("itemitem"));
        columnColor.setCellValueFactory(new PropertyValueFactory<>("itemcolor"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("itemsize"));
        columnStock.setCellValueFactory(new PropertyValueFactory<>("itemstock"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
        catalogtable.setItems(items);

    }
}
