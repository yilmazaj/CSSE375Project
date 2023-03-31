package NewGame;

import Team7.SettlersOfCatan.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

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