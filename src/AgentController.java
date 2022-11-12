import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.net.URL;
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
   private Label setOrderLabel;
   private Orders orders;
   private Pizza pizzaOrder;
   private Agent agent = new Agent();
   private ArrayList<Pizza> currentOrders = new ArrayList<Pizza>();
   private String selectedOrder = " ";
   public void displayOrders() throws FileNotFoundException {
     currentOrders = agent.getOrders();
   }
   public void changeStatus(Pizza order) throws FileNotFoundException {
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

      for(int x = 0; x < currentOrders.size(); x++){
         id = currentOrders.get(x).usrID;
         status = currentOrders.get(x).status;
         pizzaType = currentOrders.get(x).pizzaType;
         extraCheese = currentOrders.get(x).extraCheeseTopping;
         mushroom = currentOrders.get(x).mushroomTopping;
         olives = currentOrders.get(x).olivesTopping;
         onion = currentOrders.get(x).onionTopping;

         String displayOrder = id + "|" + status + "|" + pizzaType + "|" + extraCheese + "|" + mushroom + "|" + olives + "|" + onion;
         pizzaView[x] = displayOrder;
      }
      listView.getItems().addAll(pizzaView);
      listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
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
               Pizza sendToChef = new Pizza(pizzaType, mushroom, extraCheese,onion,olives,id,status);
               try {
                  changeStatus(sendToChef); // the agent has the order now
               } catch (FileNotFoundException e) {
                  throw new RuntimeException(e);
               }
               setOrderLabel.setText("User ID : " + items[0] + " Pizza Type: " +items[2]);
            }
      });
   }

}
