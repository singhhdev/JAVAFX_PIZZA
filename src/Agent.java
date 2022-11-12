import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Agent {
    private Orders order = new Orders();
    private ArrayList<Pizza> orders = new ArrayList<Pizza>();
    private Pizza pizza = new Pizza();
    public ArrayList<Pizza> getOrders() throws FileNotFoundException {
        orders = order.readOrders();
        return orders;
    }
    public void displayOrdersToAgent(){
    }
    public void sendOrderToChef(){
    }






}
