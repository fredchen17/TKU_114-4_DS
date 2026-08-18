import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = new ArrayList<>();
        products.add(new StoreProduct("P003", "Wireless Mouse", 590, 15));
        products.add(new StoreProduct("P001", "Mechanical Keyboard", 2500, 8));
        products.add(new StoreProduct("P005", "Gaming Mousepad", 590, 20));
        products.add(new StoreProduct("P002", "USB-C Cable", 290, 8));
        products.add(new StoreProduct("P004", "HD Monitor", 4500, 15));

        System.out.println("=== 原始商品資料順序 ===");
        printList(products);

        System.out.println("\n=== 1. Natural Order (依 ID 升冪) ===");
        List<StoreProduct> copy1 = new ArrayList<>(products);
        Collections.sort(copy1);
        printList(copy1);

        System.out.println("\n=== 2. Comparator 1 (依 Price 升冪，同價依 Name 升冪) ===");
        List<StoreProduct> copy2 = new ArrayList<>(products);
        copy2.sort(new PriceAndNameComparator());
        printList(copy2);

        System.out.println("\n=== 3. Comparator 2 (依 Stock 降冪，同庫存依 ID 升冪) ===");
        List<StoreProduct> copy3 = new ArrayList<>(products);
        copy3.sort(new StockAndIdComparator());
        printList(copy3);
    }

    private static void printList(List<StoreProduct> list) {
        for (StoreProduct product : list) {
            System.out.println(product);
        }
    }
}

class StoreProduct implements Comparable<StoreProduct> {
    private String id;
    private String name;
    private int price;
    private int stock;

    public StoreProduct(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    @Override
    public int compareTo(StoreProduct other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %-4s | 說明: %-20s | 價格: %4d 元 | 庫存: %2d",
                id, name, price, stock);
    }
}

class PriceAndNameComparator implements Comparator<StoreProduct> {
    @Override
    public int compare(StoreProduct p1, StoreProduct p2) {
        int priceCompare = Integer.compare(p1.getPrice(), p2.getPrice());
        if (priceCompare != 0) {
            return priceCompare;
        }
        return p1.getName().compareTo(p2.getName());
    }
}

class StockAndIdComparator implements Comparator<StoreProduct> {
    @Override
    public int compare(StoreProduct p1, StoreProduct p2) {
        int stockCompare = Integer.compare(p2.getStock(), p1.getStock());
        if (stockCompare != 0) {
            return stockCompare;
        }
        return p1.getId().compareTo(p2.getId());
    }
}