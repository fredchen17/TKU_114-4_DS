import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "會員編號：" + memberId + " | 姓名：" + name + " | Email：" + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryMember member = (LibraryMember) obj;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("M001", "張小明", "ming@gmail.com");
        LibraryMember m2 = new LibraryMember("M001", "張小明", "ming_new@gmail.com");

        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);

        System.out.println("\n=== 比較結果 ===");
        System.out.println("m1 == m2 : " + (m1 == m2));
        System.out.println("m1.equals(m2) : " + m1.equals(m2));
        System.out.println("m1.equals(null) : " + m1.equals(null));
    }
}