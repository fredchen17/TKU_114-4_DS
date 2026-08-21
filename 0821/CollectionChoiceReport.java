import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionChoiceReport {

    public static void main(String[] args) {

        System.out.println("=== 需求 1: 保留搜尋紀錄且允許重複 ===");
        System.out.println("選擇介面: List | 實作類別: ArrayList");
        List<String> searchHistory = new ArrayList<>();
        searchHistory.add("Java Tutorial");
        searchHistory.add("Spring Boot");
        searchHistory.add("Java Tutorial");
        searchHistory.add("Data Structures");
        System.out.println("操作結果 - 搜尋紀錄: " + searchHistory);
        System.out.println("第二次搜尋歷史內容: " + searchHistory.get(1));
        System.out.println();

        System.out.println("=== 需求 2: 保存不重複會員編號 ===");
        System.out.println("選擇介面: Set | 實作類別: HashSet");
        Set<String> memberIds = new HashSet<>();
        System.out.println("新增 M1001: " + memberIds.add("M1001"));
        System.out.println("新增 M1002: " + memberIds.add("M1002"));
        System.out.println("再次新增 M1001 (重複): " + memberIds.add("M1001"));
        System.out.println("操作結果 - 會員清單: " + memberIds);
        System.out.println("檢查 M1002 是否存在: " + memberIds.contains("M1002"));
        System.out.println();

        System.out.println("=== 需求 3: 以學號查詢成績 ===");
        System.out.println("選擇介面: Map | 實作類別: HashMap");
        Map<String, Integer> studentGrades = new HashMap<>();
        studentGrades.put("S101", 85);
        studentGrades.put("S102", 92);
        studentGrades.put("S103", 78);
        System.out.println("操作結果 - 所有成績紀錄: " + studentGrades);
        System.out.println("查詢 S102 成績: " + studentGrades.get("S102"));
        System.out.println("查詢不存在學號 S999: " + studentGrades.get("S999"));
        System.out.println();

        System.out.println("=== 需求 4: 依到達順序處理列印工作 (FIFO) ===");
        System.out.println("選擇介面: Queue (Deque) | 實作類別: ArrayDeque");
        Deque<String> printQueue = new ArrayDeque<>();
        printQueue.offerLast("Doc1.pdf");
        printQueue.offerLast("Report.docx");
        printQueue.offerLast("Image.png");
        System.out.println("操作結果 - 佇列狀況: " + printQueue);
        System.out.println("處理列印工作: " + printQueue.pollFirst());
        System.out.println("處理列印工作: " + printQueue.pollFirst());
        System.out.println("剩餘列印工作: " + printQueue);
        System.out.println();

        System.out.println("=== 需求 5: 復原最近操作 (LIFO) ===");
        System.out.println("選擇介面: Deque | 實作類別: ArrayDeque");
        Deque<String> actionStack = new ArrayDeque<>();
        actionStack.push("輸入文字 'Hello'");
        actionStack.push("修改字體大小");
        actionStack.push("刪除段落");
        System.out.println("操作結果 - 歷史動作堆疊: " + actionStack);
        System.out.println("執行復原 (Undo): " + actionStack.pop());
        System.out.println("執行復原 (Undo): " + actionStack.pop());
        System.out.println("剩餘可復原動作: " + actionStack);
    }
}