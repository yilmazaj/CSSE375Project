//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NonPlayableCardTest {
    NonPlayableCardTest() {
    }

    @Test
    void testGetPointValueResource() {
        ResourceType type = ResourceType.Lumber;
        NonPlayableCard nPCard1 = new ResourceCard(type.name());
        Assertions.assertEquals(nPCard1.getPointValue(), 0);
    }

    @Test
    void testGetPointValueVPCard() {
        NonPlayableCard nPCard1 = new VictoryPointCard();
        Assertions.assertEquals(nPCard1.getPointValue(), 1);
    }

    @Test
    void testGetPointValueSpecialty() {
        NonPlayableCard nPCard1 = new LargestArmy();
        NonPlayableCard nPCard2 = new MostRoads();
        Assertions.assertEquals(nPCard1.getPointValue(), 2);
        Assertions.assertEquals(nPCard2.getPointValue(), 2);
    }
}
