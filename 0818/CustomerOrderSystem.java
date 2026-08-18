class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private String productName;
    private int price;
    private int quantity;

    public OrderItem(String productName, int price, int quantity) {
        this.productName = productName;
        this.price = Math.max(0, price);
        this.quantity = Math.max(0, quantity);
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubtotal() {
        return price * quantity;
    }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;

    public CustomerOrder(String orderId, Customer customer, OrderItem[] items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items == null ? new OrderItem[0] : items;
    }

    public int calculateTotal() {
        int total = 0;
        for (OrderItem item : items) {
            if (item != null) {
                total += item.getSubtotal();
            }
        }
        return total;
    }

    public int getTotalItemQuantity() {
        int totalQty = 0;
        for (OrderItem item : items) {
            if (item != null) {
                totalQty += item.getQuantity();
            }
        }
        return totalQty;
    }

    public void printSummary() {
        System.out.println("訂單編號：" + orderId);
        System.out.println("顧客姓名：" + customer.getName() + " (ID: " + customer.getId() + ")");
        System.out.println("--- 訂單品項 ---");
        for (OrderItem item : items) {
            if (item != null) {
                System.out.println("- " + item.getProductName() + " | 單價：" + item.getPrice() + " | 數量：" + item.getQuantity() + " | 小計：" + item.getSubtotal());
            }
        }
        System.out.println("品項總數量：" + getTotalItemQuantity());
        System.out.println("訂單總金額：" + calculateTotal());
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "李大華");
        OrderItem[] items = {
            new OrderItem("鍵盤", 1200, 1),
            new OrderItem("滑鼠", 600, 2),
            new OrderItem("螢幕保護貼", 300, 1)
        };

        CustomerOrder order = new CustomerOrder("ORD-2026001", customer, items);
        order.printSummary();
    }
}