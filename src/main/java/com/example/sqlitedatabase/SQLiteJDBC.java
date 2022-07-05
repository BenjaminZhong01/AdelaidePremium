package com.example.sqlitedatabase;

import java.sql.*;
import java.util.ArrayList;

/** The type Sq lite jdbc. */
public class SQLiteJDBC {
  /** The C. */
  private Connection c = null;

  /** The Stmt. */
  private Statement stmt = null;

  /** The Id. */
  // shirts data
  private ArrayList<Integer> id = new ArrayList<Integer>();

  /** The Item. */
  private ArrayList<String> item = new ArrayList<String>();

  /** The Color. */
  private ArrayList<String> color = new ArrayList<String>();

  /** The Size. */
  private ArrayList<String> size = new ArrayList<String>();

  /** The Price. */
  private ArrayList<Double> price = new ArrayList<Double>();

  /** The Description. */
  private ArrayList<String> description = new ArrayList<String>();

  /** Instantiates a new Sq lite jdbc. */
  public SQLiteJDBC() {
        this.id = getAllID();
        this.item = getAllItem();
        this.color = getAllColor();
        this.size = getAllSize();
        this.price = getAllPrice();
        this.description = getAllDescription();
    }

  /**
   * Gets all id.
   *
   * @return the all id
   */
  private ArrayList<Integer> getAllID() {
        try{

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID FROM DATA;");

            while(rs.next()){
                id.add(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return id;
    }

  /**
   * Gets all item.
   *
   * @return the all item
   */
  private ArrayList<String> getAllItem() {
        try{

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ITEM FROM DATA;");

            while(rs.next()){
                item.add(rs.getString("item"));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return item;
    }

  /**
   * Gets all color.
   *
   * @return the all color
   */
  private ArrayList<String> getAllColor() {
        try{

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COLOR FROM DATA;");

            while(rs.next()){
                color.add(rs.getString("color"));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return color;
    }

  /**
   * Gets all size.
   *
   * @return the all size
   */
  private ArrayList<String> getAllSize() {
        try{

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SIZE FROM DATA;");

            while(rs.next()){
                size.add(rs.getString("size"));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return size;
    }

  /**
   * Gets all price.
   *
   * @return the all price
   */
  private ArrayList<Double> getAllPrice() {
        try{

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PRICE FROM DATA;");

            while(rs.next()){
                price.add(rs.getDouble("price"));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return price;
    }

  /**
   * Gets all description.
   *
   * @return the all description
   */
  private ArrayList<String> getAllDescription() {
        try{

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DESCRIPTION FROM DATA;");

            while(rs.next()){
                description.add(rs.getString("description"));
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return description;
    }

  /**
   * Get id array list.
   *
   * @return the array list
   */
  public ArrayList<Integer> getID() {
        return id;
    }

  /**
   * Get item array list.
   *
   * @return the array list
   */
  public ArrayList<String> getItem() {
        return item;
    }

  /**
   * Get color array list.
   *
   * @return the array list
   */
  public ArrayList<String> getColor() {
        return color;
    }

  /**
   * Get size array list.
   *
   * @return the array list
   */
  public ArrayList<String> getSize() {
        return size;
    }

  /**
   * Get price array list.
   *
   * @return the array list
   */
  public ArrayList<Double> getPrice() {
        return price;
    }

  /**
   * Get description array list.
   *
   * @return the array list
   */
  public ArrayList<String> getDescription() {
        return description;
    }

  /** Rewrite database. */
  public void rewriteDatabase() {}
}