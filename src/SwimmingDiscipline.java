public enum SwimmingDiscipline {
        BUTTERFLY("Butterfly"),
        CRAWL("Crawl"),
        BACKSTROKE("Rygcrawl"),
        BREASTSTROKE("Brystsvømning");

        private final String displayName;

        SwimmingDiscipline(String displayName) {
                this.displayName = displayName;
        }

        @Override
        public String toString() {
                return displayName;
        }
}
