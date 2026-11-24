public class Chairman {
    private String name = "Big Bossman";
    private final String chairmanEmail;
    private final String chairmanPassword;

    public Chairman(String chairmanEmail, String chairmanPassword) {
        this.chairmanEmail = chairmanEmail;
        this.chairmanPassword = chairmanPassword;
    }

    public String getName() {
        return name;
    }

    public String getChairmanEmail(){
        return chairmanEmail;
    }

    public String getChairmanPassword() {
        return chairmanPassword;
    }
}
