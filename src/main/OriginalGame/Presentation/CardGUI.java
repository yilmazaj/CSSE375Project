package Presentation;
import Domain.*;

import javax.swing.*;
import java.awt.*;


public class CardGUI {

    Player player;

    JPanel mainPanel;

    JButton buyButton;
    JPanel cardPanel;

    JFrame frame;


    public CardGUI (Player player) {

        this.player = player;
        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        buyButton = new JButton("Buy a Card? (1 Ore,Wool,and Grain)");
        mainPanel.add(buyButton);


        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel,BoxLayout.Y_AXIS));
        mainPanel.add(cardPanel);

        cardPanel.add(new JLabel("Your Playable Cards"));
        for(PlayableCard c : player.pCards){
            cardPanel.add(new JButton(c.getType()));
        }
        cardPanel.add(new JLabel("Your NonPlayable Cards"));
        for(NonPlayableCard c : player.nCards){
            cardPanel.add(new JLabel(c.getType()));
        }
        mainPanel.setMaximumSize(new Dimension(200, 500));
        frame = new JFrame();
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

    }

    public void updateResourcesView(){
        if(GraphicsEnvironment.isHeadless()){
            return;
        }
        cardPanel.removeAll();
        cardPanel.add(new JLabel("Your Playable Cards"));
        for(PlayableCard c : player.pCards){
            cardPanel.add(new JButton(c.getType()));
        }
        cardPanel.add(new JLabel("Your NonPlayable Cards"));
        for(NonPlayableCard c : player.nCards){
            cardPanel.add(new JLabel(c.getType()));
        }
    }
}
