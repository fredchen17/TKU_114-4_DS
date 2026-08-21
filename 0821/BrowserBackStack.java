import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    private final Deque<String> history = new ArrayDeque<>();

    public void visit(String url) {
        history.push(url);
        System.out.println("訪問網頁: " + url);
    }

    public String back() {
        if (history.isEmpty()) {
            System.out.println("返回失敗: 瀏覽紀錄為空");
            return null;
        }
        String popped = history.pop();
        System.out.println("返回離開: " + popped);
        return popped;
    }

    public String current() {
        if (history.isEmpty()) {
            System.out.println("當前網頁: 無（紀錄為空）");
            return null;
        }
        String currentUrl = history.peek();
        System.out.println("當前網頁: " + currentUrl);
        return currentUrl;
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        System.out.println("--- 操作 1 ---");
        browser.current();

        System.out.println("\n--- 操作 2 ---");
        browser.back();

        System.out.println("\n--- 操作 3 ---");
        browser.visit("https://google.com");
        browser.visit("https://github.com");
        browser.visit("https://stackoverflow.com");

        System.out.println("\n--- 操作 4 ---");
        browser.current();

        System.out.println("\n--- 操作 5 ---");
        browser.back();
        browser.current();
        browser.back();
        browser.back();
        browser.back(); 
    }
}