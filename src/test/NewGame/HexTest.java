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

public class HexTest {

    @Test
    public void hexIconTest(){
        Hex nrhex = new NoResourceHex(-1);
        Hex whex = new WoolHex(-1);
        Hex ghex = new GrainHex(-1);
        Hex ohex = new OreHex(-1);
        Hex bhex = new BrickHex(-1);
        Hex lhex = new LumberHex(-1);
        assertEquals(nrhex.getIcon().toString(), "images/Icons/NoneIcon.png");
        assertEquals(nrhex.getIcon().getIconWidth(), 20);
        assertEquals(nrhex.getIcon().getIconHeight(), 20);
        assertEquals(whex.getIcon().toString(), "images/Icons/WoolIcon.png");
        assertEquals(whex.getIcon().getIconWidth(), 20);
        assertEquals(whex.getIcon().getIconHeight(), 20);
        assertEquals(ghex.getIcon().toString(), "images/Icons/GrainIcon.png");
        assertEquals(ghex.getIcon().getIconWidth(), 20);
        assertEquals(ghex.getIcon().getIconHeight(), 20);
        assertEquals(ohex.getIcon().toString(), "images/Icons/OreIcon.png");
        assertEquals(ohex.getIcon().getIconWidth(), 20);
        assertEquals(ohex.getIcon().getIconHeight(), 20);
        assertEquals(bhex.getIcon().toString(), "images/Icons/BrickIcon.png");
        assertEquals(bhex.getIcon().getIconWidth(), 20);
        assertEquals(bhex.getIcon().getIconHeight(), 20);
        assertEquals(lhex.getIcon().toString(), "images/Icons/LumberIcon.png");
        assertEquals(lhex.getIcon().getIconWidth(), 20);
        assertEquals(lhex.getIcon().getIconHeight(), 20);
    }

    @Test
    public void getColorTest(){
        Hex hex = new Hex(-1) {
            @Override
            public String getResource() {
                return null;
            }
        };
        Hex nrhex = new NoResourceHex(-1);
        Hex whex = new WoolHex(-1);
        Hex ghex = new GrainHex(-1);
        Hex ohex = new OreHex(-1);
        Hex bhex = new BrickHex(-1);
        Hex lhex = new LumberHex(-1);
        assertEquals(hex.getColor(), Color.BLACK);
        assertEquals(nrhex.getColor(), new Color(255, 255, 240));
        assertEquals(whex.getColor(), new Color(136, 239, 167));
        assertEquals(ghex.getColor(), new Color(250, 233, 144));
        assertEquals(ohex.getColor(), new Color(169, 165, 169));
        assertEquals(bhex.getColor(), new Color(189, 79, 66));
        assertEquals(lhex.getColor(), new Color(57, 98, 48));
    }

    @Test
    public void getTypeTest(){
        Hex nrhex = new NoResourceHex(-1);
        Hex whex = new WoolHex(-1);
        Hex ghex = new GrainHex(-1);
        Hex ohex = new OreHex(-1);
        Hex bhex = new BrickHex(-1);
        Hex lhex = new LumberHex(-1);
        assertEquals(nrhex.getResource(), "None");
        assertEquals(whex.getResource(), "Wool");
        assertEquals(ghex.getResource(), "Grain");
        assertEquals(ohex.getResource(), "Ore");
        assertEquals(bhex.getResource(), "Brick");
        assertEquals(lhex.getResource(), "Lumber");
    }
}