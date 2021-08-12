public class Main {
    public static void main(String[] args) {
        Olympics olympics = new OlympicsImpl();

        olympics.addCompetition(1, "SoftUniada", 500);

        olympics.addCompetitor(5, "Ani");
        olympics.addCompetitor(2, "Stamat");
        olympics.addCompetitor(3, "Stamat");

        olympics.compete(2,1);
        olympics.disqualify(1,2);
    }
}
