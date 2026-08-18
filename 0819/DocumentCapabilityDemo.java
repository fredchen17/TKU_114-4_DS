public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument doc = new BackupDocument("2026_System_Report.pdf", 10240);

        System.out.println("=== 1. 使用 Exportable 介面參考 ===");
        Exportable exportableRef = doc;
        exportableRef.export();

        System.out.println("\n=== 2. 使用 Compressible 介面參考 ===");
        Compressible compressibleRef = doc;
        compressibleRef.compress();

        System.out.println("\n=== 3. 記憶體位址與可見性比較 ===");
        System.out.println("exportableRef 指向的物件位址: " + System.identityHashCode(exportableRef));
        System.out.println("compressibleRef 指向的物件位址: " + System.identityHashCode(compressibleRef));
        System.out.println("兩個 Reference 指向同一物件: " + (exportableRef == compressibleRef));

        System.out.println("\n=== 4. 可見 Method 差異說明 ===");
        System.out.println("exportableRef 只能呼叫 export()，無法看到 compress()；");
        System.out.println("compressibleRef 只能呼叫 compress()，無法看到 export()。");
        System.out.println("這體現了介面多型（Polymorphism）帶來的多重能力視角隔離。");
    }
}

interface Exportable {
    void export();
}

interface Compressible {
    void compress();
}

class BackupDocument implements Exportable, Compressible {
    private String fileName;
    private int fileSizeKb;

    public BackupDocument(String fileName, int fileSizeKb) {
        this.fileName = fileName;
        this.fileSizeKb = fileSizeKb;
    }

    @Override
    public void export() {
        System.out.println("[匯出成功] 檔案名稱: " + fileName + " (大小: " + fileSizeKb + " KB)");
    }

    @Override
    public void compress() {
        int compressedSize = (int) (fileSizeKb * 0.4);
        System.out.println("[壓縮成功] 檔案名稱: " + fileName + " | 原始大小: " + fileSizeKb + " KB -> 壓縮後大小: " + compressedSize + " KB");
    }
}