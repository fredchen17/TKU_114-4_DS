import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

class Patient {
    private final String id;
    private final String name;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "病歷號: " + id + " (" + name + ")";
    }
}

public class ClinicQueueSystem {

    private final Deque<Patient> waitingQueue = new ArrayDeque<>();
    private final List<Patient> completedList = new ArrayList<>();

    public void register(Patient patient) {
        waitingQueue.offerLast(patient);
        System.out.println("掛號成功: " + patient);
    }

    public boolean cancelRegistration(String patientId) {
        Iterator<Patient> iterator = waitingQueue.iterator();
        while (iterator.hasNext()) {
            Patient patient = iterator.next();
            if (patient.getId().equals(patientId)) {
                iterator.remove();
                System.out.println("取消掛號成功: " + patient);
                return true;
            }
        }
        System.out.println("取消失敗: 找不到病歷號 " + patientId);
        return false;
    }

    public Patient callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("叫號失敗: 當前無等候病患");
            return null;
        }
        Patient patient = waitingQueue.pollFirst();
        completedList.add(patient);
        System.out.println("叫號就診: " + patient);
        return patient;
    }

    public Patient peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("查看失敗: 當前無等候病患");
            return null;
        }
        Patient patient = waitingQueue.peekFirst();
        System.out.println("下一位就診病患: " + patient);
        return patient;
    }

    public void printCompletedList() {
        System.out.println("=== 當日已完成看診清單 ===");
        if (completedList.isEmpty()) {
            System.out.println("(無完成紀錄)");
        } else {
            for (int i = 0; i < completedList.size(); i++) {
                System.out.println((i + 1) + ". " + completedList.get(i));
            }
        }
    }

    public void printWaitingQueue() {
        System.out.println("當前等候隊列 (共 " + waitingQueue.size() + " 人): " + waitingQueue);
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("=== 1. 測試空隊列防護 ===");
        clinic.peekNext();
        clinic.callNext();

        System.out.println("\n=== 2. 一般掛號 (FIFO) ===");
        clinic.register(new Patient("P001", "張小明"));
        clinic.register(new Patient("P002", "李大華"));
        clinic.register(new Patient("P003", "王美麗"));
        clinic.register(new Patient("P004", "陳志明"));
        clinic.printWaitingQueue();

        System.out.println("\n=== 3. 查看下一位與叫號看診 ===");
        clinic.peekNext();
        clinic.callNext();
        clinic.printWaitingQueue();

        System.out.println("\n=== 4. 取消指定病歷號 ===");
        clinic.cancelRegistration("P003");
        clinic.cancelRegistration("P999");
        clinic.printWaitingQueue();

        System.out.println("\n=== 5. 陸續完成看診 ===");
        clinic.callNext();
        clinic.callNext();
        clinic.callNext();

        System.out.println("\n=== 6. 輸出當日已完成看診清單 ===");
        clinic.printCompletedList();
    }
}