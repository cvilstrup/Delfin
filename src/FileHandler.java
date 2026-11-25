import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FileHandler {
    private static final String MEMBERS_FILE = "members.csv";
    private static final String RESULTS_FILE = "swimresults.csv";
    private static final String SEPARATOR = ";";

    // Gemmer alle medlemmer og konkurrencesvømmernes resultater i hver sin fil
    public void saveAll(SwimmingClub swimmingClub) {
        saveMembers(swimmingClub.getMembers());
        saveSwimResults(swimmingClub.getMembers());
    }

    // Indlæser medlemmer først, fordi resultaterne skal kobles på de svømmere der allerede findes
    public void loadAll(SwimmingClub swimmingClub) {
        loadMembers(swimmingClub);
        loadSwimResults(swimmingClub);
    }

    // Format: TYPE;ID;navn;fødselsdag;email;kodeord;status;fornyelsesdato;betalingsfrist;discipliner
    private void saveMembers(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member member : members) {
                String type = "MEMBER";
                String disciplines = "";

                if (member instanceof CompetitionSwimmer swimmer) {
                    type = "COMPETITION_SWIMMER";
                    disciplines = disciplinesToText(swimmer);
                }

                writer.write(String.join(SEPARATOR,
                        type,
                        String.valueOf(member.getMemberID()),
                        member.getName(),
                        member.getBirthday().toString(),
                        member.getEmail(),
                        member.getPassword(),
                        member.getMembershipStatus().name(),
                        member.getRenewalDate().toString(),
                        member.getmemberPaymentDue().toString(),
                        disciplines));
                writer.newLine();
            }
        } catch (IOException e) {
            IO.println("Fejl: Kunne ikke gemme medlemmer: " + e.getMessage());
        }
    }

    // Format: medlems-ID;disciplin;tid i sekunder;dato
    private void saveSwimResults(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULTS_FILE))) {
            for (Member member : members) {
                if (member instanceof CompetitionSwimmer swimmer) {
                    for (SwimResult result : swimmer.getSwimResults()) {
                        writer.write(String.join(SEPARATOR,
                                String.valueOf(swimmer.getMemberID()),
                                result.getDiscipline().name(),
                                String.valueOf(result.getTimeInSeconds()),
                                result.getDate().toString()));
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            IO.println("Fejl: Kunne ikke gemme svømmeresultater: " + e.getMessage());
        }
    }

    private void loadMembers(SwimmingClub swimmingClub) {
        if (!new File(MEMBERS_FILE).exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Springer tomme linjer over og fortsætter med næste linje i filen
                if (line.isBlank()) {
                    continue;
                }
                try {
                    swimmingClub.addMember(memberFromLine(line, swimmingClub));
                } catch (RuntimeException e) {
                    IO.println("Advarsel: Springer en ugyldig linje over i " + MEMBERS_FILE + ": " + line);
                }
            }
        } catch (IOException e) {
            IO.println("Fejl: Kunne ikke indlæse medlemmer: " + e.getMessage());
        }
    }

    private Member memberFromLine(String line, SwimmingClub swimmingClub) {
        // -1 gør at en tom disciplin-kolonne til sidst stadig kommer med
        String[] parts = line.split(SEPARATOR, -1);

        String type = parts[0];
        int memberID = Integer.parseInt(parts[1]);
        String name = parts[2];
        LocalDate birthday = LocalDate.parse(parts[3]);
        String email = parts[4];
        String password = parts[5];
        MembershipStatus status = MembershipStatus.valueOf(parts[6]);
        LocalDate renewalDate = LocalDate.parse(parts[7]);
        LocalDate memberPaymentDue = LocalDate.parse(parts[8]);

        Member member;
        if (type.equals("COMPETITION_SWIMMER")) {
            CompetitionSwimmer swimmer = new CompetitionSwimmer(name, birthday, email, password, renewalDate,
                    memberPaymentDue, swimmingClub.getJuniorTeam(), swimmingClub.getSeniorTeam());
            addDisciplinesFromText(swimmer, parts[9]);
            member = swimmer;
        } else {
            member = new Member(name, birthday, email, password, renewalDate, memberPaymentDue);
        }

        // Konstruktøren giver altid et nyt ID og status ACTIVE, så de gemte værdier sættes bagefter
        member.setMemberID(memberID);
        member.setMembershipStatus(status);
        return member;
    }

    private void loadSwimResults(SwimmingClub swimmingClub) {
        if (!new File(RESULTS_FILE).exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(RESULTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Springer tomme linjer over og fortsætter med næste linje i filen
                if (line.isBlank()) {
                    continue;
                }
                try {
                    String[] parts = line.split(SEPARATOR);
                    int memberID = Integer.parseInt(parts[0]);
                    SwimmingDiscipline discipline = SwimmingDiscipline.valueOf(parts[1]);
                    double time = Double.parseDouble(parts[2]);
                    LocalDate date = LocalDate.parse(parts[3]);

                    if (swimmingClub.getMember(memberID) instanceof CompetitionSwimmer swimmer) {
                        swimmer.addSwimResult(new SwimResult(discipline, time, date));
                    }
                } catch (RuntimeException e) {
                    IO.println("Advarsel: Springer en ugyldig linje over i " + RESULTS_FILE + ": " + line);
                }
            }
        } catch (IOException e) {
            IO.println("Fejl: Kunne ikke indlæse svømmeresultater: " + e.getMessage());
        }
    }

    // Bruger name() i stedet for toString(), fordi toString() giver det danske navn som valueOf() ikke kan læse
    private String disciplinesToText(CompetitionSwimmer swimmer) {
        // Starter med en tom tekst, som disciplinerne bygges ind i én ad gangen
        StringBuilder text = new StringBuilder();
        for (SwimmingDiscipline discipline : swimmer.getDisciplines()) {

            // Sætter kun komma, hvis der allerede står en disciplin, så teksten ikke starter med ","
            if (!text.isEmpty()) {
                text.append(",");
            }

            // Tilføjer disciplinens enum-navn, f.eks. "CRAWL"
            text.append(discipline.name());
        }

        // Laver StringBuilder om til en almindelig String, f.eks. "CRAWL,BUTTERFLY"
        return text.toString();
    }

    private void addDisciplinesFromText(CompetitionSwimmer swimmer, String text) {
        if (text.isBlank()) {
            return;
        }
        for (String disciplineName : text.split(",")) {
            swimmer.addDiscipline(SwimmingDiscipline.valueOf(disciplineName));
        }
    }
}
