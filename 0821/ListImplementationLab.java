import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    public static void executeListOperations(List<Integer> list, String listType) {
        System.out.println("=== 測試 " + listType + " ===");

        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("1. 尾端新增 10, 20, 30 後: " + list);

        list.add(1, 15);
        System.out.println("2. 在索引 1 插入 15 後: " + list);

        int target = 20;
        int index = list.indexOf(target);
        boolean containsVal = list.contains(target);
        System.out.println("3. 搜尋元素 " + target + " -> 索引位置: " + index + ", 是否存在: " + containsVal);

        list.remove(1);
        System.out.println("4. 刪除索引 1 的元素後: " + list);

        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        System.out.println("5. 所有元素的總和: " + sum);
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        executeListOperations(arrayList, "ArrayList");

        List<Integer> linkedList = new LinkedList<>();
        executeListOperations(linkedList, "LinkedList");
    }
}