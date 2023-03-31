import Team7.SettlersOfCatan.Game;
import Team7.SettlersOfCatan.GameBoard;
import Team7.SettlersOfCatan.Hex;
import Team7.SettlersOfCatan.NoResourceHex;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewGameBoardTest {

    @Test
    public void paintComponentTest(){
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        f.add(panel);
        f.setVisible(true);
        Graphics g = f.getGraphics(); //Easiest way to get 2D graphics implementation to test

        GameBoard gb = new GameBoard(1);
        gb.paintComponent(g);

        int size = 54;
        assertEquals(g.getColor(),gb.intersectionPoints[size-1].color);
        assertEquals(gb.startingPoint, new Point2D.Double(100, 100));
        g.create();

    }

    private Graphics2D initGraphics2D(){
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        return image.createGraphics();
    }

    @Test
    public void testDrawHex(){
        GameBoard gb = new GameBoard(1);
        double x = 0.0;
        double y = 0.0;
        Hex testHex = new NoResourceHex(-1);
        Graphics2D g2 = initGraphics2D();
        Point2D.Double point = new Point2D.Double(x, y);
        assertEquals(gb.drawHex(testHex, g2, point).getXCenter(), x);
        assertEquals(gb.drawHex(testHex, g2, point).getYCenter(), y);
    }

    @Test
    public void drawHexShapeTest(){
        GameBoard gb = new GameBoard(1);
        GameBoard.HexagonData hdata = gb.initHexagonData(1, 1, new Polygon());
        Graphics2D g2 = initGraphics2D();
        hdata.drawHexShape(g2);
        assertEquals(g2.getColor(), new Color(51, 62, 79));
        assertEquals(g2.getStroke(), new BasicStroke(5));
    }

    @Test
    public void drawHexNumberAtPositionTest(){
        double x = 0.0;
        double y = 0.0;
        Graphics2D g2 = initGraphics2D();
        Color curColor = g2.getColor();
        GameBoard gb = new GameBoard(1);
        GameBoard.GraphicsWithIndex gwi = gb.initGraphicsWithIndex(g2, 1);
        gb.drawHexNumberAtPosition(gwi, new Point2D.Double(x, y));
        assertEquals(g2.getColor(), curColor);
        assertEquals(g2.getStroke(), new BasicStroke(10));
    }
}
