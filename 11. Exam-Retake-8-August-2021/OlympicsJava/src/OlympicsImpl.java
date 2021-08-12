import java.util.*;
import java.util.stream.Collectors;

public class OlympicsImpl implements Olympics {
    Map<Integer, Competition> competitions;
    Map<Integer, Competitor> competitorsID;
    Map<String, List<Competitor>> competitorsName;

    public OlympicsImpl() {
        this.competitions = new HashMap<>();
        this.competitorsID = new HashMap<>();
        this.competitorsName = new HashMap<>();
    }


    @Override
    public void addCompetitor(int id, String name) {
        if(this.competitorsID.putIfAbsent(id, new Competitor(id, name)) != null)
            throw new IllegalArgumentException();
        this.competitorsName.putIfAbsent(name, new ArrayList<>());
        this.competitorsName.get(name).add(new Competitor(id, name));

    }

    @Override
    public void addCompetition(int id, String name, int score) {
        if(this.competitions.putIfAbsent(id, new Competition(name, id, score)) != null)
            throw new IllegalArgumentException();

    }

    @Override
    public void compete(int competitorId, int competitionId) {
        Competitor competitor = this.competitorsID.get(competitorId);
        Competition competition = this.competitions.get(competitionId);

        if(competitor == null || competition == null)
            throw new IllegalArgumentException();

        competitor.increaseTotalScore(competition.getScore());
        competition.getCompetitors().add(competitor);
    }

    @Override
    public void disqualify(int competitionId, int competitorId) {
        Competition competition = this.competitions.get(competitionId);
        Competitor competitor = this.competitorsID.get(competitorId);

        if(competitor == null || competition == null || competition.getCompetitors().remove(competitor))
            throw new IllegalArgumentException();

        competitor.decreaseTotalScore(competition.getScore());

    }

    @Override
    public Iterable<Competitor> findCompetitorsInRange(long min, long max) {
        return this.competitorsID
                .values()
                .stream()
                .filter(competitor -> Double.compare(competitor.getTotalScore(), min) > 0 &&
                                      Double.compare(competitor.getTotalScore(), max) <= 0)
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Competitor> getByName(String name) {
        Collection<Competitor> result = this.competitorsName.get(name);

        if (result.isEmpty())
            throw new IllegalArgumentException();

        return result;
    }

    @Override
    public Iterable<Competitor> searchWithNameLength(int minLength, int maxLength) {
        List <Competitor> result = new ArrayList<>();

        for (Map.Entry<String, List<Competitor>> entry : competitorsName.entrySet()) {
            if (entry.getKey().length() >= minLength && entry.getKey().length() <= maxLength)
                result.addAll(entry.getValue());
        }
        result.sort(Comparator.comparingInt(Competitor::getId));
        return result;
//        return this.competitors
//                .value()
//                .stream()
//                .filter(entry -> entry.getKey().length() >= minLength &&
//                                 entry.getKey().length() <= maxLength )
//                .collect(Collectors.toList());

    }

    @Override
    public Boolean contains(int competitionId, Competitor comp) {

        return this.getCompetition(competitionId).getCompetitors().contains(comp);

    }

    @Override
    public int competitionsCount() {
        return this.competitions.size();
    }

    @Override
    public int competitorsCount() {
        return this.competitorsID.size();
    }

    @Override
    public Competition getCompetition(int id) {
        Competition competition = this.competitions.get(id);

        if (competition == null)
            throw new IllegalArgumentException();

        return competition;
    }
}