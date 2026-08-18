import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> originalList = new ArrayList<>();
        originalList.add("Alice");
        originalList.add("Bob");
        originalList.add("");
        originalList.add("  ");
        originalList.add(null);
        originalList.add("Alice");
        originalList.add("Charlie");
        originalList.add("Bob");
        originalList.add("David");
        originalList.add(null);

        System.out.println("=== 1. 清理前原始名單 ===");
        System.out.println("總筆數: " + originalList.size());
        System.out.println(originalList);

        List<String> cleanedList = new ArrayList<>(originalList);
        Iterator<String> iterator = cleanedList.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (String name : cleanedList) {
            String trimmedName = name.trim();
            if (!uniqueNames.add(trimmedName)) {
                duplicateNames.add(trimmedName);
            }
        }

        System.out.println("\n=== 2. 清理後名單 (已移除 null 與空白字串) ===");
        System.out.println("有效筆數: " + cleanedList.size());
        System.out.println(cleanedList);

        System.out.println("\n=== 3. 重複姓名分析報告 ===");
        System.out.println("發現重複的姓名: " + duplicateNames);
        System.out.println("最終不重複學員名單 (共 " + uniqueNames.size() + " 人): " + uniqueNames);
    }
}