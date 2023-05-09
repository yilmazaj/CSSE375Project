package Presentation;
import Domain.*;

import javax.swing.*;
import java.awt.*;


public class PlayerStatsGUI {

    Player player;

    JPanel playerNamePanel;
    public JPanel resourceDisplayPanel;

    public JPanel leftPanel;
    public JPanel rightPanel;

    public PlayerStatsGUI (Player player) {

        this.player = player;

        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        playerNamePanel = new JPanel(new GridLayout(1, 2));
        resourceDisplayPanel = new JPanel(new GridLayout(1, 2));

        leftPanel = new JPanel(new GridLayout(5,1));
        rightPanel = new JPanel(new GridLayout(5,1));

        resourceDisplayPanel.add(leftPanel);
        resourceDisplayPanel.add(rightPanel);

        playerNamePanel.add(new JLabel(player.name));
        playerNamePanel.add(new JLabel("VP: " + player.victoryPoints));
        leftPanel.add(new JLabel("Brick: " + player.brickAmount));
        leftPanel.add(new JLabel("Grain: " + player.grainAmount));
        leftPanel.add(new JLabel("Lumber: " + player.lumberAmount));
        leftPanel.add(new JLabel("Wool: " + player.woolAmount));
        leftPanel.add(new JLabel("Ore: " + player.oreAmount));

        rightPanel.add(new JLabel("Knights: " + player.knightCount));
        rightPanel.add(new JLabel("Cards: " + player.pCards.size()));

        playerNamePanel.setMaximumSize(new Dimension(200, 100));
        resourceDisplayPanel.setMaximumSize(new Dimension(200, 500));

    }

    public void updateResourcesView(){
        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        leftPanel.removeAll();
        leftPanel.add(new JLabel("Brick: " + player.brickAmount));
        leftPanel.add(new JLabel("Grain: " + player.grainAmount));
        leftPanel.add(new JLabel("Lumber: " + player.lumberAmount));
        leftPanel.add(new JLabel("Wool: " + player.woolAmount));
        leftPanel.add(new JLabel("Ore: " + player.oreAmount));
        playerNamePanel.removeAll();
        playerNamePanel.add(new JLabel(player.name));
        playerNamePanel.add(new JLabel("VP: " + player.victoryPoints));
        rightPanel.removeAll();
        rightPanel.add(new JLabel("Knights: " + player.knightCount));
        rightPanel.add(new JLabel("Cards: " + player.pCards.size()));
    }
}
