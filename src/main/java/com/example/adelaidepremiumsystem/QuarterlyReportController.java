package com.example.adelaidepremiumsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/** The type Quarterly report controller. */
public class QuarterlyReportController implements Initializable {

  /** The Qreport. */
  @FXML private TableView<Orders> qreport;

  /** The Orderid. */
  @FXML private TableColumn<Orders, Integer> orderid;

  /** The Itemid. */
  @FXML private TableColumn<Orders, Integer> itemid;

  /** The Price. */
  @FXML private TableColumn<Orders, Double> price;

  /** The Date. */
  @FXML private TableColumn<Orders, String> date;

  /** The Datelabel. */
  @FXML private Label datelabel;

  /** The Pricelabel. */
  @FXML private Label pricelabel;

  /**
   * Initialize.
   *
   * @param url the url
   * @param rb the rb
   */
  @FXML
  public void initialize(URL url, ResourceBundle rb) {
        //obtain date today
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String today = dateTime.format(formatter);
        String tdd = today.charAt(0)+""+today.charAt(1);
        String tmm = today.charAt(3)+""+today.charAt(4);
        double totalprice = 0.0;
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");

            ObservableList<Orders> orders = FXCollections.observableArrayList();
            qreport.setEditable(true);

            while(rs.next()){
                int oid = rs.getInt("orderid");
                int iid = rs.getInt("itemid");
                double price = rs.getDouble("totalprice");
                String date = rs.getString("date");
                String dd = date.charAt(0)+""+date.charAt(1);
                String mm = date.charAt(3)+""+date.charAt(4);

                //compare
                if(Integer.parseInt(mm)==Integer.parseInt(tmm)-1||Integer.parseInt(mm)==Integer.parseInt(tmm)-2
                        ||Integer.parseInt(mm)==Integer.parseInt(tmm)-3){
                    date = dd+"/"+mm+"/2021";
                    orders.add(new Orders(oid,iid,price,date));
                    totalprice += price;
                }
            }

            orderid.setCellValueFactory(new PropertyValueFactory<>("orderid"));
            itemid.setCellValueFactory(new PropertyValueFactory<>("itemid"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            qreport.setItems(orders);

            stmt.close();
            c.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        datelabel.setText("01/0"+(Integer.parseInt(tmm)-3)+"/2021  To  31/"+(Integer.parseInt(tmm)-1)+"/2021");
        pricelabel.setText(totalprice+"");
    }
}
