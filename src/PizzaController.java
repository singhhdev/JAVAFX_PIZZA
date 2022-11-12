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

    String mushroomTopping =  " ";
    String extraCheeseTopping =  " ";
    String onionTopping =  " ";
    String olivesTopping = " ";

    String usrID = null;
    Alert logoutMessage = new Alert(Alert.AlertType.NONE);
    Orders newOrder = new Orders();

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
            extraCheeseTopping = "Extra-Cheese";
            System.out.println("Extra Chesse");

        }
        if(onion.isSelected()){
            onionTopping = "Onion";
            System.out.println("Onion");

        }
        if(olives.isSelected()){
            olivesTopping = "Olives";
            System.out.println("Olives");

        }
    }
    public void finializeOrder(MouseEvent mouseEvent) throws IOException {
        Pizza newPizza = new Pizza(pizzaType,mushroomTopping,extraCheeseTopping, onionTopping, olivesTopping, usrID, "SENT");
        newOrder.addNewOrder((newPizza)); // the order is added to the arraylist

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