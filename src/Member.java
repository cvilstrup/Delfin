import java.time.LocalDate;
import java.time.Period;

public class Member {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private String email;
    private String password;
    private MembershipStatus membershipStatus; //aktiv eller passiv
    private int membershipFee;
    private LocalDate renewalDate;
    private LocalDate memberPaymentDue;

    private static int nextMemberID = 1;
    public Member(String name, LocalDate birthday, String email, String password, LocalDate renewalDate, LocalDate memberPaymentDue) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.renewalDate = renewalDate;
        this.memberPaymentDue = memberPaymentDue;
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

//    public int getMembershipFee() {
//        return membershipFee;
//    }

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

        public String getPassword () {
            return password;
        }

        public MembershipStatus getMembershipStatus () {
            return membershipStatus;
        }

        public void setMembershipStatus(MembershipStatus membershipStatus){
            this.membershipStatus = membershipStatus;
            this.membershipFee = calculateMemberFee();
        }

        public int getMembershipFee(){
        return membershipFee;
        }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }
        public LocalDate getmemberPaymentDue(){
        return memberPaymentDue;
        }

    // Betaler kontingentet og flytter betalingsfristen et år frem
    public void payMembershipFee() {
        memberPaymentDue = memberPaymentDue.plusYears(1);
    }

    @Override
    public String toString(){
        return String.format("""
                Navn: %s
                Medlems-ID: %d
                Fødselsdag: %s
                E-mail: %s
                Kodeord: %s
                Status af medlemsskab: %s
                Betalingsfrist: %s
                Type medlem: Almindeligt medlem""",name, memberID, birthday, email, password, membershipStatus, memberPaymentDue);
        }

    }
