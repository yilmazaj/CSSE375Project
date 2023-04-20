package Presentation;

import java.awt.*;
import java.awt.geom.Point2D;

public class HexPoint {
    public Point2D.Double point;
    public Color color;

    public HexPoint(Point2D.Double curPoint) {
        this.point = curPoint;
        this.color = Color.BLACK;
    }

    public HexPoint(Point2D.Double curPoint, Color curColor) {
        this.point = curPoint;
        this.color = curColor;
    }

}
