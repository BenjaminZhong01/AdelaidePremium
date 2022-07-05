package com.example.adelaidepremiumsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/** The type Customer report controller. */
public class CustomerReportController implements Initializable {

  /** The Stacked bar chart. */
  @FXML private StackedBarChart stackedBarChart;

  /** The Pie chart. */
  @FXML private PieChart pieChart;

  /**
   * Initialize.
   *
   * @param url the url
   * @param rb the rb
   */
  public void initialize(URL url, ResourceBundle rb) {

        int rangeA = 0; //0-200
        int rangeB = 0; //200-500
        int rangeC = 0; //500-1000
        int rangeD = 0; //1000-1500
        int rangeE = 0; //>1500

        int male = 0;
        int female = 0;
        int others = 0;

        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:AdelaidePremiumSystem.db");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");

            while(rs.next()){
                String gender = rs.getString("gender");
                switch (gender){
                    case "Male": male++; break;
                    case "Female": female++; break;
                    case "Others": others++; break;
                }
                double price = rs.getDouble("price");
                if(price<=200){
                    rangeA++;
                }else if(price>200&&price<=500){
                    rangeB++;
                }else if(price>500&&price<=1000){
                    rangeC++;
                }else if(price>1000&&price<=1500){
                    rangeD++;
                }else{
                    rangeE++;
                }
            }
            stmt.close();
            c.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();

        series1.setName("Price Range");
        series1.getData().add(new XYChart.Data<>("<200",rangeA));
        series1.getData().add(new XYChart.Data<>("200-500",rangeB));
        series1.getData().add(new XYChart.Data<>("500-1000",rangeC));
        series1.getData().add(new XYChart.Data<>("1000-1500",rangeD));
        series1.getData().add(new XYChart.Data<>(">1500",rangeE));

        stackedBarChart.getData().addAll(series1);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Male", male),
                        new PieChart.Data("Female", female),
                        new PieChart.Data("Non-binary", others));
        pieChart.setData(pieChartData);
    }
}
