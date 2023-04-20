package Presentation;

import Domain.Dice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CurrentTurnGUI {

    private JFrame frame;

    private JPanel panel;

    private JLabel[] diceLabels;

    private JButton rollDiceButton;

    private JButton tradeButton;

    private JButton endTurnButton;

    private JButton buildButton;

    private JButton cardButton;

    private String playerName;

    private JLabel playerNameLabel;



    private Dice dice;

    private boolean turnEnded;


    private boolean cardActionFlag;

    private boolean buildActionFlag;

    private boolean tradeActionFlag;

    public CurrentTurnGUI(String playerName, Dice dice){
        this.dice = dice;
        this.playerName = playerName;
        turnEnded = false;

        resetFlags();

        if (!GraphicsEnvironment.isHeadless()){
            initializeFields();

            initializeSwingUI();

            attachActionListeners();
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        }

        


//        frame.setDefaultCloseOperation(0);
    }
    
    private void initializeFields(){
        frame = new JFrame();
        frame.setLayout(new GridLayout());
        panel = new JPanel();
        diceLabels = new JLabel[dice.getNumDice()];
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
    }
    private void attachActionListeners(){
        rollDiceButton.addActionListener(e -> rollDiceButtonAction());
        tradeButton.addActionListener(e -> tradeButtonAction());
        buildButton.addActionListener(e -> buildButtonAction());
        endTurnButton.addActionListener(e -> endTurnButtonAction());
        cardButton.addActionListener(e -> cardButtonAction());
    }


    public boolean isTurnOver(){
        return turnEnded;
    }

    public void updateUIForNewPlayer(String playerName){
        this.playerName = playerName;
        turnEnded = false;
        if (!GraphicsEnvironment.isHeadless()){
            playerNameLabel.setText("Player " +playerName + "'s Turn");
        }
        resetContent();
    }

    private void resetButtons(){
        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        tradeButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        buildButton.setEnabled(false);
        cardButton.setEnabled(false);
        rollDiceButton.setText("Roll Dice");
        rollDiceButton.setEnabled(true);
    }

    private void resetContent(){
        resetButtons();
        resetDice();
        resetFlags();
    }

    private void resetFlags(){
        cardActionFlag =false;
        buildActionFlag = false;
        tradeActionFlag = false;
    }

    private void resetDice(){
        dice.invalidatePreviousRoll();
        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        for(int i = 0; i < dice.getNumDice(); i++){
            updateDiceImage(0,i);
        }
    }


    private void initializeSwingUI(){

        GridBagConstraints constraints = new GridBagConstraints();

        setGridBagConstraints(constraints,0,0,7,2);
        constraints.insets = new Insets(20,60,20,60);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        playerNameLabel = new JLabel("Player " +playerName + "'s Turn");
        playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(playerNameLabel,constraints);

        setGridBagConstraints(constraints, 2,5,3,1);
        rollDiceButton = new JButton("Roll Dice");
        constraints.weightx = 1;
        constraints.insets = new Insets(10,60,10,60);
        panel.add(rollDiceButton,constraints);

        setGridBagConstraints(constraints, 1,6,3,1);
        tradeButton = new JButton("Trade");
        constraints.weightx = 0.5;
        constraints.insets = new Insets(10,40,10,10);
        panel.add(tradeButton,constraints);

        setGridBagConstraints(constraints, 4,6,3,1);
        buildButton = new JButton("Build");
        constraints.insets = new Insets(10,10,10,40);
        panel.add(buildButton,constraints);

        setGridBagConstraints(constraints, 1,7,3,1);
        cardButton = new JButton("Buy/View Cards");
        constraints.insets = new Insets(10,40,10,10);
        panel.add(cardButton,constraints);

        setGridBagConstraints(constraints, 4,7,3,1);
        endTurnButton = new JButton("End Turn");
        constraints.insets = new Insets(10,10,10,40);
        panel.add(endTurnButton,constraints);

        resetButtons();
        resetDice();

    }

    public void endTurnButtonAction(){
        turnEnded = true;
    }

    public void buildButtonAction(){
        buildActionFlag = true;
    }

    public void tradeButtonAction(){
        tradeActionFlag = true;
    }

    public void cardButtonAction(){
        cardActionFlag = true;
    }

    public boolean doBuildAction(){
        if(buildActionFlag){
            buildActionFlag = false;
            return true;
        }
        return false;
    }

    public boolean doTradeAction(){
        if(tradeActionFlag){
            tradeActionFlag = false;
            return true;
        }
        return false;
    }

    public boolean doCardAction(){
        if(cardActionFlag){
            cardActionFlag = false;
            return true;
        }
        return false;
    }



    private void rollDiceButtonAction(){

        int[] rolls = dice.rollDice();
        for(int i = 0; i < diceLabels.length; i++){
            updateDiceImage(rolls[i], i);
        }

        rollDiceButton.setEnabled(false);
        rollDiceButton.setText(String.valueOf(dice.getTotal()));
        cardButton.setEnabled(true);
        tradeButton.setEnabled(true);
        buildButton.setEnabled(true);
        endTurnButton.setEnabled(true);
    }

    private void updateDiceImage(int diceRoll, int dicePosition){
        if(diceRoll < 0 || diceRoll > 6){
            return;
        }
        if(dicePosition < 0 || dicePosition > 1){
            return;
        }
        scaleAndSetDiceImage(diceRoll, dicePosition);
    }

    private void scaleAndSetDiceImage(int diceRoll, int dicePosition){
        BufferedImage dice = null;
        try {
            dice = ImageIO.read(new File("images/DiceFaces/Dice" + diceRoll + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image image = new ImageIcon(dice).getImage();
        int width = 60;
        int height = 60;
        ImageIcon resized = new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));

        if(diceLabels[dicePosition] == null){
            GridBagConstraints constraints = generateDiceConstraints(dicePosition);
            diceLabels[dicePosition] = new JLabel(resized);
            panel.add(diceLabels[dicePosition],constraints);
        }
        diceLabels[dicePosition].setIcon(resized);
    }

    private GridBagConstraints generateDiceConstraints(int dicePosition){
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 0.5;
        int left = 30;
        if(dicePosition == 0){
            left = 50;
        }
        setGridBagConstraints(constraints,1 +dicePosition*2,3,2,2);
        constraints.insets = new Insets(10,left,10,30);
        constraints.anchor = GridBagConstraints.CENTER;
        return constraints;
    }

    private void setGridBagConstraints(GridBagConstraints constraints, int x, int y, int width, int height){
        constraints.gridheight = height;
        constraints.gridwidth = width;
        constraints.gridx = x;
        constraints.gridy = y;
    }

    public static void main(String[] args){
        CurrentTurnGUI gui = new CurrentTurnGUI("ThatGuy", new Dice(2));
        gui.updateUIForNewPlayer("Roger");
    }








}
