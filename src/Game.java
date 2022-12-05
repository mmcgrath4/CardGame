import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Player player1;
    Player player2;
    Deck d;

    public Game() {
        d = makeDeck();
        d.shuffle();

        Scanner s = new Scanner(System.in);
        System.out.println("Player 1 name: ");
        String name1 = s.nextLine();
        System.out.println("Player 2 name: ");
        String name2 = s.nextLine();
        player1 = new Player(name1, new ArrayList<>());
        player2 = new Player(name2, new ArrayList<>());
    }

    public Deck makeDeck() {
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        return new Deck(ranks, suits, points);
    }

    public void makeHands() {
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0)
                player1.addCard(d.deal());
            else
                player2.addCard(d.deal());
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
    }
}
