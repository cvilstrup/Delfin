public class MembershipFees {

    String memberName;
    String membershipType;
    String MemberCount;
    int duration;


    int passiveMembershipFee = 500;
    int over18MembershipFee = 1600;
    int under18MembershipFee = 1000;
    int seniorMembershipFee = 1200;

    int CalculateAge (){

    }

    public int checkmemberstatus() {
        if (membershipType.equals(MembershipStatus.PASSIVE)) {
            return passiveMembershipFee;
        }
        if (membershipType.equals(MembershipStatus.ACTIVE)&& LocalDate)

    }
}
