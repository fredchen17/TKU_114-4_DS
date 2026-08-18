import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollmentSet = new HashSet<>();

        System.out.println("=== 1. 報名測試 ===");
        System.out.println("S001 報名 CS101: " + enrollmentSet.add(new Enrollment("S001", "CS101")));
        System.out.println("S001 報名 CS102 (同一人不同課): " + enrollmentSet.add(new Enrollment("S001", "CS102")));
        System.out.println("S002 報名 CS101: " + enrollmentSet.add(new Enrollment("S002", "CS101")));
        System.out.println("S001 重複報名 CS101 (同一人同課): " + enrollmentSet.add(new Enrollment("S001", "CS101")));

        System.out.println("\n目前總報名筆數: " + enrollmentSet.size());

        System.out.println("\n=== 2. 使用新建立但身分相同的物件測試 contains() ===");
        Enrollment checkTarget = new Enrollment("S001", "CS101");
        System.out.println("檢查是否包含 (S001, CS101): " + enrollmentSet.contains(checkTarget));

        Enrollment notFoundTarget = new Enrollment("S002", "CS102");
        System.out.println("檢查是否包含 (S002, CS102): " + enrollmentSet.contains(notFoundTarget));

        System.out.println("\n=== 3. 使用新建立但身分相同的物件測試 remove() ===");
        Enrollment removeTarget = new Enrollment("S001", "CS101");
        System.out.println("取消報名 (S001, CS101): " + enrollmentSet.remove(removeTarget));
        System.out.println("再次取消報名 (S001, CS101): " + enrollmentSet.remove(removeTarget));

        System.out.println("\n取消後總報名筆數: " + enrollmentSet.size());
    }
}

class Enrollment {
    private String studentId;
    private String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return String.format("Enrollment[Student: %s, Course: %s]", studentId, courseCode);
    }
}