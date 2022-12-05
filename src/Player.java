import java.util.ArrayList;
public class Player {
    ArrayList<Card> hand;
    int points;
    String name;

    public Player(String name) {
        this.name = name;
        points = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        points = 0;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int p) {
        points += p;
    }

    public void addCard(Card c) {
        hand.add(c);
    }

    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
