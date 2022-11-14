import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent home = FXMLLoader.load(getClass().getResource("resources/Login.fxml"));
        Scene login = new Scene(home); 
        
        //starts with the welcome page
        primaryStage.setTitle("Pizza Order Page");
        primaryStage.setScene(login);
        primaryStage.show();
    }

}
