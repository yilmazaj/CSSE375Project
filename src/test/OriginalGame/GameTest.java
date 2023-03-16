//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import Team7.SettlersOfCatan.*;

import java.awt.Color;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {
    GameTest() {
    }

    @Test
    public void testPopulateColors() {
        Game game = new Game();
        Assertions.assertEquals(game.colors[0], Color.RED);
        Assertions.assertEquals(game.colors[1], Color.ORANGE);
        Assertions.assertEquals(game.colors[2], Color.BLUE);
        Assertions.assertEquals(game.colors[3], Color.GREEN);
        Assertions.assertEquals(game.colors[4], Color.BLACK);
        Assertions.assertEquals(game.colors[5], Color.WHITE);
    }

    @Test
    public void testPopulatePlayers() {
        Game game = new Game();
        int playerNum = game.playerNum;
        Color[] colors = new Color[]{Color.RED, Color.ORANGE, Color.BLUE, Color.GREEN, Color.BLACK, Color.WHITE};

        for(int i = 0; i < playerNum; ++i) {
            Assertions.assertEquals(game.players[i].color, colors[i]);
        }

    }

    @Test
    public void testBuildStructure() {
        Game game = new Game();
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Grain");
        ResourceCard c3 = new ResourceCard("Lumber");
        ResourceCard c4 = new ResourceCard("Wool");
        ResourceCard c5 = new ResourceCard("Ore");
        ResourceCard c6 = new ResourceCard("Ore");
        ResourceCard c7 = new ResourceCard("Ore");
        ResourceCard c8 = new ResourceCard("Grain");
        ResourceCard c9 = new ResourceCard("Grain");
        ResourceCard c10 = new ResourceCard("Ore");
        ResourceCard c11 = new ResourceCard("Ore");
        ResourceCard c12 = new ResourceCard("Ore");
        ResourceCard c13 = new ResourceCard("Grain");
        ResourceCard c14 = new ResourceCard("Grain");
        game.inTurn.addResourceCard(c1);
        game.inTurn.addResourceCard(c2);
        game.inTurn.addResourceCard(c3);
        game.inTurn.addResourceCard(c4);
        game.inTurn.addResourceCard(c5);
        game.inTurn.addResourceCard(c6);
        game.inTurn.addResourceCard(c7);
        game.inTurn.addResourceCard(c8);
        game.inTurn.addResourceCard(c9);
        game.inTurn.addResourceCard(c10);
        game.inTurn.addResourceCard(c11);
        game.inTurn.addResourceCard(c12);
        game.inTurn.addResourceCard(c13);
        game.inTurn.addResourceCard(c14);
        Assertions.assertTrue(game.buildStructure("Settlement", 0));
        Assertions.assertTrue(game.buildStructure("Settlement", 50));
        Assertions.assertFalse(game.buildStructure("Settlement", 0));
        Assertions.assertFalse(game.buildStructure("Settlement", -1));
        Assertions.assertFalse(game.buildStructure("Settlement", 60));
        Assertions.assertTrue(game.buildStructure("City", 0));
        Assertions.assertFalse(game.buildStructure("City", 10));
        Assertions.assertTrue(game.board.intersections[0].structure.color.equals(game.inTurn.color));
        Assertions.assertTrue(game.board.intersections[50].structure.color.equals(game.inTurn.color));
        Assertions.assertTrue(game.board.intersections[10].structure == null);
        Assertions.assertTrue(game.board.intersections[50].structure.getType().equals("Settlement"));
        Assertions.assertTrue(game.board.intersections[0].structure.getType().equals("City"));
    }

    @Test
    public void testRotateTurns() {
        Game game = new Game();

        for(int i = 0; i < game.playerNum; ++i) {
            Assertions.assertTrue(game.inTurn.equals(game.players[i]));
            game.rotateTurns();
            Assertions.assertFalse(game.inTurn.equals(game.players[i]));
        }

        Assertions.assertTrue(game.inTurn.equals(game.players[0]));
    }

    @Test
    public void testBuildRoad() {
        Game game = new Game();
        game.buildStructure("Settlement", 0);
        game.buildStructure("Settlement", 10);
        Assertions.assertTrue(game.buildRoad(0, 1));
        Assertions.assertFalse(game.buildRoad(0, 1));
        Assertions.assertFalse(game.buildRoad(0, 10));
        Assertions.assertTrue(game.buildRoad(10, 2));
        Assertions.assertFalse(game.buildRoad(0, 8));
        game.rotateTurns();
        game.buildStructure("Settlement", 7);
        game.buildStructure("Settlement", 19);
        Assertions.assertTrue(game.buildRoad(7, 8));
        Assertions.assertFalse(game.buildRoad(10, 11));
        Assertions.assertTrue(game.buildRoad(8, 0));
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Lumber");
        game.inTurn.addResourceCard(c1);
        game.inTurn.addResourceCard(c2);
        Assertions.assertTrue(game.buildRoad(8, 9));
        ResourceCard c3 = new ResourceCard("Brick");
        ResourceCard c4 = new ResourceCard("Lumber");
        game.inTurn.addResourceCard(c3);
        game.inTurn.addResourceCard(c4);
        Assertions.assertFalse(game.buildRoad(12, 13));
    }

    @Test
    void testBuildInitialStructures() {
        Game game = new Game();
        game.buildInitialStructures();

        for(int i = 0; i < game.playerNum; ++i) {
            Assertions.assertEquals(game.players[i].numSettlements, 2);
            Assertions.assertEquals(game.players[i].numRoads, 2);
        }

    }

    @Test
    void testRollForResources() {
        Game game = new Game();
        int numResources = game.inTurn.resources.size();

        int roll;
        for(roll = 0; roll < 19; ++roll) {
            ResourceCard c1 = new ResourceCard("Brick");
            ResourceCard c2 = new ResourceCard("Grain");
            ResourceCard c3 = new ResourceCard("Lumber");
            ResourceCard c4 = new ResourceCard("Wool");
            game.inTurn.addResourceCard(c1);
            game.inTurn.addResourceCard(c2);
            game.inTurn.addResourceCard(c3);
            game.inTurn.addResourceCard(c4);
            game.buildStructure("Settlement", roll);
            --game.inTurn.numSettlements;
        }

        roll = game.rollForResources();
        if (roll != 7) {
            Assertions.assertTrue(numResources < game.inTurn.resources.size());
        }

    }

    @Test
    void testActivateRobber() {
        Game game = new Game();
        game.buildInitialStructures();
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Grain");
        ResourceCard c3 = new ResourceCard("Lumber");
        ResourceCard c4 = new ResourceCard("Wool");
        ResourceCard c5 = new ResourceCard("Ore");
        ResourceCard c6 = new ResourceCard("Ore");
        ResourceCard c7 = new ResourceCard("Ore");
        ResourceCard c8 = new ResourceCard("Grain");
        ResourceCard c9 = new ResourceCard("Grain");
        ResourceCard c10 = new ResourceCard("Ore");
        game.inTurn.addResourceCard(c1);
        game.inTurn.addResourceCard(c2);
        game.inTurn.addResourceCard(c3);
        game.inTurn.addResourceCard(c4);
        game.inTurn.addResourceCard(c5);
        game.inTurn.addResourceCard(c6);
        game.inTurn.addResourceCard(c7);
        game.inTurn.addResourceCard(c8);
        game.inTurn.addResourceCard(c9);
        game.inTurn.addResourceCard(c10);
        int indexBefore = game.board.findRobberIndex();
        game.activateRobber();
        Assertions.assertTrue(game.inTurn.resources.size() < 9);
    }

    @Test
    void testTrade() {
        Game game = new Game();
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Grain");
        game.inTurn.resources = new ArrayList();
        game.inTurn.addResourceCard(c1);
        game.inTurn.addResourceCard(c2);
        ResourceCard c3 = new ResourceCard("Ore");
        ResourceCard c4 = new ResourceCard("Wool");
        ResourceCard c5 = new ResourceCard("Grain");
        game.players[1].resources = new ArrayList();
        game.players[1].addResourceCard(c3);
        game.players[1].addResourceCard(c4);
        game.players[1].addResourceCard(c5);
        Assertions.assertTrue(game.trade(game.players[1]));
        Assertions.assertEquals(game.inTurn.resources.size(), 3);
        Assertions.assertEquals(game.players[1].resources.size(), 2);
    }

    @Test
    void testCheckSpecialties() {
        Game game = new Game();
        game.buildInitialStructures();
        ResourceCard c1 = new ResourceCard("Ore");
        ResourceCard c2 = new ResourceCard("Wool");
        ResourceCard c3 = new ResourceCard("Grain");
        game.inTurn.addResourceCard(c1);
        game.inTurn.addResourceCard(c2);
        game.inTurn.addResourceCard(c3);
        ResourceCard c4 = new ResourceCard("Brick");
        ResourceCard c5 = new ResourceCard("Lumber");
        game.inTurn.addResourceCard(c4);
        game.inTurn.addResourceCard(c5);
        PlayableCard pc = new KnightCard();
        game.inTurn.pCards.add(pc);
        game.playCard();
        game.buildRoad(0, 8);
        game.checkSpecialties();
        Assertions.assertTrue(game.inTurn.hasKnightCard);
        Assertions.assertTrue(game.inTurn.hasRoadCard);
    }

    @Test
    void testBuyCard() {
        Game game = new Game();
        ResourceCard c1 = new ResourceCard("Ore");
        ResourceCard c2 = new ResourceCard("Wool");
        ResourceCard c3 = new ResourceCard("Grain");
        game.inTurn.addResourceCard(c1);
        game.inTurn.addResourceCard(c2);
        game.inTurn.addResourceCard(c3);
        Assertions.assertTrue(game.buyCard());
        Assertions.assertTrue(game.inTurn.nCards.size() > 0 || game.inTurn.pCards.size() > 0);
    }

    @Test
    void testPlayCard() {
        Game game = new Game();
        game.buildInitialStructures();
        PlayableCard knight = new KnightCard();
        game.inTurn.pCards.add(knight);
        Assertions.assertTrue(game.playCard());
        Assertions.assertEquals(game.inTurn.knightCount, 1);
        PlayableCard yearOfPlenty = new YearOfPlentyCard();
        PlayableCard roadBuildingCard = new RoadBuildingCard();
        PlayableCard monopolyCard = new MonopolyCard();
        game.inTurn.pCards.add(yearOfPlenty);
        int resourcesBefore = game.inTurn.resources.size();
        Assertions.assertTrue(game.playCard());
        Assertions.assertTrue(game.inTurn.resources.size() > resourcesBefore);
        game.inTurn.pCards.add(roadBuildingCard);
        int roadCount = game.inTurn.numRoads;
        Assertions.assertTrue(game.playCard());
        Assertions.assertTrue(game.inTurn.numRoads > roadCount);
        game.inTurn.pCards.add(monopolyCard);
        ResourceCard r1 = new ResourceCard("Grain");
        game.players[1].addResourceCard(r1);
        int enemyCount1 = game.players[1].resources.size();
        int count1 = game.inTurn.resources.size();
        Assertions.assertTrue(game.playCard());
        int enemyCount2 = game.players[1].resources.size();
        int count2 = game.inTurn.resources.size();
        Assertions.assertTrue(enemyCount1 > enemyCount2);
        Assertions.assertTrue(count1 < count2);
    }
}
