public class DeliveryStrategySystem {
    public static void main(String[] args) {
        OrderService homeOrder = new OrderService("ORD001", 1500, new HomeDelivery());
        OrderService storeOrder = new OrderService("ORD002", 800, new StorePickup());
        OrderService selfOrder = new OrderService("ORD003", 500, new SelfPickup());

        System.out.println("=== 訂單配送測試 ===");
        homeOrder.printOrderDetails();
        System.out.println();
        storeOrder.printOrderDetails();
        System.out.println();
        selfOrder.printOrderDetails();

        System.out.println("\n=== 動態變更配送方式測試 ===");
        System.out.println("將訂單 ORD002 變更為宅配：");
        storeOrder.setDeliveryMethod(new HomeDelivery());
        storeOrder.printOrderDetails();
    }
}

interface DeliveryMethod {
    int calculateShippingFee(int orderAmount);
    String getDeliveryDescription();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public int calculateShippingFee(int orderAmount) {
        if (orderAmount >= 1000) {
            return 0;
        }
        return 100;
    }

    @Override
    public String getDeliveryDescription() {
        return "黑貓宅急便（預計 1~2 個工作天送達指定地址）";
    }
}

class StorePickup implements DeliveryMethod {
    @Override
    public int calculateShippingFee(int orderAmount) {
        if (orderAmount >= 600) {
            return 0;
        }
        return 60;
    }

    @Override
    public String getDeliveryDescription() {
        return "超商取貨（預計 2~3 個工作天送達指定門市）";
    }
}

class SelfPickup implements DeliveryMethod {
    @Override
    public int calculateShippingFee(int orderAmount) {
        return 0;
    }

    @Override
    public String getDeliveryDescription() {
        return "門市自取（請於營業時間內至指定總店領取）";
    }
}

class OrderService {
    private String orderId;
    private int orderAmount;
    private DeliveryMethod deliveryMethod;

    public OrderService(String orderId, int orderAmount, DeliveryMethod deliveryMethod) {
        this.orderId = orderId;
        this.orderAmount = orderAmount < 0 ? 0 : orderAmount;
        this.deliveryMethod = deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public int getShippingFee() {
        if (deliveryMethod == null) {
            return 0;
        }
        return deliveryMethod.calculateShippingFee(orderAmount);
    }

    public int getTotalAmount() {
        return orderAmount + getShippingFee();
    }

    public void printOrderDetails() {
        System.out.println("訂單編號: " + orderId);
        System.out.println("商品金額: " + orderAmount + " 元");
        if (deliveryMethod != null) {
            System.out.println("配送方式: " + deliveryMethod.getDeliveryDescription());
            System.out.println("運費: " + getShippingFee() + " 元");
        } else {
            System.out.println("配送方式: 未指定");
        }
        System.out.println("應付總額: " + getTotalAmount() + " 元");
    }
}