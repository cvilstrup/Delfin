import java.time.LocalDate;

public class Member {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private String email;

    public LocalDate getBirthday() {
        return birthday;
    }
}
