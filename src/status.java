import org.omg.CORBA.WStringSeqHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class status {
    public String pizzaStatus;
    private Orders order = new Orders();
    private ArrayList<Pizza> orders = new ArrayList<Pizza>();  // Arraylist of type pizza to store all the orders

    status() throws FileNotFoundException {
        
        orders = order.readOrders();           //checks if the user is tryin to reorder
        
    }
    
    public void setPizzaStatus(String newStatus){
        
        this.pizzaStatus = newStatus;          //used to alter the status for each order

    }
    
    public String orderstoString(String ID){               // used to print the oder to the agent and chef
        
        String status = "No Order";
        for(int x = 0; x < orders.size(); x++){
            if(orders.get(x).usrID.equals(ID)){
                status = orders.get(x).status;
            }
        }
        
        return status; 
    }
    
    public  String getPizzaStatus(ArrayList<Pizza> orders) throws FileNotFoundException {

        return pizzaStatus;     //used to get the pizza status for each order.
        
    }
    
}
