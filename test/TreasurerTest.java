import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasurerTest {

    private Treasurer treasurer;

    @BeforeEach
    void setUp(){
        treasurer = new Treasurer("bigbank@delfin.sk.dk","SkejsOp123");
    }

    @Test
    void getName() {
        assertEquals("Thorkild Hansen",treasurer.getName());
    }

    @Test
    void getTreasurerEmail() {
        assertEquals("bigbank@delfin.sk.dk",treasurer.getTreasurerEmail());
    }

    @Test
    void getTreasurerPassword() {
        assertEquals("SkejsOp123",treasurer.getTreasurerPassword());
    }
}