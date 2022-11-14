import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {
    private ArrayList<Pizza> orders;
    private File file = new File("output.txt");

    public Orders(){
        orders = new ArrayList<Pizza>();
    }
    public ArrayList<Pizza> readOrders() throws FileNotFoundException {
        Scanner readFile = new Scanner(file);
        ArrayList<Pizza> sendAgentOrder = new ArrayList<Pizza>();

        while(readFile.hasNextLine()){
            String line = readFile.nextLine();
            String[] items = line.split("\\|");
            String id = items[0];
            String status = items[1];
            String pizzaType = items[2];
            String extraCheese = items[3];
            String mushroom = items[4];
            String olives = items[5];
            String onion = items[6];
            String time = items[7];
            Pizza readPizzas = new Pizza(pizzaType, mushroom, extraCheese,onion,olives,id,status, time);
            sendAgentOrder.add(readPizzas);
        }
       // readFile.close();
        return sendAgentOrder;
    }
    // prints to file
    public void printToFile() throws FileNotFoundException, IOException
    {
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            int datList = orders.size();
            for (Pizza elem : orders){
                if (elem.usrID == " ") {
                    pw.print("-|");
                }else{
                    pw.print(elem.usrID + "|");
                }
                if (elem.status == " ") {
                    pw.print("-|");
                }else{
                    pw.print(elem.status + "|");
                }
                if (elem.pizzaType == " ") {
                    pw.print("-|");
                }else{
                    pw.print(elem.pizzaType + "|");
                }
                if (elem.extraCheeseTopping == " ") {
                    pw.print("-|");
                }else{
                    pw.print(elem.extraCheeseTopping + "|");
                }
                if (elem.mushroomTopping == " ") {
                    pw.print("-|");
                }else{
                    pw.print(elem.mushroomTopping + "|");
                }
                if (elem.olivesTopping == " ") {
                    pw.print("-|");
                }else{
                    pw.print(elem.olivesTopping+ "|");
                }
                if (elem.onionTopping == " ") {
                    pw.print("-|");
                }else{
                    pw.print(elem.onionTopping);
                    pw.print("-|");
                }
                if(elem.pizzaPickUptime == " " ){
                    pw.print("-|");
                }else{
                    pw.print(elem.pizzaPickUptime);
                }
                pw.println(" ");
            }
            pw.close();
    }
    public void changeAgentStatus(Pizza order) throws IOException
    {
        ArrayList<Pizza> newOrders = new ArrayList<Pizza>();
        newOrders = readOrders(); // all the orders stored in this temp file!
        orders = newOrders;
        for(int x = 0; x < orders.size(); x ++){
            if(orders.get(x).usrID.equals(order.usrID)){
                orders.get(x).status = order.status;     // for the agent view, if the order is
            }
        }
        file.delete(); // delete the file
        // write to file with the new arraylist!
        printToFile(); // make new file again
    }
    // will add the orders to the database arraylist
    public void addNewOrder(Pizza order) throws IOException
    {
        orders.add(order); // now the order is added here
        printToFile();
    }
    public ArrayList<Pizza> getOrders(){
        return this.orders;
    }
}
