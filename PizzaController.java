import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzaController {
    @FXML
    RadioButton cheese;
    @FXML
    RadioButton pepporini;
    @FXML
    RadioButton veggi;
    //checkboxes
    @FXML
    CheckBox mushroom;
    @FXML
    CheckBox extraCheese;
    @FXML
    CheckBox onion;
    @FXML
    CheckBox olives;

    @FXML
    Button orderSend;

    String pizzaType = "  ";

    String mushroomTopping =  null;
    String extraCheeseTopping =  null;
    String onionTopping =  null;
    String olivesTopping = null;

    String usrID = null;
    Alert logoutMessage = new Alert(Alert.AlertType.NONE);
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void setID(String ID){
        usrID = ID;
    }
    // now do the pizza being selected here:
    public void pizzaType(MouseEvent mouseEvent)
    {

        if(pepporini.isSelected()) {
            cheese.setSelected(false);
            veggi.setSelected(false);
            //pepporini.setSelected(true);
            pizzaType = "Pepporini";
        }
        if(cheese.isSelected()) {
            pepporini.setSelected(false);
            veggi.setSelected(false);
            cheese.setSelected(true);
            pizzaType = "Cheese";
        }
        if (veggi.isSelected()){
            cheese.setSelected(false);
            pepporini.setSelected(false);
            //veggi.setSelected(true);
            pizzaType = "Veggie";
        }
        if(mushroom.isSelected()){
            mushroomTopping = "Mushroom";
            System.out.println("Mushroom");
        }
        if(extraCheese.isSelected()){
            mushroomTopping = "Extra-Cheese";
            System.out.println("Extra Chesse");

        }
        if(onion.isSelected()){
            mushroomTopping = "Onion";
            System.out.println("Onion");

        }
        if(olives.isSelected()){
            mushroomTopping = "Olives";
            System.out.println("Olives");

        }
    }
    public void finializeOrder(MouseEvent mouseEvent) throws IOException {
        Pizza newPizza = new Pizza(pizzaType,mushroomTopping,extraCheeseTopping, onionTopping, olivesTopping, usrID, "SENT");
        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/StatusView.fxml"));
        root = login.load();
        StatusController changeCurrentStatus = login.getController();
        changeCurrentStatus.changeStatus(newPizza.status);

    }

    public void signOut(MouseEvent mouseEvent) throws IOException {

        logoutMessage.setTitle("Logged Out");
        logoutMessage.setAlertType(Alert.AlertType.CONFIRMATION);
        logoutMessage.setContentText("Student Signed Out");
        logoutMessage.show();

        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/Login.fxml"));
        root = login.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Welcome || Login");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}