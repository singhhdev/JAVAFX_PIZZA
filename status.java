public class status {
    public String pizzaStatus;
    status()
    {
        pizzaStatus = "NO ORDER";
    }
    public void setPizzaStatus(String newStatus){
        this.pizzaStatus = newStatus;

    }
    public  String getPizzaStatus(){
        return pizzaStatus;
    }
}
