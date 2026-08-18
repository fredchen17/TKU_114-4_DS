public class MessageSenderSystem {
    public static void main(String[] args) {
        MessageSender emailSender = new EmailSender();
        MessageSender smsSender = new SmsSender();
        MessageSender consoleSender = new ConsoleSender();

        System.out.println("=== 正常發送測試 ===");
        notify(emailSender, "user@example.com", "您的驗證碼為 1234");
        notify(smsSender, "0912345678", "您的餐點已外送達標");
        notify(consoleSender, "SystemAdmin", "系統記憶體使用率達 85%");

        System.out.println("\n=== 異常資料處理測試 ===");
        notify(emailSender, "", "測試訊息");
        notify(smsSender, "0987654321", "   ");
        notify(consoleSender, null, "核心系統告警");
    }

    public static void notify(MessageSender sender, String receiver, String message) {
        if (sender != null) {
            sender.send(receiver, message);
        }
    }
}

interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            System.out.println("[Email 發送失敗] 收件者或訊息內容不可為空白。");
            return;
        }
        System.out.println("[Email 成功發送] 寄至 " + receiver + " -> " + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            System.out.println("[SMS 發送失敗] 門號或訊息內容不可為空白。");
            return;
        }
        System.out.println("[SMS 成功發送] 傳送至 " + receiver + " -> " + message);
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            System.out.println("[Console 輸出失敗] 接收者或訊息內容不可為空白。");
            return;
        }
        System.out.println("[Console 日誌輸出] 對象: " + receiver + " | 訊息: " + message);
    }
}