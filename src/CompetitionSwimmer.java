import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class CompetitionSwimmer extends Member {
    private Team team;
    private ArrayList<SwimmingDiscipline> disciplines = new ArrayList<>();

    public CompetitionSwimmer(String name, LocalDate birthday, String email, String password, LocalDate renewalDate, LocalDate memberPaymentDue,
                              JuniorTeam juniorTeam, SeniorTeam seniorTeam) {
        super(name, birthday, email, password, renewalDate, memberPaymentDue);
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

    public ArrayList<SwimmingDiscipline> getDisciplines() {
        return disciplines;
    }

    @Override
    public String toString() {
        String teamName = (team instanceof JuniorTeam) ? "Junior" : "Senior";

        // Henter basisteksten fra Member og erstatter "Almindeligt medlem"
        String baseInfo = super.toString().replace("Almindeligt medlem", "Konkurrencesvømmer");

        return String.format("""
                        %s
                        Tilknyttet hold: %s, Discipliner: %s""",
                baseInfo, teamName, disciplines);
    }
}
