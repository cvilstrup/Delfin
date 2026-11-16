import java.util.ArrayList;

public class ConsoleUI {
// Gemmer svømmeklubben som ConsoleUI skal arbejde med
    private SwimmingClub swimmingClub;

// Tilføjer library til konstruktøren
    public ConsoleUI(SwimmingClub swimmingClub) {
        this.swimmingClub = swimmingClub;
    }

// Starter ConsoleUI
    public void run() {
        boolean running = true;

    // Forsætter med at vise menuen så længe programmet kører
        while (running) {

        // Viser menuen til brugeren
            showMenu();

        // Modtager valg fra brugeren
            int choice = Integer.parseInt(IO.readln("Choose an option: "));

        // Bestemmer hvad der skal ske ud fra brugerens valg
            switch (choice) {
                case 1 -> ();
                case 2 -> ();
                case 3 -> ();
                case 4 -> ();
                case 0 -> running = false;
                default -> IO.println("Invalid choice. Please try again.");
            }
        }
    }

    // Viser de forskellige valgmuligheder i Menu
    private void showMenu() {
        IO.println();
        IO.println("1. ");
        IO.println("2. ");
        IO.println("3. ");
        IO.println("4. ");
        IO.println("0. Afslut");
        IO.println();
    }

//User story 1:
//Lader "admin" registrere et nyt medlem:
    private void registerMember() {
        IO.println();
        IO.println("--- Register a New member ---");

    //Modtager navnet fra brugeren:
        String name = IO.readln("Enter name: ");

    //Modtager medlems ID fra brugeren:
        int memberId = Integer.parseInt(IO.readln("Enter member ID: "));

    //Opretter et nyt medlem med navn og ID:
        Member member = new Member(name, memberId);

    //Tilføjer medlemmet til svømmeklubben:
        SwimmingClub.addMember(member);

        IO.println();
        IO.println("Member successfully registered: " + member);

    }
}