import java.util.ArrayList;
import java.awt.*;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points, Image[] images) {
        cards = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < ranks.length; i ++) {
            for (int j = 0; j < suits.length; j++) {
                cards.add(new Card(ranks[i], suits[j], points[i], images[index]));
                index ++;
            }
        }
        cardsLeft = cards.size();
    }

    public boolean isEmpty() {
        if (cardsLeft == 0)
            return true;
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (this.isEmpty())
            return null;
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    // Randomly shuffles the deck
    public void shuffle() {
        cardsLeft = cards.size();
        for (int i = cardsLeft - 1; i > 0; i--) {
            int r = (int) (Math.random() * (i + 1));
            Card temp = cards.get(r);
            cards.set(r, cards.get(i));
            cards.set(i, temp);
        }
    }
}
