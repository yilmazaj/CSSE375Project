package Team7.SettlersOfCatan.Presentation;

import java.awt.*;
import java.awt.geom.Point2D;

public class HexagonData {
    private int xCenter;
    private int yCenter;
    private  Polygon hex;

    public final int HEX_SIDE_LENGTH = 70;

    public HexagonData(int x, int y, Polygon hexagon) {
        xCenter = x;
        yCenter = y;
        hex = hexagon;
    }

    public HexagonData(Point2D center){
        xCenter = (int) center.getX();
        yCenter = (int) center.getY();

        Polygon hexagon = new Polygon();
        hexagon.addPoint(xCenter + 1, yCenter + HEX_SIDE_LENGTH + 1);
        hexagon.addPoint(xCenter + (int) (HEX_SIDE_LENGTH * Math.sqrt(3 / 2)) + 1,
                yCenter + (int) (.5 * HEX_SIDE_LENGTH) + 1);
        hexagon.addPoint(xCenter + (int) (HEX_SIDE_LENGTH * Math.sqrt(3 / 2)) + 1,
                yCenter - (int) (.5 * HEX_SIDE_LENGTH) - 1);
        hexagon.addPoint(xCenter + 1, yCenter - HEX_SIDE_LENGTH - 1);
        hexagon.addPoint(xCenter - (int) (HEX_SIDE_LENGTH * Math.sqrt(3 / 2)) - 1,
                yCenter - (int) (.5 * HEX_SIDE_LENGTH) - 1);
        hexagon.addPoint(xCenter - (int) (HEX_SIDE_LENGTH * Math.sqrt(3 / 2)) - 1,
                yCenter + (int) (.5 * HEX_SIDE_LENGTH) + 1);

        hex = hexagon;
    }

    public void drawHexShape(Graphics2D g2) {
        g2.fillPolygon(hex);
        g2.setColor(new Color(51, 62, 79));
        g2.setStroke(new BasicStroke(5));
        g2.drawPolygon(hex);
    }

    public int getXCenter(){
        return xCenter;
    }
    public int getYCenter(){
        return yCenter;
    }
}