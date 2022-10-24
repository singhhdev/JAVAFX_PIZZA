import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.net.SocketFlow;

import java.io.IOException;

public class StatusController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    Text realTimeStatus;

    public void changeStatus(String name){
        realTimeStatus.setText("Status: " + name);
    }

    public void logOut(MouseEvent event) throws IOException {

        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/Login.fxml"));
        root = login.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome || Login");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void setSceneStatus(String pizzaStatus) {
    }
}
