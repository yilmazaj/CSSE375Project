package Presentation;
import Domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class CardGUI implements ActionListener {

    Player player;

    JPanel mainPanel;

    JButton buyButton;
    JPanel cardPanel;

    JFrame frame;

    private boolean buyingCard = false;
    private boolean playingCard = false;
    private String cardToPlay = "";

    private boolean isActive;


    public CardGUI (Player player,boolean visible) {

        this.player = player;
        isActive = visible;
        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        buyButton = new JButton("Buy a Card? (1 Ore,Wool,and Grain)");
        buyButton.addActionListener(e -> enableBuyingCard());
        mainPanel.add(buyButton);

        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel,BoxLayout.Y_AXIS));
        mainPanel.add(cardPanel);

        displayPlayableCards();
        displayNonPlayableCards();

        mainPanel.setMaximumSize(new Dimension(250, 500));
        initializeFrame(visible);
    }

    private void initializeFrame(boolean visible){
        frame = new JFrame();
        frame.add(mainPanel);
        frame.setLocation(790,400);
        frame.pack();
        frame.setVisible(visible);

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                isActive = false;
            }
        });
    }

    private void enableBuyingCard(){
        buyingCard = true;
    }

    public boolean isBuyingCard(){
        boolean result = buyingCard;
        if(buyingCard){
            buyingCard = false;
        }
        return result;
    }

    public boolean isPlayingCard(){
        boolean result = playingCard;
        if(playingCard){
            playingCard = false;
        }
        return result;
    }

    public String getCardtoPlay(){
        return cardToPlay;
    }

    public boolean isActive(){
        return isActive;
    }

    public void notifyInvalidation(){
        isActive = false;
        frame.setVisible(false);
    }



    private void displayPlayableCards(){
        cardPanel.add(new JLabel("Your Playable Cards"));
        boolean isEmpty = true;
        for(PlayableCard c : player.pCards){
            JButton playableCard = new JButton(c.getType());
            playableCard.addActionListener(this);
            cardPanel.add(playableCard);
            isEmpty = false;
        }
        if(isEmpty){
            cardPanel.add(new JLabel("No Playable Cards"));
        }
    }



    private void displayNonPlayableCards(){
        //Separate method in case display method changes from playable
        cardPanel.add(new JLabel("Your NonPlayable Cards"));
        boolean isEmpty = true;
        for(NonPlayableCard c : player.nCards){
            cardPanel.add(new JLabel(c.getType()));
            isEmpty = false;
        }
        if(isEmpty){
            cardPanel.add(new JLabel("No NonPlayable Cards"));
        }
    }


    @Override//Set Card to Play
    public void actionPerformed(ActionEvent e) {
        JButton button  = (JButton) e.getSource();
        cardToPlay = button.getText();
        playingCard = true;
    }
}
