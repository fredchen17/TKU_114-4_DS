import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildcardNumberTools {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(10, 25, 40, 15);
        List<Double> doubleList = Arrays.asList(12.5, 98.6, 45.0, 78.3);
        List<Integer> emptyList = new ArrayList<>();

        System.out.println("=== 測試 average ===");
        System.out.println("Integer 列表平均: " + average(intList));
        System.out.println("Double 列表平均: " + average(doubleList));
        System.out.println("空列表平均: " + average(emptyList));

        System.out.println("\n=== 測試 maximum ===");
        System.out.println("Integer 列表最大值: " + maximum(intList));
        System.out.println("Double 列表最大值: " + maximum(doubleList));
        System.out.println("空列表最大值: " + maximum(emptyList));

        System.out.println("\n=== 測試 addRange ===");
        List<Number> numberTarget = new ArrayList<>();
        addRange(numberTarget, 5, 10);
        System.out.println("加入 5 到 10 結果: " + numberTarget);

        List<Object> objectTarget = new ArrayList<>();
        addRange(objectTarget, 10, 5);
        System.out.println("start > end 時結果: " + objectTarget);
    }

    public static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Number num : values) {
            if (num != null) {
                sum += num.doubleValue();
            }
        }
        return sum / values.size();
    }

    public static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        double max = -Double.MAX_VALUE;
        boolean hasValidNumber = false;

        for (Number num : values) {
            if (num != null) {
                double val = num.doubleValue();
                if (!hasValidNumber || val > max) {
                    max = val;
                    hasValidNumber = true;
                }
            }
        }
        return hasValidNumber ? max : Double.NaN;
    }

    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }
}