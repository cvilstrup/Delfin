void main() {

// Her bliver svømmeklubben oprettet
    SwimmingClub swimmingClub = new SwimmingClub();
    Chairman chairman = new Chairman();
    Treasurer treasurer = new Treasurer();
    Trainer trainer = new Trainer();

// Her opretter vi ConsoleUI og giver den adgang til biblioteket
    ConsoleUI consoleUI = new ConsoleUI(swimmingClub, chairman, treasurer, trainer);

// Her kan medlemmerne af svmømmeklubben oprettes ...

    //Member member1 = new Member("Thorkild Hansen", 111);//

// Her kan medlemmerne tilføjes til svømmeklubben

    //swimmingClub.addMember(member1);"//

// Starter ConesoleUI så brugerne kan bruge vores system
    consoleUI.run();
}