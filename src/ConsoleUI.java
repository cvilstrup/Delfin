import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class ConsoleUI {

    // Gemmer svømmeklubben som ConsoleUI skal arbejde med
    private SwimmingClub swimmingClub;
    private Chairman chairman;

    // Tilføjer swimmingClub til konstruktøren
    public ConsoleUI(SwimmingClub swimmingClub, Chairman chairman) {
        this.swimmingClub = swimmingClub;
        this.chairman = chairman;
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
        IO.println();
        IO.println("1. Formand");
        IO.println("2. Kasserer");
        IO.println("3. Medlem");
        IO.println("0. Afslut");
        IO.println();
    }

    // Viser formandens menu
    private void chairmanMenu() {

        String typedEmail = IO.readln("Indtast din e-mail: ");
        String typedPassword = IO.readln("Indtast din adgangskode: ");

        if (typedEmail.equalsIgnoreCase(chairman.getChairmanEmail()) && typedPassword.equals(chairman.getChairmanPassword())) {

            IO.println();
            IO.println("Velkommen, " + chairman.getName() + "😁");

            boolean inMenu = true;

            // Fortsætter med at vise formandens menu
            while (inMenu) {

                IO.println();
                IO.println("=== Formand Menu ===");
                IO.println();
                IO.println("1. Opret medlem");
                IO.println("2. Vis medlemmer");
                IO.println("3. Juster medlemskabstype for et medlem");
                IO.println("4. Juster konkurrence rolle");
                IO.println("0. Tilbage til hovedmenu");
                IO.println();

                int choice = readChoice("Vælg handling: ");

                switch (choice) {
                    case 1 -> registerMember();
                    case 2 -> showMembers();
                    case 3 -> editMembershipStatus();
                    case 4 -> editCompetitionRole();
                    case 0 -> inMenu = false;
                    default -> IO.println("Ugyldigt valg, prøv igen");
                }
            }

        } else {
            IO.println();
            IO.println("Forkert e-mail eller adgangskode. Prøv igen.");
            IO.println();
        }
    }

    private void showMembers() {
        boolean inList = true;

        while (inList) {
            IO.println("=== Medlemmer ===");
            IO.println();
            swimmingClub.printMembers();
            IO.println();

            int choice = readChoice("Tast 0 for at gå tilbage: ");

            switch (choice) {
                case 0 -> inList = false;
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

        //Modtager fødselsdag fra brugeren
        LocalDate birthday = readChoiceBirthday("Indtast fødselsdag (DD-MM-ÅÅÅÅ): ");

        //Modtager email fra brugeren
        String email = IO.readln("Indtast email: ");

        //Modtager et kodeord fra brugeren
        String password = IO.readln("Opret og indtast adgangskode: ");

        String isCompetitiveSwimmer = readCompetitiveChoice();
        LocalDate renewalDate = LocalDate.now();

        Member member;

        if(isCompetitiveSwimmer.equalsIgnoreCase("JA")){

            JuniorTeam juniorTeam = swimmingClub.getJuniorTeam();
            SeniorTeam seniorTeam = swimmingClub.getSeniorTeam();

            member = new CompetitionSwimmer(name, birthday, email, password, renewalDate, juniorTeam, seniorTeam);

        }else{
            member = new Member(name, birthday, email, password, renewalDate);
        }

        // Tilføjer medlemmet til svømmeklubben
        swimmingClub.addMember(member);

        IO.println();
        IO.println("Medlemmet blev oprettet: \n" + member);
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
            IO.println();
            IO.println("1. Se info om mit medlemskab");
            IO.println("0. Tilbage til hovedmenu");
            IO.println();

            int choice = readChoice("Vælg handling: ");

            switch (choice) {
                case 1 -> showIndividualMemberInfo();
                case 0 -> inMenu = false;
                default -> IO.println("Ugyldigt valg, prøv igen");
            }
        }
    }

    private void showIndividualMemberInfo() {
        boolean inMenu = true;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.forLanguageTag("da-DK"));

        while (inMenu) {
            String typedEmail = IO.readln("Indtast din e-mail: ");
            String typedPassword = IO.readln("Indtast din adgangskode: ");

            Member foundMember = null;

            for (Member member : swimmingClub.members) {
                if (typedEmail.equalsIgnoreCase(member.getEmail()) && typedPassword.equals(member.getPassword())) {
                    foundMember = member;
                    break;
                }
            }

            if (foundMember != null) {
                String nextRenewal = foundMember.getRenewalDate().plusYears(1).format(formatter);

                IO.println("Velkommen, " + foundMember.getName() + "😁");
                IO.println();
                IO.println("Medlems-ID: " + foundMember.getMemberID());
                IO.println("Fødselsdag: " + foundMember.getBirthday().format(formatter));
                IO.println("Medlemsstatus: " + foundMember.getMembershipStatus());
                IO.println();
                IO.println("Du betaler " + foundMember.getMembershipFee() +" kr. i kontingent. Dit abonnementet fornyes d. " + nextRenewal);

                inMenu = false;
            } else if (swimmingClub.members.isEmpty()) {
                IO.println();
                IO.println("Der eksisterer ingen medlemmer i svømmeklubben (endnu)");
                IO.println();
            } else {
                IO.println("Forkert e-mail eller adgangskode. Prøv igen.");
            }
        }
    }

    private void editMembershipStatus(){
        boolean inMenu = true;

        while (inMenu) {
            String typedEmail = IO.readln("Indtast medlemmets e-mail: ");

            Member foundMember = null;

            for (Member member : swimmingClub.members) {
                if (typedEmail.equalsIgnoreCase(member.getEmail())) {
                    foundMember = member;
                    break;
                }
            }

            if (foundMember != null) {
                IO.println();
                IO.println("Du er nu logget ind som: " + foundMember.getName());
                IO.println(foundMember.getName() + " har et " + foundMember.getMembershipStatus() + " medlemskab");
                MembershipStatus newMembershipStatus = readNewMembershipStatusChoice();

                if(newMembershipStatus == null) {
                    IO.println("Fejl: Det var ikke muligt at ændre medlemsskabet");

                }else{
                    foundMember.setMembershipStatus(newMembershipStatus);
                    IO.println();
                    IO.println( foundMember.getName() +"'s medlemsstatus er nu ændret til " + newMembershipStatus);
                }
                inMenu = false;
            } else if (swimmingClub.members.isEmpty()) {
                IO.println();
                IO.println("Der eksisterer ingen medlemmer i svømmeklubben (endnu)");
                IO.println();
            } else {
                IO.println("Forkert e-mail eller adgangskode. Prøv igen.");
            }
            }
        }

        private void editCompetitionRole() {
            boolean inMenu = true;

            while (inMenu) {
                String typedEmail = IO.readln("Indtast konkurrence-svømmerens / medlemmets e-mail: ");
                Member foundMember = null;

                for (Member member : swimmingClub.members) {
                    if (typedEmail.equalsIgnoreCase(member.getEmail())) {
                        foundMember = member;
                        break;
                    }
                }

                if (foundMember != null) {
                    IO.println();
                    IO.println("Medlem fundet: " + foundMember.getName());

                    boolean isCurrentlyCompetitive = foundMember instanceof CompetitionSwimmer;

                    if (isCurrentlyCompetitive) {
                        IO.println(foundMember.getName() + " er i øjeblikket konkurrencesvømmer.");
                        String answer = IO.readln("Vil du ændre medlemmet til almindeligt medlem? (JA/NEJ): ");

                        if (answer.equalsIgnoreCase("JA")) {

                            CompetitionSwimmer oldSwimmer = (CompetitionSwimmer) foundMember;

                            if (oldSwimmer.getTeam() != null) {
                                oldSwimmer.getTeam().removeMember(oldSwimmer);
                            }

                            Member regularMember = new Member(
                                    foundMember.getName(),
                                    foundMember.getBirthday(),
                                    foundMember.getEmail(),
                                    foundMember.getPassword(),
                                    foundMember.getRenewalDate()
                            );

                            int index = swimmingClub.members.indexOf(foundMember);
                            swimmingClub.members.set(index, regularMember);

                            IO.println();
                            IO.println(foundMember.getName() + " er nu ændret til almindeligt medlem.");
                        }else if (answer.equalsIgnoreCase("NEJ")) {
                            IO.println();
                            IO.println("Der blev ikke foretaget nogle ændringer");
                        }
                    } else {
                        IO.println(foundMember.getName() + " er i øjeblikket et almindeligt medlem.");
                        String answer = IO.readln("Vil du ændre medlemmet til konkurrencesvømmer? (JA/NEJ): ");

                        if (answer.equalsIgnoreCase("JA")) {

                            CompetitionSwimmer newSwimmer = new CompetitionSwimmer(
                                    foundMember.getName(),
                                    foundMember.getBirthday(),
                                    foundMember.getEmail(),
                                    foundMember.getPassword(),
                                    foundMember.getRenewalDate(),
                                    swimmingClub.getJuniorTeam(),
                                    swimmingClub.getSeniorTeam()
                            );

                            int index = swimmingClub.members.indexOf(foundMember);
                            swimmingClub.members.set(index, newSwimmer);

                            IO.println(foundMember.getName() + " er nu ændret til konkurrencesvømmer.");
                        } else if (answer.equalsIgnoreCase("NEJ")) {
                            IO.println();
                            IO.println("Der blev ikke foretaget nogle ændringer");
                        }
                    }
                    inMenu = false;
                } else {
                    IO.println("Ingen medlemmer fundet med den e-mail. Prøv igen.");
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

    private MembershipStatus readNewMembershipStatusChoice () {
        try {
            IO.println();
            String message = IO.readln("Hvad skal medlemstypen ændres til? (ACTIVE eller PASSIVE): ");
            String messageUpper = message.toUpperCase();
            return MembershipStatus.valueOf(messageUpper);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private String readCompetitiveChoice(){
        try {
            IO.println();
            String message = IO.readln("Er medlemmet en konkurrencesvømmer? (JA eller NEJ): ");
            String messageUpper = message.toUpperCase();
            return String.valueOf(messageUpper);
        } catch (NumberFormatException e){
            return "Fejl.";
        }
    }

    public SwimmingClub getSwimmingClub() {
        return swimmingClub;
    }

    public void setSwimmingClub(SwimmingClub swimmingClub) {
        this.swimmingClub = swimmingClub;
    }
}