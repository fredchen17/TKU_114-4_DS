public class ReportExporterFactory {
    public static void main(String[] args) {
        String title = "年度銷售統計表";
        int[] salesData = {120, 250, 310, 180, 420};

        String[] testFormats = {"CSV", "JSON", "TEXT", "XML"};

        System.out.println("=== 報表匯出測試 ===");
        for (String format : testFormats) {
            System.out.println("----------------------------------------");
            System.out.println("請求格式: " + format);
            
            ReportExporter exporter = createExporter(format);
            exportReport(exporter, title, salesData);
        }

        System.out.println("\n----------------------------------------");
        System.out.println("=== 測試 Null 資料處理 ===");
        ReportExporter csvExporter = createExporter("CSV");
        exportReport(csvExporter, title, null);
    }

    public static ReportExporter createExporter(String format) {
        if (format == null) {
            return new TextExporter();
        }

        switch (format.trim().toUpperCase()) {
            case "CSV":
                return new CsvExporter();
            case "JSON":
                return new JsonExporter();
            case "TEXT":
            default:
                return new TextExporter();
        }
    }

    public static void exportReport(ReportExporter exporter, String title, int[] values) {
        if (exporter != null) {
            exporter.export(title, values);
        }
    }
}

interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("[CSV 匯出] " + (title == null ? "無標題" : title));
        System.out.print("數據內容: ");
        if (values == null || values.length == 0) {
            System.out.println("(無資料)");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i < values.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }
}

class JsonExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("[JSON 匯出]");
        System.out.println("{");
        System.out.println("  \"title\": \"" + (title == null ? "" : title) + "\",");
        System.out.print("  \"values\": [");

        if (values == null || values.length == 0) {
            System.out.println("]");
            System.out.println("}");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i]);
            if (i < values.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("}");
    }
}

class TextExporter implements ReportExporter {
    @Override
    public void export(String title, int[] values) {
        System.out.println("[純文字匯出] === " + (title == null ? "無標題" : title) + " ===");
        if (values == null || values.length == 0) {
            System.out.println("數據內容: (無資料)");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            System.out.println("項目 " + (i + 1) + ": " + values[i]);
        }
    }
}