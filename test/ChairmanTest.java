import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChairmanTest {

    private Chairman chairman;

    @BeforeEach
    void setUp() {
        chairman = new Chairman("denstoreformand@delfin.sk.dk","Delfin4Ever_2026!");
    }

    @Test
    void getChairmanName(){
        assertEquals("Big Bossman",chairman.getName());
    }

    @Test
    void getChairmanEmail() {
        assertEquals("denstoreformand@delfin.sk.dk",chairman.getChairmanEmail());
    }

    @Test
    void getChairmanPassword() {
        assertEquals("Delfin4Ever_2026!",chairman.getChairmanPassword());
    }
}