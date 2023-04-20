package Team7.SettlersOfCatan;

import java.util.Random;

public class Dice {

    private int[] rolls;
    private int total;
    private Random rng;
    private boolean rollForCurrentPlayerMade;

    public Dice(int numDice){
        rolls = new int[numDice];
        rng = new Random();
        rollDice();
        rollForCurrentPlayerMade = false;
    }


    //Testing stuff
    public void setTotal(int total){
        this.total = total;
    }

    public boolean hasPlayerMadeRoll(){
        return rollForCurrentPlayerMade;
    }

    public void invalidatePreviousRoll(){
        rollForCurrentPlayerMade = false;
    }

    public int getNumDice(){
        return rolls.length;
    }

    public int[] rollDice(){
        total = 0;
        for(int i = 0; i < rolls.length; i++){
            int roll = rng.nextInt(6)+1;
            rolls[i] = roll;
            total += roll;
        }
        rollForCurrentPlayerMade = true;
        return rolls.clone();
    }

    public void waitForPlayerDiceRoll(){
        while(!hasPlayerMadeRoll()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getTotal(){
        return total;
    }
}
