import javax.swing.*;
import java.awt.*;
public class WarViewer extends JFrame {
    private Game game;
    private Image backcard;
    private Image background;
    private Player p1;
    private Player p2;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 750;
    private final int p1FirstX = 10;
    private final int p1FirstY = 485;
    private final int p2FirstX = 510;
    private final int card1CornerX = 175;
    private final int card2CornerX = 680;
    private final int cardCornerY = 100;
    private final int cardHeight = 270;
    private final int cardWidth = 150;
    private final int messageX = 450;
    private final int messageY = 250;
    private final int roundX = 86;
    private final int roundY = 53;
    private final int handSize1X = 312;
    private final int handSize2X = 820;
    private final int handSize1Y = 427;
    private final int handSize2Y = 432;

    public WarViewer(Game game) {
        this.game = game;
        background = new ImageIcon("Resources/background.png").getImage();
        backcard = new ImageIcon("Resources/Cards/back.png").getImage();
        p1 = game.getPlayer1();
        p2 = game.getPlayer2();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("War Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);



    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 750, this);
        printRound(g);
        printHandSizes(g);
        drawPlayer1Hand(g);
        drawPlayer2Hand(g);
        drawCards(g);
        printRoundMessage(g);
        printGameMessage(g);

    }
    public void printRound(Graphics g) {
        g.drawString(String.valueOf(game.getRound()), roundX, roundY);
    }
    public void printHandSizes(Graphics g) {
        g.drawString(String.valueOf(p1.getHand().size()), handSize1X, handSize1Y);
        g.drawString(String.valueOf(p2.getHand().size()), handSize2X, handSize2Y);
    }
    public void drawPlayer1Hand(Graphics g) {
//        int row = 0;
        int col = 0;
        // fix magic numbers
        int x = p1FirstX;
        int y = p1FirstY;
        for (int i = 0; i < p1.getHand().size(); i ++) {
            g.drawImage(backcard, x, y, 30, 40, this);
            col ++;
            if (col % 10 == 0) {
                x = p1FirstX;
                y += 50;
            }
            else {
                x += 50;
            }

        }
    }



    public void drawPlayer2Hand(Graphics g) {
        int col = 0;
        // fix magic numbers
        int x = p2FirstX;
        int y = p1FirstY;
        for (int i = 0; i < p2.getHand().size(); i ++) {
            g.drawImage(backcard, x, y, 30, 40, this);
            col ++;
            if (col % 10 == 0) {
                x = p2FirstX;
                y += 50;
            }
            else {
                x += 50;
            }
        }
    }

    public void drawCards(Graphics g) {
        if (game.hasInput()) {
            game.getP1Card().draw(g, card1CornerX, cardCornerY, cardWidth, cardHeight, this);
            game.getP2Card().draw(g, card2CornerX, cardCornerY, cardWidth, cardHeight, this);
        }
    }

    public void printRoundMessage(Graphics g) {
        if (game.isRoundOver()) {
            g.drawString(game.getWinner().getName() + " won this round!", messageX, messageY);
        }
    }

    public void printGameMessage(Graphics g) {
        if (game.isGameOver()) {
            g.drawString("Game is over!!\n" + game.getWinner().getName() + "won!!", messageX, messageY);
        }
    }

//    public void printInstructions(Graphics g) {
//
//    }
}
