import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainerTest {
    private Trainer Trainer;
    @BeforeEach
    void setUp() {
        Trainer = new Trainer("sejtraener@delfin.sk.dk", "RuleAndConquer_69!");
    }

    @Test
    void getTrainerName() {
        assertEquals("Hr. Trænermand", Trainer.getName());
    }

    @Test
    void getTrainerEmail() {
        assertEquals("sejtraener@delfin.sk.dk", Trainer.getTrainerEmail());
    }

    @Test
    void getTrainerPassword() {
        assertEquals("RuleAndConquer_69!", Trainer.getTrainerPassword());
    }
}