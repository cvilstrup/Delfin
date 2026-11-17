import java.time.LocalDate;
import java.time.Period;

public class CompetitionSwimmer extends Member {
    LocalDate birthday;
    Trainer trainer;
    Teams teams;

    public CompetitionSwimmer(String name, LocalDate birthday, String email,Trainer trainer) {
        super(name, birthday, email);
        this.trainer = trainer;
    }

        @Override
        public LocalDate getBirthday () {
            return birthday;
        }
}
