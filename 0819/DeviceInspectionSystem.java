public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = {
            new Laptop("L001", "ThinkPad X1"),
            new Printer("P001", "Epson L3250"),
            new Router("R001", "ASUS RT-AX86U"),
            new Printer("P002", "Canon G3010")
        };

        System.out.println("=== 設備檢測與維護系統 ===");
        for (Device device : devices) {
            System.out.println("----------------------------------------");
            device.runDiagnostic();

            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}

class Device {
    private String id;
    private String model;

    public Device(String id, String model) {
        this.id = id;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void runDiagnostic() {
        System.out.println("[基本診斷] 設備 " + id + " (" + model + ") 運作正常。");
    }
}

class Laptop extends Device {
    public Laptop(String id, String model) {
        super(id, model);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[筆電診斷] 設備 " + getId() + " (" + getModel() + ") - CPU 與記憶體狀態正常。");
    }
}

class Printer extends Device {
    public Printer(String id, String model) {
        super(id, model);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[印表機診斷] 設備 " + getId() + " (" + getModel() + ") - 墨水與紙張狀態正常。");
    }

    public void cleanPrintHead() {
        System.out.println("  └─ [印表機維護] 執行噴頭清潔作業程序...");
    }
}

class Router extends Device {
    public Router(String id, String model) {
        super(id, model);
    }

    @Override
    public void runDiagnostic() {
        System.out.println("[路由器診斷] 設備 " + getId() + " (" + getModel() + ") - 網路連線與訊號強度正常。");
    }
}