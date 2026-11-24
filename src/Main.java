void main() {

// Her bliver svømmeklubben oprettet
    SwimmingClub swimmingClub = new SwimmingClub();
    Chairman chairman = new Chairman();
    Trainer trainer = new Trainer();

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