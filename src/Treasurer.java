public class Treasurer {
    private final String treasurerEmail;
    private final String treasurerPassword;

    public Treasurer(String treasurerEmail, String treasurerPassword) {
        this.treasurerEmail = treasurerEmail;
        this.treasurerPassword = treasurerPassword;
    }

    public String getTreasurerEmail(){
        return treasurerEmail;
    }

    public String getTreasurerPassword() {
        return treasurerPassword;
    }
}
