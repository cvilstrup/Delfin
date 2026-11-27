void main() {

// Indlæser loginoplysninger fra .env-filen, så de ikke står i koden
    Properties env = new Properties();          // 1. en tom "ordbog"
    try (FileReader reader = new FileReader(".env")) {
        env.load(reader);                       // 2. fyld den fra en fil
    } catch (IOException e) {
        // Stopper programmet, hvis .env mangler, da ingen så kan logge ind
        IO.println("Kunne ikke finde .env-filen. Kopiér .env.example til .env og udfyld den.");
        return;
    }

// Opretter formand, kasserer og træner med email og password fra .env
    // 3. getProperty slår en nøgle op
    Chairman chairman = new Chairman(env.getProperty("CHAIRMAN_EMAIL"), env.getProperty("CHAIRMAN_PASSWORD"));
    Treasurer treasurer = new Treasurer(env.getProperty("TREASURER_EMAIL"), env.getProperty("TREASURER_PASSWORD"));
    Trainer trainer = new Trainer(env.getProperty("TRAINER_EMAIL"), env.getProperty("TRAINER_PASSWORD"));
    
// Her bliver svømmeklubben oprettet
    SwimmingClub swimmingClub = new SwimmingClub();;


// Her opretter vi ConsoleUI og giver den adgang til biblioteket
    ConsoleUI consoleUI = new ConsoleUI(swimmingClub, chairman, treasurer, trainer);

// Henter gemte medlemmer og svømmeresultater fra filerne
    FileHandler fileHandler = new FileHandler();
    fileHandler.loadAll(swimmingClub);

// Starter ConesoleUI så brugerne kan bruge vores system

    consoleUI.run();

// Gemmer alt når brugeren vælger "Afslut"
    fileHandler.saveAll(swimmingClub);
}