package sample;

import com.github.fxrouter.FXRouter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.view.WindowCode.MainWindow;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(Main.class.getResource("view/WindowFXML/MainWindowFXML.fxml"));
        primaryStage.setTitle("Облік комп'ютерної техніки");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void isFrameTechnique() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("view/WindowFXML/TechniqueTableFXML.fxml"));
        primaryStage.setTitle("Техніка");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void isFrameEmployee() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("view/WindowFXML/EmployeeTableFXML.fxml"));
        primaryStage.setTitle("Працівники");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void isFrameEnterprise() throws IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("view/WindowFXML/EnterpriseTableFXML.fxml"));
        primaryStage.setTitle("Компанії");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}