import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public abstract class Team {
    private String name;
    Trainer trainer;

    Team(Trainer trainer, String name){
        this.trainer=trainer;
        this.name=name;
    }

}
