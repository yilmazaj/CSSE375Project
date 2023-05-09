package Domain;

import Presentation.CardGUI;
import Presentation.CurrentTurnGUI;
import Presentation.PlayersStatsGUI;
import Presentation.TradeManagerGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {

	public GameBuildingHandler gameBuildingHandler = new GameBuildingHandler(false);
	public int playerNum;
	public JPanel[] playerPanels;
	public JFrame gameFrame;
	public Color[] colors;
	public int MAX_PLAYERS = 6;
	public Player[] players;
	public Player inTurn;
	public int inTurnIndex;
	public int maxKnights;
	public int maxRoads;
	public SpecialtyCard mostRoads;
	public SpecialtyCard largestArmy;

	public Dice dice;

	public PlayersStatsGUI playersStats;

	public Robber robber;

	public TradeManagerGUI tradeManagerGUI;

	public Game () {
		dice = new Dice(2);
		playerNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of players", "2"));
		while(playerNum < 2 || playerNum > 4) {
			playerNum = Integer.parseInt(JOptionPane.showInputDialog(null, "There must be between 2 and 4 players", "2"));
		}
		playerPanels = new JPanel[playerNum];



		gameFrame = new JFrame();
		gameFrame.setSize(new Dimension(800, 700));
		gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);

		gameFrame.add(gameBuildingHandler.board);

		
		colors = new Color[MAX_PLAYERS];
		players = new Player[playerNum];
		maxKnights = 0;
		maxRoads = 2;
		mostRoads = new SpecialtyCard("MostRoads");
		largestArmy = new SpecialtyCard("LargestArmy");
		robber = new Robber();

		populateColors();
		populatePlayers();
		inTurn = players[0];
		inTurnIndex = 0;
		inTurn.isActive = true;
		giveInitialResources();

		playersStats = new PlayersStatsGUI(players);

	}

	//Constructor for Unit Tests
	public Game (int playerNum) {
		this.playerNum = playerNum;
		dice = new Dice(2);

		colors = new Color[MAX_PLAYERS];
		players = new Player[playerNum];
		maxKnights = 0;
		maxRoads = 2;
		mostRoads = new SpecialtyCard("MostRoads");
		largestArmy = new SpecialtyCard("LargestArmy");

		populateColors();

		for(int i = 0; i < playerNum; i++){
			players[i] = new Player(String.valueOf(i), colors[i]);
		}
//		populatePlayers();
		inTurn = players[0];
		inTurnIndex = 0;
		inTurn.isActive = true;


		giveInitialResources();

		playersStats = new PlayersStatsGUI(players);

	}

	private void giveInitialResources(){
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
		return gameBuildingHandler.buildRoad(i1, i2, inTurn);
	}
	
	public boolean buildStructure(String type, int intersection) {
		return gameBuildingHandler.buildStructure(type, intersection, inTurn);
	}

	public void buildRoadsUI(){
		gameBuildingHandler.buildRoadsUI(inTurn);
	}
	
	public void buildInitialStructures() {
		for(int i = 0; i < playerNum; i++) {
			JOptionPane.showMessageDialog(null, "Place two starting settlements", inTurn.name + "'s turn!", JOptionPane.INFORMATION_MESSAGE);
			int i1 = gameBuildingHandler.waitForPlayerIntersectionChoice();
			while(!gameBuildingHandler.buildStructure("Settlement", i1, inTurn)) {
				i1 = gameBuildingHandler.waitForPlayerIntersectionChoice();
			}
			int i2 = gameBuildingHandler.waitForPlayerIntersectionChoice();
			while(!gameBuildingHandler.buildStructure("Settlement", i2, inTurn)) {
				i2 = gameBuildingHandler.waitForPlayerIntersectionChoice();
			}
			gameBuildingHandler.buildRoadsUI(inTurn);
			inTurn.clearResources();
			rotateTurns();
		}
	}

	public int handleDiceRoll() {
		int total = dice.getTotal();
		//robber scenario
		if(total == 7) {
			robber.activateRobber(this);
		}
		else {
			giveResourcesFromRoll(total);
		}
		playersStats.updatePlayersStats();
		return total;
	}

	public void giveResourcesFromRoll(int total){
		ArrayList<Structure> structures;
		structures = gameBuildingHandler.getStructuresOnRolledHexes(total);
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
				return;
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

	String getPlayerNameByColor(Color c) {
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
				Thread.sleep(10);
				handlePlayerAction(turnGUI);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}



	private void handlePlayerAction(CurrentTurnGUI turnGUI){
		if(turnGUI.doTradeAction()){
			tradeManagerGUI = new TradeManagerGUI(players, inTurn);
		}
		if(turnGUI.doBuildAction()){
			if(inTurn.resources.size() != 0){
				JOptionPane.showMessageDialog(null, "Use your resources to build structures and roads", "Build stage", JOptionPane.INFORMATION_MESSAGE);
				gameBuildingHandler.buildStage(inTurn);
			} else {
				JOptionPane.showMessageDialog(null, "You have no resources to build.");
			}
		}
		if(turnGUI.doCardAction()){
			JOptionPane.showMessageDialog(null, "Buy and play cards", "Card stage", JOptionPane.INFORMATION_MESSAGE);
			CardGUI cardGUI = new CardGUI(inTurn);
//			buyCard();
//			playCard();
			inTurn.printResources();
		}
		playersStats.updatePlayersStats();
	}

	public void handlePlayerTurn(CurrentTurnGUI turnGUI){
		turnGUI.updateUIForNewPlayer(inTurn.name);
		dice.waitForPlayerDiceRoll();
		handleDiceRoll();
		waitForPlayerToEndTurn(turnGUI);
		checkSpecialties();
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
			if(pc.getType().equals("Domain.KnightCard")) {
				robber.activateRobber(this);
				inTurn.knightCount++;
			}
			else if(pc.getType().equals("Domain.MonopolyCard")) {
				String resource = JOptionPane.showInputDialog(null, "Enter the resource type you wish to acquire: Brick, Grain, Wool, Lumber, or Ore", "");
				for(int i = 0; i < playerNum; i++) {
					if(!players[i].name.equals(inTurn.name)) {
						ResourceCard r = new ResourceCard(resource);
						players[i].removeResourceCard(resource);
						inTurn.addResourceCard(r);
					}
				}
			}
			else if(pc.getType().equals("Domain.RoadBuildingCard")) {
				ResourceCard r1 = new ResourceCard("Brick");
				ResourceCard r2 = new ResourceCard("Lumber");
				ResourceCard r3 = new ResourceCard("Brick");
				ResourceCard r4 = new ResourceCard("Lumber");
				inTurn.addResourceCard(r1);
				inTurn.addResourceCard(r2);
				inTurn.addResourceCard(r3);
				inTurn.addResourceCard(r4);
				gameBuildingHandler.buildRoadsUI(inTurn);
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
