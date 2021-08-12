import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardImpl implements Board {
    private final Map<String, Card> deck;

    public BoardImpl() {
        this.deck = new HashMap<>();
    }

    @Override
    public void draw(Card card) {
        if (this.deck.putIfAbsent(card.getName(), card) != null)
            throw new IllegalArgumentException();
    }

    @Override
    public Boolean contains(String name) {
        return this.deck.containsKey(name);
    }

    @Override
    public int count() {
        return this.deck.size();
    }

    @Override
    public void play(String attackerCardName, String attackedCardName) {
        Card attacker = this.deck.get(attackerCardName);
        Card attacked = this.deck.get(attackedCardName);

        if (attacker == null || attacked == null || attacker.getLevel() != attacked.getLevel())
            throw new IllegalArgumentException();

        if (attacker.getHealth() > 0 && attacked.getHealth() > 0)
            attacker.attack(attacked);
    }

    @Override
    public void remove(String name) {
        if (this.deck.remove(name) == null)
            throw new IllegalArgumentException();
    }

    @Override
    public void removeDeath() {
        this.deck.entrySet().removeIf(entry -> entry.getValue().getHealth() <= 0);
    }

    @Override
    public Iterable<Card> getBestInRange(int start, int end) {
        return this.deck.values()
                .stream()
                .filter(card -> card.getScore() >= start &&
                                card.getScore() <= end)
                .sorted((card1, card2) -> card2.getLevel() - card1.getLevel())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Card> listCardsByPrefix(String prefix) {
        return this.deck.values()
                .stream()
                .filter(card -> card.getName().startsWith(prefix))
                .sorted((card1, card2) -> {
                    String reverseName1 = new StringBuilder(card1.getName()).reverse().toString();
                    String reverseName2 = new StringBuilder(card2.getName()).reverse().toString();

                    if (reverseName1.compareTo(reverseName2) == 0)
                        return card1.getLevel() - card2.getLevel();

                    return reverseName1.compareTo(reverseName2);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Card> searchByLevel(int level) {
        return this.deck.values()
                .stream()
                .filter(card -> card.getLevel() == level)
                .sorted((card1, card2) -> card2.getScore() - card1.getScore())
                .collect(Collectors.toList());
    }

    @Override
    public void heal(int health) {
        this.deck.values()
                .stream()
                .min((card1, card2) -> card1.getHealth() - card2.getHealth())
                .ifPresent(card -> card.increaseHealth(health));
    }
}
