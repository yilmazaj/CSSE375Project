import Domain.Dice;
import Presentation.CurrentTurnGUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CurrentTurnGUITest{


    CurrentTurnGUI gui;
    Dice dice;

    @BeforeEach
    public void setup(){
        dice = new Dice(2);
        gui = new CurrentTurnGUI("Roger",dice);
    }

    @Test
    public void checkTurnOverOffAtStart(){
        assertFalse(gui.isTurnOver());
    }

    @Test
    public void checkEndTurnButton(){
        gui.endTurnButtonAction();
        assertTrue(gui.isTurnOver());
    }

    @Test
    public void checkNewTurnResetsTurnOver(){
        gui.endTurnButtonAction();
        gui.updateUIForNewPlayer("Todd");
        assertFalse(gui.isTurnOver());
    }

    @Test
    public void flagsOnTest1(){
        assertFalse(gui.doBuildAction());
        assertFalse(gui.doCardAction());
        assertFalse(gui.doTradeAction());
    }

    @Test
    public void flagsOnTest2(){
        gui.buildButtonAction();
        gui.tradeButtonAction();
        gui.cardButtonAction();

        assertTrue(gui.doBuildAction());
        assertTrue(gui.doCardAction());
        assertTrue(gui.doTradeAction());
    }
}