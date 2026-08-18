import java.util.ArrayList;
import java.util.List;

public class GenericRepositorySystem {
    public static void main(String[] args) {
        System.out.println("=== 測試 Repository<String> ===");
        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Java");
        stringRepo.add("Python");
        stringRepo.add("C++");

        System.out.println("元素總數: " + stringRepo.size());
        stringRepo.printAll();

        System.out.println("取得 index 1 的元素: " + stringRepo.get(1));
        System.out.println("移除 \"Python\": " + stringRepo.remove("Python"));
        System.out.println("移除後元素總數: " + stringRepo.size());
        stringRepo.printAll();

        System.out.println("\n=== 測試 Repository<Product> ===");
        Repository<Product> productRepo = new Repository<>();
        Product p1 = new Product("P001", "MacBook Pro", 60000);
        Product p2 = new Product("P002", "iPhone", 30000);
        Product p3 = new Product("P003", "AirPods", 6000);

        productRepo.add(p1);
        productRepo.add(p2);
        productRepo.add(p3);

        System.out.println("商品總數: " + productRepo.size());
        productRepo.printAll();

        System.out.println("取得 index 0 的商品: " + productRepo.get(0));
        System.out.println("移除 iPhone: " + productRepo.remove(p2));
        System.out.println("移除後商品總數: " + productRepo.size());
        productRepo.printAll();

        System.out.println("\n=== 邊界條件測試 ===");
        System.out.println("取得不合法 index (-1): " + stringRepo.get(-1));
        System.out.println("取得超出範圍 index (10): " + stringRepo.get(10));
        System.out.println("移除不存在的元素: " + stringRepo.remove("Go"));
    }
}

class Repository<T> {
    private List<T> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        if (item != null) {
            items.add(item);
        }
    }

    public T get(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    public boolean remove(T item) {
        if (item == null) {
            return false;
        }
        return items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public void printAll() {
        System.out.println("--- 儲存庫內容清單 ---");
        if (items.isEmpty()) {
            System.out.println("(內容為空)");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println("[" + i + "] " + items.get(i));
        }
    }
}

class Product {
    private String id;
    private String name;
    private int price;

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return id != null ? id.equals(product.id) : product.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("商品編號: %-4s | 名稱: %-12s | 價格: %d 元", id, name, price);
    }
}