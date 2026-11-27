import java.time.LocalDate;
import java.time.Period;

public class Member {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private String email;
    private String password;
    private Membership membership;

    private static int nextMemberID = 1;
    public Member(String name, LocalDate birthday, String email, String password, LocalDate renewalDate, LocalDate memberPaymentDue) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.membership = new Membership(renewalDate, memberPaymentDue, birthday);
        memberID = nextMemberID++;
    }

    public int getAge(){
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    public int getMemberID () {
            return memberID;
        }

    // Bruges når medlemmer indlæses fra fil, så de beholder deres ID og nye medlemmer ikke får et ID der er taget
    public void setMemberID(int memberID) {
        this.memberID = memberID;
        if (memberID >= nextMemberID) {
            nextMemberID = memberID + 1;
        }
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

    public Membership getMembership() {
        return membership;
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
                Type medlem: Almindeligt medlem""",name, memberID, birthday, email, password, membership.getMembershipStatus(), membership.getMemberPaymentDue());
        }

    }
