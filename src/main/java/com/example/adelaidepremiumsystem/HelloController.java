package com.example.adelaidepremiumsystem;
import com.example.sqlitedatabase.Order;
import com.example.sqlitedatabase.SQLiteJDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * The type Hello controller.
 */
public class HelloController implements  Initializable{

    /**
     * The Tableview.
     */
@FXML private TableView<Item> tableview;

    /**
     * The Column id.
     */
@FXML private TableColumn<Item,Integer> columnID;
    /**
     * The Column item.
     */
@FXML private TableColumn<Item,String> columnItem;
    /**
     * The Column color.
     */
@FXML private TableColumn<Item,String> columnColor;
    /**
     * The Column size.
     */
@FXML private TableColumn<Item,String> columnSize;
    /**
     * The Column price.
     */
@FXML private TableColumn<Item,Double> columnPrice;
    /**
     * The Column stock.
     */
@FXML private TableColumn<Item,Integer> columnStock;
    /**
     * The Column description.
     */
@FXML private TableColumn<Item,String> columnDescription;

    /**
     * The Checkbyid.
     */
@FXML private TextField checkbyid = new TextField();
    /**
     * The Availability.
     */
@FXML private TextField availability = new TextField();
    /**
     * The Itemdescription.
     */
@FXML private Label itemdescription;


    /**
     * The Orderitemid.
     */
@FXML private TextField orderitemid = new TextField();
    /**
     * The Orderlist.
     */
@FXML private TextArea orderlist = new TextArea();

    /**
     * The Creditcardid.
     */
@FXML private TextField creditcardid = new TextField();
    /**
     * The Password.
     */
@FXML private TextField password = new TextField();
    /**
     * The Orderinfo.
     */
@FXML private TextField orderinfo = new TextField();

    /**
     * The Name.
     */
@FXML private TextField name = new TextField();
    /**
     * The Gender.
     */
@FXML private ChoiceBox gender;
    /**
     * The Gender list.
     */
ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female","Others");
    /**
     * The Address.
     */
@FXML private TextArea address = new TextArea();

    /**
     * The Order.
     */
HashMap<Integer,Integer> order = new HashMap<>();
    /**
     * The Order area.
     */
String orderArea = "";
    /**
     * The Total price.
     */
double totalPrice = 0.0;
    /**
     * The Order id list.
     */
ArrayList<Integer> orderIDList = new ArrayList<>();


