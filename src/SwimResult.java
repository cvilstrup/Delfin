import java.time.LocalDate;

public class SwimResult {
    //x
        private SwimmingDiscipline discipline;
        private double timeInSeconds;
        private LocalDate date;

        public SwimResult(SwimmingDiscipline discipline, double timeInSeconds, LocalDate date) {
            this.discipline = discipline;
            this.timeInSeconds = timeInSeconds;
            this.date = date;
        }

        public SwimmingDiscipline getDiscipline() {
            return discipline;
        }

        public double getTimeInSeconds() {
            return timeInSeconds;
        }

        public LocalDate getDate() {
            return date;
        }

        @Override
        public String toString() {
            return discipline + ": " + timeInSeconds + " sek. (" + date + ")";
        }

}
