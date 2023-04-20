package Presentation;

import Domain.Player;

import javax.swing.*;
import java.awt.*;

public class PlayersStatsGUI extends JFrame {

    public JFrame frame;
    public Presentation.PlayerStatsGUI playerStatsGUIs[];
    public Player[] players;

    public PlayersStatsGUI(Player[] players) {

        this.players = players;
        this.playerStatsGUIs = new PlayerStatsGUI[players.length];

        frame = new JFrame();
        frame.getContentPane().setLayout(new GridLayout(players.length*2, 1));

        for(int i = 0; i < players.length; i++){
            playerStatsGUIs[i] = new PlayerStatsGUI(players[i]);
            frame.add(playerStatsGUIs[i].playerNamePanel);
            frame.add(playerStatsGUIs[i].resourceDisplayPanel);
        }

        frame.pack();
        frame.setVisible(true);
        frame.setSize(300, 500);
//        frame.setDefaultCloseOperation(0);
    }

    public void updatePlayersStats() {
        for(int i = 0; i < players.length; i++){
            playerStatsGUIs[i].updateResourcesView();
        }
        frame.revalidate();
        frame.repaint();
    }
}