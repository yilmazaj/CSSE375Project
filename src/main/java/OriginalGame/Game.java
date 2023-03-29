package Team7.SettlersOfCatan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JFrame {
	
	public int playerNum;
	public JPanel[] playerPanels;
	public GameBoard board;
	public JFrame gameFrame;
	public Color[] colors;
	public int MAX_PLAYERS = 6;
	public Player[] players;
	public Player inTurn;
	public int inTurnIndex;
	public int maxKnights;
	public int maxRoads;
	public MostRoads mostRoads;
	public LargestArmy largestArmy;

	public Dice dice;
	
	public Game () {
		dice = new Dice(2);
		playerNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of players", "2"));
		while(playerNum < 2 || playerNum > 4) {
			playerNum = Integer.parseInt(JOptionPane.showInputDialog(null, "There must be between 2 and 4 players", "2"));
		}
		playerPanels = new JPanel[playerNum];
		
		board = new GameBoard();

		gameFrame = new JFrame();
		gameFrame.setSize(new Dimension(1000, 800));
		setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.add(board, BorderLayout.CENTER);
		
		colors = new Color[MAX_PLAYERS];
		players = new Player[playerNum];
		maxKnights = 0;
		maxRoads = 2;
		mostRoads = new MostRoads();
		largestArmy = new LargestArmy();
		
		populateColors();
		populatePlayers();
		inTurn = players[0];
		inTurnIndex = 0;
		inTurn.isActive = true;
		for(int i = 0; i < playerNum; i++) {
			ResourceCard c1 = new ResourceCard("Brick");
			ResourceCard c2 = new ResourceCard("Grain");
			ResourceCard c3 = new ResourceCard("Lumber");
			ResourceCard c4 = new ResourceCard("Wool");
			ResourceCard c5 = new ResourceCard("Brick");
			ResourceCard c6 = new ResourceCard("Grain");
			ResourceCard c7 = new ResourceCard("Lumber");
			ResourceCard c8 = new ResourceCard("Wool");
			ResourceCard c9 = new ResourceCard("Brick");
			ResourceCard c10 = new ResourceCard("Brick");
			ResourceCard c11 = new ResourceCard("Lumber");
			ResourceCard c12 = new ResourceCard("Lumber");
			players[i].addResourceCard(c1);
			players[i].addResourceCard(c2);
			players[i].addResourceCard(c3);
			players[i].addResourceCard(c4);
			players[i].addResourceCard(c5);
			players[i].addResourceCard(c6);
			players[i].addResourceCard(c7);
			players[i].addResourceCard(c8);
			players[i].addResourceCard(c9);
			players[i].addResourceCard(c10);
			players[i].addResourceCard(c11);
			players[i].addResourceCard(c12);
		}
		
	}
	
	public void rotateTurns() {
		inTurn.isActive = false;
		if(inTurnIndex == playerNum - 1) {
			inTurnIndex = 0;
		}
		else {
			inTurnIndex++;
		}
		inTurn = players[inTurnIndex];
	}
	
	public boolean buildRoad(int i1, int i2) {
		Road temp = board.getRoadByIntersections(i1, i2);
		ArrayList<ResourceCard> requiredResources = new ArrayList<ResourceCard>();
		ResourceCard c1 = new ResourceCard("Brick");
		ResourceCard c2 = new ResourceCard("Lumber");
		requiredResources.add(c1);
		requiredResources.add(c2);
		if(temp == null) {
			JOptionPane.showMessageDialog(null, "There is no path between those two intersections", "Failed to build road", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(temp.color != null) {
			JOptionPane.showMessageDialog(null, "There is already a road between those two intersections", "Failed to build road", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if((board.intersections[i1].structure != null && board.intersections[i1].structure.color.equals(inTurn.color)) 
				|| (board.intersections[i2].structure != null && board.intersections[i2].structure.color.equals(inTurn.color))) {
			if(inTurn.containsAllResources(requiredResources)) {
				board.roads[temp.indexNum].activateRoad(inTurn.color);
				board.intersections[i1].updateRoad(i1, i2, board.roads[temp.indexNum]);
				board.intersections[i2].updateRoad(i2, i1, board.roads[temp.indexNum]);
				inTurn.numRoads++;
				inTurn.removeAllResources(requiredResources);
				// AJ CODE
				board.invalidate();
				board.roads[temp.indexNum].color = inTurn.color;
				board.repaint();
				// AJ CODE
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Player does not have enough resources to build a road", "Failed to build road", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if(board.intersections[i1].structure == null || board.intersections[i2].structure == null) {
			if(board.intersections[i1].containsRoad(inTurn.color) || board.intersections[i2].containsRoad(inTurn.color)) {
				if(inTurn.containsAllResources(requiredResources)) {
					board.roads[temp.indexNum].activateRoad(inTurn.color);
					board.intersections[i1].updateRoad(i1, i2, board.roads[temp.indexNum]);
					board.intersections[i2].updateRoad(i2, i1, board.roads[temp.indexNum]);
					inTurn.numRoads++;
					inTurn.removeAllResources(requiredResources);
					// AJ CODE
					board.invalidate();
					board.roads[temp.indexNum].color = inTurn.color;
					board.repaint();
					// AJ CODE
					return true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Player does not have enough resources to build a road", "Failed to build road", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "New road connects to no friendly structures or roads", "Failed to build road", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		else if(!board.intersections[i1].structure.color.equals(inTurn.color) && !board.intersections[i1].structure.color.equals(inTurn.color)) {
			JOptionPane.showMessageDialog(null, "Can't place a road between two enemy structures", "Failed to build road", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return false;
		
	}
	
	public boolean buildStructure(String type, int intersection) {
		if(intersection < 0 || intersection > 53) {
			JOptionPane.showMessageDialog(null, "Invalid intersection number entered", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		Structure s = null;
		ArrayList<ResourceCard> requiredResources = new ArrayList<ResourceCard>();
		if(type.equals("Settlement") || type.equals("settlement")) {
			if(board.intersections[intersection].structure == null) {
				if(inTurn.numSettlements < 5) {
					ResourceCard c1 = new ResourceCard("Brick");
					ResourceCard c2 = new ResourceCard("Grain");
					ResourceCard c3 = new ResourceCard("Lumber");
					ResourceCard c4 = new ResourceCard("Wool");
					requiredResources.add(c1);
					requiredResources.add(c2);
					requiredResources.add(c3);
					requiredResources.add(c4);
					if(inTurn.containsAllResources(requiredResources)) {
						s = new Settlement(inTurn.color);
						inTurn.numSettlements++;
						board.intersections[intersection].structure = s;
						s.hexes = board.intersections[intersection].hexes;
						inTurn.removeAllResources(requiredResources);
						// AJ CODE
						board.invalidate();
						board.intersectionPoints[intersection].color = inTurn.color;
						board.repaint();
						// AJ CODE
						return true;
					}
					else {
						JOptionPane.showMessageDialog(null, "Player does not have the resources required to build a settlement", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Player can not place any more settlements", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Intersection already has a structure", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		else if (type.equals("City") || type.equals("city")) {
			if(board.intersections[intersection].structure != null && 
					board.intersections[intersection].structure.color.equals(inTurn.color) && 
					board.intersections[intersection].structure.getType().equals("Settlement")) {
				if(inTurn.numCities < 4) {
					ResourceCard c1 = new ResourceCard("Ore");
					ResourceCard c2 = new ResourceCard("Ore");
					ResourceCard c3 = new ResourceCard("Ore");
					ResourceCard c4 = new ResourceCard("Grain");
					ResourceCard c5 = new ResourceCard("Grain");
					requiredResources.add(c1);
					requiredResources.add(c2);
					requiredResources.add(c3);
					requiredResources.add(c4);
					requiredResources.add(c5);
					if(inTurn.containsAllResources(requiredResources)) {
						s = new City(inTurn.color);
						inTurn.numCities++;
						board.intersections[intersection].structure = s;
						s.hexes = board.intersections[intersection].hexes;
						inTurn.removeAllResources(requiredResources);
						return true;
					}
					else {
						JOptionPane.showMessageDialog(null, "Player does not have the resources required to build a city", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Player can not place any more cities", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Player must already have a settlement at this intersection", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid Structure name entered", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public void buildInitialStructures() {
		for(int i = 0; i < playerNum; i++) {
			JOptionPane.showMessageDialog(null, "Place your initial structures", inTurn.name + "'s turn!", JOptionPane.INFORMATION_MESSAGE);
			int i1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the intersection to place your first settlement", ""));
			while(!buildStructure("Settlement", i1)) {
				i1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter a valid intersection", ""));
			}
			int i2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the intersection to place your second settlement", ""));
			while(!buildStructure("Settlement", i2)) {
				i2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter a valid intersection", ""));
			}
			int road1Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the first intersection of your first road", ""));
			int road1Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the second intersection of your first road", ""));
			while(!buildRoad(road1Int1, road1Int2)) {
				JOptionPane.showMessageDialog(null, "Please enter a valid set of intersections for your road", "Invalid road", JOptionPane.ERROR_MESSAGE);
				road1Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "First intersection", ""));
				road1Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Second intersection", ""));
			}
			int road2Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the first intersection of your second road", ""));
			int road2Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the second intersection of your second road", ""));
			while(!buildRoad(road2Int1, road2Int2)) {
				JOptionPane.showMessageDialog(null, "Please enter a valid set of intersections for your road", "Invalid road", JOptionPane.ERROR_MESSAGE);
				road2Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "First intersection", ""));
				road2Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Second intersection", ""));
			}
			rotateTurns();
		}
	}
	
	public int handleDiceRoll() {
		int total = dice.getTotal();
		//robber scenario
		if(total == 7) {
			activateRobber();
		}
		else {
			giveResourcesFromRoll(total);
		}
		return total;
	}

	private void giveResourcesFromRoll(int total){
		ArrayList<Structure> structures;
		structures = board.getStructuresOnRolledHexes(total);
		//may not work if structure is on both rolled hexes
		for(int i = 0; i < structures.size(); i++) {
			String resource = "None";
			for(int j = 0; j < 3; j++) {
				if(structures.get(i).hexes[j].getNumber() == total) {
					if(structures.get(i).hexes[j].hasRobber) {
						break;
					}
					resource = structures.get(i).hexes[j].getResource();
					break;
				}
			}
			if(resource.equals("None")) {
				continue;
			}
			Player currentPlayer = getPlayerOfColor(structures.get(i).color);
			if(structures.get(i).getType().equals("Settlement")) {
				ResourceCard c1 = new ResourceCard(resource);
				currentPlayer.addResourceCard(c1);
			}
			else {
				ResourceCard c1 = new ResourceCard(resource);
				currentPlayer.addResourceCard(c1);
				ResourceCard c2 = new ResourceCard(resource);
				currentPlayer.addResourceCard(c2);
			}
		}
	}
	
	public void activateRobber() {
		for(int i = 0; i < playerNum; i++) {
			while(players[i].resources.size() > 7) {
				players[i].removeRandomCard();
			}
		}
		int moveRobber = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose a hex to move the robber to", ""));
		while(moveRobber < 0 || moveRobber > 18) {
			moveRobber = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter a valid hex number", ""));
		}
		board.moveRobber(moveRobber);
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0; i < 6; i++) {
			Structure s = board.hexes[moveRobber].intersections[i].structure;
			if(s != null) {
				if(!s.color.equals(inTurn.color)) {
					names.add(getPlayerNameByColor(s.color));
				}
			}
		}
		if(names.isEmpty()) {
			return;
		}
		String choose = "Pick one of the following players to steal a random resource from: ";
		for(int i = 0; i < names.size(); i++) {
			choose = choose + names.get(i) + ", ";
		}
		Player stealFrom = null;
		while(stealFrom == null) {
			String stealName = JOptionPane.showInputDialog(null, choose, "");
			for(int i = 0; i < playerNum; i++) {
				if(players[i].name.equals(stealName)) {
					stealFrom = players[i];
					break;
				}
			}
		}
		inTurn.addResourceCard(stealFrom.removeRandomCard());
		
	}

	private String getPlayerNameByColor(Color c) {
		for(int i = 0; i < playerNum; i++) {
			if(players[i].color.equals(c)) {
				return players[i].name;
			}
		}
		return null;
	}
	
	private Player getPlayerOfColor(Color c) {
		for(int i = 0; i < this.playerNum; i++) {
			if(players[i].color.equals(c)) {
				return players[i];
			}
		}
		return null;
	}

	private void waitForPlayerToEndTurn(CurrentTurnGUI turnGUI){
		while(!turnGUI.isTurnOver()){
			try {
				Thread.sleep(1);
				handlePlayerAction(turnGUI);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void handlePlayerAction(CurrentTurnGUI turnGUI){
		if(turnGUI.doTradeAction()){
			JOptionPane.showMessageDialog(null, "Request trades with other players", "Trade stage", JOptionPane.INFORMATION_MESSAGE);
			tradeStage();
		}
		if(turnGUI.doBuildAction()){
			JOptionPane.showMessageDialog(null, "Use your resources to build structures and roads", "Build stage", JOptionPane.INFORMATION_MESSAGE);
			buildStage();
		}
		if(turnGUI.doCardAction()){
			JOptionPane.showMessageDialog(null, "Buy and play cards", "Card stage", JOptionPane.INFORMATION_MESSAGE);
			buyCard();
			playCard();
			inTurn.printResources();
		}
	}
	
	public void waitForPlayerDiceRoll(){
		while(!dice.hasPlayerMadeRoll()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void handlePlayerTurn(CurrentTurnGUI turnGUI){
		turnGUI.updateUIForNewPlayer(inTurn.name);
		waitForPlayerDiceRoll();
		handleDiceRoll();
		waitForPlayerToEndTurn(turnGUI);
		checkSpecialties();
	}


	
	public static void main(String[] args) {
		System.out.println("The Start of Catan.");
		Game game = new Game();
		game.buildInitialStructures();
		CurrentTurnGUI turnGUI = new CurrentTurnGUI("Default",game.dice);

		while(game.inTurn.victoryPoints < 10) {
			game.handlePlayerTurn(turnGUI);
			if(game.inTurn.victoryPoints >= 10) {
				break;
			}
			game.rotateTurns();
		}
		JOptionPane.showMessageDialog(null, game.inTurn.name + " has reached 10 victory points and won the game!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void checkSpecialties() {
		if(inTurn.knightCount > this.maxKnights) {
			if(!inTurn.hasKnightCard) {
				maxKnights = inTurn.knightCount;
				for(int i = 0; i < playerNum; i++) {
					if(players[i].hasKnightCard) {
						players[i].hasKnightCard = false;
						players[i].removeNonPlayableCard(this.largestArmy);
					}
				}
				inTurn.hasKnightCard = true;
				inTurn.addNonPlayableCard(this.largestArmy);
				JOptionPane.showMessageDialog(null, inTurn.name + " now has the largest army card", "Largest Army", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if(inTurn.numRoads > this.maxRoads) {
			if(!inTurn.hasRoadCard) {
				maxRoads = inTurn.numRoads;
				for(int i = 0; i < playerNum; i++) {
					if(players[i].hasRoadCard) {
						players[i].hasRoadCard = false;
						players[i].removeNonPlayableCard(this.mostRoads);
					}
				}
				inTurn.hasRoadCard = true;
				inTurn.addNonPlayableCard(this.mostRoads);
				JOptionPane.showMessageDialog(null, inTurn.name + " now has the most roads card", "Most Roads", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}

	public boolean playCard() {
		int answer = JOptionPane.showConfirmDialog(null, "Would you like to play a development card?", "Play a card?", JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION) {
			if(inTurn.pCards.isEmpty()) {
				JOptionPane.showMessageDialog(null, "You don't own any playable cards", "No available cards", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			JOptionPane.showMessageDialog(null, "Enter the number of the card you want to play in the next window", "Choose a card", JOptionPane.INFORMATION_MESSAGE);
			int cardIndex = Integer.parseInt(JOptionPane.showInputDialog(null, inTurn.displayPlayableCards(), ""));
			PlayableCard pc = inTurn.pCards.get(cardIndex);
			if(pc.getType().equals("KnightCard")) {
				activateRobber();
				inTurn.knightCount++;
			}
			else if(pc.getType().equals("MonopolyCard")) {
				String resource = JOptionPane.showInputDialog(null, "Enter the resource type you wish to acquire: Brick, Grain, Wool, Lumber, or Ore", "");
				for(int i = 0; i < playerNum; i++) {
					if(!players[i].name.equals(inTurn.name)) {
						ResourceCard r = new ResourceCard(resource);
						players[i].removeResourceCard(resource);
						inTurn.addResourceCard(r);
					}
				}
			}
			else if(pc.getType().equals("RoadBuildingCard")) {
				ResourceCard r1 = new ResourceCard("Brick");
				ResourceCard r2 = new ResourceCard("Lumber");
				ResourceCard r3 = new ResourceCard("Brick");
				ResourceCard r4 = new ResourceCard("Lumber");
				inTurn.addResourceCard(r1);
				inTurn.addResourceCard(r2);
				inTurn.addResourceCard(r3);
				inTurn.addResourceCard(r4);
				int road1Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the first intersection of your first road", ""));
				int road1Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the second intersection of your first road", ""));
				while(!buildRoad(road1Int1, road1Int2)) {
					JOptionPane.showMessageDialog(null, "Please enter a valid set of intersections for your road", "Invalid road", JOptionPane.ERROR_MESSAGE);
					road1Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "First intersection", ""));
					road1Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Second intersection", ""));
				}
				int road2Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the first intersection of your second road", ""));
				int road2Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the second intersection of your second road", ""));
				while(!buildRoad(road2Int1, road2Int2)) {
					JOptionPane.showMessageDialog(null, "Please enter a valid set of intersections for your road", "Invalid road", JOptionPane.ERROR_MESSAGE);
					road2Int1 = Integer.parseInt(JOptionPane.showInputDialog(null, "First intersection", ""));
					road2Int2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Second intersection", ""));
				}
			}
			else {
				String resource1 = JOptionPane.showInputDialog(null, "Enter the first resource type you wish to acquire: Brick, Grain, Wool, Lumber, or Ore", "");
				String resource2 = JOptionPane.showInputDialog(null, "Enter the second resource type you wish to acquire: Brick, Grain, Wool, Lumber, or Ore", "");
				ResourceCard r1 = new ResourceCard(resource1);
				ResourceCard r2 = new ResourceCard(resource2);
				inTurn.addResourceCard(r1);
				inTurn.addResourceCard(r2);
			}
			inTurn.pCards.remove(cardIndex);
			return true;
		}
		return false;
	}

	public boolean buyCard() {
		int answer = JOptionPane.showConfirmDialog(null, "Would you like to buy a development card?", "Buy a card?", JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION) {
			ResourceCard c1 = new ResourceCard("Ore");
			ResourceCard c2 = new ResourceCard("Wool");
			ResourceCard c3 = new ResourceCard("Grain");
			ArrayList<ResourceCard> cards = new ArrayList<ResourceCard>();
			cards.add(c1);
			cards.add(c2);
			cards.add(c3);
			if(inTurn.containsAllResources(cards)) {
				Random r = new Random();
				double bound = r.nextDouble();
				NonPlayableCard nc = null;
				PlayableCard pc = null;
				if(bound <= .45) {
					nc = new VictoryPointCard();
					inTurn.addNonPlayableCard(nc);
				}
				else if(bound > .45 && bound <= .8) {
					pc = new KnightCard();
					inTurn.pCards.add(pc);
				}
				else {
					int progCard = r.nextInt(3);
					if(progCard == 0) {
						pc = new MonopolyCard();
						inTurn.pCards.add(pc);
					}
					else if(progCard == 1) {
						pc = new RoadBuildingCard();
						inTurn.pCards.add(pc);
					}
					else {
						pc = new YearOfPlentyCard();
						inTurn.pCards.add(pc);
					}
				}
				if(nc == null) {
					JOptionPane.showMessageDialog(null, "You have received a " + pc.getType() + " card", "Development card purchased", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "You have received a " + nc.getType() + " card", "Development card purchased", JOptionPane.INFORMATION_MESSAGE);
				}
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "You don't have enough resources to buy a development card", "Not enough resources", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return false;
		
	}

	private void buildStage() {
		int answer = JOptionPane.showConfirmDialog(null, "Would you like to build?", "Build?", JOptionPane.YES_NO_OPTION);
		while(answer == JOptionPane.YES_OPTION) {
			String s = JOptionPane.showInputDialog(null, "Structure or Road?", "");
			if(s.equals("Structure")) {
				String type = JOptionPane.showInputDialog(null, "Settlement or City?", "");
				int i = Integer.parseInt(JOptionPane.showInputDialog(null, "What intersection should this be placed at?", ""));
				this.buildStructure(type, i);
			}
			else if(s.equals("Road")) {
				int i1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the first intersection of the road.", ""));
				int i2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the second intersection of the road.", ""));
				this.buildRoad(i1, i2);
			}
			else {
				JOptionPane.showMessageDialog(null, "You must input either Structure or Road", "Invalid input!", JOptionPane.ERROR_MESSAGE);
			}
			answer = JOptionPane.showConfirmDialog(null, "Would you like to build more?", "Build?", JOptionPane.YES_NO_OPTION);
		}
	}

	private void tradeStage() {
		int answer = JOptionPane.showConfirmDialog(null, "Would you like to trade?", "Trade?", JOptionPane.YES_NO_OPTION);
		while(answer == JOptionPane.YES_OPTION) {
			String name = JOptionPane.showInputDialog(null, "Who do you want to trade with?", "");
			Player tradeWith = getPlayerByName(name);
			while(tradeWith == null || tradeWith.name.equals(inTurn.name)) {
				name = JOptionPane.showInputDialog(null, "Please enter the name of another player", "");
				tradeWith = getPlayerByName(name);
			}
			trade(tradeWith);
			
			answer = JOptionPane.showConfirmDialog(null, "Would you like to trade more?", "Trade?", JOptionPane.YES_NO_OPTION);
		}
		
	}
	
	public boolean trade(Player tradeWith) {
		int numBrick = 0;
		int numGrain = 0;
		int numWool = 0;
		int numOre = 0;
		int numLumber = 0;
		ArrayList<ResourceCard> resourcesOut = new ArrayList<ResourceCard>();
		ArrayList<ResourceCard> resourcesIn = new ArrayList<ResourceCard>();
		JOptionPane.showMessageDialog(null, "Enter how much of each resource you want to trade away.", "Trade Away", JOptionPane.INFORMATION_MESSAGE);
		numBrick = Integer.parseInt(JOptionPane.showInputDialog(null, "How many brick?", ""));
		numGrain = Integer.parseInt(JOptionPane.showInputDialog(null, "How many grain?", ""));
		numWool = Integer.parseInt(JOptionPane.showInputDialog(null, "How many wool?", ""));
		numOre = Integer.parseInt(JOptionPane.showInputDialog(null, "How many ore?", ""));
		numLumber = Integer.parseInt(JOptionPane.showInputDialog(null, "How many lumber?", ""));
		for(int i = 0; i < numBrick; i++) {
			ResourceCard r = new ResourceCard("Brick");
			resourcesOut.add(r);
		}
		for(int i = 0; i < numGrain; i++) {
			ResourceCard r = new ResourceCard("Grain");
			resourcesOut.add(r);
		}
		for(int i = 0; i < numWool; i++) {
			ResourceCard r = new ResourceCard("Wool");
			resourcesOut.add(r);
		}
		for(int i = 0; i < numOre; i++) {
			ResourceCard r = new ResourceCard("Ore");
			resourcesOut.add(r);
		}
		for(int i = 0; i < numLumber; i++) {
			ResourceCard r = new ResourceCard("Lumber");
			resourcesOut.add(r);
		}
		if(!inTurn.containsAllResources(resourcesOut)) {
			JOptionPane.showMessageDialog(null, "You don't own all those resources", "Invalid input!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null, "Enter how much of each resource you want to trade for.", "Trade Away", JOptionPane.INFORMATION_MESSAGE);
		numBrick = Integer.parseInt(JOptionPane.showInputDialog(null, "How many brick?", ""));
		numGrain = Integer.parseInt(JOptionPane.showInputDialog(null, "How many grain?", ""));
		numWool = Integer.parseInt(JOptionPane.showInputDialog(null, "How many wool?", ""));
		numOre = Integer.parseInt(JOptionPane.showInputDialog(null, "How many ore?", ""));
		numLumber = Integer.parseInt(JOptionPane.showInputDialog(null, "How many lumber?", ""));
		for(int i = 0; i < numBrick; i++) {
			ResourceCard r = new ResourceCard("Brick");
			resourcesIn.add(r);
		}
		for(int i = 0; i < numGrain; i++) {
			ResourceCard r = new ResourceCard("Grain");
			resourcesIn.add(r);
		}
		for(int i = 0; i < numWool; i++) {
			ResourceCard r = new ResourceCard("Wool");
			resourcesIn.add(r);
		}
		for(int i = 0; i < numOre; i++) {
			ResourceCard r = new ResourceCard("Ore");
			resourcesIn.add(r);
		}
		for(int i = 0; i < numLumber; i++) {
			ResourceCard r = new ResourceCard("Lumber");
			resourcesIn.add(r);
		}
		if(!tradeWith.containsAllResources(resourcesIn)) {
			JOptionPane.showMessageDialog(null, tradeWith.name + " doesn't own all those resources", "Invalid input!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		JOptionPane.showMessageDialog(null, "Please hand over the controls to " + tradeWith.name , "Valid trade request", JOptionPane.INFORMATION_MESSAGE);
		int answer = JOptionPane.showConfirmDialog(null, "Do you accept the trade?", tradeWith.name, JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, tradeWith.name + " accepted your trade request", "Trade accepted!", JOptionPane.INFORMATION_MESSAGE);
			for(int i = 0; i < resourcesIn.size(); i++) {
				inTurn.addResourceCard(resourcesIn.get(i));
			}
			inTurn.removeAllResources(resourcesOut);
			for(int i = 0; i < resourcesOut.size(); i++) {
				tradeWith.addResourceCard(resourcesOut.get(i));
			}
			tradeWith.removeAllResources(resourcesIn);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, tradeWith.name + " declined your trade request", "Trade declined!", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	private Player getPlayerByName(String name) {
		for(int i = 0; i < playerNum; i++) {
			if(players[i] == null) {
				return null;
			}
			if(players[i].name.equals(name)) {
				return players[i];
			}
		}
		return null;
	}

	public void populateColors() {
		colors[0] = Color.RED;
		colors[1] = Color.ORANGE;
		colors[2] = Color.BLUE;
		colors[3] = Color.GREEN;
		colors[4] = Color.BLACK;
		colors[5] = Color.WHITE;
	}
	
	public void populatePlayers() {
		for(int i = 0; i < playerNum; i++) {
			String curName = JOptionPane.showInputDialog(null, "Enter player name", "Name");
			while(getPlayerByName(curName) != null) {
				JOptionPane.showMessageDialog(null, "That name is already taken", "Invalid input!", JOptionPane.ERROR_MESSAGE);
				curName = JOptionPane.showInputDialog(null, "Enter player name", "");
			}
			playerPanels[i] = new JPanel();
			playerPanels[i].setSize(new Dimension(50, 20));
			playerPanels[i].setBackground(colors[i]);
			playerPanels[i].setName(curName);
			
			players[i] = new Player(new PlayerInfo(colors[i], curName, i));
		}
	}
	
	class PlayerInfo {
		Color playerColor;
		String playerName;
		int playerInd;
		
		public PlayerInfo(Color pColor, String pName, int pInd) {
			this.playerColor = pColor;
			this.playerName = pName;
			this.playerInd = pInd;
		}
	}

}
