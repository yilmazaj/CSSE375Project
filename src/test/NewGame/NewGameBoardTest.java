import Team7.SettlersOfCatan.Game;
import Team7.SettlersOfCatan.GameBoard;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

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


}
