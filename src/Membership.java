import java.time.LocalDate;
import java.time.Period;

public class Membership {

    private MembershipStatus membershipStatus;
    private int membershipFee;
    private LocalDate renewalDate;
    private LocalDate memberPaymentDue;

    public Membership(LocalDate renewalDate, LocalDate memberPaymentDue, LocalDate birthday) {
        this.renewalDate = renewalDate;
        this.memberPaymentDue = memberPaymentDue;
        this.membershipStatus = MembershipStatus.ACTIVE;
        this.membershipFee = calculateMemberFee(birthday);
    }

    public int calculateMemberFee(LocalDate birthday) {
        int passiveMembershipFee = 500;
        int over18MembershipFee = 1600;
        int under18MembershipFee = 1000;
        int seniorMembershipFee = 1200;

        int age = Period.between(birthday, LocalDate.now()).getYears();

        if (membershipStatus == MembershipStatus.PASSIVE) {
            return passiveMembershipFee;
        }

        if (age < 18) {
            return under18MembershipFee;
        }

        if (age >= 60) {
            return seniorMembershipFee;
        }

        return over18MembershipFee;
    }

    public int getMembershipFee() {
        return membershipFee;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus, LocalDate birthday) {
        this.membershipStatus = membershipStatus;
        this.membershipFee = calculateMemberFee(birthday);
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }

    public LocalDate getMemberPaymentDue() {
        return memberPaymentDue;
    }

    // Betaler kontingentet og flytter betalingsfristen et år frem
    public void payMembershipFee() {
        memberPaymentDue = memberPaymentDue.plusYears(1);
    }
}