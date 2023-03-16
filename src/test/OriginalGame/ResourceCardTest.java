//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResourceCardTest {
    ResourceCardTest() {
    }

    @Test
    void testGetResourceType() {
        ResourceType lumber = ResourceType.Lumber;
        ResourceType brick = ResourceType.Brick;
        ResourceType grain = ResourceType.Grain;
        ResourceType wool = ResourceType.Wool;
        ResourceType ore = ResourceType.Ore;
        ResourceCard card1 = new ResourceCard(lumber.name());
        ResourceCard card2 = new ResourceCard(brick.name());
        ResourceCard card3 = new ResourceCard(grain.name());
        ResourceCard card4 = new ResourceCard(wool.name());
        ResourceCard card5 = new ResourceCard(ore.name());
        Assertions.assertEquals(card1.getType(), "Lumber");
        Assertions.assertEquals(card2.getType(), "Brick");
        Assertions.assertEquals(card3.getType(), "Grain");
        Assertions.assertEquals(card4.getType(), "Wool");
        Assertions.assertEquals(card5.getType(), "Ore");
    }
}
