public class Pizza {
    
    
    //attribues required to store the selctions made
    String pizzaType = "  ";
    String mushroomTopping =  " ";
    String extraCheeseTopping =   " ";
    String onionTopping =   " ";
    String olivesTopping =  " ";
    String usrID;
    String status;
    String pizzaPickUptime;
    
    //default pizza constructor
    Pizza(){}
    
    /*pizza constructor to create a pizza object for each order created
    it takes the toppings, pizzaType user id,  status and pickup time.*/
    Pizza(String pizzaType, String mushroomTopping, String extraCheeseTopping, String onionTopping, String olivesTopping, String usrID, String status, String pizzaPickupTime){
        this.pizzaType = pizzaType;
        this.mushroomTopping = mushroomTopping;
        this.extraCheeseTopping = extraCheeseTopping;
        this.onionTopping = onionTopping;
        this.olivesTopping = olivesTopping;
        this.usrID = usrID;
        this.status = status;
        this.pizzaPickUptime = pizzaPickupTime;
    }

    public void setStatus(String status){
        
        this.status = status;                //used to get the status from the agent and the chef
        
    }
    public void setUsrID(String ID){
        
        this.usrID = ID;                       // sets the user id attribute to the id entered by the user
        
    }
    
    
   /*all the methods here are used to initialize the attributes with the the values chosen by the student*/
    public void setPizzaType(String type){
        
        this.pizzaType = type;
        
    }

    public void setMushroomTopping(String type){
        
        this.mushroomTopping = type;
        
    }
    
    public void setExtraCheeseTopping(String type){
        
        this.extraCheeseTopping = type;
        
    }
    
    public void setOnionTopping(String type){
        
        this.onionTopping = type;
    }
    
    public void setOlivesTopping(String type){
        
        this.olivesTopping = type;
    }

}
