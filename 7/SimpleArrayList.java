public class SimpleArrayList {
    private Integer[] data;
    private int size; // number of valid elements

    // 1. Default constructor
    public SimpleArrayList() {
        data = new Integer[0];
        size = 0;
    }

    // 2. Constructor with initial size
    public SimpleArrayList(int initialSize) {
        data = new Integer[initialSize];
        for (int i = 0; i < initialSize; i++) {
            data[i] = 0;
        }
        size = initialSize;
    }

    // 3. Add element
    public void add(Integer i) {
        Integer[] newData = new Integer[size + 1];
        for (int j = 0; j < size; j++) {
            newData[j] = data[j];
        }
        newData[size] = i;
        data = newData;
        size++;
    }

    // 4. Get element
    public Integer get(int index) {
        if (index >= 0 && index < size) {
            return data[index];
        }
        return null;
    }

    // 5. Set element
    public Integer set(int index, Integer element) {
        if (index >= 0 && index < size) {
            Integer old = data[index];
            data[index] = element;
            return old;
        }
        return null;
    }

    // 6. Remove element
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

    // 7. Clear array
    public void clear() {
        data = new Integer[0];
        size = 0;
    }

    // 8. Get size
    public int size() {
        return size;
    }

    // 9. Retain all elements that are also in l
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

    // Helper method: check if list l contains element
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
