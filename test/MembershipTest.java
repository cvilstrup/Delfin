import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MembershipTest {
    Membership membership;

    @BeforeEach
    void setUp() {
     membership = new Membership(
                LocalDate.now().plusYears(1),
                LocalDate.now().plusMonths(1),
                LocalDate.now().minusYears(30));
    }

   @Test
   void testNewMembershipIsActive(){
    assertEquals(MembershipStatus.ACTIVE,membership.getMembershipStatus());
}
    @Test
    void testEdgeCasesForPaymentAmount(){
        membership = new Membership(
                LocalDate.now().plusYears(1),
                LocalDate.now().plusMonths(1),
                LocalDate.now().minusYears(17));
        assertEquals(1000,membership.getMembershipFee());

        membership = new Membership(
                LocalDate.now().plusYears(1),
                LocalDate.now().plusMonths(1),
                LocalDate.now().minusYears(18));
        assertEquals(1600,membership.getMembershipFee());

        membership = new Membership(
                LocalDate.now().plusYears(1),
                LocalDate.now().plusMonths(1),
                LocalDate.now().minusYears(59));
        assertEquals(1600,membership.getMembershipFee());

        membership = new Membership(
                LocalDate.now().plusYears(1),
                LocalDate.now().plusMonths(1),
                LocalDate.now().minusYears(60));
        assertEquals(1200,membership.getMembershipFee());
    }

    @Test
    void testPassiveMemberPaymentAmount(){
        membership.setMembershipStatus(MembershipStatus.PASSIVE,LocalDate.now().minusYears(20));
        assertEquals(500,membership.getMembershipFee());
    }

    @Test
    void payingMovesNextPayment1YearAhead(){
        Member member = new Member(
                "Test Testesen",
                LocalDate.now().minusYears(30),   // birthday: 30 år gammel
                "test@delfin.dk",
                "Kode123",
                LocalDate.now().plusYears(1),     // renewalDate
                LocalDate.now().plusMonths(1));   // memberPaymentDue
    }
}
