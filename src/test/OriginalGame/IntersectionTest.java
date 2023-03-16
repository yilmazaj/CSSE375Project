//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import java.awt.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntersectionTest {
    IntersectionTest() {
    }

    @Test
    void testContainsRoad() {
        Road r1 = new Road(0, 1, 0);
        Road r2 = new Road(0, 7, 1);
        r1.color = Color.RED;
        Hex h = new Hex(0);
        Intersection i = new Intersection(h);
        i.setRoads(r1, r2);
        Assertions.assertTrue(i.containsRoad(Color.RED));
        Assertions.assertFalse(i.containsRoad(Color.BLUE));
    }
}
