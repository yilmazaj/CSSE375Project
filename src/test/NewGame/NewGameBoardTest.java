import Team7.SettlersOfCatan.*;
import Team7.SettlersOfCatan.Presentation.GameBoard;
import Team7.SettlersOfCatan.Presentation.GraphicsWithIndex;
import Team7.SettlersOfCatan.Presentation.HexagonData;
//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class NewGameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setup(){ //ANDREW REFACTOR
        gameBoard = new GameBoard();
    }

    @Test
    public void paintComponentTest(){
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        f.add(panel);
        f.setVisible(true);
        Graphics g = f.getGraphics(); //Easiest way to get 2D graphics implementation to test

        gameBoard.paintComponent(g);

        int size = 54;
        assertEquals(g.getColor(),gameBoard.intersectionPoints[size-1].color);
        assertEquals(gameBoard.startingPoint, new Point2D.Double(100, 100));
        g.create();

    }

    private Graphics2D initGraphics2D(){
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        return image.createGraphics();
    }

    @Test
    public void testDrawHex(){
        double x = 0.0;
        double y = 0.0;
        Hex testHex = new NoResourceHex(-1);
        Graphics2D g2 = initGraphics2D();
        Point2D.Double point = new Point2D.Double(x, y);
        assertEquals(gameBoard.drawHex(testHex, g2, point).getXCenter(), x);
        assertEquals(gameBoard.drawHex(testHex, g2, point).getYCenter(), y);
    }

    @Test
    public void drawHexNumberAtPositionTest(){
        double x = 0.0;
        double y = 0.0;
        Graphics2D g2 = initGraphics2D();

        GraphicsWithIndex gwi = new GraphicsWithIndex(g2, 1);
        gameBoard.drawHexNumberAtPosition(gwi, new Point2D.Double(x, y));
        assertEquals(g2.getColor(), new Color(51,62,79));
        assertEquals(g2.getStroke(), new BasicStroke(10));
    }

}
