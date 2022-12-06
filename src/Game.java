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


    public void playGame() {
        makeHands();
        boolean gameOver = false;
        int roundNum = 1;
        int index1 = 0;
        int index2 = 0;
        while (!gameOver) {

            System.out.println("Round " + roundNum);
            roundNum ++;

            // Gets players' cards for that round
            Card p1Card;
            if (index1 < player1.getHand().size())
                p1Card = player1.getHand().get(index1);
            else {
                index1 = 0;
                p1Card = player1.getHand().get(index1);
            }
            index1 ++;
            Card p2Card;
            if (index2 < player2.getHand().size())
                p2Card = player2.getHand().get(index2);
            else {
                index2 = 0;
                p2Card = player2.getHand().get(index2);
            }
            index2 ++;

            //print both player's cards
            String p1Name = player1.getName();
            String p2Name = player2.getName();
            System.out.println(p1Name + " Card: " + p1Card + "\n" + p2Name + " Card: " + p2Card);

            //compare higher point value
            //remove card from loser's hand and add to winner's
            boolean tie = false;
            Player winner = new Player("");
            if (getHigher(p1Card, p2Card) == p1Card) {
                winner = player1;
                player2.getHand().remove(p2Card);
                player1.getHand().add(p2Card);
            }
            else if (getHigher(p1Card, p2Card) == p2Card) {
                winner = player2;
                player1.getHand().remove(p1Card);
                player2.getHand().add(p1Card);
            }
            else
                tie = true;

            //print winner and hand sizes
            System.out.println("Winner: " + winner.getName());
            System.out.println(p1Name + " Hand Size: " + player1.getHand().size());
            System.out.println(p2Name + " Hand Size: " + player2.getHand().size());
            //if one hand is empty then gameOver = true
            if (player1.getHand().isEmpty() || player2.getHand().isEmpty())
                gameOver = true;
        }
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

    public Card getHigher(Card c1, Card c2) {
        if (c1.getPoint() > c2.getPoint())
            return c1;
        else if (c1.getPoint() < c2.getPoint())
            return c2;
        return null;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
