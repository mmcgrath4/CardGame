
import java.awt.*;
public class Card {
    String rank;
    String suit;
    int point;
    Image image;

    public Card(String rank, String suit, int point, Image image) {
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        this.image = image;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
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
        return rank + " of " + suit;
    }

    public void draw(Graphics g, int x, int y, WarViewer view) {
        g.drawImage(image, x, y, view);
    }
}
