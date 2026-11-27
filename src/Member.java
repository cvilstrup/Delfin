import java.text.Format;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Member {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private String email;
    private String password;
    private Membership membership;

    private ArrayList<SwimResult> swimResults = new ArrayList<>();

    private static int nextMemberID = 1;

    public Member(String name, LocalDate birthday, String email, String password, LocalDate renewalDate, LocalDate memberPaymentDue) {
        this.memberID = nextMemberID++;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.membership = new Membership(renewalDate, memberPaymentDue, birthday);
    }

    public Member(int existingMemberID, String name, LocalDate birthday, String email, String password, LocalDate renewalDate, LocalDate memberPaymentDue, MembershipStatus membershipStatus, ArrayList<SwimResult> swimResults) {
        this.memberID = existingMemberID;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.membership = new Membership(renewalDate, memberPaymentDue, birthday);
        this.membership.setMembershipStatus(membershipStatus);
        this.swimResults = (swimResults != null) ? swimResults : new ArrayList<>();
    }

    public ArrayList<SwimResult> getSwimResults() {
        return swimResults;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.forLanguageTag("da-DK"));
        return String.format("""
                Navn: %s
                Medlems-ID: %d
                Fødselsdag: %s
                E-mail: %s
                Kodeord: %s
                Status af medlemsskab: %s
                Betalingsfrist: %s
                Type medlem: Almindeligt medlem""",name, memberID, birthday.format(formatter), email, password, membership.getMembershipStatus(), membership.getMemberPaymentDue().format(formatter));
        }

    }
