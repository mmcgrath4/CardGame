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
    private final int bottomFirstX = 20;
    private final int bottomFirstY = 500;
    private final int DECK_SIZE = 52;
    private final int NUM_ROWS = 6;
    private final int NUM_COLS = 10;

    public WarViewer(Game game) {
        this.game = game;
        background = new ImageIcon("Resources/background.png").getImage();
        backcard = new ImageIcon("Resources/cards/back.png").getImage();
        p1 = game.getPlayer1();
        p2 = game.getPlayer2();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("War Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);



    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, 1000, 750, this);
        drawPlayer1Hand(g);
        drawPlayer2Hand(g);
        if (game.hasInput()) {

        }
    }

    public void drawPlayer1Hand(Graphics g) {
        int row = 0;
        int col = 0;
        // fix magic numbers
        int x = bottomFirstX;
        int y = bottomFirstY;
        for (int i = 0; i < p1.getHand().size(); i ++) {
            g.drawImage(backcard, x, y, this);
            if (col == 10) {
                x = bottomFirstX;
            }
            else {
                x +=
            }
        }
    }

    public void drawPlayer2Hand(Graphics g) {

    }

}
