import Domain.Player;
import Domain.ResourceCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import Domain.GameBuildingHandler;

import java.awt.*;

public class GameBuildingHandlerTest {
    private GameBuildingHandler gbh;
    Player testPlayer1;
    Player testPlayer2;
    @BeforeEach
    public void setup()
    {
        gbh = new GameBuildingHandler(true);
        testPlayer1 = new Player("TEST", Color.BLACK);
        testPlayer2 = new Player("TEST2", Color.WHITE);
    }

    public void givePlayerSettlementResources(Player p)
    {
        p.addResourceCard(new ResourceCard("Brick"));
        p.addResourceCard(new ResourceCard("Grain"));
        p.addResourceCard(new ResourceCard("Lumber"));
        p.addResourceCard(new ResourceCard("Wool"));
    }

    public void givePlayerCityResources(Player p)
    {
        p.addResourceCard(new ResourceCard("Ore"));
        p.addResourceCard(new ResourceCard("Ore"));
        p.addResourceCard(new ResourceCard("Ore"));
        p.addResourceCard(new ResourceCard("Grain"));
        p.addResourceCard(new ResourceCard("Grain"));
    }

    public void givePlayerRoadResources(Player p)
    {
        p.addResourceCard(new ResourceCard("Brick"));
        p.addResourceCard(new ResourceCard("Lumber"));
    }

    @Test
    public void testBuildRoadNoFriendlyStructure()
    {
        boolean result = gbh.buildRoad(1, 2, new Player("TEST", Color.BLACK));
        assertEquals(false, result);
    }
    @Test
    public void testBuildRoadNoPath()
    {
        boolean result = gbh.buildRoad(0, 2, new Player("TEST", Color.BLACK));
        assertEquals(false, result);
    }
    @Test
    public void testBuildRoadAlreadyPath()
    {
        givePlayerRoadResources(testPlayer1);
        givePlayerRoadResources(testPlayer2);
        givePlayerSettlementResources(testPlayer1);
        givePlayerSettlementResources(testPlayer2);
        gbh.buildStructure("Settlement", 0, testPlayer1);
        gbh.buildStructure("Settlement", 1, testPlayer2);
        gbh.buildRoad(0, 1, testPlayer1);
        boolean result = gbh.buildRoad(0, 1, testPlayer2);
        assertEquals(false, result);
    }
    @Test
    public void testBuildRoadNotEnoughResources()
    {
        givePlayerSettlementResources(testPlayer1);
        gbh.buildStructure("Settlement", 0, testPlayer1);
        boolean result = gbh.buildRoad(0, 1, testPlayer1);
        assertEquals(false, result);
    }
    @Test
    public void testBuildRoadValid()
    {
        givePlayerSettlementResources(testPlayer1);
        givePlayerRoadResources(testPlayer1);
        gbh.buildStructure("Settlement", 0, testPlayer1);
        boolean result = gbh.buildRoad(0, 1, testPlayer1);
        assertEquals(true, result);
    }
    @Test
    public void testBuildStructureSettlement()
    {
        givePlayerSettlementResources(testPlayer1);
        boolean result = gbh.buildStructure("Settlement", 0, testPlayer1);
        assertEquals(true, result);
    }
    @Test
    public void testBuildStructureCity()
    {
        givePlayerSettlementResources(testPlayer1);
        givePlayerCityResources(testPlayer1);
        gbh.buildStructure("Settlement", 0, testPlayer1);
        boolean result = gbh.buildStructure("City", 0, testPlayer1);
        assertEquals(true, result);
    }
    @Test
    public void testWaitForPlayerIntersectionChoice()
    {
        int result = gbh.waitForPlayerIntersectionChoice();
        assertEquals(0, result);
    }
}