    /**
     * Initialize.
     *
     * @param url the url
     * @param rb the rb
     */
@FXML
    public void initialize(URL url, ResourceBundle rb){
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DATA");

            ObservableList<Item> items = FXCollections.observableArrayList();
            tableview.setEditable(true);
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
            columnItem.setCellValueFactory(new PropertyValueFactory<>("itemitem"));
            columnColor.setCellValueFactory(new PropertyValueFactory<>("itemcolor"));
            columnSize.setCellValueFactory(new PropertyValueFactory<>("itemsize"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("itemprice"));
            columnStock.setCellValueFactory(new PropertyValueFactory<>("itemstock"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
            tableview.setItems(items);

            stmt.close();
            c.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        gender.setItems(genderList);
    }

    /**
     * On refresh button click.
     */
@FXML
    protected void onRefreshButtonClick(){
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DATA");

            ObservableList<Item> items = FXCollections.observableArrayList();
            tableview.setEditable(true);
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
            columnItem.setCellValueFactory(new PropertyValueFactory<>("itemitem"));
            columnColor.setCellValueFactory(new PropertyValueFactory<>("itemcolor"));
            columnSize.setCellValueFactory(new PropertyValueFactory<>("itemsize"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("itemprice"));
            columnStock.setCellValueFactory(new PropertyValueFactory<>("itemstock"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("itemdescription"));
            tableview.setItems(items);

            stmt.close();
            c.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }



        //TODO: refresh the tableview
    }

    /**
     * On catalog button click.
     *
     * @throws Exception the exception
     */
@FXML
    protected void onCatalogButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Catalog.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Random Catalog");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * On stocktake report button click.
     *
     * @throws Exception the exception
     */
@FXML
    protected void onStocktakeReportButtonClick() throws Exception{

         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reports.fxml"));
         Parent root1 = (Parent) fxmlLoader.load();
         Stage stage = new Stage();
         stage.setTitle("Stocktake Report");
         stage.setScene(new Scene(root1));
         stage.show();
    }

    /**
     * On customer button click.
     *
     * @throws Exception the exception
     */
@FXML
    protected void onCustomerButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Customer Report");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * On quarterly button click.
     *
     * @throws Exception the exception
     */
@FXML
    protected void onQuarterlyButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuarterlyReport.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Quarterly Report");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * On top item button click.
     *
     * @throws Exception the exception
     */
@FXML
    protected void onTopItemButtonClick() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TopItems.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Top 10 Items");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * On check button click.
     */
@FXML
    protected void onCheckButtonClick(){
        int id = Integer.parseInt(checkbyid.getText());
        int stock = 0; String description = "";
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DATA");
            while(rs.next()){
                int iddb = rs.getInt("id");
                if(iddb == id){
                    stock = rs.getInt("stock");
                    description = rs.getString("description");
                    break;
                }
                description = "Item Not Found!";
            }
            stmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        availability.setText(stock+"");
        itemdescription.setText(description);
    }

    /**
     * On add button click.
     */
@FXML
    protected void onAddButtonClick(){
        int id = Integer.parseInt(checkbyid.getText());
        int stock = 0; String description = "";
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DATA");
            while(rs.next()){
                int iddb = rs.getInt("id");
                if(iddb == id){
                    stock = rs.getInt("stock")+1;
                    description = rs.getString("description");
                    break;
                }
                description = "Item Not Found!";
            }
            stmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        availability.setText(stock+"");
        itemdescription.setText(description);
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            PreparedStatement pstmt = c.prepareStatement("UPDATE DATA SET stock =? WHERE id=?");
            pstmt.setInt(1,stock);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
            pstmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * On order button click.
     */
@FXML
    protected void onOrderButtonClick(){
        int id = Integer.parseInt(orderitemid.getText());
        int stock = 0; double price = 0.0; String description = "";
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DATA");
            while(rs.next()){
                int iddb = rs.getInt("id");
                if(iddb == id){
                    stock = rs.getInt("stock")-1;
                    price = rs.getDouble("price");
                    description = rs.getString("description");
                    break;
                }
                description = "Item Not Found!";
            }
            stmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            PreparedStatement pstmt = c.prepareStatement("UPDATE DATA SET stock =? WHERE id=?");
            pstmt.setInt(1,stock);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
            pstmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        orderitemid.setText("");
        String add = "Item: "+description+"  $"+price+"\n";
        orderArea += add;
        totalPrice += price;
        orderlist.setText(orderArea+"\n"+"Total Price: $"+totalPrice);
        order.put(id,stock);
        orderIDList.add(id);
    }

    /**
     * On checkout button click.
     */
@FXML
    protected void onCheckoutButtonClick(){
        int orderID = 0;
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
            while(rs.next()){
                orderID = rs.getInt("orderid");
            }
            orderID++;
            stmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        orderArea = "";

        String cardid = creditcardid.getText();
        String pw = password.getText();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        for(int i=0;i<orderIDList.size();i++){
            try{
                Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
                PreparedStatement pstmt = c.prepareStatement("INSERT INTO ORDERS VALUES(?,?,?,?,?,?,?)");
                pstmt.setInt(1,orderID);
                pstmt.setString(2,cardid);
                pstmt.setString(3,pw);
                pstmt.setString(4,dateTime.format(formatter));
                pstmt.setDouble(5,totalPrice);
                pstmt.setInt(6,orderIDList.get(i));
                if(order.get(orderIDList.get(i))<0){
                    pstmt.setString(7,"Backed");
                }else{
                    pstmt.setString(7,"");
                }
                pstmt.executeUpdate();
                pstmt.close();
                c.close();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }

        String cName = name.getText();
        String cGender = (String) gender.getValue();
        String cAddress = address.getText();

        //check if the creditcard exists
        boolean cardExisted = false;
        double priceExisted = 0.0;
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while(rs.next()){
                if(cardid.equals(rs.getString("creditcardid"))){
                    cardExisted = true;
                    priceExisted = rs.getDouble("price");
                    break;
                }
            }
            stmt.close();
            c.close();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        if(cardExisted){
            try{
                Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
                PreparedStatement pstmt = c.prepareStatement("UPDATE customers SET price =? WHERE creditcardid=?");
                pstmt.setDouble(1,priceExisted+totalPrice);
                pstmt.setString(2,cardid);
                pstmt.executeUpdate();
                pstmt.close();
                c.close();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }else{
            try{
                Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
                PreparedStatement pstmt = c.prepareStatement("INSERT INTO customers VALUES(?,?,?,?,?)");
                pstmt.setString(1,cardid);
                pstmt.setString(2,cName);
                pstmt.setString(3,cGender);
                pstmt.setString(4,cAddress);
                pstmt.setDouble(5,totalPrice);
                pstmt.executeUpdate();
                pstmt.close();
                c.close();
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }

        order.clear();
        orderIDList.clear();
        orderlist.setText("");
        creditcardid.setText("");
        password.setText("");
        name.setText("");
        address.setText("");
        totalPrice = 0.0;
    }
}