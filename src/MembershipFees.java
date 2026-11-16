import java.time.LocalDate;
import java.time.Period;

public class MembershipFees {

    Member member;


    int passiveMembershipFee = 500;
    int over18MembershipFee = 1600;
    int under18MembershipFee = 1000;
    int seniorMembershipFee = 1200;

    public int calculateAge() {
        return Period.between(member.getBirthday(), LocalDate.now()).getYears();
    }

    public int checkmemberstatus() {
        int age = calculateAge();
        if (membershipType.equals(MembershipStatus.PASSIVE)) {
            return passiveMembershipFee;
        }
        if (membershipType.equals(MembershipStatus.ACTIVE) && age <= 18) {
            return under18MembershipFee;
        }
        if (membershipType.equals(MembershipStatus.ACTIVE) && age >= 18) {
            return over18MembershipFee;
        }
        if (membershipType.equals(MembershipStatus.ACTIVE) && age >= 60) {
            return seniorMembershipFee;
        }
    }
}
