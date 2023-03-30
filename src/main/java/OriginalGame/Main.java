package Team7.SettlersOfCatan;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("The Start of Catan.");
        Game game = new Game();
        game.buildInitialStructures();
        CurrentTurnGUI turnGUI = new CurrentTurnGUI("Default",game.dice);
        game.playersStats.updatePlayersStats();

        while(game.inTurn.victoryPoints < 10) {
            game.handlePlayerTurn(turnGUI);

            if(game.inTurn.victoryPoints >= 10) {
                break;
            }
            game.rotateTurns();
        }
        JOptionPane.showMessageDialog(null, game.inTurn.name + " has reached 10 victory points and won the game!", "Game Over!", JOptionPane.INFORMATION_MESSAGE);
    }
}
