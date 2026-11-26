import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUITest {

    private ConsoleUI consoleUI;
    private SwimmingClub swimmingClub;
    private Chairman chairman;
    private Trainer trainer;
    private Treasurer treasurer;

    //De her variabler oprettes til at programmet kan overtage tastaturen og køre uden at brugeren selv skal indtaste noget
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        swimmingClub = new SwimmingClub();
        chairman = new Chairman("denstoreformand@delfin.sk.dk","Delfin4Ever_2026!");
        treasurer = new Treasurer("bigbank@delfin.sk.dk","SkejsOp123");
        trainer = new Trainer("sejtræner@delfin.sk.dk","RuleAndConquer_69!");
        consoleUI = new ConsoleUI(swimmingClub, chairman,treasurer,trainer);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    //Rydder op efter hver test metode
    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void run() {
        //Istedet for at brugeren skriver det fortæller vi programmet hvad den skal skrive for os
        String simulatedInput = "0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        consoleUI = new ConsoleUI(swimmingClub, chairman, treasurer, trainer);

        consoleUI.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Vælg bruger: "));
    }

    @Test
    void getSwimmingClub() {
        assertEquals(swimmingClub, consoleUI.getSwimmingClub());
    }

    @Test
    void setSwimmingClub() {
        SwimmingClub newSwimmingClub = new SwimmingClub();

        consoleUI.setSwimmingClub(newSwimmingClub);

        assertEquals(newSwimmingClub, consoleUI.getSwimmingClub());
        assertNotEquals(swimmingClub, consoleUI.getSwimmingClub());
    }
}