import java.util.Objects;

class Task {
    private final String id;
    private final String title;

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task[" + id + ": " + title + "]";
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean addFirst(Task task) {
        if (task == null || containsId(task.getId())) {
            System.out.println("addFirst 失敗: ID 重複或無效 (" + (task != null ? task.getId() : "null") + ")");
            return false;
        }
        TaskNode newNode = new TaskNode(task);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("addFirst 成功: " + task);
        return true;
    }

    public boolean addLast(Task task) {
        if (task == null || containsId(task.getId())) {
            System.out.println("addLast 失敗: ID 重複或無效 (" + (task != null ? task.getId() : "null") + ")");
            return false;
        }
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("addLast 成功: " + task);
        return true;
    }

    public Task findById(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getId().equals(id)) {
                System.out.println("findById 成功: 找到 " + current.task);
                return current.task;
            }
            current = current.next;
        }
        System.out.println("findById 失敗: 找不到 ID " + id);
        return null;
    }

    public boolean removeById(String id) {
        if (head == null) {
            System.out.println("removeById 失敗: List 為空，無法刪除 ID " + id);
            return false;
        }

        if (head.task.getId().equals(id)) {
            System.out.println("removeById 成功 (刪除 Head): " + head.task);
            head = head.next;
            size--;
            return true;
        }

        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.getId().equals(id)) {
                System.out.println("removeById 成功: " + current.next.task);
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }

        System.out.println("removeById 失敗: 找不到 ID " + id);
        return false;
    }

    public boolean insertAfter(String existingId, Task newTask) {
        if (newTask == null || containsId(newTask.getId())) {
            System.out.println("insertAfter 失敗: 新任務 ID 重複或無效 (" + (newTask != null ? newTask.getId() : "null") + ")");
            return false;
        }

        TaskNode current = head;
        while (current != null) {
            if (current.task.getId().equals(existingId)) {
                TaskNode newNode = new TaskNode(newTask);
                newNode.next = current.next;
                current.next = newNode;
                size++;
                System.out.println("insertAfter 成功: 在 " + existingId + " 後插入 " + newTask);
                return true;
            }
            current = current.next;
        }

        System.out.println("insertAfter 失敗: 找不到目標 ID " + existingId);
        return false;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        System.out.print("List 內容 (size=" + size + "): ");
        if (head == null) {
            System.out.println("[ Empty ]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        TaskNode current = head;
        while (current != null) {
            sb.append(current.task.toString());
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    private boolean containsId(String id) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getId().equals(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

public class LinkedTaskListSystem {

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("=== 1. 測試空 List 操作 ===");
        taskList.printAll();
        taskList.removeById("T101");
        taskList.findById("T101");

        System.out.println("\n=== 2. 測試新增與重複 ID 阻擋 ===");
        taskList.addFirst(new Task("T102", "撰寫報告"));
        taskList.addFirst(new Task("T101", "開會預備"));
        taskList.addLast(new Task("T104", "程式重構"));
        taskList.printAll();

        taskList.addFirst(new Task("T101", "重複 ID 測試"));
        taskList.addLast(new Task("T102", "重複 ID 測試"));

        System.out.println("\n=== 3. 測試指定位置插入 (insertAfter) ===");
        taskList.insertAfter("T102", new Task("T103", "單元測試"));
        taskList.insertAfter("T999", new Task("T105", "無效插入"));
        taskList.printAll();

        System.out.println("\n=== 4. 測試搜尋 (findById) ===");
        taskList.findById("T103");
        taskList.findById("T999");

        System.out.println("\n=== 5. 測試刪除 Middle ===");
        taskList.removeById("T102");
        taskList.printAll();

        System.out.println("\n=== 6. 測試刪除 Tail ===");
        taskList.removeById("T104");
        taskList.printAll();

        System.out.println("\n=== 7. 測試刪除 Head ===");
        taskList.removeById("T101");
        taskList.printAll();

        System.out.println("\n=== 8. 刪除剩餘元素至清空 ===");
        taskList.removeById("T103");
        taskList.printAll();
        taskList.removeById("T103");
    }
}