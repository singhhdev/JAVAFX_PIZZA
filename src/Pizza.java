public class Pizza {

    String pizzaType = "  ";

    String mushroomTopping =  " ";
    String extraCheeseTopping =   " ";
    String onionTopping =   " ";
    String olivesTopping =  " ";

    String usrID;
    String status;
    Pizza(){

    }
    Pizza(String pizzaType, String mushroomTopping, String extraCheeseTopping, String onionTopping, String olivesTopping, String usrID, String status){
        this.pizzaType = pizzaType;
        this.mushroomTopping = mushroomTopping;
        this.extraCheeseTopping = extraCheeseTopping;
        this.onionTopping = onionTopping;
        this.olivesTopping = olivesTopping;
        this.usrID = usrID;
        this.status = status;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public void setUsrID(String ID){
        this.usrID = ID;
    }
    public void setPizzaType(String type){
        this.pizzaType = type;
    }

    public void setMushroomTopping(String type){
        this.mushroomTopping = type;
    }public void setExtraCheeseTopping(String type){
        this.extraCheeseTopping = type;
    }public void setOnionTopping(String type){
        this.onionTopping = type;
    }
    public void setOlivesTopping(String type){
        this.olivesTopping = type;
    }

}
