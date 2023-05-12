import Domain.Game;
import Presentation.ColorPickerGUI;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ColorPickerTest {

    @Test
    public void testColorExists(){
        ColorPickerGUI gui = new ColorPickerGUI(3);
        gui.colorArray[0] = Color.RED;
        assertEquals(true, gui.colorArrayContains(Color.RED));
    }

    @Test
    public void testColorValid(){
        ColorPickerGUI gui = new ColorPickerGUI(3);
        gui.colorArray[0] = Color.RED;
        assertEquals(false, gui.colorArrayContains(Color.BLUE));
    }

    @Test
    public void testArrayFull(){
        ColorPickerGUI g = new ColorPickerGUI(3);
        g.colorArray[0] = Color.RED;
        g.colorArray[1] = Color.BLUE;
        g.colorArray[2] = Color.cyan;
        assertTrue(g.arrayFull());
    }
}
