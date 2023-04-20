package Domain;

import Presentation.Intersection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Hex {
	
	private int number;
	public Intersection[] intersections = new Intersection[6];
	public boolean hasRobber;
	private ImageIcon resourceIcon;
	public Point2D.Double center;

	public Hex(int i) {
		hasRobber = false;
		number = i;
		resourceIcon = new ImageIcon("images/Icons/"+getResource()+"Icon.png");
	}


	public int getNumber() {return number;}
	public abstract String getResource();
	public Color getColor(){
		return Color.BLACK;
	}
	public void setIntersections(Intersection i1, Intersection i2, Intersection i3, Intersection i4,
			Intersection i5, Intersection i6) {
		intersections[0] = i1;
		intersections[1] = i2;
		intersections[2] = i3;
		intersections[3] = i4;
		intersections[4] = i5;
		intersections[5] = i6;
	}

	public void drawIcon(Graphics2D g2, Point2D.Double point) {
		resourceIcon.paintIcon(new Canvas(), g2, (int)Math.round(point.getX()), (int)Math.round(point.getY()));
	}

	public ImageIcon getIcon(){
		return resourceIcon;
	}

	public void setCenter(int x, int y){
		center = new Point2D.Double(x, y);
	}
}
