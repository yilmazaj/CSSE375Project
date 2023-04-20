package NewGame;

import Domain.Structure;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructureTest {

    @Test
    public void structureIconTest(){
        Structure settlement = new Structure(Color.RED, "Settlement");
        Structure city = new Structure(Color.BLUE, "City");
        assertEquals(settlement.getIcon().toString(), "images/Icons/SettlementIcon.png");
        assertEquals(settlement.getIcon().getIconWidth(), 20);
        assertEquals(settlement.getIcon().getIconHeight(), 20);
        assertEquals(city.getIcon().toString(), "images/Icons/CityIcon.png");
        assertEquals(city.getIcon().getIconWidth(), 20);
        assertEquals(city.getIcon().getIconHeight(), 20);
    }

    @Test
    public void structureCollapsedHierarchyTest(){
        Structure settlement = new Structure(Color.RED, "Settlement");
        Structure city = new Structure(Color.BLACK, "City");
        assertEquals("Settlement", settlement.getType());
        assertEquals("City", city.getType());
    }

}