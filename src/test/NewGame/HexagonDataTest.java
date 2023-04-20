import Team7.SettlersOfCatan.Hex;
import Team7.SettlersOfCatan.NoResourceHex;
import Team7.SettlersOfCatan.Presentation.GraphicsWithIndex;
import Team7.SettlersOfCatan.Presentation.HexagonData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexagonDataTest {

    private Graphics2D initGraphics2D(){
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        return image.createGraphics();
    }


    @Test
    public void drawHexShapeTest(){
        HexagonData hdata = new HexagonData(1, 1, new Polygon());
        Graphics2D g2 = initGraphics2D();
        hdata.drawHexShape(g2);
        assertEquals(g2.getColor(), new Color(51, 62, 79));
        assertEquals(g2.getStroke(), new BasicStroke(5));
    }

    @Test
    public void drawHexNumberAtPositionTest(){
        Graphics2D g2 = initGraphics2D();

        HexagonData hexData = new HexagonData(new Point2D.Double(1.0,1.0));

        GraphicsWithIndex gwi = new GraphicsWithIndex(g2, 1);
        hexData.drawHexShape(gwi.getGraphics());
        assertEquals(g2.getColor(), new Color(51,62,79));
    }
}
