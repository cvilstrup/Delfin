import java.time.LocalDate;
import java.time.Period;

public abstract class Team {
    private String name;
    Trainer trainer;

    Team(Trainer trainer, String name){
        this.trainer=trainer;
        this.name=name;
    }
}
