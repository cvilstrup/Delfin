public class Trainer {
    private String name = "Hr. Trænermand";
    private String trainerEmail;
    private String trainerPassword;

    Trainer(String trainerEmail, String trainerPassword){
        this.trainerEmail=trainerEmail;
        this.trainerPassword=trainerPassword;
        this.name = name;
    }

    Trainer(){
      this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public String getTrainerPassword() {
        return trainerPassword;
    }
}
