@SuppressWarnings("unchecked")
class ArrayStack<T> {
    private final T[] data;
    private int top = 0;

    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.data = (T[]) new Object[capacity];
    }

    public void push(T value) {
        if (isFull()) {
            System.out.println("Push 失敗: 堆疊已滿");
            return;
        }
        data[top++] = value;
        System.out.println("Push 成功: " + value);
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Pop 失敗: 堆疊為空");
            return null;
        }
        T value = data[--top];
        data[top] = null;
        System.out.println("Pop 成功: " + value);
        return value;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Peek 失敗: 堆疊為空");
            return null;
        }
        T value = data[top - 1];
        System.out.println("Peek 成功: " + value);
        return value;
    }

    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == data.length;
    }
}

public class GenericArrayStackDemo {

    public static void main(String[] args) {
        System.out.println("=== 測試 ArrayStack<String> ===");
        ArrayStack<String> stringStack = new ArrayStack<>(2);

        System.out.println("isEmpty: " + stringStack.isEmpty());
        stringStack.peek();
        stringStack.pop();

        stringStack.push("Java");
        stringStack.push("Python");
        stringStack.push("C++");

        System.out.println("isFull: " + stringStack.isFull());
        System.out.println("size: " + stringStack.size());
        stringStack.peek();

        stringStack.pop();
        stringStack.pop();
        stringStack.pop();

        System.out.println("\n=== 測試 ArrayStack<Integer> ===");
        ArrayStack<Integer> intStack = new ArrayStack<>(3);

        intStack.push(100);
        intStack.push(200);

        System.out.println("size: " + intStack.size());
        intStack.peek();

        intStack.pop();
        System.out.println("size: " + intStack.size());
    }
}