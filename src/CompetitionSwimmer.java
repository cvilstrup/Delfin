import java.time.LocalDate;
import java.time.Period;

public class CompetitionSwimmer extends Member {
    private Team team;
    SwimmingDiscipline swimmingDiscipline;
    public CompetitionSwimmer(String name, LocalDate birthday, String email, String password, LocalDate renewalDate,
                              JuniorTeam juniorTeam, SeniorTeam seniorTeam, SwimmingDiscipline swimmingDiscipline)
    {
        super(name, birthday, email,password, renewalDate);
        calculateTeam(juniorTeam, seniorTeam);
        this.swimmingDiscipline = swimmingDiscipline;
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