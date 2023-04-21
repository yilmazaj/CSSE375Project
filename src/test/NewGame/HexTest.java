import Domain.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexTest {

    @Test
    public void hexIconTestNoResource(){
        Hex nrhex = new NoResourceHex(-1);
        assertEquals(nrhex.getIcon().toString(), "images/Icons/NoneIcon.png");
        assertEquals(nrhex.getIcon().getIconWidth(), 20);
        assertEquals(nrhex.getIcon().getIconHeight(), 20);
    }
    @Test
    public void hexIconTestWool(){
        Hex whex = new WoolHex(-1);
        assertEquals(whex.getIcon().toString(), "images/Icons/WoolIcon.png");
        assertEquals(whex.getIcon().getIconWidth(), 20);
        assertEquals(whex.getIcon().getIconHeight(), 20);
    }
    @Test
    public void hexIconTestGrain(){
        Hex ghex = new GrainHex(-1);
        assertEquals(ghex.getIcon().toString(), "images/Icons/GrainIcon.png");
        assertEquals(ghex.getIcon().getIconWidth(), 20);
        assertEquals(ghex.getIcon().getIconHeight(), 20);
    }
    @Test
    public void hexIconTestOre(){
        Hex ohex = new OreHex(-1);
        assertEquals(ohex.getIcon().toString(), "images/Icons/OreIcon.png");
        assertEquals(ohex.getIcon().getIconWidth(), 20);
        assertEquals(ohex.getIcon().getIconHeight(), 20);
    }
    @Test
    public void hexIconTestBrick(){
        Hex bhex = new BrickHex(-1);
        assertEquals(bhex.getIcon().toString(), "images/Icons/BrickIcon.png");
        assertEquals(bhex.getIcon().getIconWidth(), 20);
        assertEquals(bhex.getIcon().getIconHeight(), 20);
    }
    @Test
    public void hexIconTestLumber(){
        Hex lhex = new LumberHex(-1);
        assertEquals(lhex.getIcon().toString(), "images/Icons/LumberIcon.png");
        assertEquals(lhex.getIcon().getIconWidth(), 20);
        assertEquals(lhex.getIcon().getIconHeight(), 20);
    }

    @Test
    public void getColorTestDefault(){
        Hex hex = new Hex(-1) {
            @Override
            public String getResource() {
                return null;
            }
        };
        assertEquals(hex.getColor(), Color.BLACK);
    }
    @Test
    public void getColorTestNoResource(){
        Hex nrhex = new NoResourceHex(-1);
        assertEquals(nrhex.getColor(), new Color(255, 255, 240));
    }
    @Test
    public void getColorTestWool(){
        Hex whex = new WoolHex(-1);
        assertEquals(whex.getColor(), new Color(136, 239, 167));
    }
    @Test
    public void getColorTestGrain(){
        Hex ghex = new GrainHex(-1);
        assertEquals(ghex.getColor(), new Color(250, 233, 144));
    }
    @Test
    public void getColorTestOre(){
        Hex ohex = new OreHex(-1);
        assertEquals(ohex.getColor(), new Color(169, 165, 169));
    }
    @Test
    public void getColorTestBrick(){
        Hex bhex = new BrickHex(-1);
        assertEquals(bhex.getColor(), new Color(189, 79, 66));
    }
    @Test
    public void getColorTestLumber(){
        Hex lhex = new LumberHex(-1);
        assertEquals(lhex.getColor(), new Color(57, 98, 48));
    }

    @Test
    public void getTypeTestNoResource(){
        Hex nrhex = new NoResourceHex(-1);
        assertEquals(nrhex.getResource(), "None");
    }
    @Test
    public void getTypeTestWool(){
        Hex whex = new WoolHex(-1);
        assertEquals(whex.getResource(), "Wool");
    }
    @Test
    public void getTypeTestGrain(){
        Hex ghex = new GrainHex(-1);
        assertEquals(ghex.getResource(), "Grain");
    }
    @Test
    public void getTypeTestOre(){
        Hex ohex = new OreHex(-1);
        assertEquals(ohex.getResource(), "Ore");
    }
    @Test
    public void getTypeTestBrick(){
        Hex bhex = new BrickHex(-1);
        assertEquals(bhex.getResource(), "Brick");
    }
    @Test
    public void getTypeTestLumber(){
        Hex lhex = new LumberHex(-1);
        assertEquals(lhex.getResource(), "Lumber");
    }

}