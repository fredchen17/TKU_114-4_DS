import java.util.Arrays;

final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        return Arrays.copyOf(quantities, quantities.length);
    }

    public int totalQuantity() {
        int sum = 0;
        for (int q : quantities) {
            sum += q;
        }
        return sum;
    }

    public int outOfStockCount() {
        int count = 0;
        for (int q : quantities) {
            if (q == 0) {
                count++;
            }
        }
        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        int[] testData = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-01", testData);

        System.out.println("倉庫編號：" + snapshot.getWarehouseId());
        System.out.println("總數量：" + snapshot.totalQuantity());
        System.out.println("缺貨品項數：" + snapshot.outOfStockCount());

        testData[0] = 999;
        int[] retrievedData = snapshot.getQuantities();
        retrievedData[1] = 888;

        System.out.println("\n--- 修改外部陣列後測試 ---");
        System.out.println("總數量（應維持 8）：" + snapshot.totalQuantity());
        System.out.println("內部陣列：" + Arrays.toString(snapshot.getQuantities()));

        InventorySnapshot nullSnapshot = new InventorySnapshot("WH-02", null);
        System.out.println("\n--- 測試 null 傳入 ---");
        System.out.println("Null 測試總數量：" + nullSnapshot.totalQuantity());
        System.out.println("Null 測試缺貨品項數：" + nullSnapshot.outOfStockCount());
    }
}