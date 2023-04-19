package Team7.SettlersOfCatan;

import java.util.ArrayList;

public class TradeManager {

    private Player[] players;
    private Player inTurn;

    public int[] resourceOut = {0,0,0,0,0};
    public int[] resourceIn = {0,0,0,0,0};

    public TradeManager(Player[] players, Player inTurn){
        this.players = players;
        this.inTurn = inTurn;
    }

    public boolean checkIfValidOutput(){
        return (resourceOut[0] <= inTurn.brickAmount) &&
               (resourceOut[1] <= inTurn.grainAmount) &&
               (resourceOut[2] <= inTurn.lumberAmount) &&
               (resourceOut[3] <= inTurn.woolAmount) &&
               (resourceOut[4] <= inTurn.oreAmount);
    }

    public ArrayList<Integer> checkIfValidTrade(){
        ArrayList<Integer> valid = new ArrayList<>();
        for(int i = 0; i < players.length; i++){
            // Players cannot trade with themselves
            if(players[i].name.equals(inTurn.name) == false){
                if((resourceIn[0] <= players[i].brickAmount) &&
                   (resourceIn[1] <= players[i].grainAmount) &&
                   (resourceIn[2] <= players[i].lumberAmount) &&
                   (resourceIn[3] <= players[i].woolAmount) &&
                   (resourceIn[4] <= players[i].oreAmount)){
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

    public void setResourceOut(String[] resources){
        for(int i = 0; i < resources.length; i++){
            if(resources[i].length() != 0){
                resourceOut[i] = Integer.parseInt(resources[i]);
            }
        }
        System.out.println("Offering: " + resourceOut[0] + "," + resourceOut[1] + "," + resourceOut[2] + "," + resourceOut[3] + "," + resourceOut[4]);
    }

    public void setResourceIn(String[] resources){
        for(int i = 0; i < resources.length; i++){
            if(resources[i].length() != 0){
                resourceIn[i] = Integer.parseInt(resources[i]);
            }
        }
        System.out.println("Receiving: " + resourceIn[0] + "," + resourceIn[1] + "," + resourceIn[2] + "," + resourceIn[3] + "," + resourceIn[4]);
    }

    public void handleTrade(String tradeWith){
        Player tradee = findPlayer(players, tradeWith);

        ArrayList<ResourceCard> resourcesOut = createResourceCards(resourceOut);
        ArrayList<ResourceCard> resourcesIn = createResourceCards(resourceIn);

        for(int i = 0; i < resourcesIn.size(); i++) {
            inTurn.addResourceCard(resourcesIn.get(i));
        }
        for(int i = 0; i < resourcesOut.size(); i++) {
            tradee.addResourceCard(resourcesOut.get(i));
        }

        inTurn.removeAllResources(resourcesOut);
        tradee.removeAllResources(resourcesIn);
    }

    private ArrayList<ResourceCard> createResourceCards(int[] toTrade){
        ArrayList<ResourceCard> resourceCards = new ArrayList<>();
        for(int i = 0; i < toTrade[0]; i++) { resourceCards.add(new ResourceCard("Brick")); }
        for(int i = 0; i < toTrade[1]; i++) { resourceCards.add(new ResourceCard("Grain")); }
        for(int i = 0; i < toTrade[2]; i++) { resourceCards.add(new ResourceCard("Lumber")); }
        for(int i = 0; i < toTrade[3]; i++) { resourceCards.add(new ResourceCard("Wool")); }
        for(int i = 0; i < toTrade[4]; i++) { resourceCards.add(new ResourceCard("Ore")); }
        return resourceCards;
    }

}
