package software.unf.dk.timetracker;

import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

class Classification {
    private String name;



    private int amount = 10;

    public static ConcurrentHashMap<String, Classification> classificationMap = new ConcurrentHashMap<>();

    public Classification(String name) {
        this.name = name;
    }
    private ArrayList<Action> actions;


//    public ArrayList<Action>

    public String getName() {

        return name;
    }

    public int getAmount() {
        return amount;
    }
    public void setName(String name){
        this.name = name;

    }

    public static ArrayList<Classification> mapToList(ConcurrentHashMap<String, Classification> map) {
        ArrayList<Classification> list = new ArrayList<>();
        for (Map.Entry<String, Classification> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
    
    public static ConcurrentHashMap<String, Classification> listToMap(ArrayList<Classification> list) {
        ConcurrentHashMap<String, Classification> map = new ConcurrentHashMap<>();
        for (Classification c : list) {
            map.put(c.getName(), c);
        }
        return map;
    }

    public static ArrayList<String> mapToStringList(ConcurrentHashMap<String, Classification> map) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, Classification> entry : map.entrySet()) {
            list.add(entry.getValue().getName());
        }
        return list;
    }


    // TEMP:
    public static boolean classificationExists(Classification classification){

        // loops through all classifications in the hasMap, if found return true.
        for (Classification c :
                classificationMap.values()) {
            if (classification == c) {
                return true;
            }
        }

        // Did not find anything.
        return false;
    }
}
