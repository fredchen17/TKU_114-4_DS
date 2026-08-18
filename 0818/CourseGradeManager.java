public class CourseGradeManager {

    public static void main(String[] args) {
        CourseGrade[] students = {
            new CourseGrade("S001", "張小明", 85, 90, 88, 100),
            new CourseGrade("S002", "李大華", 40, 50, 45, 60),
            new CourseGrade("S003", "王美麗", 95, 92, 98, 90),
            new CourseGrade("S004", "陳志強", 60, 55, 50, 70),
            new CourseGrade("S005", "林雅婷", 30, 40, 20, 50)
        };

        double totalScoreSum = 0;
        CourseGrade topStudent = students[0];

        System.out.println("=== 學生成績明細 ===");
        for (CourseGrade student : students) {
            System.out.println(student.toString());
            
            double finalScore = student.calculateFinalScore();
            totalScoreSum += finalScore;

            if (finalScore > topStudent.calculateFinalScore()) {
                topStudent = student;
            }
        }

        double averageScore = totalScoreSum / students.length;
        System.out.println("\n=== 班級統計資訊 ===");
        System.out.printf("全班平均成績：%.2f 分\n", averageScore);
        System.out.printf("最高分學生：%s (%s) - %.2f 分 [%s]\n", 
                topStudent.getStudentName(), 
                topStudent.getStudentId(), 
                topStudent.calculateFinalScore(), 
                topStudent.getLevel());

        System.out.println("\n=== 不及格名單 (總分 < 60) ===");
        boolean hasFailedStudent = false;
        for (CourseGrade student : students) {
            if (student.calculateFinalScore() < 60) {
                System.out.printf("- %s (%s)：%.2f 分 [%s]\n", 
                        student.getStudentName(), 
                        student.getStudentId(), 
                        student.calculateFinalScore(), 
                        student.getLevel());
                hasFailedStudent = true;
            }
        }

        if (!hasFailedStudent) {
            System.out.println("無不及格學生。");
        }
    }
}

class CourseGrade {
    private String studentId;
    private String studentName;
    private double dailyScore;
    private double midtermScore;
    private double finalExamScore;
    private double attendanceScore;

    public CourseGrade(String studentId, String studentName, double dailyScore, double midtermScore, double finalExamScore, double attendanceScore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dailyScore = validateScore(dailyScore);
        this.midtermScore = validateScore(midtermScore);
        this.finalExamScore = validateScore(finalExamScore);
        this.attendanceScore = validateScore(attendanceScore);
    }

    private double validateScore(double score) {
        if (score < 0) return 0;
        if (score > 100) return 100;
        return score;
    }

    public double calculateFinalScore() {
        return (dailyScore * 0.50) + (midtermScore * 0.20) + (finalExamScore * 0.20) + (attendanceScore * 0.10);
    }

    public String getLevel() {
        double finalScore = calculateFinalScore();
        if (finalScore >= 90) return "A";
        if (finalScore >= 80) return "B";
        if (finalScore >= 70) return "C";
        if (finalScore >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("學號: %-5s | 姓名: %-4s | 平時: %5.1f | 期中: %5.1f | 期末: %5.1f | 出席: %5.1f | 總分: %5.2f | 等第: %s",
                studentId, studentName, dailyScore, midtermScore, finalExamScore, attendanceScore, calculateFinalScore(), getLevel());
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}