import java.util.Arrays;
public class IntList {
    private int size;
    private int capacity;
    private int[] arr;

    private void resize() {
        int[] newarr = new int[capacity * 2];
        newarr = Arrays.copyOf(arr, capacity * 2);
        arr = newarr;
        capacity *= 2;
    }

    public IntList() {
        size = 0;
        capacity = 100;
        arr = new int[100];
    }

    public IntList(int[] copy) {
        size = copy.length;
        capacity = Math.max(100, copy.length * 2);
        arr = Arrays.copyOf(copy, capacity);
    }

    public void add(int value) {
        if (size == capacity) {
            resize();
        }
        arr[size] = value;
        size++;
    }

    public int get(int index) {
        return arr[index];
    }

    public void set(int index, int value) {
        arr[index] = value;
    }

    public int size() {
        return size;
    }

}