package Team7.SettlersOfCatan;

import javax.swing.*;
import java.awt.*;

public class PlayerTurnComponent {

    public static void main(String[] args) {

        JFrame frame = new JFrame("My Frame");

        JPanel playerNamePanel = new JPanel();
        JPanel diceRollPanel = new JPanel();
        JPanel playerActionsPanel = new JPanel();

        playerNamePanel.add(new JLabel("Player Name: playerName"));
        diceRollPanel.add(new JButton("Roll"));
        diceRollPanel.add(new JLabel("Rolled: rollValue"));
        playerActionsPanel.add(new JButton("Trade"));
        playerActionsPanel.add(new JButton("Build"));

        frame.getContentPane().setLayout(new GridLayout(3, 1));

        frame.getContentPane().add(playerNamePanel);
        frame.getContentPane().add(diceRollPanel);
        frame.getContentPane().add(playerActionsPanel);

        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
