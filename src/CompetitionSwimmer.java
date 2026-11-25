import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionSwimmer extends Member {
    private JuniorTeam juniorTeam;
    private SeniorTeam seniorTeam;
    private ArrayList<SwimmingDiscipline> disciplines = new ArrayList<>();
    private ArrayList<SwimResult> swimResults = new ArrayList<>();

    public CompetitionSwimmer(String name, LocalDate birthday, String email, String password, LocalDate renewalDate, LocalDate memberPaymentDue,
                              JuniorTeam juniorTeam, SeniorTeam seniorTeam) {
        super(name, birthday, email, password, renewalDate, memberPaymentDue);
        this.juniorTeam = juniorTeam;
        this.seniorTeam=seniorTeam;
    }

    public Team getTeam() {
        if (getAge() < 18) {
            return juniorTeam;
        }
        return seniorTeam;
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
        String teamName = (getTeam() instanceof JuniorTeam) ? "Junior" : "Senior";

        String baseInfo = super.toString().replace("Almindeligt medlem", "Konkurrencesvømmer");

        return String.format("""
                        %s
                        Tilknyttet hold: %s, Discipliner: %s""",
                baseInfo, teamName, disciplines);
    }
}
