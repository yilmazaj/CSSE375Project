import Domain.GameBuildingHandler;
import Domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
