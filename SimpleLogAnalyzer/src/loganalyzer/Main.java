package loganalyzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loganalyzer.service.LogAnalyzer;
import loganalyzer.service.ILogAnalyzer;
import loganalyzer.servicelocator.ServiceLocator;


public class Main extends Application {

    public static void main(String[] args) {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        ILogAnalyzer logAnalyzer = new LogAnalyzer();
        serviceLocator.register(ILogAnalyzer.class, logAnalyzer);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("SimpleLogAnalyser");
        primaryStage.setScene(new Scene(root, 470, 400));
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
