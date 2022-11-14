import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;



public class Agent {
    private Orders order = new Orders();
    private ArrayList<Pizza> orders = new ArrayList<Pizza>();  //arraylist to store the orders placed by students
    private Pizza pizza = new Pizza();
    public ArrayList<Pizza> getOrders() throws FileNotFoundException {
        orders = order.readOrders();
        return orders;   // returns the oders stored in the arraylist
    }
    
    public void displayOrdersToAgent(){
        //displays the order to the agent form the arraylist
    }
    
    public void sendOrderToChef(){
        //used to send the order to the chef
    }
}
