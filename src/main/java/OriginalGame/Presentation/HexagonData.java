package Team7.SettlersOfCatan.Presentation;

import java.awt.*;

public class HexagonData {
    public int xCenter;
    public int yCenter;
    public Polygon hex;

    public HexagonData(int x, int y, Polygon hexagon) {
        xCenter = x;
        yCenter = y;
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