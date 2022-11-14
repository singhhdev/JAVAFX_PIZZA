import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.net.SocketFlow;

import java.io.IOException;

public class StudentController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Button signOut;
    @FXML
    Button orderPizzaButton;
    @FXML
    Button statusButton;
    Alert logoutMessage = new Alert(Alert.AlertType.NONE);
    String usrID;
    public void setId(String id)
    {
        usrID = id;
    }
    public void logOut(MouseEvent event) throws IOException {
        logoutMessage.setTitle("Logged Out");
        logoutMessage.setAlertType(Alert.AlertType.CONFIRMATION);
        logoutMessage.setContentText("Student Signed Out");
        logoutMessage.show();

        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/Login.fxml"));
        root = login.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome || Login");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void sendOrder(MouseEvent mouseEvent) throws IOException {
        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/PizzaView.fxml"));
        root = login.load();
        PizzaController pizza =  login.getController();
        pizza.setID(usrID);
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Make Pizza");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void changeStatus(MouseEvent mouseEvent) throws IOException {
        FXMLLoader statusView = new FXMLLoader(getClass().getResource("resources/StatusView.fxml"));
        root = statusView.load();
        StatusController status =  statusView.getController();
        status.changeStatus(usrID);

        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("STATUS");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
}
