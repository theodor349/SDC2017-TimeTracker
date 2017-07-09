package software.unf.dk.timetracker;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Classification {
    // Static.
    public static ConcurrentHashMap<Integer, Classification> classificationMap = new ConcurrentHashMap<>();

    // Non-statics.
    private String name;
    private int id;
    private boolean visible = true;

    // Instantiate method. Called when reading file.
    public Classification(String name, int id, boolean visible) {
        this.name = name;
        this.id = id;
        this.visible = visible;
        if(name.equals("the"))
            Log.e("Test", this + "");
    }

    // Name.
    public String getName() {

        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    static boolean createNew(String name) {
        if (Classification.nameExists(name)) {
            return false;
        }
        int id = Classification.getUniqueId();
        if (id == -1) {
            Log.e("Classification", "Failed to make new category");
            return false;
        }
        Log.e("Test", "Creating class "+name+" with id "+id);
        Classification c = new Classification(name, id, true);
        Classification.classificationMap.put(id, c);
        Log.e("Test", "Address is " + classificationMap.get(id));
        return true;
    }
    static int getUniqueId() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (!classificationMap.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }

    static Classification getClassificationByName(String name){
        for (Map.Entry<Integer, Classification> e: classificationMap.entrySet()) {
            if(e.getValue().getName().equals(name)) {
                Log.e("Test", e.getValue() +"");
                return e.getValue();
            }
        }
        return null;
    }

    public static int getIdByName(String name){
        for (Map.Entry<Integer, Classification> e: classificationMap.entrySet()) {
            Log.e("Test getId", "name: "+name+" entry name "+ e.getValue().getName() + " entry id " + e.getValue().getId());
            if(e.getValue().getName().equals(name)) {
                Log.e("Test", e.getValue() +"");
                return e.getValue().getId();
            }
        }
        return -1;
    }

    private static boolean nameExists(String name) {
        for (Classification c : mapToList(classificationMap)) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Return classifications. (HashMap)
    static ArrayList<Classification> mapToList(ConcurrentHashMap<Integer, Classification> map) {
        ArrayList<Classification> list = new ArrayList<>();
        for (Map.Entry<Integer, Classification> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }
    static ConcurrentHashMap<Integer, Classification> listToMap(ArrayList<Classification> list) {
        ConcurrentHashMap<Integer, Classification> map = new ConcurrentHashMap<>();
        for (Classification c : list) {
            map.put(c.getId(), c);
        }
        return map;
    }
    static ArrayList<String> mapToStringList(ConcurrentHashMap<Integer, Classification> map) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<Integer, Classification> entry : map.entrySet()) {
            list.add(entry.getValue().getName());
        }
        return list;
    }

    // Parcel definitions
    /*protected Classification(Parcel in) {
        name = in.readString();
        id = in.readInt();
        visible = in.readByte() != 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(id);
        out.writeByte((byte) (visible ? 1 : 0));
    }

    public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };*/
}
