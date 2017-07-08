package software.unf.dk.timetracker;

import android.util.Log;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

class Classification {
    // Static.
    public static ConcurrentHashMap<String, Classification> classificationMap = new ConcurrentHashMap<>();

    // Non-statics.
    private String name;
    private ArrayList<Action> actions;






    // Instantiate method.
    public Classification(String name) {
        this.name = name;
        actions = new ArrayList<>();
    }

    // Name.
    public String getName() {

        return name;
    }
    public void setName(String name){
        this.name = name;

    }

    // Add actions.
    public void addAction(Action a){
        if(actions == null){
            actions = new ArrayList<>();
        }
        actions.add(a);
        if(name.equals("theodor")) {
            Log.e("Test", "HI AN ACTION HAVE BEEN ADDED, WITH CLASSIFICATION WITH NAME: " + name + this);
            if (actions == null)
                Log.e("Test", "(Actions) THE LIST DO NOT EXIST");
            else
                Log.e("Test", "(Actions) THE LIST EXIST AND IS: " + actions.size() + " LONG " + this);
        }
    }
    // Return actions list.
    public ArrayList<Action> getActions(){
        if(actions == null) {
            Log.e("Test", "(Actions Return) THE LIST DO NOT EXIST " + this);
            return null;
        }
        Log.e("Test", "(Actions Return) THE LIST EXIST AND IS: " + actions.size() + " LONG");
        return actions;
    }

    // Return classifications.
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
}
