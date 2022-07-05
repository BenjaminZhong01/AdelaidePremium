package com.example.sqlitedatabase;

import com.example.adelaidepremiumsystem.Item;

import java.sql.*;
import java.util.ArrayList;

/** The type Order. */
public class Order {
  /** The Credit card id. */
  private String creditCardID;

  /** The Password. */
  private String password;

  /** The Items. */
  private ArrayList<Item> items = new ArrayList<>();

  /** The Order id. */
  private int orderID = 0;

  /** The Order description. */
  private String orderDescription = "";

  /** The Back order. */
  private boolean backOrder = false;

  /**
   * Instantiates a new Order.
   *
   * @param creditCardID the credit card id
   * @param password the password
   * @param items the items
   */
  public Order(String creditCardID, String password, ArrayList<Item> items) {
        this.creditCardID = creditCardID;
        this.password = password;
        this.items = items;
        orderID++;
        for(int i=0;i<items.size();i++){
            this.orderDescription += items.get(i).getItemdescription();
        }
    }

  /** Set orderin database. */
  public void setOrderinDatabase() {
        try{
            Connection c = null;
            //Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            PreparedStatement pstmt = c.prepareStatement("INSECT INTO ORDER VALUES(?,?,?,?);");
            pstmt.setInt(1,orderID);
            pstmt.setString(2,creditCardID);
            pstmt.setString(3,orderDescription);
            pstmt.setBoolean(4,backOrder);
            pstmt.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
