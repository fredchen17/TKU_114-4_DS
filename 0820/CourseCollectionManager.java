import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseCollectionManager {
    public static void main(String[] args) {
        CourseManager manager = new CourseManager();

        System.out.println("=== 1. 新增報名資料測試 ===");
        manager.addStudent(new StudentCourse("S001", "Alice", 95, "Java"));
        manager.addStudent(new StudentCourse("S002", "Bob", 82, "Python"));
        manager.addStudent(new StudentCourse("S003", "Charlie", 75, "Java"));
        manager.addStudent(new StudentCourse("S004", "David", 68, "Web"));
        manager.addStudent(new StudentCourse("S005", "Eve", 55, "  "));
        manager.addStudent(new StudentCourse("S006", "Frank", 82, "Java"));
        
        // 測試重複學號（預期新增失敗）
        manager.addStudent(new StudentCourse("S001", "Alice Duplicate", 100, "AI"));

        System.out.println("\n=== 目前所有學員清單 ===");
        manager.printAllStudents();

        System.out.println("\n=== 2. 更新成績測試 (updateScore) ===");
        manager.updateScore("S005", 88);
        manager.updateScore("S999", 100);

        System.out.println("\n=== 3. 依標籤查詢 (findByTag) ===");
        System.out.println("查詢標籤 'Java':");
        for (StudentCourse sc : manager.findByTag("Java")) {
            System.out.println("  " + sc);
        }

        System.out.println("\n=== 4. 成績級距統計 (scoreDistribution) ===");
        Map<String, Integer> dist = manager.scoreDistribution();
        for (Map.Entry<String, Integer> entry : dist.entrySet()) {
            System.out.println("級距 " + entry.getKey() + ": " + entry.getValue() + " 人");
        }

        System.out.println("\n=== 5. 排名前 N 名 (top) ===");
        System.out.println("取前 3 名:");
        for (StudentCourse sc : manager.top(3)) {
            System.out.println("  " + sc);
        }

        System.out.println("\n取前 10 名 (超出總人數):");
        for (StudentCourse sc : manager.top(10)) {
            System.out.println("  " + sc);
        }

        System.out.println("\n=== 6. 移除低於指定分數學員 (removeBelow) ===");
        System.out.println("移除分數低於 70 分的學員...");
        manager.removeBelow(70);

        System.out.println("\n清理後的總覽:");
        manager.printAllStudents();
    }
}

class StudentCourse {
    private String studentId;
    private String name;
    private int score;
    private String tag;

    public StudentCourse(String studentId, String name, int score, String tag) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
        this.tag = (tag == null || tag.trim().isEmpty()) ? "未分類" : tag.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTag() {
        return tag;
    }

    public String getGradeLevel() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourse that = (StudentCourse) o;
        return studentId != null ? studentId.equals(that.studentId) : that.studentId == null;
    }

    @Override
    public int hashCode() {
        return studentId != null ? studentId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("學號: %-4s | 姓名: %-8s | 分數: %3d (%s) | 標籤: %-6s",
                studentId, name, score, getGradeLevel(), tag);
    }
}

class CourseManager {
    private List<StudentCourse> studentList = new ArrayList<>();
    private Set<StudentCourse> studentSet = new HashSet<>();
    private Map<String, StudentCourse> studentMap = new HashMap<>();

    public boolean addStudent(StudentCourse student) {
        if (student == null || studentSet.contains(student)) {
            System.out.println("新增失敗: 學號重複或資料無效 (" + (student != null ? student.getStudentId() : "null") + ")");
            return false;
        }
        studentList.add(student);
        studentSet.add(student);
        studentMap.put(student.getStudentId(), student);
        return true;
    }

    public boolean updateScore(String studentId, int score) {
        StudentCourse student = studentMap.get(studentId);
        if (student == null) {
            System.out.println("更新失敗: 找不到學號 " + studentId);
            return false;
        }
        student.setScore(score);
        System.out.println("成功更新學號 " + studentId + " 的成績為 " + score + " 分");
        return true;
    }

    public List<StudentCourse> findByTag(String tag) {
        List<StudentCourse> result = new ArrayList<>();
        String targetTag = (tag == null || tag.trim().isEmpty()) ? "未分類" : tag.trim();

        for (StudentCourse sc : studentList) {
            if (sc.getTag().equalsIgnoreCase(targetTag)) {
                result.add(sc);
            }
        }
        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> distribution = new HashMap<>();
        distribution.put("A", 0);
        distribution.put("B", 0);
        distribution.put("C", 0);
        distribution.put("D", 0);
        distribution.put("F", 0);

        for (StudentCourse sc : studentList) {
            String grade = sc.getGradeLevel();
            distribution.put(grade, distribution.get(grade) + 1);
        }
        return distribution;
    }

    public List<StudentCourse> top(int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }

        List<StudentCourse> sortedList = new ArrayList<>(studentList);
        sortedList.sort(new Comparator<StudentCourse>() {
            @Override
            public int compare(StudentCourse s1, StudentCourse s2) {
                int scoreCompare = Integer.compare(s2.getScore(), s1.getScore());
                if (scoreCompare != 0) {
                    return scoreCompare;
                }
                return s1.getStudentId().compareTo(s2.getStudentId());
            }
        });

        int limit = Math.min(count, sortedList.size());
        return sortedList.subList(0, limit);
    }

    public void removeBelow(int minimum) {
        Iterator<StudentCourse> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            StudentCourse sc = iterator.next();
            if (sc.getScore() < minimum) {
                iterator.remove();
                studentSet.remove(sc);
                studentMap.remove(sc.getStudentId());
            }
        }
    }

    public void printAllStudents() {
        System.out.println("目前共有 " + studentList.size() + " 位學員:");
        for (StudentCourse sc : studentList) {
            System.out.println("  " + sc);
        }
    }
}