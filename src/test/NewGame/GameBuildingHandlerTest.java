import Team7.SettlersOfCatan.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import Team7.SettlersOfCatan.GameBuildingHandler;

import java.awt.*;

public class GameBuildingHandlerTest {
    private GameBuildingHandler gbh;
    @BeforeEach
    public void setup()
    {
        gbh = new GameBuildingHandler(true);
    }

    @Test
    public void testBuildRoadNoStructure()
    {
        boolean result = gbh.buildRoad(1, 2, new Player("TEST", Color.BLACK));
    }
}
