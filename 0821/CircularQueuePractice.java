@SuppressWarnings("unchecked")
class CircularQueue<T> {
    private final T[] data;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.data = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean enqueue(T item) {
        if (isFull()) {
            System.out.println("Enqueue 失敗 (" + item + "): Queue 已滿");
            return false;
        }
        data[rear] = item;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Dequeue 失敗: Queue 為空");
            return null;
        }
        T item = data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void printState(String action) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < capacity; i++) {
            sb.append(data[i] == null ? "null" : data[i]);
            if (i < capacity - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.printf("%-18s -> Array: %-22s | front: %d | rear: %d | size: %d%n",
                action, sb.toString(), front, rear, size);
    }
}

public class CircularQueuePractice {

    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);

        System.out.println("=== 步驟 1: 逐步操作並追蹤狀態 ===");
        queue.printState("Initial State");

        queue.enqueue("A");
        queue.printState("enqueue A");

        queue.enqueue("B");
        queue.printState("enqueue B");

        queue.enqueue("C");
        queue.printState("enqueue C");

        queue.dequeue();
        queue.printState("dequeue");

        queue.dequeue();
        queue.printState("dequeue");

        queue.enqueue("D");
        queue.printState("enqueue D");

        queue.enqueue("E");
        queue.printState("enqueue E");

        queue.enqueue("F");
        queue.printState("enqueue F");

        queue.dequeue();
        queue.printState("dequeue");

        queue.enqueue("G");
        queue.printState("enqueue G");

        System.out.println("\n=== 步驟 2: 依 FIFO 順序取出剩餘所有元素 ===");
        while (!queue.isEmpty()) {
            String item = queue.dequeue();
            System.out.println("取出元素: " + item);
        }

        System.out.println("\n=== 清空後最終狀態 ===");
        queue.printState("Final State");
    }
}