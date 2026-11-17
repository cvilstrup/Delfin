import java.time.LocalDate;
import java.time.Period;

public class Member {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private String email;
    private MembershipStatus membershipStatus; //aktiv eller passiv
    private int membershipFee;

    private static int nextMemberID = 1;
    public Member(String name, LocalDate birthday, String email) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        membershipStatus = MembershipStatus.ACTIVE;
        membershipFee = calculateMemberFee();
        memberID = nextMemberID++;
    }

    public int calculateMemberFee() {
        int passiveMembershipFee = 500;
        int over18MembershipFee = 1600;
        int under18MembershipFee = 1000;
        int seniorMembershipFee = 1200;

        int age = Period.between(getBirthday(), LocalDate.now()).getYears();

        if (getMembershipStatus().equals(MembershipStatus.PASSIVE)) {
            return passiveMembershipFee;
        }

        if (getMembershipStatus().equals(MembershipStatus.ACTIVE) && age < 18) {
            return under18MembershipFee;
        }

        if (getMembershipStatus().equals(MembershipStatus.ACTIVE) && age >= 60) {
            return seniorMembershipFee;
        }

        if (getMembershipStatus().equals(MembershipStatus.ACTIVE) && age >= 18) {
            return over18MembershipFee;
        }

        throw new IllegalStateException("Unknown membership status");
    }

    public int getMembershipFee() {
        return membershipFee;
    }

    public int getMemberID () {
            return memberID;
        }

        public String getName () {
            return name;
        }

        public LocalDate getBirthday () {
            return birthday;
        }

        public String getEmail () {
            return email;
        }

        public MembershipStatus getMembershipStatus () {
            return membershipStatus;
        }
    }
