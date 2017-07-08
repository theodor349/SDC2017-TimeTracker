package software.unf.dk.timetracker;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

class Classification {
    // Static.
    public static ConcurrentHashMap<String, Classification> classificationMap = new ConcurrentHashMap<>();

    // Non-statics.
    private String name;
    private ArrayList<Action> actions;

    /**
     * Maybe use a in int ID instead of the name. For what happens when the name changes?
     * Do we want to delete data when "deleting" it or should we just mark it as "deleted" (with a bool)?
     * Need to check whether or not there is an instance of this, before adding one to the map.
     *
     */

    // Instantiate method.
    public Classification(String name) {
        this.name = name;

        // Instantiate Arrays and list...
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
    }
    // Return actions list.
    public ArrayList<Action> getActions(){
        return actions;
    }

    // Return classifications. (HashMap)
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
