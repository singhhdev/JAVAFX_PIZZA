import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChefController implements Initializable {
    @FXML
    private ListView<String> listView = new ListView<String>();
    @FXML
    private ListView<String> listViewTwo = new ListView<String>();
    private Orders order = new Orders();
    private Pizza currentPizzaOrder = new Pizza();
    private ArrayList<Pizza> allOrders = new ArrayList<Pizza>();
    private ArrayList<Pizza> validOrders = new ArrayList<Pizza>();
    private ArrayList<Pizza> completedOrders = new ArrayList<Pizza>();

    private String selectedOrder = " ";
    private Stage stage;
    private Scene scene;
    private Parent root;
    Alert logoutMessage = new Alert(Alert.AlertType.NONE);

    // first get the orders here from agent:
    public void displayOrders() throws FileNotFoundException {
        allOrders = order.readOrders();
    }
    // now that the orders are here, we need to display them to the chef
    // only if the orders are valid or preparing from agent
    public void validateOrders(){
        for(int x = 0; x < allOrders.size(); x++){
            if(allOrders.get(x).status.equals("Preparing")){
                validOrders.add(allOrders.get(x));
            }
        }
    }
    public void validateCompletedOrders(){
        for(int x = 0; x < allOrders.size(); x++){
            if(allOrders.get(x).status.equals("Completed!")){
                completedOrders.add(allOrders.get(x));
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] pizzaView = new String[30];
        try {
            displayOrders();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        validateOrders();
        for (int x = 0; x < pizzaView.length; x++) {
            pizzaView[x] = " ";
        }
        String id = "";
        String status = "";
        String pizzaType = "";
        String extraCheese = "";
        String mushroom = "";
        String olives = "";
        String onion = "";
        String time = " ";
        for (int x = 0; x < validOrders.size(); x++) {
            id = validOrders.get(x).usrID;
            status = validOrders.get(x).status;
            pizzaType = validOrders.get(x).pizzaType;
            extraCheese = validOrders.get(x).extraCheeseTopping;
            mushroom = validOrders.get(x).mushroomTopping;
            olives = validOrders.get(x).olivesTopping;
            onion = validOrders.get(x).onionTopping;
            time = validOrders.get(x).pizzaPickUptime;
            String displayOrder = id + "|" + status + "|" + pizzaType + "|" + extraCheese + "|" + mushroom + "|" + olives + "|" + onion + "|" + time;
            pizzaView[x] = displayOrder;
        }
        listView.getItems().addAll(pizzaView);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedOrder = listView.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void changeStatus() throws IOException {
       // selectedOrder = listView.getSelectionModel().getSelectedItem();
        String[] items = selectedOrder.split("\\|");

        // get the selected order from agent:

        String id = items[0];
        String status = "Completed!";
        String pizzaType = items[2];
        String extraCheese = items[3];
        String mushroom = items[4];
        String olives = items[5];
        String onion = items[6];
        String time = items[7];
        Pizza sendToChef = new Pizza(pizzaType, mushroom, extraCheese,onion,olives,id,status, time);
        order.changeAgentStatus(sendToChef);

        completedOrders.add(sendToChef);
    }
    public void moveToCompletedOrder(ActionEvent actionEvent) throws  IOException{

    }
    public void signOut(MouseEvent mouseEvent) throws IOException {

        logoutMessage.setTitle("Logged Out");
        logoutMessage.setAlertType(Alert.AlertType.CONFIRMATION);
        logoutMessage.setContentText("Chef Signed Out");
        logoutMessage.show();

        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/Login.fxml"));
        root = login.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Welcome || Login");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void moveToCompleted(ActionEvent actionEvent) throws IOException {
        changeStatus(); // change the status
        String[] pizzaViewv = new String[30];

        for(int x = 0; x < pizzaViewv.length; x++){
            pizzaViewv[x] = " ";
        }

        String idd = "";
        String statuss ="";
        String pizzaTypee = "";
        String extraCheesee = "";
        String mushroomm = "";
        String olivess = "";
        String onionn = "";
        String time = "";
        for(int x = 0; x < completedOrders.size(); x++){
            idd = completedOrders.get(x).usrID;
            statuss = completedOrders.get(x).status;
            pizzaTypee = completedOrders.get(x).pizzaType;
            extraCheesee = completedOrders.get(x).extraCheeseTopping;
            mushroomm = completedOrders.get(x).mushroomTopping;
            olivess = completedOrders.get(x).olivesTopping;
            onionn = completedOrders.get(x).onionTopping;
            time = completedOrders.get(x).pizzaPickUptime;
            String displayOrderr = idd + "|" + statuss + "|" + pizzaTypee + "|" + extraCheesee + "|" + mushroomm + "|" + olivess + "|" + onionn + "|" + time;
            pizzaViewv[x] = displayOrderr;
        }
        listViewTwo.getItems().addAll(pizzaViewv);
    }
}

