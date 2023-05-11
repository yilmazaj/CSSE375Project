import Domain.BoardBuilder;
import Domain.Hex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardBuilderTest {
    BoardBuilder boardBuilder;
    @BeforeEach
    public void setup(){
        boardBuilder = new BoardBuilder();
    }

    @Test
    public void checkCreateHexWithNullResourceReturnsNull(){
        assertEquals(boardBuilder.createHexOfType(null), null);
    }
    @Test
    public void checkCreateHexWithNoneResourceReturnsNoResourceHex(){
        Hex returnedHex = boardBuilder.createHexOfType("none");
        assertEquals(returnedHex.getResource(), "None");
        assertEquals(returnedHex.getNumber(), 7);
    }
    @Test
    public void checkCreateGrainHex(){
        Hex returnedHex = boardBuilder.createHexOfTypeAndNumber("grain", 1);
        assertEquals(returnedHex.getResource(), "Grain");
        assertEquals(returnedHex.getNumber(), 1);
    }
    @Test
    public void checkCreateWoolHex(){
        Hex returnedHex = boardBuilder.createHexOfTypeAndNumber("wool", 1);
        assertEquals(returnedHex.getResource(), "Wool");
        assertEquals(returnedHex.getNumber(), 1);
    }
    @Test
    public void checkCreateLumberHex(){
        Hex returnedHex = boardBuilder.createHexOfTypeAndNumber("lumber", 1);
        assertEquals(returnedHex.getResource(), "Lumber");
        assertEquals(returnedHex.getNumber(), 1);
    }
    @Test
    public void checkCreateBrickHex(){
        Hex returnedHex = boardBuilder.createHexOfTypeAndNumber("brick", 1);
        assertEquals(returnedHex.getResource(), "Brick");
        assertEquals(returnedHex.getNumber(), 1);
    }
    @Test
    public void checkCreateOreHex(){
        Hex returnedHex = boardBuilder.createHexOfTypeAndNumber("ore", 1);
        assertEquals(returnedHex.getResource(), "Ore");
        assertEquals(returnedHex.getNumber(), 1);
    }
    @Test
    public void checkCreateHexWithZeroNumberReturnsNull(){
        Hex returnedHex = boardBuilder.createHexOfTypeAndNumber("grain", 0);
        assertEquals(returnedHex, null);
    }
    @Test
    public void checkCreateHexUnknownResourceReturnsNull(){
        Hex returnedHex = boardBuilder.createHexOfTypeAndNumber("qwerty", 1);
        assertEquals(returnedHex, null);
    }
}
