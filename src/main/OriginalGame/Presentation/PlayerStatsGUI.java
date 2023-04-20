package Presentation;
import Domain.*;

import javax.swing.*;
import java.awt.*;


public class PlayerStatsGUI {

    Player player;

    JPanel playerNamePanel;
    public JPanel resourceDisplayPanel;

    public PlayerStatsGUI (Player player) {

        this.player = player;

        if(GraphicsEnvironment.isHeadless()){
            return;
        }
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
        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        resourceDisplayPanel.removeAll();
        resourceDisplayPanel.add(new JLabel("Brick: " + player.brickAmount));
        resourceDisplayPanel.add(new JLabel("Grain: " + player.grainAmount));
        resourceDisplayPanel.add(new JLabel("Lumber: " + player.lumberAmount));
        resourceDisplayPanel.add(new JLabel("Wool: " + player.woolAmount));
        resourceDisplayPanel.add(new JLabel("Ore: " + player.oreAmount));
    }
}
