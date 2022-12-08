// Mikey McGrath
// 12/7/22

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Deck d;
    private Card p1Card;
    private Card p2Card;
    private int index1;
    private int index2;
    private final Scanner s;
    private Player winner;

    public Game() {
        // Initializes the deck and shuffles it
        d = makeDeck();
        d.shuffle();
        p1Card = new Card("", "", 0);
        p2Card = new Card("", "", 0);
        index1 = -1;
        index2 = -1;
        winner = new Player("");
        s = new Scanner(System.in);
        makePlayers();
    }


    public void playGame() {
        printInstructions();
        makeHands();
        int roundNum = 1;
        String p1Name = player1.getName();
        String p2Name = player2.getName();
        int handSize1 = 26;
        int handSize2 = 26;
        boolean gameOver = false;
        while (!gameOver) {
            System.out.println("\nRound " + roundNum);
            // Gets user input on which card they want to choose and ensures the index is within range
             do {
                 System.out.println(p1Name + ", what index do you choose? Must be in between 0 and " + (handSize1 - 1));
                 index1 = s.nextInt();
             } while (index1 > (handSize1 - 1) || index1 < 0);
            do {
                System.out.println(p2Name + ", what index do you choose? Must be in between 0 and " + (handSize2 - 1));
                index2 = s.nextInt();
            } while (index2 > (handSize2 - 1) || index2 < 0);
            // Gets players' cards for that round
            getCards();
            // Prints both cards
            System.out.println(p1Name + " Card: " + p1Card + "\n" + p2Name + " Card: " + p2Card);
            boolean tie = false;
            // Generates two random indexes in the hands to place cards at
            int r1 = (int) (Math.random() * player1.getHand().size());
            int r2 = (int) (Math.random() * player2.getHand().size());
            // Checks which card has a higher point value and edits the hands accordingly
            if (getHigher(p1Card, p2Card) == p1Card) {
                player1Win(r1);
            }
            else if (getHigher(p1Card, p2Card) == p2Card) {
                player2Win(r2);
            }
            else {
                tie = true;
                returnCards(r1, r2);
            }
            handSize1 = player1.getHand().size();
            handSize2 = player2.getHand().size();
            // Prints winner and hand sizes
            if (!tie) {
                System.out.println("Winner: " + winner.getName() + "\n");
                System.out.println(p1Name + " Hand Size: " + handSize1);
                System.out.println(p2Name + " Hand Size: " + handSize2);
                // Checks if one player has all the cards
                if (player1.getHand().isEmpty() || player2.getHand().isEmpty())
                    gameOver = true;
            }
            // Prints tie message and hand sizes
            else {
                System.out.println("Tie. Cards are returned to each player.");
                System.out.println(p1Name + " Hand Size: " + handSize1);
                System.out.println(p2Name + " Hand Size: " + handSize2);
            }
            // If the game has reached the maximum amount of rounds, the winner becomes the player with more cards.
            if (roundNum > 48) {
                gameOver = true;
                if (handSize1 > handSize2)
                    winner = player1;
                else
                    winner = player2;
            }
            roundNum ++;
        }
        // Prints winner statement for the whole game
        System.out.println("\n" + winner.getName() + " Won !!");
    }

    // Gets user input for names and initializes the two players
    public void makePlayers() {
        System.out.println("Player 1 name: ");
        String name1 = s.nextLine();
        System.out.println("Player 2 name: ");
        String name2 = s.nextLine();
        player1 = new Player(name1, new ArrayList<>());
        player2 = new Player(name2, new ArrayList<>());
    }

    public void printInstructions() {
        System.out.println("""
                You are playing War.
                Each round, you will pick a random card to play.
                The card with the higher point value wins that round. They add the other player's card to their hand. If it is a tie, the cards are returned in a random spot of their hands.
                The goal is to get all 52 cards of the deck.
                There is a maximum of 49 rounds played at which time the player with more cards wins.""");
    }

    // Initializes a normal deck of 52 cards
    public Deck makeDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        return new Deck(ranks, suits, points);
    }

    // Populates hands with shuffled cards from the deck
    public void makeHands() {
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0)
                player1.addCard(d.deal());
            else
                player2.addCard(d.deal());
        }
    }

    public void getCards() {
        p1Card = player1.getHand().get(index1);
        p2Card = player2.getHand().get(index2);
    }

    // Returns the card with a higher point value
    // Returns null if it is a tie
    public Card getHigher(Card c1, Card c2) {
        if (c1.getPoint() > c2.getPoint())
            return c1;
        else if (c1.getPoint() < c2.getPoint())
            return c2;
        return null;
    }

    // Takes away player2's card and adds it to a random spot in player1's hand
    // Adds player1's card to a random spot in their deck
    public void player1Win(int randomIndex) {
        winner = player1;
        player2.getHand().remove(p2Card);
        player1.getHand().add(randomIndex, p2Card);
        player1.getHand().remove(p1Card);
        player1.getHand().add(randomIndex, p1Card);
    }

    // Takes away player1's card and adds it to a random spot in player2's hand
    // Adds player2's card to a random spot in their deck
    public void player2Win(int randomIndex) {
        winner = player2;
        player1.getHand().remove(p1Card);
        player2.getHand().add(randomIndex, p1Card);
        player2.getHand().remove(p2Card);
        player2.getHand().add(randomIndex, p2Card);
    }

    // Returns the two cards played to a random spot in their respective decks
    public void returnCards(int random1, int random2) {
        player1.getHand().remove(p1Card);
        player2.getHand().remove(p2Card);
        player1.getHand().add(random1, p1Card);
        player2.getHand().add(random2, p2Card);
    }


    public static void main(String[] args) {
        Game g = new Game();
        g.playGame();
    }
}
