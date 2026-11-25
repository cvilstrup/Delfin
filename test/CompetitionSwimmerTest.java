import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionSwimmerTest {

    private CompetitionSwimmer competitionSwimmer;
    private Trainer juniorTrainer;
    private Trainer seniorTrainer;
    private JuniorTeam juniorTeam;
    private SeniorTeam seniorTeam;

    @BeforeEach
    void setUp() {
        juniorTrainer = new Trainer("junior@delfin.sk.dk", "Kode123");
        seniorTrainer = new Trainer("senior@delfin.sk.dk", "Kode123");

        juniorTeam = new JuniorTeam(juniorTrainer, "Juniorholdet");
        seniorTeam = new SeniorTeam(seniorTrainer, "Seniorholdet");

        competitionSwimmer = new CompetitionSwimmer("Jeffrey Epstein",
                                                    LocalDate.of(1951,1,20),
                                                    "j.epstein@gmail.com",
                                                    "JegElskerBørn123",
                                                    LocalDate.of(2027,11,25),
                                                    LocalDate.of(2027,1,1),
                                                    juniorTeam,seniorTeam);
    }

    @Test
    void getTeam() {
        assertEquals(seniorTeam, competitionSwimmer.getTeam());
    }

    @Test
    void addDiscipline() {
        competitionSwimmer.addDiscipline(SwimmingDiscipline.CRAWL);

        assertEquals(1, competitionSwimmer.getDisciplines().size());
        assertTrue(competitionSwimmer.getDisciplines().contains(SwimmingDiscipline.CRAWL));
    }

    @Test
    void addSwimResult() {
        SwimResult result = new SwimResult(SwimmingDiscipline.CRAWL, 54.2, LocalDate.of(2026, 11, 20));
        competitionSwimmer.addSwimResult(result);

        assertEquals(1, competitionSwimmer.getSwimResults().size());
        assertEquals(result, competitionSwimmer.getSwimResults().get(0));
    }

    @Test
    void getSwimResults() {
        assertTrue(competitionSwimmer.getSwimResults().isEmpty());
    }

    @Test
    void getDisciplines() {
        assertTrue(competitionSwimmer.getDisciplines().isEmpty());
    }

    @Test
    void testToString() {
        String output = competitionSwimmer.toString();

        assertTrue(output.contains("Konkurrencesvømmer"));
        assertTrue(output.contains("Tilknyttet hold: Senior"));
    }
}