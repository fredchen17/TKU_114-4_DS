import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private final String name;
    private final int id;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer #" + id + " (" + name + ")";
    }
}

public class CounterWaitingQueue {

    private final Deque<Customer> queue = new ArrayDeque<>();

    public void addCustomer(Customer customer) {
        queue.offerLast(customer);
        System.out.println("顧客加入隊列: " + customer);
    }

    public Customer peekNext() {
        if (queue.isEmpty()) {
            System.out.println("查看失敗: 當前無人等候");
            return null;
        }
        Customer next = queue.peekFirst();
        System.out.println("下一位等候顧客: " + next);
        return next;
    }

    public Customer serveNext() {
        if (queue.isEmpty()) {
            System.out.println("服務失敗: 當前無人等候");
            return null;
        }
        Customer served = queue.pollFirst();
        System.out.println("正在服務: " + served);
        return served;
    }

    public int getWaitingCount() {
        int count = queue.size();
        System.out.println("當前等候人數: " + count);
        return count;
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();

        counter.getWaitingCount();
        counter.peekNext();
        counter.serveNext();

        System.out.println();

        counter.addCustomer(new Customer(101, "Alice"));
        counter.addCustomer(new Customer(102, "Bob"));
        counter.addCustomer(new Customer(103, "Charlie"));

        System.out.println();

        counter.getWaitingCount();
        counter.peekNext();

        System.out.println();

        counter.serveNext();
        counter.getWaitingCount();
        counter.serveNext();
        counter.serveNext();

        System.out.println();

        counter.serveNext();
        counter.getWaitingCount();
    }
}