package amalfi.wgu_c482;

import java.util.ArrayList;

/**
 * Class that creates a unique id for each part or product.
 * As parts and products are deleted, their ids are saved in a list
 * and used first when new ids are needed. This prevents the id integers from
 * growing infinitely.
 */
public class UniqueID {
    private static final ArrayList<Integer> availablePartIDs = new ArrayList<>();
    private static final ArrayList<Integer> availableProdIDs = new ArrayList<>();

    private static final int[] nextPartID = {1};
    private static final int[] nextProdID = {1};

    /**
     * @return next available part id
     */
    public static int[] getNextPartID() {
        return nextPartID;
    }

    /**
     * @return next available product id
     */
    public static int[] getNextProdID() {
        return nextProdID;
    }

    /**
     * @param nextPartID the next available part id to set
     */
    public static void setNextPartID(int nextPartID) {
        UniqueID.nextPartID[0] = nextPartID;
    }

    /**
     * @param nextProdID the next available product id to set
     */
    public static void setNextProdID(int nextProdID) {
        UniqueID.nextProdID[0] = nextProdID;
    }

    /**
     * @return the list of available part ids (due to part deletion)
     */
    public static ArrayList<Integer> getAvailablePartIDs() {
        return availablePartIDs;
    }

    /**
     * @return the list of available product ids (due to product deletion)
     */
    public static ArrayList<Integer> getAvailableProdIDs() {
        return availableProdIDs;
    }

    /**
     * @param newAvailable the id to add to availablePartIDs
     */
    public static void addToAvailablePartIDs(Integer newAvailable) {
        availablePartIDs.add(newAvailable);
    }

    /**
     * @param newAvailable the id to add to availableProdIDs
     */
    public static void addToAvailableProdIDs(Integer newAvailable) {
        availableProdIDs.add(newAvailable);
    }

    /**
     * Gets the first index of availablePartIDs, if there are any.
     * If no availablePartIDs, the nextPartID is used and set to increment by one.
     * @return the unique id
     */
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

    /**
     * Gets the first index of availableProdIDs, if there are any.
     * If no availableProdIDs, the nextProdID is used and set to increment by one.
     * @return the unique id
     */
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
