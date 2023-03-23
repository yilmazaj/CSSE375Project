package Team7.SettlersOfCatan;

import javax.swing.*;
import java.awt.*;


public class PlayerStatsComponent extends JFrame {
    String playerName;
    int victoryPoints;
    int brickAmount;
    int grainAmount;
    int lumberAmount;
    int woolAmount;
    int oreAmount;
    JFrame frame;
    JPanel playerNamePanel, resourceDisplayPanel;

    PlayerStatsComponent(String name, int vp, int brick, int grain, int lumber, int wool, int ore) {

        this.playerName = name;
        this.victoryPoints = vp;
        this.brickAmount = brick;
        this.grainAmount = grain;
        this.lumberAmount = lumber;
        this.woolAmount = wool;
        this.oreAmount = ore;

        frame = new JFrame("My Frame");

        playerNamePanel = new JPanel(new GridLayout(1, 2));
        resourceDisplayPanel = new JPanel(new GridLayout(5, 1));

        playerNamePanel.add(new JLabel("Player Name: " + this.playerName));
        playerNamePanel.add(new JLabel("VP: " + this.victoryPoints));
        resourceDisplayPanel.add(new JLabel("Brick: " + this.brickAmount));
        resourceDisplayPanel.add(new JLabel("Grain: " + this.grainAmount));
        resourceDisplayPanel.add(new JLabel("Lumber: " + this.lumberAmount));
        resourceDisplayPanel.add(new JLabel("Wool: " + this.woolAmount));
        resourceDisplayPanel.add(new JLabel("Ore: " + this.oreAmount));

        frame.getContentPane().setLayout(new GridLayout(2, 1));

        frame.getContentPane().add(playerNamePanel);
        frame.getContentPane().add(resourceDisplayPanel);

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateResourceValues(int brick, int grain, int lumber, int wool, int ore){
        this.brickAmount += brick;
        this.grainAmount += grain;
        this.lumberAmount += lumber;
        this.woolAmount += wool;
        this.oreAmount += ore;
        updateResourcesView();
    }

    public void updateResourcesView(){
        resourceDisplayPanel.removeAll();
        resourceDisplayPanel.add(new JLabel("Brick: " + this.brickAmount));
        resourceDisplayPanel.add(new JLabel("Grain: " + this.grainAmount));
        resourceDisplayPanel.add(new JLabel("Lumber: " + this.lumberAmount));
        resourceDisplayPanel.add(new JLabel("Wool: " + this.woolAmount));
        resourceDisplayPanel.add(new JLabel("Ore: " + this.oreAmount));
    }

    public static void main(String[] args) {
        PlayerStatsComponent p = new PlayerStatsComponent("Player", 0, 0, 0, 0, 0, 0);
        p.updateResourceValues(0,1,2,3,4);
    }
}
