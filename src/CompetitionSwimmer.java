import java.time.LocalDate;

public class CompetitionSwimmer extends Member {
    Trainer trainer;

    public CompetitionSwimmer(String name, LocalDate birthday, String email,Trainer trainer) {
        super(name, birthday, email);
        this.trainer = trainer;
    }
}
