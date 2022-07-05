module com.example.adelaidepremiumsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.adelaidepremiumsystem to javafx.fxml;
    exports com.example.adelaidepremiumsystem;
}