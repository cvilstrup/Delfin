import java.util.ArrayList;
import java.util.List;

public class SwimmingClub {

    private JuniorTeam juniorTeam = new JuniorTeam(new Trainer(), "Juniorhold");
    private SeniorTeam seniorTeam = new SeniorTeam(new Trainer(), "Seniorhold");

// Opretter ArrayLists til at holde styr på swømmeklubbens medlemmer ...
    public List<Member> members = new ArrayList<>();

// Tilføjer et medlem til svømmeklubbens liste over medlemmer
    public void addMember(Member member) {
        members.add(member);
    }


// Henter array listen med alle medlemmer i svømmeklubben
    public List<Member> getMembers() {
        return members;
    }

// Går igennem array listen og printer alle medlemmer i svømmeklubben
    public void printMembers() {
        for (Member member : members) {
            IO.println(member);
            IO.println();
        }
    }

// Finder et bestemt medlem ud fra deres medlems ID
    public Member getMember(int memberID) {
        for (Member member : members) {
            if (memberID == member.getMemberID()) {
                return member;
            }
        }
    // Returnerer null hvis der ikke findes et medlem med det ID
        return null;
    }

    public JuniorTeam getJuniorTeam() {
        return juniorTeam;
    }

    public SeniorTeam getSeniorTeam() {
        return seniorTeam;
    }

    public boolean isEmailTaken(String email) {
        for (Member member : members) {
            if (member.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

// Lægger alle medlemmers kontingent sammen og returnerer det samlede forventede beløb
    public int calculateRevenue(){
        int totalRevenue = 0;
        for (Member member : members){
            totalRevenue += member.getMembershipFee();
        }
        return totalRevenue;
    }
}