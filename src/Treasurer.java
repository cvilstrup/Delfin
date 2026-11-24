public class Treasurer {
    private String name = "Thorkild Hansen";
    private final String treasurerEmail;
    private final String treasurerPassword;

    public Treasurer(String treasurerEmail, String treasurerPassword) {
        this.treasurerEmail = treasurerEmail;
        this.treasurerPassword = treasurerPassword;
    }

    public String getName() {
        return name;
    }

    public String getTreasurerEmail(){
        return treasurerEmail;
    }

    public String getTreasurerPassword() {
        return treasurerPassword;
    }
}
