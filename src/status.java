import org.omg.CORBA.WStringSeqHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class status {
    public String pizzaStatus;
    private Orders order = new Orders();
    private ArrayList<Pizza> orders = new ArrayList<Pizza>();

    status() throws FileNotFoundException {
        orders = order.readOrders();
    }
    public void setPizzaStatus(String newStatus){
        this.pizzaStatus = newStatus;

    }
    public String orderstoString(String ID){
        String status = "No Order";
        for(int x = 0; x < orders.size(); x++){
            if(orders.get(x).usrID.equals(ID)){
                status = orders.get(x).status;
            }
        }
        return status;
    }
    public  String getPizzaStatus(ArrayList<Pizza> orders) throws FileNotFoundException {

        return pizzaStatus;
    }
}
