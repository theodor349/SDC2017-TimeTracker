package software.unf.dk.timetracker;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Classification {
    private String name;
    public static ConcurrentHashMap<String, Classification> classificationMap = new ConcurrentHashMap<>();

    public Classification(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
