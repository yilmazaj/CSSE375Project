package Team7.SettlersOfCatan;

import javax.swing.*;
import java.awt.*;


public class PlayerStatsComponent extends JFrame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("My Frame");

        JPanel playerNamePanel = new JPanel(new GridLayout(1, 2));
        JPanel resourceDisplayPanel = new JPanel(new GridLayout(5, 1));

        playerNamePanel.add(new JLabel("Player Name: playerName"));
        playerNamePanel.add(new JLabel("VP: victoryPoints"));
        resourceDisplayPanel.add(new JLabel("Brick: brickAmount"));
        resourceDisplayPanel.add(new JLabel("Grain: grainAmount"));
        resourceDisplayPanel.add(new JLabel("Lumber: lumberAmount"));
        resourceDisplayPanel.add(new JLabel("Wool: woolAmount"));
        resourceDisplayPanel.add(new JLabel("Ore: oreAmount"));

        frame.getContentPane().setLayout(new GridLayout(2, 1));

        frame.getContentPane().add(playerNamePanel);
        frame.getContentPane().add(resourceDisplayPanel);

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
