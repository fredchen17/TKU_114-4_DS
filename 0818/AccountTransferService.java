public class AccountTransferService {
    public static void main(String[] args) {
        System.out.println("=== 測試 1: 成功轉帳 ===");
        Account acc1 = new Account("A001", 1000);
        Account acc2 = new Account("A002", 500);
        System.out.println("轉帳前 -> " + acc1 + " | " + acc2);
        boolean result1 = TransferService.transfer(acc1, acc2, 300);
        System.out.println("轉帳結果: " + result1);
        System.out.println("轉帳後 -> " + acc1 + " | " + acc2);

        System.out.println("\n=== 測試 2: 餘額不足 ===");
        Account acc3 = new Account("A003", 200);
        Account acc4 = new Account("A004", 500);
        System.out.println("轉帳前 -> " + acc3 + " | " + acc4);
        boolean result2 = TransferService.transfer(acc3, acc4, 500);
        System.out.println("轉帳結果: " + result2);
        System.out.println("轉帳後 -> " + acc3 + " | " + acc4);

        System.out.println("\n=== 測試 3: 同帳戶轉帳 ===");
        Account acc5 = new Account("A005", 1000);
        System.out.println("轉帳前 -> " + acc5);
        boolean result3 = TransferService.transfer(acc5, acc5, 100);
        System.out.println("轉帳結果: " + result3);
        System.out.println("轉帳後 -> " + acc5);

        System.out.println("\n=== 測試 4: Null 目標 ===");
        Account acc6 = new Account("A006", 1000);
        System.out.println("轉帳前 -> " + acc6);
        boolean result4 = TransferService.transfer(acc6, null, 100);
        System.out.println("轉帳結果: " + result4);
        System.out.println("轉帳後 -> " + acc6);
    }
}

class Account {
    private String accountNumber;
    private int balance;

    public Account(String accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "帳號: " + accountNumber + ", 餘額: " + balance;
    }
}

class TransferService {
    public static boolean transfer(Account source, Account target, int amount) {
        if (source == null || target == null) {
            return false;
        }
        if (source == target) {
            return false;
        }
        if (amount <= 0 || source.getBalance() < amount) {
            return false;
        }

        source.withdraw(amount);
        target.deposit(amount);
        return true;
    }
}