import java.util.Arrays;

public class WalletHistoryManager {
    public static void main(String[] args) {
        Wallet walletA = new Wallet("W001", "Alice", 1000, 5);
        Wallet walletB = new Wallet("W002", "Bob", 500, 5);

        walletA.deposit(200);
        walletA.withdraw(100);

        walletA.transferTo(walletB, 300);

        System.out.println("=== Wallet A Statement ===");
        walletA.printStatement();

        System.out.println("\n=== Wallet B Statement ===");
        walletB.printStatement();

        System.out.println("\n=== 查詢 Wallet A 的第 2 筆交易 ===");
        Transaction tx = walletA.findTransaction(2);
        if (tx != null) {
            System.out.println("找到交易：" + tx);
        } else {
            System.out.println("找不到該筆交易。");
        }

        System.out.println("\n=== 計算 Wallet A 存款 (DEPOSIT) 總額 ===");
        System.out.println("DEPOSIT 總額：" + walletA.totalByType("DEPOSIT"));

        System.out.println("\n=== 計算 Wallet A 轉帳輸出 (TRANSFER_OUT) 總額 ===");
        System.out.println("TRANSFER_OUT 總額：" + walletA.totalByType("TRANSFER_OUT"));
    }
}

class Transaction {
    private int sequence;
    private String type;
    private int amount;
    private int balanceAfter;

    public Transaction(int sequence, String type, int amount, int balanceAfter) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public int getSequence() {
        return sequence;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return String.format("#%d | 類型: %-12s | 金額: %5d | 餘額: %5d", sequence, type, amount, balanceAfter);
    }
}

class Wallet {
    private String walletId;
    private String owner;
    private int balance;
    private Transaction[] transactions;
    private int transactionCount;

    public Wallet(String walletId, String owner, int initialBalance, int capacity) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = initialBalance;
        this.transactions = new Transaction[capacity];
        this.transactionCount = 0;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isFull() {
        return transactionCount >= transactions.length;
    }

    private boolean addTransaction(String type, int amount, int newBalance) {
        if (isFull()) {
            return false;
        }
        transactionCount++;
        transactions[transactionCount - 1] = new Transaction(transactionCount, type, amount, newBalance);
        return true;
    }

    public boolean deposit(int amount) {
        if (amount <= 0 || isFull()) {
            return false;
        }
        balance += amount;
        addTransaction("DEPOSIT", amount, balance);
        return true;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0 || balance < amount || isFull()) {
            return false;
        }
        balance -= amount;
        addTransaction("WITHDRAW", amount, balance);
        return true;
    }

    public boolean transferTo(Wallet target, int amount) {
        if (target == null || target == this) {
            return false;
        }
        if (amount <= 0 || balance < amount) {
            return false;
        }
        if (this.isFull() || target.isFull()) {
            return false;
        }

        this.balance -= amount;
        this.addTransaction("TRANSFER_OUT", amount, this.balance);

        target.balance += amount;
        target.addTransaction("TRANSFER_IN", amount, target.balance);

        return true;
    }

    public Transaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }
        return null;
    }

    public int totalByType(String type) {
        if (type == null) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (type.equalsIgnoreCase(transactions[i].getType())) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    public void printStatement() {
        System.out.println("錢包 ID: " + walletId + " | 持有者: " + owner + " | 當前餘額: " + balance);
        System.out.println("--- 交易明細 ---");
        if (transactionCount == 0) {
            System.out.println("無交易紀錄。");
        } else {
            for (int i = 0; i < transactionCount; i++) {
                System.out.println(transactions[i]);
            }
        }
    }
}