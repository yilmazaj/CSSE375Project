package TestClasses;

import Domain.Game;

public class GameTest extends Game {
    public GameTest(int numPlayers){
        super(numPlayers);
    }

    public int simulateHandleDiceRoll(int total){
        if(total == 7) {
            robber.activateRobber(this);
        }
        else {
            giveResourcesFromRoll(total);
        }
        playersStats.updatePlayersStats();
        return total;
    }
}
