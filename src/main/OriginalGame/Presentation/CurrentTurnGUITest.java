package Presentation;
import Domain.Dice;

public class CurrentTurnGUITest extends CurrentTurnGUI {

    public CurrentTurnGUITest(String playerName, Dice dice) {
        super(playerName, dice);
    }

    @Override
    public void initialize(){
        resetFlags();
    }

    @Override
    public void updateUIForNewPlayer(String playerName){
        this.playerName = playerName;
        turnEnded = false;
        resetContent();
    }

    @Override
    public void resetContent(){
        resetDice();
        super.resetFlags();
    }

    @Override
    public void resetDice(){
        dice.invalidatePreviousRoll();
    }
}
