class Book {
    private String id;
    private String title;
    private double price;
    private int stock;

    public Book(String id, String title, double price, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "書號：" + id + " | 書名：" + title + " | 價格：" + price + " | 庫存：" + stock;
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "Java 程式設計", 650.0, 5),
            new Book("B002", "資料結構實作", 580.0, 2),
            new Book("B003", "演算法圖解", 720.0, 1),
            new Book("B004", "資料庫系統概論", 490.0, 8)
        };

        System.out.println("=== 所有書籍資訊 ===");
        for (Book b : books) {
            System.out.println(b);
        }

        double totalValue = 0;
        Book highestBook = books[0];

        for (Book b : books) {
            totalValue += b.getPrice() * b.getStock();
            if (b.getPrice() > highestBook.getPrice()) {
                highestBook = b;
            }
        }

        System.out.println("\n庫存總價值：" + totalValue);
        System.out.println("價格最高的書籍：" + highestBook.getTitle() + " ($" + highestBook.getPrice() + ")");

        System.out.println("\n=== 庫存小於或等於 3 的書籍 ===");
        for (Book b : books) {
            if (b.getStock() <= 3) {
                System.out.println(b);
            }
        }
    }
}