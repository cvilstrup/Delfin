import java.time.LocalDate;

public class CompetitionSwimmer extends Member {
    Trainer trainer;

    public CompetitionSwimmer(String name, LocalDate birthday, String email,Trainer trainer) {
        super(name, birthday, email);
        this.trainer = trainer;
    }
    public static String getHold (int age) {
        if (age < 18) {
            return "Ungdomsholdet";
        } else {
            return "Seniorholdet";
        }
    }}
