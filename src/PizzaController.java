import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PizzaController implements Initializable {

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
    private ListView<String> listView = new ListView<String>();
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
    private String pizzaPickUpTime = "" ;
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
            //System.out.println("Mushroom");
        }
        if(extraCheese.isSelected()){
            extraCheeseTopping = "Extra-Cheese";
           // System.out.println("Extra Chesse");

        }
        if(onion.isSelected()){
            onionTopping = "Onion";
            //System.out.println("Onion");

        }
        if(olives.isSelected()){
            olivesTopping = "Olives";
           // System.out.println("Olives");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] times = {"9:00 AM", "10:00 AM", "11:00 AM", "12:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};
        listView.getItems().addAll(times);
        pizzaPickUpTime = listView.getSelectionModel().getSelectedItem();
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                pizzaPickUpTime = listView.getSelectionModel().getSelectedItem();
            }
        });
    }

    public String getPizzaTime(){
        return pizzaPickUpTime;
    }

    public void finializeOrder(MouseEvent mouseEvent) throws IOException {
        Pizza newPizza = new Pizza(pizzaType,mushroomTopping,extraCheeseTopping, onionTopping, olivesTopping, usrID, "SENT", pizzaPickUpTime);
        newOrder.addNewOrder((newPizza)); // the order is added to the arraylist

        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/StatusView.fxml"));
        root = login.load();
        StatusController changeCurrentStatus = login.getController();
        changeCurrentStatus.changeStatus(newPizza.status);

        logoutMessage.setTitle("Pizza Order Created!");
        logoutMessage.setAlertType(Alert.AlertType.CONFIRMATION);
        logoutMessage.setContentText("Waiting Approval...");
        logoutMessage.show();
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