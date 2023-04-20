package Team7.SettlersOfCatan.Presentation;

import Team7.SettlersOfCatan.Player;
import Team7.SettlersOfCatan.ResourceCard;
import Team7.SettlersOfCatan.TradeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class TradeManagerGUI {

    private TradeManager tM;

    private JFrame frame;

    private JPanel enterResources;
    private JPanel tradeOptions;

    private JLabel label;
    private JLabel brickLabel;
    private JLabel grainLabel;
    private JLabel lumberLabel;
    private JLabel woolLabel;
    private JLabel oreLabel;
    private JLabel tradeOptionsLabel;

    private JTextField brick;
    private JTextField grain;
    private JTextField lumber;
    private JTextField wool;
    private JTextField ore;

    private JButton tradeAway;
    private JButton tradeFor;
    private JButton[] yesButtons;
    private JButton[] noButtons;

    private ArrayList<Integer> validTradees;

    private Player[] players;

    public TradeManagerGUI(Player[] players, Player inTurn){
        tM = new TradeManager(players, inTurn);
        this.players = players;

        frame = new JFrame();
        frame.getContentPane().setLayout(new GridLayout());

        initializeLabels();
        initializeTextFields();
        initializeTradeOutGUI();
        tradeAway = new JButton("Accept");
        tradeAway.addActionListener(this::actionPerformed);
        enterResources.add(tradeAway);

        frame.add(enterResources);

        frame.pack();
        frame.setVisible(true);
        frame.setSize(200, 300);
    }

    public void initializeLabels(){
        label = new JLabel("Trade Away:");
        brickLabel = new JLabel("Brick Amount");
        grainLabel = new JLabel("Grain Amount");
        lumberLabel = new JLabel("Lumber Amount");
        woolLabel = new JLabel("Wool Amount");
        oreLabel = new JLabel("Ore Amount");
    }

    public void initializeTextFields(){
        brick = new JTextField();
        grain = new JTextField();
        lumber = new JTextField();
        wool = new JTextField();
        ore = new JTextField();
    }

    public void initializeTradeOutGUI(){
        enterResources = new JPanel();
        enterResources.setLayout(new BoxLayout(enterResources, BoxLayout.Y_AXIS));

        enterResources.add(label);
        enterResources.add(brickLabel);
        enterResources.add(brick);
        enterResources.add(grainLabel);
        enterResources.add(grain);
        enterResources.add(lumberLabel);
        enterResources.add(lumber);
        enterResources.add(woolLabel);
        enterResources.add(wool);
        enterResources.add(oreLabel);
        enterResources.add(ore);
    }

    public void resetTextFields(){
        brick.setText(null);
        grain.setText(null);
        lumber.setText(null);
        wool.setText(null);
        ore.setText(null);
    }

    public void initializeTradeInGUI(){
        label.setText("Trade For");
        enterResources.remove(tradeAway);
        tradeFor = new JButton("Accept");
        tradeFor.addActionListener(this::actionPerformed);
        enterResources.add(tradeFor);
        frame.revalidate();
        frame.repaint();
    }

    public void initializeTradeOptionsGUI(){
        int numPlayers = players.length;

        yesButtons = new JButton[numPlayers];
        noButtons = new JButton[numPlayers];

        tradeOptions = new JPanel();

        tradeOptionsLabel = new JLabel("Trade Options");
        tradeOptions.add(tradeOptionsLabel);

        for(int i = 0; i < numPlayers; i++){
            JPanel tempPanel = new JPanel();

            // Add player name label
            tempPanel.add(new JLabel(players[i].name));

            // Add yes button
            JButton yesButton = new JButton("Yes");
            yesButton.addActionListener(this::actionPerformed);
            if(validTradees.contains(i) == false){
                yesButton.setEnabled(false);
            }
            yesButtons[i] = yesButton;
            tempPanel.add(yesButton);

            // Add no button
            JButton noButton = new JButton("No");
            noButton.addActionListener(this::actionPerformed);
            noButtons[i] = noButton;
            tempPanel.add(noButton);

            tradeOptions.add(tempPanel);
        }
        frame.add(tradeOptions);
        frame.revalidate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == tradeAway){
            String[] resources = {brick.getText(), grain.getText(), lumber.getText(), wool.getText(), ore.getText()};
            tM.setResourceOut(resources);
            if(tM.checkIfValidOutput() == false){
                JOptionPane.showMessageDialog(null, "You don't own all those resources","Invalid input!", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                return;
            }
            resetTextFields();
            initializeTradeInGUI();

        } else if (e.getSource() == tradeFor){
            String[] resources = {brick.getText(), grain.getText(), lumber.getText(), wool.getText(), ore.getText()};
            tM.setResourceIn(resources);
            // This will be used for the next UI
            validTradees = tM.checkIfValidTrade();
            // Create a new GUI with buttons containing all players
            // validTradees will be used to gray out / not gray out "Accept" Buttons
            frame.remove(enterResources);
            frame.revalidate();
            frame.repaint();
            initializeTradeOptionsGUI();

        } else if (Arrays.stream(yesButtons).toList().contains(e.getSource())){
            int index = Arrays.stream(yesButtons).toList().indexOf(e.getSource());
            JButton button = yesButtons[index];
            JLabel playerLabel = (JLabel) button.getParent().getComponent(0);
            String playerName = playerLabel.getText();
            frame.dispose();
            tM.handleTrade(playerName);

        } else if (Arrays.stream(noButtons).toList().contains(e.getSource())){
            int index = Arrays.stream(noButtons).toList().indexOf(e.getSource());
            JButton button = noButtons[index];
            tradeOptions.remove(button.getParent());
            if(tradeOptions.getComponents().length == 1){
                // Only the JLabel is left
                JOptionPane.showMessageDialog(null, "Nobody wanted to trade","Invalid!", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
            }
            frame.revalidate();
            frame.repaint();
        }
    }

    public static void main(String[] args){
        Player[] players = new Player[3];
        players[0] = new Player("Jerry", Color.BLACK);
        players[1] = new Player("Billy", Color.RED);
        players[2] = new Player("Sam", Color.GREEN);

        players[0].addResourceCard(new ResourceCard("Brick"));
        players[0].addResourceCard(new ResourceCard("Brick"));
        players[0].brickAmount = 2; // [2,0,0,0,0]
        players[1].addResourceCard(new ResourceCard("Lumber"));
        players[1].addResourceCard(new ResourceCard("Lumber"));
        players[1].addResourceCard(new ResourceCard("Lumber"));
        players[1].lumberAmount = 3; // [0,0,3,0,0]

        TradeManagerGUI gui = new TradeManagerGUI(players, players[0]);
    }
}
