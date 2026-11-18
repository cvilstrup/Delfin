import java.time.LocalDate;
import java.time.Period;

public class CompetitionSwimmer extends Member {
    LocalDate birthday;
    Trainer trainer;
    Team team;

    public CompetitionSwimmer(String name, LocalDate birthday, String email, Team team) {
        super(name, birthday, email);
        calculateTeam();
    }

        @Override
        public LocalDate getBirthday () {
            return birthday;
        }
    public void calculateTeam() {
        int age = Period.between(getBirthday(), LocalDate.now()).getYears();
        if (age < 18) {
                this.team=JuniorTeam;
            if (age >= 18) {
                this.team=SeniorTeam;
            } else {
                throw new IllegalStateException("Unknown membership status");
            }
        }
    }
}
