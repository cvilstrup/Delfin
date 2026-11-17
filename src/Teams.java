import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Teams {
    ArrayList<Member> juniorTeam = new ArrayList<>();
    ArrayList<Member> seinorTeam = new ArrayList<>();

    public void calculateTeam(Member member) {
        int age = Period.between(member.getBirthday(), LocalDate.now()).getYears();
        if (age < 18) {
            juniorTeam.add(member);
            if (age >= 18) {
                seinorTeam.add(member);
            } else {
                throw new IllegalStateException("Unknown membership status");
            }
        }
    }
}
