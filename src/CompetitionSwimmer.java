import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
//x
public class CompetitionSwimmer extends Member {
    private Team team;
    private ArrayList<SwimmingDiscipline> disciplines = new ArrayList<>();
    private ArrayList<SwimResult> swimResults = new ArrayList<>();

    public CompetitionSwimmer(String name, LocalDate birthday, String email, String password, LocalDate renewalDate,
                              JuniorTeam juniorTeam, SeniorTeam seniorTeam) {
        super(name, birthday, email, password, renewalDate);
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

    public void addDiscipline(SwimmingDiscipline discipline) {
        if (!disciplines.contains(discipline)) {
            disciplines.add(discipline);
        }
    }

    public void addSwimResult(SwimResult result) {
        swimResults.add(result);
    }

    public ArrayList<SwimResult> getSwimResults() {
        return swimResults;
    }

    public ArrayList<SwimmingDiscipline> getDisciplines() {
        return disciplines;
    }

    @Override
    public String toString() {
        String teamName = (team instanceof JuniorTeam) ? "Junior" : "Senior";

        String baseInfo = super.toString().replace("Almindeligt medlem", "Konkurrencesvømmer");

        return String.format("""
                        %s
                        Tilknyttet hold: %s, Discipliner: %s""",
                baseInfo, teamName, disciplines);
    }
}
