import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Map;

public class ConsoleUI {

    // Gemmer svømmeklubben som ConsoleUI skal arbejde med
    private SwimmingClub swimmingClub;
    private Chairman chairman;
    private Treasurer treasurer;
    private Trainer trainer;
    Member member;

    // Tilføjer swimmingClub til konstruktøren
    public ConsoleUI(SwimmingClub swimmingClub, Chairman chairman, Treasurer treasurer, Trainer trainer) {
        this.swimmingClub = swimmingClub;
        this.chairman = chairman;
        this.treasurer = treasurer;
        this.trainer = trainer;
    }

    // Starter ConsoleUI
    public void run() {
        boolean running = true;

        // Fortsætter med at vise menuen så længe programmet kører
        while (running) {

            // Viser hovedmenuen til brugeren
            showMenu();

            //random comment

            // Modtager valg fra brugeren
            int choice = readChoice("Vælg bruger: ");

            // Bestemmer hvad der skal ske ud fra brugerens valg
            switch (choice) {
                case 1 -> chairmanMenu();
                case 2 -> trainerMenu();
                case 3 -> treasurerMenu();
                case 4 -> memberMenu();
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
        IO.println("2. Træner");
        IO.println("3. Kasserer");
        IO.println("4. Medlem");
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

// Lader formanden registrere et nyt medlem
    private void registerMember() {

        boolean doubleMemberEmail = false;

        IO.println();
        IO.println("--- Opret medlem ---");

        // Modtager navnet fra brugeren
        String name = IO.readln("Indtast navn: ");

        //Modtager fødselsdag fra brugeren
        LocalDate birthday = readChoiceBirthday("Indtast fødselsdag (DD-MM-ÅÅÅÅ): ");

        String email = "";
        String password = "";

        while(!doubleMemberEmail) {
            //Modtager email fra brugeren
            email = IO.readln("Indtast email: ");
            //Modtager et kodeord fra brugeren
            password = IO.readln("Opret og indtast adgangskode: ");

            if (swimmingClub.isEmailTaken(email)) {
                IO.println();
                IO.println("Denne email er allerede i brug. Brug venligst en anden");
            } else {
                doubleMemberEmail = true;
            }
        }

        String isCompetitiveSwimmer = readCompetitiveChoice();
        LocalDate renewalDate = LocalDate.now();
        LocalDate memberPaymentDue = LocalDate.now().plusDays(14);

        if(isCompetitiveSwimmer.equalsIgnoreCase("JA")){

            JuniorTeam juniorTeam = swimmingClub.getJuniorTeam();
            SeniorTeam seniorTeam = swimmingClub.getSeniorTeam();

            CompetitionSwimmer swimmer = new CompetitionSwimmer(name, birthday, email, password, renewalDate, memberPaymentDue, juniorTeam, seniorTeam);
            chooseDisciplines(swimmer);
            member = swimmer;

        }else{
            member = new Member(name, birthday, email, password, renewalDate,memberPaymentDue);
        }


        // Tilføjer medlemmet til svømmeklubben
        swimmingClub.addMember(member);

        IO.println();
        IO.println("Medlemmet blev oprettet: \n" + member);
    }

    private void trainerMenu(){

            String typedEmail = IO.readln("Indtast din e-mail: ");
            String typedPassword = IO.readln("Indtast din adgangskode: ");

        if (typedEmail.equalsIgnoreCase(trainer.getTrainerEmail()) && typedPassword.equals(trainer.getTrainerPassword())){

            IO.println();
            IO.println("Velkommen, " + trainer.getName() + "😁");

            boolean inMenu = true;

            while (inMenu){
                IO.println();
                IO.println("=== Træner Menu ===");
                IO.println();
                IO.println("1. Register svømmetid");
                IO.println("2. Vis træningsresultater");
                IO.println("3. Vis stævne resultater");
                IO.println("4. Vis top 5 resultater");
                IO.println("0. Tilbage til hovedmenu");
                IO.println();

                int choice = readChoice("Vælg handling: ");

                switch (choice) {
                    case 1 -> registerSwimtime();
                    case 2 -> showTrainingResults();
                    case 3 -> showCompetitionResults();
                    case 4 -> showTop5Results();
                    case 0 -> inMenu = false;
                    default -> IO.println("Ugyldigt valg, prøv igen");
                }
            }
        }
    }

    // Viser kassererens menu
    private void treasurerMenu() {

        String typedEmail = IO.readln("Indtast din e-mail: ");
        String typedPassword = IO.readln("Indtast din adgangskode: ");

        if (typedEmail.equalsIgnoreCase(treasurer.getTreasurerEmail()) && typedPassword.equals(treasurer.getTreasurerPassword())) {

            IO.println();
            IO.println("Velkommen, " + treasurer.getName() + "😁");

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
                    case 1 -> showExpectedRevenue();
                    case 2 -> IO.println("Vis medlemmer i restance");
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

    // Viser kassereren det samlede forventede kontingent for alle medlemmer
    private void showExpectedRevenue() {
        IO.println();
        IO.println("--- Forventet kontingent ---");
        IO.println("Antal medlemmer: " + swimmingClub.getMembers().size());
        IO.println("Samlet forventet kontingent: " + swimmingClub.calculateRevenue() + " kr.");
    }

    // Viser medlemmets menu
    private void memberMenu() {

        if (swimmingClub.getMembers().isEmpty()) {
            IO.println();
            IO.println("Der eksisterer ingen medlemmer i systemet endnu.");
            return;
        }

        Member foundMember = null;

        while (foundMember == null) {
            String typedEmail = IO.readln("Indtast din e-mail (eller 0 for at annullere): ");
            if (typedEmail.equals("0")) {
                return;
            }
            String typedPassword = IO.readln("Indtast din adgangskode: ");

            for (Member member : swimmingClub.getMembers()) {
                if (typedEmail.equalsIgnoreCase(member.getEmail()) && typedPassword.equals(member.getPassword())) {
                    foundMember = member;
                    break;
                }
            }

            if (foundMember == null) {
                IO.println("Forkert e-mail eller adgangskode. Prøv igen.\n");
            }
        }
        IO.println();
        IO.println("Velkommen, " + foundMember.getName() + "😁");

        boolean inMenu = true;
        while (inMenu) {
            IO.println();
            IO.println("=== Medlem Menu ===");
            IO.println();
            IO.println("1. Se info om mit medlemskab");
            IO.println("2. Betal for dit medlemskab");
            IO.println("3. Se mine træningstider");
            IO.println("0. Tilbage til hovedmenu");
            IO.println();

            int choice = readChoice("Vælg handling: ");

            switch (choice) {
                case 1 -> showIndividualMemberInfo(foundMember);
                case 2 -> payMembership(foundMember);
                case 3 -> showIndividualMemberTrainingResults(foundMember);
                case 0 -> inMenu = false;
                default -> IO.println("Ugyldigt valg, prøv igen");
            }
        }
    }

    private void showIndividualMemberInfo(Member foundMember) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.forLanguageTag("da-DK"));
        String nextRenewal = foundMember.getMembership().getRenewalDate().plusYears(1).format(formatter);

        IO.println();
        IO.println("Medlems-ID: " + foundMember.getMemberID());
        IO.println("Fødselsdag: " + foundMember.getBirthday().format(formatter));
        IO.println("Medlemsstatus: " + foundMember.getMembership().getMembershipStatus());
        IO.println();
        IO.println("Du betaler " + foundMember.getMembership().getMembershipFee() + " kr. i kontingent. Dit abonnement fornyes d. " + nextRenewal);
        IO.println("Betalingsfrist: " + foundMember.getMembership().getMemberPaymentDue().format(formatter));

        if (foundMember.getMembership().getMemberPaymentDue().isBefore(LocalDate.now())) {
            IO.println("Din betalingsfrist er overskredet. Betal venligst hurtigst muligt.");
        }
    }

    private void registerSwimtime() {
        boolean inMenu = true;

        while (inMenu) {
            String typedEmail = IO.readln("Indtast konkurrence-svømmerens e-mail (tryk '0' for at gå tilbage): ");

            if (typedEmail.equals("0")) {
                inMenu = false;
                continue;
            }

            Member foundMember = null;

            for (Member member : swimmingClub.getMembers()) {
                if (typedEmail.equalsIgnoreCase(member.getEmail())) {
                    foundMember = member;
                    break;
                }
            }

            if (foundMember == null) {
                IO.println("Intet medlem fundet med e-mailen: " + typedEmail + ". Prøv igen.\n");
                continue;
            }

            if (foundMember instanceof CompetitionSwimmer swimmer) {
                ArrayList<SwimmingDiscipline> disciplines = swimmer.getDisciplines();

                if (disciplines.isEmpty()) {
                    IO.println(swimmer.getName() + " er ikke tilmeldt nogen svømmediscipliner endnu.");
                } else {
                    SwimmingDiscipline chosenDiscipline;

                    if (disciplines.size() == 1) {
                        chosenDiscipline = disciplines.get(0);
                        IO.println(swimmer.getName() + " specialiserer sig i: " + chosenDiscipline);
                    } else {
                        IO.println(swimmer.getName() + " specialiserer sig i:");
                        for (int i = 0; i < disciplines.size(); i++) {
                            IO.println((i + 1) + ". " + disciplines.get(i));
                        }
                        int choice = Integer.parseInt(IO.readln("Vælg disciplin (nummer): "));
                        chosenDiscipline = disciplines.get(choice - 1);
                    }

                    IO.println();
                    double time = Double.parseDouble(IO.readln("Indtast tid i sekunder for " + chosenDiscipline + ": "));
                    SwimResult result = new SwimResult(chosenDiscipline, time, LocalDate.now());
                    swimmer.addSwimResult(result);

                    IO.println();
                    IO.println("Tid registreret: " + result);
                }

                inMenu = false;

            } else {
                IO.println(foundMember.getName() + " er et almindeligt medlem og kan ikke få registreret konkurrencetider.");
                inMenu = false;
            }
        }
    }

    private void showTrainingResults(){

        IO.println("--- Registrerede svømmetider ---");
        boolean anyResultsFound = false;

        for (Member member : swimmingClub.getMembers()) {

            if (member instanceof CompetitionSwimmer swimmer) {
                ArrayList<SwimResult> results = swimmer.getSwimResults();

                if (!results.isEmpty()) {
                    anyResultsFound = true;
                    IO.println("\nSvømmer: " + swimmer.getName() + " (ID: " + swimmer.getMemberID() + ")");

                    for (SwimResult result : results) {
                        IO.println("  - " + result);
                    }
                }
            }
        }

        if (!anyResultsFound) {
            IO.println("Der er endnu ikke registreret nogen svømmetider.");
        }

        IO.println();
    }

    private void showCompetitionResults(){

    }

    private void showTop5Results(){
        IO.println("--- Delfinens Top 5 resultater ---");

        IO.println("\n--- Delfinens Juniorhold ---");
        printTop5ForTeam(true);

        IO.println("\n--- Delfinens Seniorhold ---");
        printTop5ForTeam(false);

        IO.println();
    }

    private void printTop5ForTeam(boolean isJunior) {
        boolean anyResultsFound = false;

        for (SwimmingDiscipline discipline : SwimmingDiscipline.values()) {
            ArrayList<Map.Entry<CompetitionSwimmer, SwimResult>> bestInDiscipline = new ArrayList<>();

            for (Member member : swimmingClub.getMembers()) {
                if (member instanceof CompetitionSwimmer swimmer) {
                    boolean swimmerIsJunior = swimmer.getTeam() instanceof JuniorTeam;
                    if (swimmerIsJunior != isJunior) {
                        continue;
                    }

                    SwimResult bestResult = null;

                    for (SwimResult result : swimmer.getSwimResults()) {
                        if (result.getDiscipline() == discipline) {
                            if (bestResult == null || result.getTimeInSeconds() < bestResult.getTimeInSeconds()) {
                                bestResult = result;
                            }
                        }
                    }

                    if (bestResult != null) {
                        bestInDiscipline.add(Map.entry(swimmer, bestResult));
                    }
                }
            }

            if (!bestInDiscipline.isEmpty()) {
                anyResultsFound = true;
                IO.println("\nDisciplin: " + discipline);

                bestInDiscipline.sort(Comparator.comparingDouble(entry -> entry.getValue().getTimeInSeconds()));

                int limit = Math.min(5, bestInDiscipline.size());
                for (int i = 0; i < limit; i++) {
                    CompetitionSwimmer swimmer = bestInDiscipline.get(i).getKey();
                    SwimResult result = bestInDiscipline.get(i).getValue();

                    IO.println((i + 1) + ". " + swimmer.getName() + " - " + result.getTimeInSeconds() + " sek. (" + result.getDate() + ")");
                }
            }
        }

        if (!anyResultsFound) {
            IO.println("Der er endnu ikke registreret nogen svømmetider for dette hold (endnu).");
        }
    }

    private void payMembership(Member foundMember) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.forLanguageTag("da-DK"));

        IO.println();
        IO.println("Du skal betale " + foundMember.getMembership().getMembershipFee() + " kr. senest d. "
                + foundMember.getMembership().getMemberPaymentDue().format(formatter));
        String answer = IO.readln("Vil du betale nu? (JA/NEJ): ");

        if (answer.equalsIgnoreCase("JA")) {
            foundMember.getMembership().payMembershipFee();
            IO.println("Tak for din betaling! Din næste betalingsfrist er d. "
                    + foundMember.getMembership().getMemberPaymentDue().format(formatter));
        } else {
            IO.println("Der blev ikke foretaget nogen betaling.");
        }
    }

    private void showIndividualMemberTrainingResults(Member foundMember) {
        IO.println();
        IO.println("--- " + foundMember.getName() + "'s træningsresultater ---");
        IO.println();

        if (foundMember instanceof CompetitionSwimmer swimmer) {
            ArrayList<SwimResult> sortedResults = new ArrayList<>(swimmer.getSwimResults());

            if (!sortedResults.isEmpty()) {
                sortedResults.sort(Comparator.comparing(SwimResult::getTimeInSeconds));

                IO.println("Svømmer: " + swimmer.getName() + " (ID: " + swimmer.getMemberID() + ")");

                int rank = 1;
                for (SwimResult result : sortedResults) {
                    IO.println(" " + rank + ". " + result);
                    rank++;
                }
            } else {
                IO.println("Du har ingen registrerede træningsresultater (endnu).");
            }
        } else {
            IO.println("Du er ikke registreret som konkurrencesvømmer.");
        }
    }

    private void editMembershipStatus(){
        boolean inMenu = true;

        while (inMenu) {
            String typedEmail = IO.readln("Indtast medlemmets e-mail: ");

            Member foundMember = null;

            for (Member member : swimmingClub.getMembers()) {
                if (typedEmail.equalsIgnoreCase(member.getEmail())) {
                    foundMember = member;
                    break;
                }
            }

            if (foundMember != null) {
                IO.println();
                IO.println("Du er nu logget ind som: " + foundMember.getName());
                IO.println(foundMember.getName() + " har et " + 	foundMember.getMembership().getMembershipStatus() + " medlemskab");
                MembershipStatus newMembershipStatus = readNewMembershipStatusChoice();

                if(newMembershipStatus == null) {
                    IO.println("Fejl: Det var ikke muligt at ændre medlemsskabet");

                }else{
                    foundMember.getMembership().setMembershipStatus(newMembershipStatus, foundMember.getBirthday());
                    IO.println();
                    IO.println( foundMember.getName() +"'s medlemsstatus er nu ændret til " + newMembershipStatus);
                }
                inMenu = false;
            } else if (swimmingClub.getMembers().isEmpty()) {
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

                for (Member member : swimmingClub.getMembers()) {
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

                            Member regularMember = new Member(
                                    foundMember.getName(),
                                    foundMember.getBirthday(),
                                    foundMember.getEmail(),
                                    foundMember.getPassword(),
                                    foundMember.getMembership().getRenewalDate(),
                                    foundMember.getMembership().getMemberPaymentDue()
                            );

                            int index = swimmingClub.getMembers().indexOf(foundMember);
                            swimmingClub.getMembers().set(index, regularMember);

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
                                    foundMember.getMembership().getRenewalDate(),
                                    foundMember.getMembership().getMemberPaymentDue(),
                                    swimmingClub.getJuniorTeam(),
                                    swimmingClub.getSeniorTeam()
                            );
                            chooseDisciplines(newSwimmer);

                            int index = swimmingClub.getMembers().indexOf(foundMember);
                            swimmingClub.getMembers().set(index, newSwimmer);

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

    // Lader brugeren vælge en eller flere discipliner til en konkurrencesvømmer
    private void chooseDisciplines(CompetitionSwimmer swimmer) {
        SwimmingDiscipline[] disciplines = SwimmingDiscipline.values();
        boolean choosing = true;

        while (choosing) {
            IO.println();
            IO.println("Vælg disciplin:");
            for (int i = 0; i < disciplines.length; i++) {
                IO.println((i + 1) + ". " + disciplines[i]);
            }
            IO.println();
            IO.println("0. Færdig");

            int choice = readChoice("Vælg: ");
            if (choice == 0) {
                choosing = false;
            } else if (choice >= 1 && choice <= disciplines.length) {
                swimmer.addDiscipline(disciplines[choice - 1]);
            } else {
                IO.println("Ugyldigt valg, prøv igen");
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