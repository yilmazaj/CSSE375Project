package Team7.SettlersOfCatan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TradeManager {

    private Player findPlayer(Player[] players, String name){
        for(int i = 0; i < players.length; i++){
            if(players[i].name.equals(name)){
                return players[i];
            }
        }
        return null;
    }

    public void tradeStage(Player inTurn, Player[] players) {
        int answer = JOptionPane.showConfirmDialog(null, "Would you like to trade?", "Trade?", JOptionPane.YES_NO_OPTION);
        while(answer == JOptionPane.YES_OPTION) {
            String name = JOptionPane.showInputDialog(null, "Who do you want to trade with?", "");
            Player tradePartner = findPlayer(players, name);
            while(tradePartner == null || tradePartner.name.equals(inTurn.name)) {
                name = JOptionPane.showInputDialog(null, "Please enter the name of another player", "");
                tradePartner = findPlayer(players, name);
            }
            trade(inTurn, tradePartner);

            answer = JOptionPane.showConfirmDialog(null, "Would you like to trade more?", "Trade?", JOptionPane.YES_NO_OPTION);
        }

    }

    public boolean trade(Player inTurn, Player tradePartner) {
        ResourcesWrapper resourcesOut;
        ResourcesWrapper resourcesIn;
        resourcesOut = doTradeDialogue(true);
        if(!inTurn.hasAllResources(resourcesOut.resourceValues)) {
            JOptionPane.showMessageDialog(null, "You don't own all those resources","Invalid input!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        resourcesIn = doTradeDialogue(false);
        if(!tradePartner.hasAllResources(resourcesIn.resourceValues)) {
            JOptionPane.showMessageDialog(null, tradePartner.name + " doesn't own all those resources", "Invalid input!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int answer = JOptionPane.showConfirmDialog(null, "Do you accept the trade?",
                tradePartner.name, JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, tradePartner.name + " accepted your trade request","Trade accepted!", JOptionPane.INFORMATION_MESSAGE);
            for(int i = 0; i < resourcesIn.resourceCards.size(); i++) {
                inTurn.addResourceCard(resourcesIn.resourceCards.get(i));
            }
            inTurn.removeAllResources(resourcesOut.resourceCards);
            for(int i = 0; i < resourcesOut.resourceCards.size(); i++) {
                tradePartner.addResourceCard(resourcesOut.resourceCards.get(i));
            }
            tradePartner.removeAllResources(resourcesIn.resourceCards);

            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, tradePartner.name + " declined your trade request","Trade declined!", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    private ResourcesWrapper doTradeDialogue(boolean outgoing) {
        if(outgoing) {
            JOptionPane.showMessageDialog(null, "Enter how much of each resource you want to trade away.", "Trade Away", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Enter how much of each resource you want to trade for.", "Trade For", JOptionPane.INFORMATION_MESSAGE);
        }
        ResourcesWrapper resources = new ResourcesWrapper();
        int numBrick = Integer.parseInt(JOptionPane.showInputDialog(null, "How many brick?", ""));
        int numGrain = Integer.parseInt(JOptionPane.showInputDialog(null, "How many grain?", ""));
        int numLumber = Integer.parseInt(JOptionPane.showInputDialog(null, "How many lumber?", ""));
        int numWool = Integer.parseInt(JOptionPane.showInputDialog(null, "How many wool?", ""));
        int numOre = Integer.parseInt(JOptionPane.showInputDialog(null, "How many ore?", ""));
        for(int i = 0; i < numBrick; i++) {
            resources.resourceCards.add(new ResourceCard("Brick")); resources.resourceValues[0]++;
        }
        for(int i = 0; i < numGrain; i++) {
            resources.resourceCards.add(new ResourceCard("Grain")); resources.resourceValues[1]++;
        }
        for(int i = 0; i < numLumber; i++) {
            resources.resourceCards.add(new ResourceCard("Lumber")); resources.resourceValues[2]++;
        }
        for(int i = 0; i < numWool; i++) {
            resources.resourceCards.add(new ResourceCard("Wool")); resources.resourceValues[3]++;
        }
        for(int i = 0; i < numOre; i++) {
            resources.resourceCards.add(new ResourceCard("Ore")); resources.resourceValues[4]++;
        }
        return resources;
    }

    public class ResourcesWrapper {
        ArrayList<ResourceCard> resourceCards;
        int[] resourceValues;
        public ResourcesWrapper() {
            this.resourceCards = new ArrayList<>();
            this.resourceValues = new int[5];
        }
    }

}
