import Domain.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiceTest{

    @Test
    public void diceHasPlayerMadeRollTest1(){
        Dice dice = new Dice(2);
        assertFalse(dice.hasPlayerMadeRoll());
    }

    @Test
    public void diceHasPlayerMadeRollTest2(){
        Dice dice = new Dice(2);
        dice.rollDice();
        assertTrue(dice.hasPlayerMadeRoll());
    }

    @Test
    public void diceHasPlayerMadeRollTest3(){
        Dice dice = new Dice(2);
        dice.rollDice();
        dice.invalidatePreviousRoll();
        assertFalse(dice.hasPlayerMadeRoll());
    }
}