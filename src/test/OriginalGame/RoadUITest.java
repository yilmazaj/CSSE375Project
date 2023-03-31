import Team7.SettlersOfCatan.Game;
import Team7.SettlersOfCatan.GameBoard;
import Team7.SettlersOfCatan.ResourceCard;
import Team7.SettlersOfCatan.Structure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoadUITest {
    @Test
    void testBuildRoadUI() {
        Game g = new Game(2);
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Grain");
        ResourceCard c3 = new ResourceCard("Lumber");
        ResourceCard c4 = new ResourceCard("Wool");
        for(int i = 0; i < 5; i++) {
            g.buildStructure("Settlement", i);
            g.players[0].addResourceCard(c1);
            g.players[0].addResourceCard(c2);
            g.players[0].addResourceCard(c3);
            g.players[0].addResourceCard(c4);
        }
        g.buildRoadsUI();
        Assertions.assertTrue(g.players[0].numRoads == 2);
    }
}
