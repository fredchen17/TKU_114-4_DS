class Equipment {
    private String id;
    private String name;
    private int availableCount;
    
    public Equipment(String id, String name, int availableCount) {
        this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id;
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name;
        this.availableCount = Math.max(0, availableCount);
    }

    public boolean borrowOne() {
        if (this.availableCount > 0) {
            this.availableCount--;
            return true;
        }
        return false;
    }
    public void returnItems(int quantity) {
        if (quantity > 0) {
            this.availableCount += quantity;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    @Override
    public String toString() {
        return String.format("設備編號: %s | 名稱: %s | 可借數量: %d", id, name, availableCount);
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment eq1 = new Equipment("EQ-001", "筆記型電腦", 1);
        
        Equipment eq2 = new Equipment("", "  ", -5);

        System.out.println("=== 初始庫存狀態 ===");
        System.out.println(eq1);
        System.out.println(eq2);

        System.out.println("\n=== 測試借用與庫存防護 ===");
        boolean success1 = eq1.borrowOne();
        System.out.println("借用 eq1 (1/2)：" + (success1 ? "成功" : "失敗") + " -> " + eq1);

        boolean success2 = eq1.borrowOne();
        System.out.println("借用 eq1 (2/2)：" + (success2 ? "成功" : "失敗") + " -> " + eq1);

        System.out.println("\n=== 測試歸還設備 ===");
        eq1.returnItems(-3);
        System.out.println("歸還 -3 台 eq1（無效操作）：" + eq1);

        eq1.returnItems(2);
        System.out.println("歸還 2 台 eq1："+ eq1);
    }
}