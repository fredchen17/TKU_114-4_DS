public class GenericArrayTools {
    public static void main(String[] args) {
        System.out.println("=== 測試 countMatches ===");
        String[] words = {"apple", "banana", "apple", null, "orange", "apple"};
        System.out.println("apple 出現次數: " + countMatches(words, "apple"));
        System.out.println("null 出現次數: " + countMatches(words, null));

        System.out.println("\n=== 測試 last ===");
        Integer[] numbers = {10, 20, 30, 40, 50};
        System.out.println("最後一個元素: " + last(numbers));
        System.out.println("空陣列呼叫 last: " + last(new String[0]));

        System.out.println("\n=== 測試 swap ===");
        System.out.println("交換前: " + java.util.Arrays.toString(numbers));
        swap(numbers, 0, 4);
        System.out.println("交換 index 0 與 4 後: " + java.util.Arrays.toString(numbers));
        swap(numbers, -1, 2);
    }

    public static <T> int countMatches(T[] data, T target) {
        if (data == null || data.length == 0) {
            return 0;
        }
        int count = 0;
        for (T item : data) {
            if (target == null) {
                if (item == null) {
                    count++;
                }
            } else {
                if (target.equals(item)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    public static <T> void swap(T[] data, int first, int second) {
        if (data == null || data.length == 0) {
            return;
        }
        if (first < 0 || first >= data.length || second < 0 || second >= data.length) {
            return;
        }
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }
}