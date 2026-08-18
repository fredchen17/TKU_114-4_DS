class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, int initialBalance) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = Math.max(0, initialBalance);
        this.transactionCount = 0;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        this.transactionCount++;
        return true;
    }

    public boolean pay(int amount) {
        if (amount <= 0 || this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        this.transactionCount++;
        return true;
    }

    public boolean refund(int amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        this.transactionCount++;
        return true;
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

    public int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public String toString() {
        return "錢包ID：" + walletId + " | 持有者：" + owner + " | 餘額：" + balance + " | 交易次數：" + transactionCount;
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("W101", "張小明", 1000);
        System.out.println("=== 初始狀態 ===");
        System.out.println(wallet);

        System.out.println("\n=== 測試操作 ===");
        System.out.println("儲值 500：" + wallet.deposit(500));
        System.out.println("付款 300：" + wallet.pay(300));
        System.out.println("付款 2000 (餘額不足)：" + wallet.pay(2000));
        System.out.println("付款 -100 (無效金額)：" + wallet.pay(-100));
        System.out.println("退款 200：" + wallet.refund(200));

        System.out.println("\n=== 最終狀態 ===");
        System.out.println(wallet);
    }
}