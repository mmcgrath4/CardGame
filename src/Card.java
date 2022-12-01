public class Card {
    int rank;
    String suit;
    int point;

    public Card(int rank, String suit, int point) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String toString(){
        return rank + " " + suit;
    }
}
