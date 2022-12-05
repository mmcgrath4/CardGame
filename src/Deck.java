import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards;
    int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points) {
        cards = new ArrayList<Card>();
        for (int i = 0; i < suits.length; i ++) {
            for (int j = 0; j < ranks.length; j++) {
                cards.add(new Card(ranks[j], suits[i], points[j]));
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
