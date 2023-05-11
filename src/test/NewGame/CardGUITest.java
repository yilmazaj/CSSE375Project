import Domain.Dice;
import Domain.Game;
import Domain.KnightCard;
import Domain.PlayableCard;
import Presentation.CardGUI;
import TestClasses.CurrentTurnGUITest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class CardGUITest {

    CardGUI cardGUI;
    Game game;

    @BeforeEach
    public void setup(){
        game = new Game(1);
        cardGUI = new CardGUI(game.inTurn,false);
    }




    @Test
    public void checkInactiveAtStart(){
        assertFalse(cardGUI.isActive());
    }

    @Test
    public void checkActiveConstruction(){
        cardGUI = new CardGUI(game.inTurn,true);
        assertTrue(cardGUI.isActive());
    }

    @Test
    public void checkDefaultIsBuyingCard(){
        assertFalse(cardGUI.isBuyingCard());
    }

    @Test
    public void checkDefaultIsPlayingCard(){
        assertFalse(cardGUI.isPlayingCard());
    }

    @Test
    public void checkEnablePlayingCard(){
        game.inTurn.pCards.add(new KnightCard());
        cardGUI = new CardGUI(game.inTurn,true);
        JButton testButton = new JButton("KnightCard");
        testButton.addActionListener(cardGUI);

        testButton.doClick();

        assertTrue(cardGUI.isPlayingCard());
        assertEquals(cardGUI.getCardtoPlay(),"KnightCard");
    }


}
