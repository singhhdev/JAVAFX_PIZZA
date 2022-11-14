import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.rmi.activation.ActivationInstantiator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class AgentController implements Initializable {
   @FXML
   private ListView<String> listView;
   @FXML
   private Label orderLabel;
   @FXML
   private Button sendToChefButton;
   @FXML
   private  Button refreshButton;
   @FXML
   private Label setOrderLabel;
   private Orders orders = new Orders();
   private Pizza pizzaOrder = new Pizza();
   private Agent agent = new Agent();
   private ArrayList<Pizza> currentOrders = new ArrayList<Pizza>();
   private String selectedOrder = " ";
    private Stage stage;
    private Scene scene;
    private Parent root;
   Alert logoutMessage = new Alert(Alert.AlertType.NONE);

    public void displayOrders() throws FileNotFoundException {
     currentOrders = agent.getOrders();
   }
   public void changeStatus(Pizza order) throws IOException {
      orders.changeAgentStatus(order);
   }
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      String[] pizzaView = new String[30];
      try {
         displayOrders();
      } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
      }
      for(int x = 0; x < pizzaView.length; x++){
         pizzaView[x] = " ";
      }
      String id = "";
      String status ="";
      String pizzaType = "";
      String extraCheese = "";
      String mushroom = "";
      String olives = "";
      String onion = "";
      String time = " ";
      for(int x = 0; x < currentOrders.size(); x++){
         id = currentOrders.get(x).usrID;
         status = currentOrders.get(x).status;
         pizzaType = currentOrders.get(x).pizzaType;
         extraCheese = currentOrders.get(x).extraCheeseTopping;
         mushroom = currentOrders.get(x).mushroomTopping;
         olives = currentOrders.get(x).olivesTopping;
         onion = currentOrders.get(x).onionTopping;
         time = currentOrders.get(x).pizzaPickUptime;
         String displayOrder = id + "|" + status + "|" + pizzaType + "|" + extraCheese + "|" + mushroom + "|" + olives + "|" + onion + "|" + time;
         pizzaView[x] = displayOrder;
      }
      listView.getItems().addAll(pizzaView);

      listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
         {
               selectedOrder = listView.getSelectionModel().getSelectedItem();
               String[] items = selectedOrder.split("\\|");
               // get the selected order from agent:
               String id = items[0];
               String status = "SENT TO CHEF";
               String pizzaType = items[2];
               String extraCheese = items[3];
               String mushroom = items[4];
               String olives = items[5];
               String onion = items[6];
               String time = items[7];
               Pizza sendToChef = new Pizza(pizzaType, mushroom, extraCheese,onion,olives,id,"SENT TO CHEF", time);
               setOrderLabel.setText("User ID : " + items[0] + " Pizza Type: " +items[2]);
            }
      });
   }
    public void refreshList(ActionEvent actionEvent) throws  IOException{
        listView.getItems().clear();
        String[] pizzaViewv = new String[30];
        try {
            displayOrders();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        String timee = " ";
        for(int x = 0; x < currentOrders.size(); x++){
            idd = currentOrders.get(x).usrID;
            statuss = currentOrders.get(x).status;
            pizzaTypee = currentOrders.get(x).pizzaType;
            extraCheesee = currentOrders.get(x).extraCheeseTopping;
            mushroomm = currentOrders.get(x).mushroomTopping;
            olivess = currentOrders.get(x).olivesTopping;
            onionn = currentOrders.get(x).onionTopping;
            timee = currentOrders.get(x).pizzaPickUptime;
            String displayOrderr = idd + "|" + statuss + "|" + pizzaTypee + "|" + extraCheesee + "|" + mushroomm + "|" + olivess + "|" + onionn + "|" + timee;
            pizzaViewv[x] = displayOrderr;
        }
        listView.getItems().addAll(pizzaViewv);
    }
    public void statusChanger(ActionEvent actionEvent) throws IOException {
        selectedOrder = listView.getSelectionModel().getSelectedItem();
        String[] items = selectedOrder.split("\\|");
        // get the selected order from agent:
        String id = items[0];
        String status = "Preparing";
        String pizzaType = items[2];
        String extraCheese = items[3];
        String mushroom = items[4];
        String olives = items[5];
        String onion = items[6];
        String time = items[7];
        Pizza sendToChef = new Pizza(pizzaType, mushroom, extraCheese,onion,olives,id,status, time);
        orders.changeAgentStatus(sendToChef);
        //system.out.println("Order sent to chef!");
        logoutMessage.setTitle("Order Approved");
        logoutMessage.setAlertType(Alert.AlertType.CONFIRMATION);
        logoutMessage.setContentText("Order number: " + id + " sent to Chef!");
        logoutMessage.show();

    }
    public void signOut(MouseEvent mouseEvent) throws IOException {

        logoutMessage.setTitle("Logged Out");
        logoutMessage.setAlertType(Alert.AlertType.CONFIRMATION);
        logoutMessage.setContentText("Agent Signed Out");
        logoutMessage.show();

        FXMLLoader login = new FXMLLoader(getClass().getResource("resources/Login.fxml"));
        root = login.load();
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("Welcome || Login");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }}
