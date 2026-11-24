void main() {

    Properties env = new Properties();
    try (FileReader reader = new FileReader(".env")) {
        env.load(reader);
    } catch (IOException e) {
        IO.println("Kunne ikke finde .env-filen. Kopiér .env.example til .env og udfyld den.");
        return;
    }
    Chairman chairman = new Chairman(env.getProperty("CHAIRMAN_EMAIL"), env.getProperty("CHAIRMAN_PASSWORD"));
    Treasurer treasurer = new Treasurer(env.getProperty("TREASURER_EMAIL"), env.getProperty("TREASURER_PASSWORD"));
    Trainer trainer = new Trainer();
    
// Her bliver svømmeklubben oprettet
    SwimmingClub swimmingClub = new SwimmingClub();;


// Her opretter vi ConsoleUI og giver den adgang til biblioteket
    ConsoleUI consoleUI = new ConsoleUI(swimmingClub, chairman, trainer);

// Henter gemte medlemmer og svømmeresultater fra filerne
    FileHandler fileHandler = new FileHandler();
    fileHandler.loadAll(swimmingClub);

// Starter ConesoleUI så brugerne kan bruge vores system
    consoleUI.run();

// Gemmer alt når brugeren vælger "Afslut"
    fileHandler.saveAll(swimmingClub);
}