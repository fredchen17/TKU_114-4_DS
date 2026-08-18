import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {
    public static void main(String[] args) {
        String[] rawTags = {"Java", "Python", "Java", "Backend", "AI", "Python", "Java", "Frontend"};

        List<String> tagList = new ArrayList<>();
        Set<String> tagSet = new HashSet<>();
        Map<String, Integer> tagMap = new HashMap<>();

        for (String tag : rawTags) {
            tagList.add(tag);
            tagSet.add(tag);
            tagMap.put(tag, tagMap.getOrDefault(tag, 0) + 1);
        }

        System.out.println("=== 1. List (保留原始輸入順序) ===");
        System.out.println(tagList);

        System.out.println("\n=== 2. Set (去重後的獨立標籤) ===");
        System.out.println(tagSet);

        System.out.println("\n=== 3. Map (各標籤出現次數統計) ===");
        for (Map.Entry<String, Integer> entry : tagMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " 次");
        }

        System.out.println("\n=== 三種集合結構之用途說明 ===");
        System.out.println("1. List: 適用於需要保留元素輸入順序、允許重複值的情境（如歷史紀錄、流式資料處理）。");
        System.out.println("2. Set: 適用於快速去重、檢查元素是否存在，且不允許重複值的情境（如獨立標籤庫、會員權限過濾）。");
        System.out.println("3. Map: 適用於 Key-Value 鍵值對映射，能依 Key 快速查找對應數值（如次數統計、屬性配置檔）。");
    }
}   