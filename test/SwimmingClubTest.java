import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class SwimmingClubTest {

    private SwimmingClub swimmingClub;

    @BeforeEach
    void setUp() {
        swimmingClub = new SwimmingClub();
    }

    @Test
    void addMemberAddsMemberToList() {
        // Arrange
        Member member = new Member(
                "Peter Hansen",
                LocalDate.of(2005, 5, 15),
                "peter@email.dk",
                "1234",
                LocalDate.now(),
                LocalDate.now()
        );

        // Act
        swimmingClub.addMember(member);

        // Assert
        assertEquals(1, swimmingClub.getMembers().size());
    }

    @Test
    void getMemberReturnsCorrectMember() {
        // Arrange
        Member member1 = new Member(
                "Peter Hansen",
                LocalDate.of(2005, 5, 15),
                "peter@email.dk",
                "1234",
                LocalDate.now(),
                LocalDate.now()
        );

        Member member2 = new Member(
                "Anna Jensen",
                LocalDate.of(2000, 8, 20),
                "anna@email.dk",
                "5678",
                LocalDate.now(),
                LocalDate.now()
        );

        swimmingClub.addMember(member1);
        swimmingClub.addMember(member2);

        // Act
        Member result = swimmingClub.getMember(member2.getMemberID());

        // Assert
        assertEquals(member2, result);
    }


    @Test
    void getMemberReturnsNullWhenMemberDoesNotExist() {
        // Arrange
        Member member = new Member(
                "Peter Hansen",
                LocalDate.of(2005, 5, 15),
                "peter@email.dk",
                "1234",
                LocalDate.now(),
                LocalDate.now()
        );

        swimmingClub.addMember(member);

        // Act
        Member result = swimmingClub.getMember(999);

        // Assert
        assertNull(result);
    }


    @Test
    void calculateRevenueReturnsZeroWhenNoMembers() {
        // Act
        int result = swimmingClub.calculateRevenue();

        // Assert
        assertEquals(0, result);
    }


    @Test
    void calculateRevenueAddsFeesForDifferentAgeGroups() {
        // Arrange
        Member junior = new Member(
                "Peter Hansen",
                LocalDate.now().minusYears(15),
                "peter@email.dk",
                "1234",
                LocalDate.now(),
                LocalDate.now()
        );

        Member senior = new Member(
                "Anna Jensen",
                LocalDate.now().minusYears(25),
                "anna@email.dk",
                "5678",
                LocalDate.now(),
                LocalDate.now()
        );

        Member seniorOver60 = new Member(
                "Lars Nielsen",
                LocalDate.now().minusYears(65),
                "lars@email.dk",
                "9012",
                LocalDate.now(),
                LocalDate.now()
        );

        swimmingClub.addMember(junior);
        swimmingClub.addMember(senior);
        swimmingClub.addMember(seniorOver60);

        // Act
        int result = swimmingClub.calculateRevenue();

        // Assert
        assertEquals(3800, result);
    }


    @Test
    void calculateRevenueCountsPassiveMembersAs500() {
        // Arrange
        Member activeMember = new Member(
                "Peter Hansen",
                LocalDate.now().minusYears(25),
                "peter@email.dk",
                "1234",
                LocalDate.now(),
                LocalDate.now()
        );

        Member passiveMember = new Member(
                "Anna Jensen",
                LocalDate.now().minusYears(25),
                "anna@email.dk",
                "5678",
                LocalDate.now(),
                LocalDate.now()
        );

        passiveMember.getMembership().setMembershipStatus(
                MembershipStatus.PASSIVE,
                passiveMember.getBirthday()
        );

        swimmingClub.addMember(activeMember);
        swimmingClub.addMember(passiveMember);

        // Act
        int result = swimmingClub.calculateRevenue();

        // Assert
        assertEquals(2100, result);
    }

}
