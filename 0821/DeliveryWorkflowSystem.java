import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Package {
    private final String id;
    private final String address;

    public Package(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "包裹[" + id + "] -> 地址: " + address;
    }
}

public class DeliveryWorkflowSystem {

    private final Map<String, Package> packageMap = new HashMap<>();
    private final Deque<Package> pendingQueue = new ArrayDeque<>();
    private final Deque<Package> completedStack = new ArrayDeque<>();

    public boolean addPackage(String id, String address) {
        if (packageMap.containsKey(id)) {
            System.out.println("新增失敗: 包裹編號 " + id + " 已存在");
            return false;
        }
        Package pkg = new Package(id, address);
        packageMap.put(id, pkg);
        pendingQueue.offerLast(pkg);
        System.out.println("成功新增包裹: " + pkg);
        return true;
    }

    public Package processNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("處理失敗: 無等待配送包裹");
            return null;
        }
        Package pkg = pendingQueue.pollFirst();
        completedStack.push(pkg);
        System.out.println("已完成配送: " + pkg);
        return pkg;
    }

    public Package undoLastDelivery() {
        if (completedStack.isEmpty()) {
            System.out.println("Undo 失敗: 無可復原的配送歷程");
            return null;
        }
        Package pkg = completedStack.pop();
        pendingQueue.offerFirst(pkg);
        System.out.println("已復原配送: " + pkg + " (重置至等待佇列首位)");
        return pkg;
    }

    public Package searchPackage(String id) {
        Package pkg = packageMap.get(id);
        if (pkg == null) {
            System.out.println("查詢結果: 找不到包裹編號 " + id);
            return null;
        }
        
        String status = "未知";
        if (completedStack.contains(pkg)) {
            status = "已完成配送";
        } else if (pendingQueue.contains(pkg)) {
            status = "等待配送中";
        }
        
        System.out.println("查詢結果: " + pkg + " | 狀態: " + status);
        return pkg;
    }

    public void printStatistics() {
        System.out.println("=== 物流統計資訊 ===");
        System.out.println("總包裹數量: " + packageMap.size());
        System.out.println("等待配送數: " + pendingQueue.size());
        System.out.println("已完成配送數: " + completedStack.size());
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem system = new DeliveryWorkflowSystem();

        System.out.println("=== 1. 測試邊界條件與重複 ID 阻擋 ===");
        system.processNext();
        system.undoLastDelivery();
        system.searchPackage("PKG001");
        system.addPackage("PKG001", "台北市信義區路一段1號");
        system.addPackage("PKG001", "新北市板橋區縣民大道2號");

        System.out.println("\n=== 2. 批量新增包裹 ===");
        system.addPackage("PKG002", "新北市淡水區中正路100號");
        system.addPackage("PKG003", "台中市西區台灣大道二段");
        system.printStatistics();

        System.out.println("\n=== 3. 處理配送 (FIFO) ===");
        system.processNext();
        system.processNext();
        system.printStatistics();

        System.out.println("\n=== 4. 查詢包裹狀態 ===");
        system.searchPackage("PKG001");
        system.searchPackage("PKG003");

        System.out.println("\n=== 5. 復原上一次配送 (Undo) ===");
        system.undoLastDelivery();
        system.printStatistics();
        system.searchPackage("PKG002");

        System.out.println("\n=== 6. 重新處理配送 ===");
        system.processNext();
        system.processNext();
        system.printStatistics();
    }
}