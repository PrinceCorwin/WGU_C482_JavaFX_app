package amalfi.wgu_c482;

import java.util.ArrayList;

public class UniqueID {
    private static ArrayList<Integer> availablePartIDs = new ArrayList<>();
    private static ArrayList<Integer> availableProdIDs = new ArrayList<>();

    private static int[] nextPartID = {1};
    private static int[] nextProdID = {1};


    public static int[] getNextPartID() {
        return nextPartID;
    }
    public static int[] getNextProdID() {
        return nextProdID;
    }
    public static void setNextPartID(int nextPartID) {
        UniqueID.nextPartID[0] = nextPartID;
    }
    public static void setNextProdID(int nextProdID) {
        UniqueID.nextProdID[0] = nextProdID;
    }
    public static ArrayList<Integer> getAvailablePartIDs() {
        return availablePartIDs;
    }
    public static ArrayList<Integer> getAvailableProdIDs() {
        return availableProdIDs;
    }

    public static void addToAvailablePartIDs(Integer newAvailable) {
        availablePartIDs.add(newAvailable);
    }
    public static void addToAvailableProdIDs(Integer newAvailable) {
        availableProdIDs.add(newAvailable);
    }
    public static Integer getUniquePartID() {
        if (!(getAvailablePartIDs().size() == 0)) {
            int removedID = getAvailablePartIDs().get(0);
            availablePartIDs.remove(0);
            return removedID;
        } else {
            int newID = getNextPartID()[0];
            setNextPartID(newID + 1);
            return newID;
        }
    }
    public static Integer getUniqueProdID() {
        if (!(getAvailableProdIDs().size() == 0)) {
            int removedID = getAvailableProdIDs().get(0);
            availableProdIDs.remove(0);
            return removedID;
        } else {
            int newID = getNextProdID()[0];
            setNextProdID(newID + 1);
            return newID;
        }
    }
}
