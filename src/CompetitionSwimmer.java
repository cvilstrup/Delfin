import java.time.LocalDate;
import java.time.Period;

public class CompetitionSwimmer extends Member {
    private Team team;
    public CompetitionSwimmer(String name, LocalDate birthday, String email, String password,
                              JuniorTeam juniorTeam, SeniorTeam seniorTeam)
    {
        super(name, birthday, email,password);
        calculateTeam(juniorTeam, seniorTeam);
    }

    private void calculateTeam(JuniorTeam juniorTeam, SeniorTeam seniorTeam) {
        int age = Period.between(getBirthday(), LocalDate.now()).getYears();
        if (age < 18) {
            team = juniorTeam;
        } else {
            team = seniorTeam;
        }
        team.addMember(this);
    }

    public Team getTeam() {
        return team;
    }
}