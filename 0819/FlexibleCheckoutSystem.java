public class FlexibleCheckoutSystem {
    public static void main(String[] args) {
        PricingPolicy regular = new RegularPricing();
        PricingPolicy vip = new VipDiscountPricing();
        PricingPolicy threshold = new ThresholdDiscountPricing();

        NotificationChannel email = new EmailNotification();
        NotificationChannel sms = new SmsNotification();
        NotificationChannel console = new ConsoleNotification();

        FlexibleCheckoutService service = new FlexibleCheckoutService();

        System.out.println("=== 測試 1: 原價 + Email ===");
        CheckoutResult r1 = service.checkout("ORD-001", 1000, regular, email);
        System.out.println(r1);

        System.out.println("\n=== 測試 2: VIP 八五折 + SMS ===");
        CheckoutResult r2 = service.checkout("ORD-002", 1000, vip, sms);
        System.out.println(r2);

        System.out.println("\n=== 測試 3: 滿 2000 折 300 (未滿額) + Console ===");
        CheckoutResult r3 = service.checkout("ORD-003", 1500, threshold, console);
        System.out.println(r3);

        System.out.println("\n=== 測試 4: 滿 2000 折 300 (已滿額) + Email ===");
        CheckoutResult r4 = service.checkout("ORD-004", 2500, threshold, email);
        System.out.println(r4);

        System.out.println("\n=== 測試 5: VIP 八五折 + Console ===");
        CheckoutResult r5 = service.checkout("ORD-005", 3000, vip, console);
        System.out.println(r5);

        System.out.println("\n=== 測試 6: 原價 + SMS ===");
        CheckoutResult r6 = service.checkout("ORD-006", 500, regular, sms);
        System.out.println(r6);
    }
}

interface PricingPolicy {
    double calculateFinalPrice(double originalPrice);
}

class RegularPricing implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return originalPrice;
    }
}

class VipDiscountPricing implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        return originalPrice * 0.85;
    }
}

class ThresholdDiscountPricing implements PricingPolicy {
    @Override
    public double calculateFinalPrice(double originalPrice) {
        if (originalPrice >= 2000) {
            return originalPrice - 300;
        }
        return originalPrice;
    }
}

interface NotificationChannel {
    boolean sendNotification(String orderId, double finalPrice);
}

class EmailNotification implements NotificationChannel {
    @Override
    public boolean sendNotification(String orderId, double finalPrice) {
        System.out.printf("[Email] 訂單 %s 結帳成功，應付金額: $%.1f\n", orderId, finalPrice);
        return true;
    }
}

class SmsNotification implements NotificationChannel {
    @Override
    public boolean sendNotification(String orderId, double finalPrice) {
        System.out.printf("[SMS] 訂單 %s 結帳成功，應付金額: $%.1f\n", orderId, finalPrice);
        return true;
    }
}

class ConsoleNotification implements NotificationChannel {
    @Override
    public boolean sendNotification(String orderId, double finalPrice) {
        System.out.printf("[Console] 訂單 %s 結帳成功，應付金額: $%.1f\n", orderId, finalPrice);
        return true;
    }
}

class CheckoutResult {
    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private boolean notificationStatus;

    public CheckoutResult(String orderId, double originalPrice, double finalPrice, boolean notificationStatus) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public boolean isNotificationStatus() {
        return notificationStatus;
    }

    @Override
    public String toString() {
        return String.format("結帳結果 -> 訂單號: %s | 原價: %.1f 元 | 最終金額: %.1f 元 | 通知狀態: %s",
                orderId, originalPrice, finalPrice, notificationStatus ? "成功" : "失敗");
    }
}

class FlexibleCheckoutService {
    public CheckoutResult checkout(String orderId, double originalPrice, PricingPolicy pricingPolicy, NotificationChannel notificationChannel) {
        if (pricingPolicy == null || notificationChannel == null) {
            return new CheckoutResult(orderId, originalPrice, originalPrice, false);
        }

        double finalPrice = pricingPolicy.calculateFinalPrice(originalPrice);
        boolean notifySuccess = notificationChannel.sendNotification(orderId, finalPrice);

        return new CheckoutResult(orderId, originalPrice, finalPrice, notifySuccess);
    }
}