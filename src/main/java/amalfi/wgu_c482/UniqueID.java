package amalfi.wgu_c482;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UniqueID {
    private static ArrayList<Integer> availableIDs = new ArrayList<>();
    private static int[] nextID = {1};


    public static int[] getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        UniqueID.nextID[0] = nextID;
    }

    public static ArrayList<Integer> getAvailableIDs() {
        return availableIDs;
    }
    public static void setAvailableIDs(ArrayList<Integer> newAvailableList) {
        availableIDs = newAvailableList;
    }

    public static void addToAvailable(Integer newAvailable) {
        availableIDs.add(newAvailable);
    }
    public static Integer getUniqueID() {
        if (!(getAvailableIDs().size() == 0)) {
            int removedID = getAvailableIDs().get(0);
            availableIDs.remove(0);
            return removedID;
        } else {
            int newID = getNextID()[0];
            setNextID(newID + 1);
            return newID;
        }
    }
}
