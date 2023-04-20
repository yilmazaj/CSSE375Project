import Domain.SpecialtyCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpecialtyCardTest {

    @Test
    public void specialtyCardsCollapsedHierarchyTest(){
        SpecialtyCard mostRoads = new SpecialtyCard("MostRoads");
        SpecialtyCard largestArmy = new SpecialtyCard("LargestArmy");

        assertEquals("MostRoads", mostRoads.getType());
        assertEquals("LargestArmy", largestArmy.getType());

        assertTrue(2 == mostRoads.getPointValue() && 2 == largestArmy.getPointValue());
    }

}
