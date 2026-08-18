public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new SalariedEmployee("E001", "張大明", 55000),
            new HourlyEmployee("E002", "李小華", 200, 160),
            new CommissionEmployee("E003", "王阿美", 30000, 500000, 0.05),
            new SalariedEmployee("E004", "陳建國", 62000),
            new HourlyEmployee("E005", "林靜宜", 180, 120)
        };

        double totalPayroll = 0;
        Employee highestPaidEmployee = employees[0];

        System.out.println("=== 員工薪資結算明細 ===");
        for (Employee emp : employees) {
            double pay = emp.calculatePay();
            totalPayroll += pay;

            System.out.printf("工號: %-5s | 姓名: %-4s | 實領薪資: %8.2f 元\n",
                    emp.getId(), emp.getName(), pay);

            if (pay > highestPaidEmployee.calculatePay()) {
                highestPaidEmployee = emp;
            }
        }

        System.out.println("\n=== 薪資統計結果 ===");
        System.out.printf("公司總薪資支出：%.2f 元\n", totalPayroll);
        System.out.printf("最高薪資員工：%s (%s) - %.2f 元\n",
                highestPaidEmployee.getName(),
                highestPaidEmployee.getId(),
                highestPaidEmployee.calculatePay());
    }
}

abstract class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
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

class SalariedEmployee extends Employee {
    private double monthlySalary;

    public SalariedEmployee(String id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary < 0 ? 0 : monthlySalary;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;

    public HourlyEmployee(String id, String name, double hourlyRate, double hoursWorked) {
        super(id, name);
        this.hourlyRate = hourlyRate < 0 ? 0 : hourlyRate;
        this.hoursWorked = hoursWorked < 0 ? 0 : hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

class CommissionEmployee extends Employee {
    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    public CommissionEmployee(String id, String name, double baseSalary, double salesAmount, double commissionRate) {
        super(id, name);
        this.baseSalary = baseSalary < 0 ? 0 : baseSalary;
        this.salesAmount = salesAmount < 0 ? 0 : salesAmount;
        this.commissionRate = commissionRate < 0 ? 0 : commissionRate;
    }

    @Override
    public double calculatePay() {
        return baseSalary + (salesAmount * commissionRate);
    }
}