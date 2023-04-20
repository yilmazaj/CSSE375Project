import Domain.Game;
import Domain.ResourceCard;
import Domain.Robber;
import Domain.Structure;
import Presentation.GameBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobberTest {
    @Test
    void testInitializeRobber() {
        GameBoard board = new GameBoard();
        Assertions.assertTrue(board.hexes[board.findRobberIndex()].getResource().equals("None"));
    }

    @Test
    void testMoveRobber() {
        GameBoard board = new GameBoard();
        board.moveRobber(10);
        Assertions.assertEquals(board.findRobberIndex(), 10);
    }

    @Test
    void testActivateRobberWithSteal() {
        Game game = new Game(2);
        Robber r = new Robber();
        game.inTurn = game.players[1];
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Grain");
        ResourceCard c3 = new ResourceCard("Lumber");
        ResourceCard c4 = new ResourceCard("Wool");
        ResourceCard c5 = new ResourceCard("Ore");
        ResourceCard c6 = new ResourceCard("Ore");
        ResourceCard c7 = new ResourceCard("Ore");
        for(int i = 0; i<game.players.length;i++){
            game.players[i].addResourceCard(c1);
            game.players[i].addResourceCard(c2);
            game.players[i].addResourceCard(c3);
            game.players[i].addResourceCard(c4);
            game.players[i].addResourceCard(c5);
            game.players[i].addResourceCard(c6);
            game.players[i].addResourceCard(c7);
        }
        game.buildStructure("Settlement",0);
        game.inTurn = game.players[0];
        r.activateRobberWithInputs(game, 0, "1");
        Assertions.assertTrue(game.inTurn.resources.size() == 8);
        Assertions.assertTrue(game.players[1].resources.size() == 6); //7 initial - 1 stolen
    }

    @Test
    void testActivateRobberTooManyCards() {
        Game game = new Game(2);
        Robber r = new Robber();
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
        for(int i = 0; i<game.players.length;i++){
            game.players[i].addResourceCard(c1);
            game.players[i].addResourceCard(c2);
            game.players[i].addResourceCard(c3);
            game.players[i].addResourceCard(c4);
            game.players[i].addResourceCard(c5);
            game.players[i].addResourceCard(c6);
            game.players[i].addResourceCard(c7);
            game.players[i].addResourceCard(c8);
            game.players[i].addResourceCard(c9);
            game.players[i].addResourceCard(c10);
        }
        Assertions.assertTrue(game.inTurn.resources.size() > 7);
        r.activateRobberWithInputs(game, 18, "1");
        Assertions.assertTrue(game.inTurn.resources.size() == 7);
        Assertions.assertTrue(game.players[1].resources.size() == 7);
    }

    @Test
    void testGetStructureByHexInvalidHex() {
        Game game = new Game(2);
        Robber r = new Robber();
        Structure s = r.getStuctureByHex(game,30,5);
        Assertions.assertTrue(s== null);
    }

    @Test
    void testGetStructureByHexInvalidIntersection() {
        Game game = new Game(2);
        Robber r = new Robber();
        Structure s = r.getStuctureByHex(game,7,9);
        Assertions.assertTrue(s == null);
    }

    @Test
    void testGetStructureByHex() {
        Game game = new Game(2);
        Robber r = new Robber();
        game.inTurn = game.players[0];
        game.buildStructure("Settlement",0);
        Structure s = r.getStuctureByHex(game,0,0);
        Assertions.assertFalse(s == null);
        Assertions.assertTrue(s.equals(game.gameBuildingHandler.board.intersections[0].structure));
    }

    @Test
    public void testWaitForPlayerIntersectionChoice()
    {
        Robber r = new Robber();
        Game g = new Game();
        int result = r.waitForPlayerHexChoice(g, true);
        assertEquals(0, result);
    }
}
