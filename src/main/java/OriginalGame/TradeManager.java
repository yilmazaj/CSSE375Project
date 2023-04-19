package Team7.SettlersOfCatan;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TradeManager {

    public String testOutput;
    private Player[] players;
    private Player inTurn;

    public TradeManager(Player[] players, Player inTurn){
        this.players = players;
        this.inTurn = inTurn;
    }

    public boolean checkIfValidOutput(int[] resourcesOffered){
        return (resourcesOffered[0] <= inTurn.brickAmount) &&
               (resourcesOffered[1] <= inTurn.grainAmount) &&
               (resourcesOffered[2] <= inTurn.lumberAmount) &&
               (resourcesOffered[3] <= inTurn.woolAmount) &&
               (resourcesOffered[4] <= inTurn.oreAmount);
    }

    public ArrayList<Integer> checkIfValidTrade(int[] resourcesDemanded){
        ArrayList<Integer> valid = new ArrayList<>();
        for(int i = 0; i < players.length; i++){
            // Players cannot trade with themselves
            if(players[i].name.equals(inTurn.name) == false){
                if((resourcesDemanded[0] <= players[i].brickAmount) &&
                   (resourcesDemanded[1] <= players[i].grainAmount) &&
                   (resourcesDemanded[2] <= players[i].lumberAmount) &&
                   (resourcesDemanded[3] <= players[i].woolAmount) &&
                   (resourcesDemanded[4] <= players[i].oreAmount)){
                    valid.add(i);
                }
            }
        }
        return valid;
    }

    private Player findPlayer(Player[] players, String name){
        for(int i = 0; i < players.length; i++){
            if(players[i].name.equals(name)){
                return players[i];
            }
        }
        return null;
    }

    public void handleTrade(String tradeWith, int[] outgoing, int[] demanded){
        Player tradee = findPlayer(players, tradeWith);

        ArrayList<ResourceCard> resourcesOut = doTrade(outgoing);
        ArrayList<ResourceCard> resourcesIn = doTrade(demanded);

        for(int i = 0; i < resourcesIn.size(); i++) {
            inTurn.addResourceCard(resourcesIn.get(i));
        }

        for(int i = 0; i < resourcesOut.size(); i++) {
            tradee.addResourceCard(resourcesOut.get(i));
        }

        // These don't work.
        inTurn.removeAllResources(resourcesOut);
        tradee.removeAllResources(resourcesIn);
    }

    private ArrayList<ResourceCard> doTrade(int[] toTrade){
        ArrayList<ResourceCard> resourceCards = new ArrayList<>();
        for(int i = 0; i < toTrade[0]; i++) {
            resourceCards.add(new ResourceCard("Brick"));
        }
        for(int i = 0; i < toTrade[1]; i++) {
            resourceCards.add(new ResourceCard("Grain"));
        }
        for(int i = 0; i < toTrade[2]; i++) {
            resourceCards.add(new ResourceCard("Lumber"));
        }
        for(int i = 0; i < toTrade[3]; i++) {
            resourceCards.add(new ResourceCard("Wool"));
        }
        for(int i = 0; i < toTrade[4]; i++) {
           resourceCards.add(new ResourceCard("Ore"));
        }
        return resourceCards;
    }

}
