import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Player player1;
    Player player2;
    Deck d;
    Card p1Card;
    Card p2Card;
    int index1;
    int index2;
    Scanner s;

    public Game() {
        d = makeDeck();
        d.shuffle();
        p1Card = new Card("", "", 0);
        p2Card = new Card("", "", 0);
        index1 = 0;
        index2 = 0;

        s = new Scanner(System.in);
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
        String p1Name = player1.getName();
        String p2Name = player2.getName();
        Player winner = new Player("");
        int handSize1 = 26;
        int handSize2 = 26;
        while (!gameOver) {

            System.out.println("\nRound " + roundNum);
            System.out.println(p1Name + ", what index do you choose? Must be in between 0 and " + (handSize1 - 1));
            index1 = s.nextInt();
            System.out.println(p2Name + ", what index do you choose? Must be in between 0 and " + (handSize2 - 1));
            index2 = s.nextInt();

            // Gets players' cards for that round
            getCards();

            //print both player's cards

            System.out.println(p1Name + " Card: " + p1Card + "\n" + p2Name + " Card: " + p2Card);

            //compare higher point value
            boolean tie = false;

            int r1 = (int) (Math.random() * player1.getHand().size());
            int r2 = (int) (Math.random() * player2.getHand().size());
            if (getHigher(p1Card, p2Card) == p1Card) {
                winner = player1;
                //removes from loser's hand and adds to winner's
                player2.getHand().remove(p2Card);
                player1.getHand().add(r1, p2Card);
                //moves winner's card to end of his hand
                player1.getHand().remove(p1Card);
                player1.getHand().add(r2, p1Card);
            }
            else if (getHigher(p1Card, p2Card) == p2Card) {
                winner = player2;
                player1.getHand().remove(p1Card);
                player2.getHand().add(r1, p1Card);
                player2.getHand().remove(p2Card);
                player2.getHand().add(r2, p2Card);
            }
            else {
                tie = true;
                player1.getHand().remove(p1Card);
                player2.getHand().remove(p2Card);
                player1.addCard(p1Card);
                player2.addCard(p2Card);
            }

            handSize1 = player1.getHand().size();
            handSize2 = player2.getHand().size();
            //print winner and hand sizes
            if (!tie) {
                System.out.println("Winner: " + winner.getName());
                System.out.println(p1Name + " Hand Size: " + handSize1);
                System.out.println(p2Name + " Hand Size: " + handSize2);
                //if one hand is empty then gameOver = true
                if (player1.getHand().isEmpty() || player2.getHand().isEmpty())
                    gameOver = true;
            }
            else {
                System.out.println("Tie. Cards are returned to each player.");
            }
            roundNum ++;
        }
        System.out.println("\n" + winner.getName() + " Won !!");

    }

    public Deck makeDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
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

    public void getCards() {
//        if (index1 < player1.getHand().size())
//            p1Card = player1.getHand().get(index1);
//        else {
//            index1 = 0;
//            p1Card = player1.getHand().get(index1);
//        }
//        index1++;
//        if (index2 < player2.getHand().size())
//            p2Card = player2.getHand().get(index2);
//        else {
//            index2 = 0;
//            p2Card = player2.getHand().get(index2);
//        }
//        index2++;
        p1Card = player1.getHand().get(index1);
        p2Card = player2.getHand().get(index2);
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
