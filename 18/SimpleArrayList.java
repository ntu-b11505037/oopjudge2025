/**
 * SimpleArrayList is a custom implementation of a dynamic array that holds Integer elements.
 * It supports operations like add, get, set, remove, clear, retainAll, and size.
 */
public class SimpleArrayList {
    private Integer[] data;
    private int size; // number of valid elements

    /**
     * Constructs an empty SimpleArrayList.
     */
    public SimpleArrayList() {
        data = new Integer[0];
        size = 0;
    }

    /**
     * Constructs a SimpleArrayList with a specified initial size, initialized with zeros.
     * 
     * @param initialSize the number of elements to initialize
     */
    public SimpleArrayList(int initialSize) {
        data = new Integer[initialSize];
        for (int i = 0; i < initialSize; i++) {
            data[i] = 0;
        }
        size = initialSize;
    }

    /**
     * Adds a new element to the end of the list.
     * 
     * @param i the Integer element to add
     */
    public void add(Integer i) {
        Integer[] newData = new Integer[size + 1];
        for (int j = 0; j < size; j++) {
            newData[j] = data[j];
        }
        newData[size] = i;
        data = newData;
        size++;
    }

    /**
     * Retrieves the element at the specified index.
     * 
     * @param index the index of the element
     * @return the Integer element or null if index is invalid
     */
    public Integer get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        }
        return null;
    }

    /**
     * Replaces the element at the specified index with a new element.
     * 
     * @param index the index to replace
     * @param element the new element
     * @return the old element or null if index is invalid
     */
    public Integer set(int index, Integer element) {
        if (index >= 0 && index < size) {
            Integer old = data[index];
            data[index] = element;
            return old;
        }
        return null;
    }

    /**
     * Removes the element at the specified index.
     * 
     * @param index the index to remove
     * @return true if removal is successful, false otherwise
     */
    public boolean remove(int index) {
        if (index < 0 || index >= size || data[index] == null) {
            return false;
        }
        Integer[] newData = new Integer[size - 1];
        for (int i = 0, j = 0; i < size; i++) {
            if (i != index) {
                newData[j++] = data[i];
            }
        }
        data = newData;
        size--;
        return true;
    }

    /**
     * Clears all elements from the list.
     */
    public void clear() {
        data = new Integer[0];
        size = 0;
    }

    /**
     * Returns the number of valid elements in the list.
     * 
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Retains only the elements in this list that are contained in the specified list.
     * 
     * @param l the list whose elements should be retained
     * @return true if any elements were removed, false otherwise
     */
    public boolean retainAll(SimpleArrayList l) {
        boolean changed = false;
        int newSize = 0;
        Integer[] newData = new Integer[size];

        for (int i = 0; i < size; i++) {
            if (contains(l, data[i])) {
                newData[newSize++] = data[i];
            } else {
                changed = true;
            }
        }

        if (changed) {
            Integer[] finalData = new Integer[newSize];
            for (int i = 0; i < newSize; i++) {
                finalData[i] = newData[i];
            }
            data = finalData;
            size = newSize;
        }
        return changed;
    }

    /**
     * Helper method to check if the list contains a specific element.
     * 
     * @param l the list to check in
     * @param element the element to find
     * @return true if the element exists in the list, false otherwise
     */
    private boolean contains(SimpleArrayList l, Integer element) {
        for (int i = 0; i < l.size(); i++) {
            if (element == null && l.get(i) == null) {
                return true;
            }
            if (element != null && element.equals(l.get(i))) {
                return true;
            }
        }
        return false;
    }
} 
