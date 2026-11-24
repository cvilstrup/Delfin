public class Chairman {
    private final String chairmanEmail;
    private final String chairmanPassword;

    public Chairman(String chairmanEmail, String chairmanPassword) {
        this.chairmanEmail = chairmanEmail;
        this.chairmanPassword = chairmanPassword;
    }

    public String getChairmanEmail(){
        return chairmanEmail;
    }

    public String getChairmanPassword() {
        return chairmanPassword;
    }
}
