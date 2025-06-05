/**
 * A simplified version of an ArrayList for storing Integer objects.
 * Provides basic dynamic array operations such as add, get, set, remove, and retainAll.
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
     * Constructs a SimpleArrayList with a given initial size. All values are initialized to 0.
     *
     * @param initialSize the initial number of elements
     */
    public SimpleArrayList(int initialSize) {
        data = new Integer[initialSize];
        for (int i = 0; i < initialSize; i++) {
            data[i] = 0;
        }
        size = initialSize;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param i the Integer to be added
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
     * Returns the element at the specified index.
     *
     * @param index the position to retrieve
     * @return the Integer at the specified index, or null if index is invalid
     */
    public Integer get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        }
        return null;
    }

    /**
     * Replaces the element at the specified index with the given element.
     *
     * @param index   the position to replace
     * @param element the new Integer to set
     * @return the old value previously at that position, or null if index is invalid
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
     * @param index the position to remove
     * @return {@code true} if the element was removed successfully; {@code false} if index is invalid or element is null
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
     * Clears the list by removing all elements.
     */
    public void clear() {
        data = new Integer[0];
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the current size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Retains only the elements that are also in the specified list.
     *
     * @param l another SimpleArrayList to compare with
     * @return {@code true} if the list was changed as a result; {@code false} otherwise
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
     * Helper method to check if a list contains a given element.
     *
     * @param l       the list to check in
     * @param element the element to look for
     * @return {@code true} if element is found; {@code false} otherwise
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
