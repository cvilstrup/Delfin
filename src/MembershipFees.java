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

    public int checkMemberStatus() {
        int age = calculateAge();
        if (member.getMembershipStatus().equals(MembershipStatus.PASSIVE)) {
            return passiveMembershipFee;
        }
        if (member.getMembershipStatus().equals(MembershipStatus.ACTIVE) && age <= 18) {
            return under18MembershipFee;
        }
        if (member.getMembershipStatus().equals(MembershipStatus.ACTIVE) && age >= 18) {
            return over18MembershipFee;
        }
        if (member.getMembershipStatus().equals(MembershipStatus.ACTIVE) && age >= 60) {
            return seniorMembershipFee;
        }
    }
}
