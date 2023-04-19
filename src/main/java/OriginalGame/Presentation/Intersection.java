package Team7.SettlersOfCatan.Presentation;

import Team7.SettlersOfCatan.Hex;
import Team7.SettlersOfCatan.Road;
import Team7.SettlersOfCatan.Structure;

import java.awt.*;
import java.awt.geom.Point2D;

public class Intersection {
	public Hex[] hexes = new Hex[3];
	public Road[] roads = new Road[3];
	public Structure structure = null;
	
	public Intersection(Hex h) {
		hexes[0] = h;
	}
	
	public Intersection(Hex h1, Hex h2) {
		hexes[0] = h1;
		hexes[1] = h2;
	}
	
	public Intersection(Hex h1, Hex h2, Hex h3) {
		hexes[0] = h1;
		hexes[1] = h2;
		hexes[2] = h3;
	}
	
	
	public boolean addStructure(Structure s) {
		if(!containsRoad(s.color)) {
			return false;
		}
		if(structure == null) {
			structure = s;
			return true;
		}
		return false;
	}

	
	public void setRoads(Road r1, Road r2, Road r3) {
		roads[0] = r1;
		roads[1] = r2;
		roads[2] = r3;
	}
	
	public void setRoads(Road r1, Road r2) {
		roads[0] = r1;
		roads[1] = r2;
	}
	
	public boolean containsRoad(Color color) {
		for(int i = 0; i < 3; i++) {
			if(roads[i] == null) {
				continue;
			}
			if(roads[i].color == null) {
				continue;
			}
			if(roads[i].color.equals(color)) {
				return true;
			}
		}
		return false;
	}

	public boolean updateRoad(int i1, int i2, Road r) {
		for(int i = 0; i < 3; i++) {
			if(roads[i].intersection1Num == i1) {
				if(roads[i].intersection2Num == i2) {
					roads[i] = r;
					return true;
				}
			}
			else if(roads[i].intersection1Num == i2) {
				if(roads[i].intersection2Num == i1) {
					roads[i] = r;
					return true;
				}
			}
		}
		return false;
		
	}

	public void drawStructureIcon(Graphics2D g2, Point2D.Double point) {
		if(structure != null) {
			structure.drawIcon(g2, point);
		}
	}
}
