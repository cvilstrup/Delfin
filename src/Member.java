import java.time.LocalDate;

public class Member {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private String email;

    public int getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }
}
