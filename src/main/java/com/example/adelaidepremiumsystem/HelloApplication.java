package com.example.adelaidepremiumsystem;

import com.example.sqlitedatabase.SQLiteJDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** The type Hello application. */
public class HelloApplication extends Application {
  /**
   * Start.
   *
   * @param stage the stage
   * @throws IOException the io exception
   */
  @Override
  public void start(Stage stage) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Adelaide Premium System");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
        launch();
    }
}