import Team7.SettlersOfCatan.Player;
import Team7.SettlersOfCatan.TradeManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class TradeTest {

//    @Test
//    public void testInvalidFromTradee(){
//        TradeManager tradeManager = new TradeManager();
//
//        Player[] players = new Player[3];
//        players[0] = new Player("Jerry", Color.BLACK);
//        players[1] = new Player("Bill", Color.WHITE);
//        players[2] = new Player("Sam", Color.RED);
//        players[0].brickAmount = 3;
//        players[1].oreAmount = 2;
//
//        int[] outgoing = {1,0,0,0,0};
//        int[] demanded = {0,0,0,1,0};
//
//        tradeManager.tradeStageTest(players[0], players, "Bill", outgoing, demanded, true);
//
//        assertEquals("Bill doesn't own all those resources", tradeManager.testOutput);
//        assertEquals(3, players[0].brickAmount);
//        assertEquals(0, players[0].oreAmount);
//        assertEquals(0, players[1].brickAmount);
//        assertEquals(2, players[1].oreAmount);
//    }
//
//    @Test
//    public void testInvalidFromTrader(){
//        TradeManager tradeManager = new TradeManager();
//
//        Player[] players = new Player[3];
//        players[0] = new Player("Jerry", Color.BLACK);
//        players[1] = new Player("Bill", Color.WHITE);
//        players[2] = new Player("Sam", Color.RED);
//        players[0].brickAmount = 3;
//        players[1].oreAmount = 2;
//
//        int[] outgoing = {0,1,0,0,0};
//        int[] demanded = {0,0,0,0,1};
//
//        tradeManager.tradeStageTest(players[0], players, "Bill", outgoing, demanded, true);
//
//        assertEquals("You don't own all those resources", tradeManager.testOutput);
//        assertEquals(3, players[0].brickAmount);
//        assertEquals(0, players[0].oreAmount);
//        assertEquals(0, players[1].brickAmount);
//        assertEquals(2, players[1].oreAmount);
//    }
//
//    @Test
//    public void testValidRejectedTrade(){
//        TradeManager tradeManager = new TradeManager();
//
//        Player[] players = new Player[3];
//        players[0] = new Player("Jerry", Color.BLACK);
//        players[1] = new Player("Bill", Color.WHITE);
//        players[2] = new Player("Sam", Color.RED);
//        players[0].brickAmount = 3;
//        players[1].oreAmount = 2;
//
//        int[] outgoing = {1,0,0,0,0};
//        int[] demanded = {0,0,0,0,1};
//
//        tradeManager.tradeStageTest(players[0], players, "Bill", outgoing, demanded, false);
//
//        assertEquals("Rejected", tradeManager.testOutput);
//        assertEquals(3, players[0].brickAmount);
//        assertEquals(0, players[0].oreAmount);
//        assertEquals(0, players[1].brickAmount);
//        assertEquals(2, players[1].oreAmount);
//    }
//
//    @Test
//    public void testValidAcceptedTrade(){
//
//        TradeManager tradeManager = new TradeManager();
//
//        Player[] players = new Player[3];
//        players[0] = new Player("Jerry", Color.BLACK);
//        players[1] = new Player("Bill", Color.WHITE);
//        players[2] = new Player("Sam", Color.RED);
//        players[0].brickAmount = 3;
//        players[1].oreAmount = 2;
//
//        int[] outgoing = {1,0,0,0,0};
//        int[] demanded = {0,0,0,0,1};
//
//        tradeManager.tradeStageTest(players[0], players, "Bill", outgoing, demanded, true);
//
//        assertEquals("Accepted", tradeManager.testOutput);
//        assertEquals(1, players[0].oreAmount);
//        assertEquals(1, players[1].brickAmount);
//    }

}
