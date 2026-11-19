import java.time.LocalDate;
import java.time.Period;

public class CompetitionSwimmer extends Member {
    private Team team;
    public CompetitionSwimmer(String name, LocalDate birthday, String email, String password, LocalDate renewalDate,
                              JuniorTeam juniorTeam, SeniorTeam seniorTeam)
    {
        super(name, birthday, email,password, renewalDate);
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

    public String toString(){
        return String.format("""
                           Navn: %s
                           Medlems-ID: %d
                           Fødselsdag: %s
                           E-mail: %s
                           Kodeord: %s
                           Status af medlemsskab: %s
                           Type medlem: (konkurrence eller almindelig)
                           Tilknyttet hold: (junior eller senior)
                            """);
    }
}