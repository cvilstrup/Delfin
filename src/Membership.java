import java.time.LocalDate;
import java.time.Period;

public class Membership {
    private LocalDate birthday;
    private MembershipStatus membershipStatus;
    private LocalDate renewalDate;
    private LocalDate memberPaymentDue;

    public Membership(LocalDate renewalDate, LocalDate memberPaymentDue, LocalDate birthday) {
        this.renewalDate = renewalDate;
        this.memberPaymentDue = memberPaymentDue;
        this.membershipStatus = MembershipStatus.ACTIVE;
        this.birthday=birthday;
    }

    public int calculateMemberFee() {
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
        return calculateMemberFee();
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
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