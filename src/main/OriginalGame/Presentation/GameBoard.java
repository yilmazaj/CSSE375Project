package Presentation;

import Domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends JPanel {
	public Hex[] hexes = new Hex[19];
	public Intersection[] intersections = new Intersection[54];
	public Road[] roads = new Road[72];
	public final int hexSideLength = 70;
	public final double hexDiameter = 2 * (hexSideLength * Math.sqrt(3) / 2) + 20;
	public final double hexRadius = hexDiameter / 2;
	public final Point2D.Double startingPoint = new Point2D.Double(100, 100);
	public IntersectionPoint[] intersectionPoints = new IntersectionPoint[54];

	public IntersectionButtonManager manager = new IntersectionButtonManager();
	public HexButtonManager hexButtonManager = new HexButtonManager();

	private boolean firstTime = true;
	private BoardBuilder boardBuilder = new BoardBuilder();

	public GameBoard() {
		this.setLayout(null);
		initCustomBoard();
		setupIntersections();
	}

	public void setResources(int option){
		if(option == JOptionPane.YES_OPTION){
			placeManualHexes();
			setupIntersections();
		} else {
			placeRandomHexes();
			setupIntersections();
		}
		setupIntersectionButtons();
	}

	public void setupIntersections(){
		setIntersections();
		setRoads();
		setIntersectionCoords();
	}

	public void initCustomBoard(){
		for (int i = 0; i < 19; i++) {
			hexes[i] = new NoResourceHex(7);
			hexes[i].hasRobber = false;
		}
		hexes[0].hasRobber = true;
	}
	public void placeManualHexes(){
		int continueEditingBoard = JOptionPane.YES_OPTION;
		if(!GraphicsEnvironment.isHeadless()){
			JOptionPane.showMessageDialog(null, "Click a hex to set its resource and number", "Configure board", JOptionPane.INFORMATION_MESSAGE);
		}
		while(continueEditingBoard == JOptionPane.YES_OPTION){
			int selected = selectBoardHex();
			createHexAt(selected);
			if(!GraphicsEnvironment.isHeadless()){
				continueEditingBoard = JOptionPane.showConfirmDialog(null, "Would you like to continue setting resources?", "Continue?", JOptionPane.YES_NO_OPTION);
			} else {
				continueEditingBoard = JOptionPane.NO_OPTION;
			}
		}
		initManualRobberPosition();
	}

	private void createHexAt(int selected) {
		Hex createdHex = null;
		while(createdHex == null)
		{
			createdHex = boardBuilder.createHex();
		}
		hexes[selected] = createdHex;
	}

	public void initManualRobberPosition(){
		int selected = 0;
		if(!GraphicsEnvironment.isHeadless()){
			JOptionPane.showMessageDialog(null, "Click a hex to place the robber and finish board setup", "Place robber", JOptionPane.INFORMATION_MESSAGE);
			selected = selectBoardHex();
		}
		hexes[selected].hasRobber = true;
	}

	public int selectBoardHex() {
		this.enableHexButtons(true);
		int selected = -1;
		while (selected == -1) {
			try {
				if(!GraphicsEnvironment.isHeadless())
					selected = this.getSelectedHex();
				else
					selected = 0;
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		this.enableHexButtons(false);
		return selected;
	}

	private void setupIntersectionButtons(){
		for(int i = 0; i < intersectionPoints.length; i++){
			IntersectionPoint point = intersectionPoints[i];
			JButton intersectionButton = manager.createIntersectionButton(point, i);
			this.add(intersectionButton);
		}
	}

	public int getSelectedIntersection(){
		return manager.getSelectedIntersection();
	}

	public void enableIntersectionButtons(boolean setTo){
		manager.enableIntersectionButtons(setTo);
	}

	public void setupHexButtons(){
		for(int i = 0; i < hexes.length; i++){
			Point2D.Double hexCenter = hexes[i].center;
//			System.out.println(hexes[i].center);
//			System.out.println(hexCenter.x + " " + hexCenter.y);
			HexPoint point = new HexPoint(hexCenter);
			JButton hexButton = hexButtonManager.createHexButton(point, i);
			this.add(hexButton);
		}
	}

	public int getSelectedHex(){
		return hexButtonManager.getSelectedHex();
	}

	public void enableHexButtons(boolean setTo){
		hexButtonManager.enableHexButtons(setTo);
	}

	public void placeRandomHexes() {
		int[] nums = { 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12, 7 };
		int grain = 0, brick = 0, wool = 0, lumber = 0;
		for (int i = 0; i < 19; i++) {
			int index = 0;
			while (nums[index] == 0) {
				index++;
			}
			if (nums[index] == 7) {
				hexes[i] = new NoResourceHex(nums[index]);
				hexes[i].hasRobber = true;
			} else if (grain < 4) {
				hexes[i] = new GrainHex(nums[index]);
				grain++;
			} else if (wool < 4) {
				hexes[i] = new WoolHex(nums[index]);
				wool++;
			} else if (lumber < 4) {
				hexes[i] = new LumberHex(nums[index]);
				lumber++;
			} else if (brick < 3) {
				hexes[i] = new BrickHex(nums[index]);
				brick++;
			} else {
				hexes[i] = new OreHex(nums[index]);
			}
			nums[index] = 0;
		}
		mixHexes();
	}

	private void mixHexes() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 19; j++) {
				Hex temp = hexes[j];
				Random r = new Random();
				int ran = r.nextInt(19);
				hexes[j] = hexes[ran];
				hexes[ran] = temp;
			}
		}
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Point2D.Double current = startingPoint;

		GraphicsWithIndex g2 = new GraphicsWithIndex((Graphics2D) g, 0);
		for (int i = 0; i < hexes.length; i++) {
			g2.setNewPosition(i);
			drawHexNumberAtPosition(g2, current);
		}
		for (int i = 0; i < roads.length; i++) {
			g2.setNewPosition(i);
			drawRoadAtIndex(g2);
		}
		for (int i = 0; i < intersectionPoints.length; i++) {
			g2.setNewPosition(i);
			drawIntersectionAtIndex(g2);
		}
		if(firstTime){
			setupHexButtons();
			firstTime = false;
		}

	}

	public void drawIntersectionAtIndex(GraphicsWithIndex graphic) {
		int height = 20;
		int width = 20;
		int i = graphic.getPosition();
		Graphics2D g2 = graphic.getGraphics();
		g2.setColor(intersectionPoints[i].color);
		g2.fillOval((int) (intersectionPoints[i].point.getX() - 0.5 * width),
				(int) (intersectionPoints[i].point.getY() - 0.5 * height), height, width);
		intersections[i].drawStructureIcon(g2, intersectionPoints[i].point);
	}

	public void drawRoadAtIndex(GraphicsWithIndex graphic) {
		int i = graphic.getPosition();
		Graphics2D g2 = graphic.getGraphics();
		int int1 = roads[i].intersection1Num;
		int int2 = roads[i].intersection2Num;
		int x1 = (int) intersectionPoints[int1].point.getX();
		int y1 = (int) intersectionPoints[int1].point.getY();
		int x2 = (int) intersectionPoints[int2].point.getX();
		int y2 = (int) intersectionPoints[int2].point.getY();
		Color curColor = g2.getColor();
		g2.setColor(roads[i].color);
		g2.setStroke(new BasicStroke(10));
		g2.drawLine(x1, y1, x2, y2);
		g2.setColor(curColor);
	}

	private Point2D.Double calculateHexCenter(Point2D.Double current, int hexNum){
		if (hexNum < 3) {
			current.x = current.x + hexNum * hexDiameter + hexDiameter;
		} else if (hexNum < 7) {
			current.x = current.x + (hexNum - 3) * hexDiameter + 0.5 * hexDiameter - 1;
			current.y = current.y + 1.5 * hexSideLength + 1;
		} else if (hexNum < 12) {
			current.x = current.x + (hexNum - 7) * hexDiameter - 1;
			current.y = current.y + 3 * hexSideLength + 2;
		} else if (hexNum < 16) {
			current.x = current.x + (hexNum - 12) * hexDiameter + 0.5 * hexDiameter - 1;
			current.y = current.y + 4.5 * hexSideLength + 3;
		} else if (hexNum < 19) {
			current.x = current.x + (hexNum - 16) * hexDiameter + hexDiameter - 1;
			current.y = current.y + 6 * hexSideLength + 5;
		}
		return current;
	}

	public void drawHexNumberAtPosition(GraphicsWithIndex graphic, Point2D.Double current) {
		int i = graphic.getPosition();
		Graphics2D g2 = graphic.getGraphics();
		HexagonData currentHex;
		current = calculateHexCenter(current,i);
		currentHex = drawHex(hexes[i], g2, current);
		current.x = 100;
		current.y = 100;

		Color curColor = g2.getColor();
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(10));
		hexes[i].setCenter((int) (currentHex.getXCenter()), (int) (currentHex.getYCenter()));
		g2.drawString("" + hexes[i].getNumber(), (int) (currentHex.getXCenter()),
				(int) (currentHex.getYCenter()));
		g2.setColor(curColor);
	}

	public HexagonData drawHex(Hex hex, Graphics2D g2, Point2D.Double point) {
		HexagonData hexShape = new HexagonData(point);
		g2.setColor(hex.getColor());

		hexShape.drawHexShape(g2);
		hex.drawIcon(g2, point);

		return hexShape;
	}



	public void setIntersections() {
		intersections[0] = new Intersection(hexes[0]);

		intersections[1] = new Intersection(hexes[0]);
		intersections[2] = new Intersection(hexes[0], hexes[1]);
		intersections[3] = new Intersection(hexes[1]);
		intersections[4] = new Intersection(hexes[1], hexes[2]);
		intersections[5] = new Intersection(hexes[2]);
		intersections[6] = new Intersection(hexes[2]);
		intersections[7] = new Intersection(hexes[3]);
		intersections[8] = new Intersection(hexes[0], hexes[3]);
		intersections[9] = new Intersection(hexes[0], hexes[3], hexes[4]);
		intersections[10] = new Intersection(hexes[0], hexes[1], hexes[4]);
		intersections[11] = new Intersection(hexes[1], hexes[4], hexes[5]);
		intersections[12] = new Intersection(hexes[1], hexes[2], hexes[5]);
		intersections[13] = new Intersection(hexes[2], hexes[5], hexes[6]);
		intersections[14] = new Intersection(hexes[2], hexes[6]);
		intersections[15] = new Intersection(hexes[6]);
		intersections[16] = new Intersection(hexes[7]);
		intersections[17] = new Intersection(hexes[3], hexes[7]);
		intersections[18] = new Intersection(hexes[3], hexes[7], hexes[8]);
		intersections[19] = new Intersection(hexes[3], hexes[4], hexes[8]);
		intersections[20] = new Intersection(hexes[4], hexes[8], hexes[9]);
		intersections[21] = new Intersection(hexes[4], hexes[5], hexes[9]);
		intersections[22] = new Intersection(hexes[5], hexes[9], hexes[10]);
		intersections[23] = new Intersection(hexes[5], hexes[6], hexes[10]);
		intersections[24] = new Intersection(hexes[6], hexes[10], hexes[11]);
		intersections[25] = new Intersection(hexes[6], hexes[11]);
		intersections[26] = new Intersection(hexes[11]);
		intersections[27] = new Intersection(hexes[7]);
		intersections[28] = new Intersection(hexes[7], hexes[12]);
		intersections[29] = new Intersection(hexes[7], hexes[8], hexes[12]);
		intersections[30] = new Intersection(hexes[8], hexes[12], hexes[13]);
		intersections[31] = new Intersection(hexes[8], hexes[9], hexes[13]);
		intersections[32] = new Intersection(hexes[9], hexes[13], hexes[14]);
		intersections[33] = new Intersection(hexes[9], hexes[10], hexes[14]);
		intersections[34] = new Intersection(hexes[10], hexes[14], hexes[15]);
		intersections[35] = new Intersection(hexes[10], hexes[11], hexes[15]);
		intersections[36] = new Intersection(hexes[11], hexes[15]);
		intersections[37] = new Intersection(hexes[11]);
		intersections[38] = new Intersection(hexes[12]);
		intersections[39] = new Intersection(hexes[12], hexes[16]);
		intersections[40] = new Intersection(hexes[12], hexes[13], hexes[16]);
		intersections[41] = new Intersection(hexes[13], hexes[16], hexes[17]);
		intersections[42] = new Intersection(hexes[13], hexes[14], hexes[17]);
		intersections[43] = new Intersection(hexes[14], hexes[17], hexes[18]);
		intersections[44] = new Intersection(hexes[14], hexes[15], hexes[18]);
		intersections[45] = new Intersection(hexes[15], hexes[18]);
		intersections[46] = new Intersection(hexes[15]);
		intersections[47] = new Intersection(hexes[16]);
		intersections[48] = new Intersection(hexes[16]);
		intersections[49] = new Intersection(hexes[16], hexes[17]);
		intersections[50] = new Intersection(hexes[17]);
		intersections[51] = new Intersection(hexes[17], hexes[18]);
		intersections[52] = new Intersection(hexes[18]);
		intersections[53] = new Intersection(hexes[18]);
		setHexIntersections();
	}

	private void setHexIntersections() {
		hexes[0].setIntersections(intersections[0], intersections[1], intersections[2], intersections[8],
				intersections[9], intersections[10]);
		hexes[1].setIntersections(intersections[2], intersections[3], intersections[4], intersections[10],
				intersections[11], intersections[12]);
		hexes[2].setIntersections(intersections[4], intersections[5], intersections[6], intersections[12],
				intersections[13], intersections[14]);
		hexes[3].setIntersections(intersections[7], intersections[8], intersections[9], intersections[17],
				intersections[18], intersections[19]);
		hexes[4].setIntersections(intersections[9], intersections[10], intersections[11], intersections[19],
				intersections[20], intersections[21]);
		hexes[5].setIntersections(intersections[11], intersections[12], intersections[13], intersections[21],
				intersections[22], intersections[23]);
		hexes[6].setIntersections(intersections[13], intersections[14], intersections[15], intersections[23],
				intersections[24], intersections[25]);
		hexes[7].setIntersections(intersections[16], intersections[17], intersections[18], intersections[27],
				intersections[28], intersections[29]);
		hexes[8].setIntersections(intersections[18], intersections[19], intersections[20], intersections[29],
				intersections[30], intersections[31]);
		hexes[9].setIntersections(intersections[20], intersections[21], intersections[22], intersections[31],
				intersections[32], intersections[33]);
		hexes[10].setIntersections(intersections[22], intersections[23], intersections[24], intersections[33],
				intersections[34], intersections[35]);
		hexes[11].setIntersections(intersections[24], intersections[25], intersections[26], intersections[35],
				intersections[36], intersections[37]);
		hexes[12].setIntersections(intersections[28], intersections[29], intersections[30], intersections[38],
				intersections[39], intersections[40]);
		hexes[13].setIntersections(intersections[30], intersections[31], intersections[32], intersections[40],
				intersections[41], intersections[42]);
		hexes[14].setIntersections(intersections[32], intersections[33], intersections[34], intersections[42],
				intersections[43], intersections[44]);
		hexes[15].setIntersections(intersections[34], intersections[35], intersections[36], intersections[44],
				intersections[45], intersections[46]);
		hexes[16].setIntersections(intersections[39], intersections[40], intersections[41], intersections[47],
				intersections[48], intersections[49]);
		hexes[17].setIntersections(intersections[41], intersections[42], intersections[43], intersections[49],
				intersections[50], intersections[51]);
		hexes[18].setIntersections(intersections[43], intersections[44], intersections[45], intersections[51],
				intersections[52], intersections[53]);

	}

	public void setRoads() {
		roads[0] = new Road(0, 1, 0);
		roads[1] = new Road(1, 2, 1);
		roads[2] = new Road(2, 3, 2);
		roads[3] = new Road(3, 4, 3);
		roads[4] = new Road(4, 5, 4);
		roads[5] = new Road(5, 6, 5);
		roads[6] = new Road(0, 8, 6);
		roads[7] = new Road(2, 10, 7);
		roads[8] = new Road(4, 12, 8);
		roads[9] = new Road(6, 14, 9);
		roads[10] = new Road(7, 8, 10);
		roads[11] = new Road(8, 9, 11);
		roads[12] = new Road(9, 10, 12);
		roads[13] = new Road(10, 11, 13);
		roads[14] = new Road(11, 12, 14);
		roads[15] = new Road(12, 13, 15);
		roads[16] = new Road(13, 14, 16);
		roads[17] = new Road(14, 15, 17);
		roads[18] = new Road(7, 17, 18);
		roads[19] = new Road(9, 19, 19);
		roads[20] = new Road(11, 21, 20);
		roads[21] = new Road(13, 23, 21);
		roads[22] = new Road(15, 25, 22);
		roads[23] = new Road(16, 17, 23);
		roads[24] = new Road(17, 18, 24);
		roads[25] = new Road(18, 19, 25);
		roads[26] = new Road(19, 20, 26);
		roads[27] = new Road(20, 21, 27);
		roads[28] = new Road(21, 22, 28);
		roads[29] = new Road(22, 23, 29);
		roads[30] = new Road(23, 24, 30);
		roads[31] = new Road(24, 25, 31);
		roads[32] = new Road(25, 26, 32);
		roads[33] = new Road(16, 27, 33);
		roads[34] = new Road(18, 29, 34);
		roads[35] = new Road(20, 31, 35);
		roads[36] = new Road(22, 33, 36);
		roads[37] = new Road(24, 35, 37);
		roads[38] = new Road(26, 37, 38);
		roads[39] = new Road(27, 28, 39);
		roads[40] = new Road(28, 29, 40);
		roads[41] = new Road(29, 30, 41);
		roads[42] = new Road(30, 31, 42);
		roads[43] = new Road(31, 32, 43);
		roads[44] = new Road(32, 33, 44);
		roads[45] = new Road(33, 34, 45);
		roads[46] = new Road(34, 35, 46);
		roads[47] = new Road(35, 36, 47);
		roads[48] = new Road(36, 37, 48);
		roads[49] = new Road(28, 38, 49);
		roads[50] = new Road(30, 40, 50);
		roads[51] = new Road(32, 42, 51);
		roads[52] = new Road(34, 44, 52);
		roads[53] = new Road(36, 46, 53);
		roads[54] = new Road(38, 39, 54);
		roads[55] = new Road(39, 40, 55);
		roads[56] = new Road(40, 41, 56);
		roads[57] = new Road(41, 42, 57);
		roads[58] = new Road(42, 43, 58);
		roads[59] = new Road(43, 44, 59);
		roads[60] = new Road(44, 45, 60);
		roads[61] = new Road(45, 46, 61);
		roads[62] = new Road(39, 47, 62);
		roads[63] = new Road(41, 49, 63);
		roads[64] = new Road(43, 51, 64);
		roads[65] = new Road(45, 53, 65);
		roads[66] = new Road(47, 48, 66);
		roads[67] = new Road(48, 49, 67);
		roads[68] = new Road(49, 50, 68);
		roads[69] = new Road(50, 51, 69);
		roads[70] = new Road(51, 52, 70);
		roads[71] = new Road(52, 53, 71);
		connectRoads();
	}

	private void connectRoads() {
		intersections[0].setRoads(roads[0], roads[6]);
		intersections[1].setRoads(roads[0], roads[1]);
		intersections[2].setRoads(roads[1], roads[2], roads[7]);
		intersections[3].setRoads(roads[2], roads[3]);
		intersections[4].setRoads(roads[3], roads[4], roads[8]);
		intersections[5].setRoads(roads[4], roads[5]);
		intersections[6].setRoads(roads[5], roads[9]);
		intersections[7].setRoads(roads[10], roads[18]);
		intersections[8].setRoads(roads[6], roads[10], roads[11]);
		intersections[9].setRoads(roads[11], roads[12], roads[19]);
		intersections[10].setRoads(roads[7], roads[12], roads[13]);
		intersections[11].setRoads(roads[13], roads[14], roads[20]);
		intersections[12].setRoads(roads[8], roads[14], roads[15]);
		intersections[13].setRoads(roads[15], roads[16], roads[21]);
		intersections[14].setRoads(roads[9], roads[16], roads[17]);
		intersections[15].setRoads(roads[17], roads[22]);
		intersections[16].setRoads(roads[23], roads[33]);
		intersections[17].setRoads(roads[18], roads[23], roads[24]);
		intersections[18].setRoads(roads[24], roads[25], roads[34]);
		intersections[19].setRoads(roads[19], roads[25], roads[26]);
		intersections[20].setRoads(roads[26], roads[27], roads[35]);
		intersections[21].setRoads(roads[20], roads[27], roads[28]);
		intersections[22].setRoads(roads[28], roads[29], roads[36]);
		intersections[23].setRoads(roads[21], roads[29], roads[30]);
		intersections[24].setRoads(roads[30], roads[31], roads[37]);
		intersections[25].setRoads(roads[22], roads[31], roads[32]);
		intersections[26].setRoads(roads[32], roads[38]);
		intersections[27].setRoads(roads[33], roads[39]);
		intersections[28].setRoads(roads[39], roads[40], roads[49]);
		intersections[29].setRoads(roads[34], roads[40], roads[41]);
		intersections[30].setRoads(roads[41], roads[42], roads[50]);
		intersections[31].setRoads(roads[35], roads[42], roads[43]);
		intersections[32].setRoads(roads[43], roads[44], roads[51]);
		intersections[33].setRoads(roads[36], roads[44], roads[45]);
		intersections[34].setRoads(roads[45], roads[46], roads[52]);
		intersections[35].setRoads(roads[37], roads[46], roads[47]);
		intersections[36].setRoads(roads[47], roads[48], roads[53]);
		intersections[37].setRoads(roads[38], roads[48]);
		intersections[38].setRoads(roads[49], roads[54]);
		intersections[39].setRoads(roads[54], roads[55], roads[62]);
		intersections[40].setRoads(roads[50], roads[55], roads[56]);
		intersections[41].setRoads(roads[56], roads[57], roads[63]);
		intersections[42].setRoads(roads[51], roads[57], roads[58]);
		intersections[43].setRoads(roads[58], roads[59], roads[64]);
		intersections[44].setRoads(roads[52], roads[59], roads[60]);
		intersections[45].setRoads(roads[60], roads[61], roads[65]);
		intersections[46].setRoads(roads[53], roads[61]);
		intersections[47].setRoads(roads[62], roads[66]);
		intersections[48].setRoads(roads[66], roads[67]);
		intersections[49].setRoads(roads[63], roads[67], roads[68]);
		intersections[50].setRoads(roads[68], roads[69]);
		intersections[51].setRoads(roads[64], roads[69], roads[70]);
		intersections[52].setRoads(roads[70], roads[71]);
		intersections[53].setRoads(roads[65], roads[71]);

	}

	private void setIntersectionCoords() {
		double curX = startingPoint.x + hexRadius;
		double curY = startingPoint.y - 0.5 * hexSideLength;
		boolean standardHeight = true;
		int count = 0;
		IntersectionHelper instrHelp = new IntersectionHelper(count, curX, curY, standardHeight);
		instrHelp = setIntersectionCoordsHelper(instrHelp, 7);

		instrHelp.curX = startingPoint.x - instrHelp.count * hexRadius;
		instrHelp.curY = startingPoint.y + hexSideLength;
		instrHelp.standardHeight = !instrHelp.standardHeight;
		instrHelp = setIntersectionCoordsHelper(instrHelp, 16);

		instrHelp.curX = startingPoint.x - (instrHelp.count + 1) * hexRadius;
		instrHelp.curY = startingPoint.y + 2 * hexSideLength + hexRadius * 0.5;
		instrHelp.standardHeight = !instrHelp.standardHeight;
		instrHelp = setIntersectionCoordsHelper(instrHelp, 27);

		instrHelp.curX = startingPoint.x - (instrHelp.count + 1) * hexRadius;
		instrHelp.curY = startingPoint.y + 3 * hexSideLength + hexRadius;
		instrHelp = setIntersectionCoordsHelper(instrHelp, 38);

		instrHelp.curX = startingPoint.x - (instrHelp.count) * hexRadius;
		instrHelp.curY = startingPoint.y + 4 * hexSideLength + hexRadius * 1.5;
		instrHelp.standardHeight = !instrHelp.standardHeight;
		instrHelp = setIntersectionCoordsHelper(instrHelp, 47);

		instrHelp.curX = startingPoint.x - (instrHelp.count - 1) * hexRadius;
		instrHelp.curY = startingPoint.y + 5 * hexSideLength + hexRadius * 2;
		instrHelp.standardHeight = !instrHelp.standardHeight;
		instrHelp = setIntersectionCoordsHelper(instrHelp, 54);

	}

	public IntersectionHelper setIntersectionCoordsHelper(IntersectionHelper iH, int limit) {
		for (int i = iH.count; i < limit; i++) {
			if (iH.standardHeight == true) {
				intersectionPoints[i] = new IntersectionPoint(new Point2D.Double(iH.curX + i * hexRadius, iH.curY));
				iH.count++;
				iH.standardHeight = false;
			} else {
				intersectionPoints[i] = new IntersectionPoint(
						new Point2D.Double(iH.curX + i * hexRadius, iH.curY - hexRadius * 0.5));
				iH.count++;
				iH.standardHeight = true;
			}
		}
		return iH;
	}

	public int findRobberIndex() {
		for (int i = 0; i < 19; i++) {
			if (hexes[i].hasRobber) {
				return i;
			}
		}
		return -1;
	}

	public Road getRoadByIntersections(int i1, int i2) {
		if (i1 == i2 || i1 < 0 || i1 > 71 || i2 < 0 || i2 > 71) {
			return null;
		}
		for (int i = 0; i < 3; i++) {
			if (intersections[i1].roads[i] == null) {
				continue;
			} else if (intersections[i1].roads[i].isBetweenIntersections(i1, i2)) {
				return intersections[i1].roads[i];
			}
		}
		return null;
	}

	public void moveRobber(int newIndex) {
		int startIndex = findRobberIndex();
		hexes[startIndex].hasRobber = false;
		hexes[newIndex].hasRobber = true;
	}

	public ArrayList<Structure> getStructuresOnRolledHexes(int total) {
		ArrayList<Structure> structures = new ArrayList<Structure>();
		for (int i = 0; i < hexes.length; i++) {
			if (hexes[i].getNumber() == total) {
				for (int j = 0; j < 6; j++) {
					if (hexes[i].intersections[j].structure != null) {
						structures.add(hexes[i].intersections[j].structure);
					}
				}
			}
		}
		return structures;
	}

}
