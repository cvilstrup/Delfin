import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ConsoleUI {

// Gemmer svømmeklubben som ConsoleUI skal arbejde med
    private SwimmingClub swimmingClub;

// Tilføjer swimmingClub til konstruktøren
    public ConsoleUI(SwimmingClub swimmingClub) {
        this.swimmingClub = swimmingClub;
    }

// Starter ConsoleUI
    public void run() {
        boolean running = true;

    // Fortsætter med at vise menuen så længe programmet kører
        while (running) {

        // Viser hovedmenuen til brugeren
            showMenu();

        // Modtager valg fra brugeren
            int choice = readChoice("Vælg bruger: ");

        // Bestemmer hvad der skal ske ud fra brugerens valg
            switch (choice) {
                case 1 -> chairmanMenu();
                case 2 -> cashierMenu();
                case 3 -> memberMenu();
                case 0 -> running = false;
                default -> IO.println("Ugyldigt valg, prøv venligst igen");
            }
        }
    }

// Viser de forskellige valgmuligheder i hovedmenuen
    private void showMenu() {
        IO.println();
        IO.println("=== Hovedmenu ===");
        IO.println("1. Formand");
        IO.println("2. Kasserer");
        IO.println("3. Medlem");
        IO.println("0. Afslut");
        IO.println();
    }

// Viser formandens menu
    private void chairmanMenu() {
        boolean inMenu = true;

    // Fortsætter med at vise formandens menu
        while (inMenu) {

            IO.println();
            IO.println("=== Formand Menu ===");
            IO.println("1. Opret medlem");
            IO.println("2. Vis medlemmer");
            IO.println("0. Tilbage til hovedmenu");
            IO.println();

            int choice = readChoice("Vælg handling: ");

            switch (choice) {
                case 1 -> registerMember();
                case 2 -> IO.println("Vis medlemmer");
                case 0 -> inMenu = false;
                default -> IO.println("Ugyldigt valg, prøv igen");
            }
        }
    }

// User story 1:
// Lader formanden registrere et nyt medlem
    private void registerMember() {
        IO.println();
        IO.println("--- Opret medlem ---");

    // Modtager navnet fra brugeren
        String name = IO.readln("Indtast navn: ");

    // Modtager medlems ID fra brugeren
        int memberID = readChoice("Indtast medlems ID: ");

    //Modtager medlems ID fra brugeren
        LocalDate birthday = readChoiceBirthday("Indtast fødselsdag (DD-MM-ÅÅÅÅ): ");

    //Modtager medlems ID fra brugeren
        String email = IO.readln("Indtast email: ");

    //Modtager medlems ID fra brugeren
        MembershipStatus membershipStatus = readChoiceMembershipStatus("Indtast status på medlemsskab: ");
        //aktiv eller passiv

    // Opretter et nyt medlem med navn og ID
        Member member = new Member(memberID, name, birthday, email);

    // Tilføjer medlemmet til svømmeklubben
        swimmingClub.addMember(member);

        IO.println();
        IO.println("Medlemmet blev oprettet: " + member);
    }

// Viser kassererens menu
    private void cashierMenu() {
        boolean inMenu = true;

        while (inMenu) {

            IO.println();
            IO.println("=== Kasserer Menu ===");
            IO.println("1. Vis kontingenter");
            IO.println("2. Vis medlemmer i restance");
            IO.println("0. Tilbage til hovedmenu");
            IO.println();

            int choice = readChoice("Vælg handling: ");

            switch (choice) {
                case 1 -> IO.println("Vis kontingenter");
                case 2 -> IO.println("Vis medlemmer i restance");
                case 0 -> inMenu = false;
                default -> IO.println("Ugyldigt valg, prøv igen");
            }
        }
    }

// Viser medlemmets menu
    private void memberMenu() {
        boolean inMenu = true;

        while (inMenu) {

            IO.println();
            IO.println("=== Medlem Menu ===");
            IO.println("0. Tilbage til hovedmenu");
            IO.println();

            int choice = readChoice("Vælg handling: ");

            switch (choice) {
                case 0 -> inMenu = false;
                default -> IO.println("Ugyldigt valg, prøv igen");
            }
        }
    }

// Læser brugerens valg og håndterer ugyldigt input
    private int readChoice(String message) {
        try {
            return Integer.parseInt(IO.readln(message));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private LocalDate readChoiceBirthday(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            try {
                String input = IO.readln(message);
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                IO.println("Fejl: Ugyldig dato. Brug formatet DD-MM-ÅÅÅÅ (f.eks. 15-05-1995).");
            }
        }
    }

    private MembershipStatus readChoiceMembershipStatus(String message) {
        while (true) {
            try {
                String input = IO.readln(message).trim().toUpperCase();
                return MembershipStatus.valueOf(input);
            } catch (IllegalArgumentException e) {
                IO.println("Fejl: Ugyldig medlemsstatus. Skriv enten ACTIVE eller PASSIVE.");
            }
        }
    }

    public SwimmingClub getSwimmingClub() {
        return swimmingClub;
    }

    public void setSwimmingClub(SwimmingClub swimmingClub) {
        this.swimmingClub = swimmingClub;
    }
}
