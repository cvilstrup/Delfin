import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public abstract class Team {
    private String name;
    Trainer trainer;
    private ArrayList<Member> members = new ArrayList<>();

    Team(Trainer trainer, String name){
        this.trainer=trainer;
        this.name=name;
    }

    public void addMember(Member member) {
        members.add(member);
    }
}
