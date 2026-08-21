@SuppressWarnings("unchecked")
class DynamicArray<T> {
    private Object[] data;
    private int size;

    public DynamicArray() {
        this(4);
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            int newCapacity = data.length * 2;
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    public void add(T value) {
        ensureCapacity();
        data[size++] = value;
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            System.out.println("新增失敗: 索引 " + index + " 超出範圍 (size: " + size + ")");
            return;
        }
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("取得失敗: 索引 " + index + " 超出範圍 (size: " + size + ")");
            return null;
        }
        return (T) data[index];
    }

    public T set(int index, T value) {
        if (index < 0 || index >= size) {
            System.out.println("修改失敗: 索引 " + index + " 超出範圍 (size: " + size + ")");
            return null;
        }
        T oldValue = (T) data[index];
        data[index] = value;
        return oldValue;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("刪除失敗: 索引 " + index + " 超出範圍 (size: " + size + ")");
            return null;
        }
        T removedValue = (T) data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null;
        return removedValue;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public void printInfo() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println("內容: " + sb + " | size: " + size + " | capacity: " + capacity());
    }
}

public class DynamicArrayPractice {

    public static void main(String[] args) {
        System.out.println("=== 測試 DynamicArray<String> ===");
        DynamicArray<String> strArray = new DynamicArray<>(2);

        strArray.add("A");
        strArray.add("B");
        strArray.printInfo();

        strArray.add("C");
        strArray.printInfo();

        strArray.add(1, "X");
        strArray.printInfo();

        System.out.println("get(2): " + strArray.get(2));
        System.out.println("set(2, \"Y\"): " + strArray.set(2, "Y"));
        strArray.printInfo();

        System.out.println("remove(1): " + strArray.remove(1));
        strArray.printInfo();

        System.out.println("\n=== 測試 DynamicArray<Integer> ===");
        DynamicArray<Integer> intArray = new DynamicArray<>(2);

        intArray.add(10);
        intArray.add(20);
        intArray.add(30);
        intArray.printInfo();

        System.out.println("\n=== 測試異常與邊界條件 ===");
        DynamicArray<String> emptyArray = new DynamicArray<>(2);

        System.out.println("1. 空結構刪除測試:");
        emptyArray.remove(0);

        System.out.println("2. 索引 -1 測試:");
        emptyArray.add(-1, "Error");
        emptyArray.get(-1);
        emptyArray.set(-1, "Error");
        emptyArray.remove(-1);

        System.out.println("3. 索引等於 size 測試:");
        emptyArray.get(emptyArray.size());
        emptyArray.remove(emptyArray.size());
    }
}