import java.time.LocalDate;

public class Member {
    private int memberID;
    private String name;
    private LocalDate birthday;
    private String email;
    private MembershipStatus membershipStatus; //aktiv eller passiv

    public Member(int memberID, String name, LocalDate birthday, String email, MembershipStatus membershipStatus){
        this.memberID = memberID;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.membershipStatus = membershipStatus;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public MembershipStatus getMembershipStatus(){
        return membershipStatus;
    }
}
