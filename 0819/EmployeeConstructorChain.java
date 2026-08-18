public class EmployeeConstructorChain {
    public static void main(String[] args) {
        System.out.println("=== 建立全職員工物件 ===");
        FullTimeEmployee ft = new FullTimeEmployee("E001", "Alice", 50000);
        System.out.println("全職員工薪資: " + ft.calculatePay());

        System.out.println("\n=== 建立兼職員工物件 ===");
        PartTimeEmployee pt = new PartTimeEmployee("E002", "Bob", 200, 80);
        System.out.println("兼職員工薪資: " + pt.calculatePay());

        System.out.println("\n=== 邊界條件測試（負數轉 0） ===");
        FullTimeEmployee ftNegative = new FullTimeEmployee("E003", "Charlie", -30000);
        System.out.println("負數月薪結果: " + ftNegative.calculatePay());

        PartTimeEmployee ptNegative = new PartTimeEmployee("E004", "David", -150, -20);
        System.out.println("負數時薪與時數結果: " + ptNegative.calculatePay());

        System.out.println("\n=== Constructor 實際執行順序說明 ===");
        System.out.println("1. 建立 FullTimeEmployee 時：");
        System.out.println("   先執行父類別 EmployeeBase 的 constructor，再執行子類別 FullTimeEmployee 的 constructor。");
        System.out.println("2. 建立 PartTimeEmployee 時：");
        System.out.println("   先執行父類別 EmployeeBase 的 constructor，再執行子類別 PartTimeEmployee 的 constructor。");
    }
}

abstract class EmployeeBase {
    private String id;
    private String name;

    public EmployeeBase(String id, String name) {
        System.out.println("執行 Constructor: EmployeeBase");
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private double monthlySalary;

    public FullTimeEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        System.out.println("執行 Constructor: FullTimeEmployee");
        this.monthlySalary = monthlySalary < 0 ? 0 : monthlySalary;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends EmployeeBase {
    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);
        System.out.println("執行 Constructor: PartTimeEmployee");
        this.hourlyRate = hourlyRate < 0 ? 0 : hourlyRate;
        this.hoursWorked = hoursWorked < 0 ? 0 : hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}