import Team7.SettlersOfCatan.CurrentTurnGUI;
import Team7.SettlersOfCatan.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentTurnGUITest{

    @Test
    public void validTurnEndTest1(){
        Dice dice = new Dice(2);
        CurrentTurnGUI gui = new CurrentTurnGUI("Roger",dice);
        assertFalse(gui.isTurnOver());
    }

    @Test
    public void validTurnEndTest2(){
        Dice dice = new Dice(2);
        CurrentTurnGUI gui = new CurrentTurnGUI("Roger",dice);
        gui.endTurnButtonAction();
        assertTrue(gui.isTurnOver());
    }

    @Test
    public void validTurnEndTest3(){
        Dice dice = new Dice(2);
        CurrentTurnGUI gui = new CurrentTurnGUI("Roger",dice);
        gui.endTurnButtonAction();
        gui.updateUIForNewPlayer("Todd");
        assertFalse(gui.isTurnOver());
    }

    @Test
    public void flagsOnTest1(){
        Dice dice = new Dice(2);
        CurrentTurnGUI gui = new CurrentTurnGUI("Roger",dice);

        assertFalse(gui.doBuildAction());
        assertFalse(gui.doCardAction());
        assertFalse(gui.doTradeAction());
    }

    @Test
    public void flagsOnTest2(){
        Dice dice = new Dice(2);
        CurrentTurnGUI gui = new CurrentTurnGUI("Roger",dice);

        gui.buildButtonAction();
        gui.tradeButtonAction();
        gui.cardButtonAction();

        assertTrue(gui.doBuildAction());
        assertTrue(gui.doCardAction());
        assertTrue(gui.doTradeAction());
    }
}