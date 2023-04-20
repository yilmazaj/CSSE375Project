import Domain.Player;
import Domain.ResourceCard;
import Domain.TradeManager;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TradeManagerTest {

    public Player[] initializePlayers(){
        Player[] players = new Player[2];
        players[0] = new Player("Jerry", Color.BLACK);
        players[1] = new Player("Bill", Color.WHITE);
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

    @Test
    public void testInputFromTradees(){
        Player[] players = initializePlayers();
        TradeManager tM = new TradeManager(players, players[0]);

        // one player has no resources, other has [0,0,0,0,2] on initialization
        tM.resourceIn = new int[] {0,0,0,0,3};
        ArrayList<Integer> testArr = tM.checkIfValidTrade();
        assertEquals(0, testArr.size());

        // should now be a single player w/ the demanded resources
        tM.setResourceIn(new String[] {"0", "0", "0", "0", "1"});
        testArr = tM.checkIfValidTrade();
        assertEquals(1, testArr.size());
    }

    @Test
    public void testSuccessfulTrade(){
        Player[] players = initializePlayers();
        TradeManager tM = new TradeManager(players, players[0]);

        tM.resourceOut = new int[] {1,0,0,0,0};
        tM.resourceIn = new int[] {0,0,0,0,1};

        // players[0] is inTurn / trader, players[1] is tradee
        tM.handleTrade(players[1].name);

        int[] traderNewAmounts = new int[] {players[0].brickAmount, players[0].grainAmount,
                                            players[0].lumberAmount,players[0].woolAmount,
                                            players[0].oreAmount};

        int[] tradeeNewAmounts = new int[] {players[1].brickAmount, players[1].grainAmount,
                                            players[1].lumberAmount,players[1].woolAmount,
                                            players[1].oreAmount};

        assertTrue(Arrays.equals(new int[] {2,0,0,0,1}, traderNewAmounts));
        assertTrue(Arrays.equals(new int[] {1,0,0,0,1}, tradeeNewAmounts));
    }

}
