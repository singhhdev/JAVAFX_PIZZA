package controllers;

import java.util.HashMap;
public class IDS {
    // we will use hash map here and check to see if the id is equl and to which it corresponds with:
    HashMap<String, String> ids = new HashMap<String,String>();
    // constructor:
    public IDS(){
        // student ids:
        ids.put("912923", "Student");
        ids.put("122222", "Student");
        ids.put("121111", "Student");
        ids.put("123456", "Student");
        // agent ids:
        ids.put("182312", "Agent");
        ids.put("392912", "Agent");
        ids.put("999212", "Agent");
        ids.put("999999", "Agent");
        //chef ids:
        ids.put("812312", "Chef");
        ids.put("832923", "Chef");
        ids.put("119231", "Chef");
        ids.put("000000", "Chef");

    }

    public HashMap<String, String> getValidIds(){
        return ids;

    }


}