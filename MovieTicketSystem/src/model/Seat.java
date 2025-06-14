// File: src/model/Seat.java
package model;

public class Seat {
    private char row;
    private int number;
    private boolean selected;
    private boolean available = true; // 預設為可用

    public Seat(char row, int number) {
        this.row = row;
        this.number = number;
    }

    public char getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Seat)) return false;
        Seat other = (Seat) obj;
        return row == other.row && number == other.number;
    }

    @Override
    public int hashCode() {
        return row * 31 + number;
    }

    @Override
    public String toString() {
        return "" + row + number;
    }
}
