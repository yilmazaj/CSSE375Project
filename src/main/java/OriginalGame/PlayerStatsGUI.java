package Team7.SettlersOfCatan;

import javax.swing.*;
import java.awt.*;


public class PlayerStatsGUI extends JPanel {

    Player player;

    JPanel playerNamePanel, resourceDisplayPanel;

    public PlayerStatsGUI (Player player) {

        this.player = player;

        playerNamePanel = new JPanel(new GridLayout(1, 2));
        resourceDisplayPanel = new JPanel(new GridLayout(5, 1));

        playerNamePanel.add(new JLabel(player.name));
        playerNamePanel.add(new JLabel("VP: " + player.victoryPoints));
        resourceDisplayPanel.add(new JLabel("Brick: " + player.brickAmount));
        resourceDisplayPanel.add(new JLabel("Grain: " + player.grainAmount));
        resourceDisplayPanel.add(new JLabel("Lumber: " + player.lumberAmount));
        resourceDisplayPanel.add(new JLabel("Wool: " + player.woolAmount));
        resourceDisplayPanel.add(new JLabel("Ore: " + player.oreAmount));

        playerNamePanel.setMaximumSize(new Dimension(200, 100));
        resourceDisplayPanel.setMaximumSize(new Dimension(200, 500));

    }

    public void updateResourcesView(){
        resourceDisplayPanel.removeAll();
        System.out.println("Player: " + player.name + " has: " + player.brickAmount + " " +
                            player.grainAmount + " " + player.lumberAmount + " " + player.woolAmount +
                            " " + player.oreAmount);
        resourceDisplayPanel.add(new JLabel("Brick: " + player.brickAmount));
        resourceDisplayPanel.add(new JLabel("Grain: " + player.grainAmount));
        resourceDisplayPanel.add(new JLabel("Lumber: " + player.lumberAmount));
        resourceDisplayPanel.add(new JLabel("Wool: " + player.woolAmount));
        resourceDisplayPanel.add(new JLabel("Ore: " + player.oreAmount));
    }
}
