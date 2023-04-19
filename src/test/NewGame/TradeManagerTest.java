import Team7.SettlersOfCatan.Player;
import Team7.SettlersOfCatan.ResourceCard;
import Team7.SettlersOfCatan.TradeManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.Arrays;

public class TradeManagerTest {

    public Player[] initializePlayers(){
        Player[] players = new Player[3];
        players[0] = new Player("Jerry", Color.BLACK);
        players[1] = new Player("Bill", Color.WHITE);
        players[2] = new Player("Sam", Color.RED);
        players[0].brickAmount = 3;
        players[1].oreAmount = 2;
        for(int i = 0; i < 3; i++){ players[0].addResourceCard(new ResourceCard("Brick")); }
        for(int i = 0; i < 2; i++){ players[1].addResourceCard(new ResourceCard("Ore")); }

        return players;
    }

    @Test
    public void testSetResources(){
        Player[] players = initializePlayers();
        TradeManager tM = new TradeManager(players, players[0]);

        tM.setResourceIn(new String[] {"0","1","2","0","0"});
        assertTrue(Arrays.equals(tM.resourceIn, new int[] {0,1,2,0,0}));

        tM.setResourceOut(new String[] {"0","1","2","0","5"});
        assertTrue(Arrays.equals(tM.resourceOut, new int[] {0,1,2,0,5}));
    }

    @Test
    public void testOutputFromTrader(){
        Player[] players = initializePlayers();
        TradeManager tM = new TradeManager(players, players[0]);

        // inTurn player, player[0], has [3,0,0,0,0] on initialization
        tM.setResourceOut(new String[] {"0","1","2","0","0"});
        assertFalse(tM.checkIfValidOutput());

        // inTurn player has the resources for offering the following in a trade
        tM.setResourceOut(new String[] {"2", "0", "0", "0", "0"});
        assertTrue(tM.checkIfValidOutput());
    }

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


}
