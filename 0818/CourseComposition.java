class Instructor {
    private String id;
    private String name;

    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Course {
    private String courseCode;
    private String title;
    private Instructor instructor;

    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    public String summary() {
        return "課程代碼：" + courseCode + " | 課程名稱：" + title + " | 授課教師：" + instructor.getName() + " (" + instructor.getId() + ")";
    }
}

public class CourseComposition {
    public static void main(String[] args) {
        Instructor inst = new Instructor("T001", "張教授");

        Course c1 = new Course("CS101", "物件導向程式設計", inst);
        Course c2 = new Course("CS102", "資料結構", inst);

        System.out.println(c1.summary());
        System.out.println(c2.summary());
    }
}