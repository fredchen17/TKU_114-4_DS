import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    private final Deque<String> undoStack = new ArrayDeque<>();
    private final Deque<String> redoStack = new ArrayDeque<>();

    public void type(String text) {
        undoStack.push(text);
        redoStack.clear();
        printState("輸入 \"" + text + "\"");
    }

    public String undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Undo 失敗: 無可復原的操作");
            printState("Undo 失敗");
            return null;
        }
        String text = undoStack.pop();
        redoStack.push(text);
        printState("Undo -> 取消 \"" + text + "\"");
        return text;
    }

    public String redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Redo 失敗: 無可重做的操作");
            printState("Redo 失敗");
            return null;
        }
        String text = redoStack.pop();
        undoStack.push(text);
        printState("Redo -> 重做 \"" + text + "\"");
        return text;
    }

    public void printState(String action) {
        System.out.printf("%-20s | Undo Stack: %-25s | Redo Stack: %s%n",
                action, undoStack, redoStack);
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        System.out.println("=== 1. 測試空 Stack 邊界條件 ===");
        editor.undo();
        editor.redo();

        System.out.println("\n=== 2. 測試連續輸入與 Undo/Redo ===");
        editor.type("A");
        editor.type("B");
        editor.type("C");

        editor.undo();
        editor.undo();

        editor.redo();

        System.out.println("\n=== 3. 測試新輸入清空 Redo Stack ===");
        editor.type("D");
        editor.redo();

        System.out.println("\n=== 4. 清空所有 Undo 操作 ===");
        editor.undo();
        editor.undo();
        editor.undo();
    }
}