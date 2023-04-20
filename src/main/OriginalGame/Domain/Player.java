package Domain;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    public String name;
    public Color color;
    public ArrayList<ResourceCard> resources;
    protected boolean isActive;
    public int numSettlements;
    protected int numCities;
    public int numRoads;
    public int victoryPoints;
    public ArrayList<NonPlayableCard> nCards = new ArrayList();
    public ArrayList<PlayableCard> pCards = new ArrayList();
    public int knightCount;
    public boolean hasKnightCard;
    public boolean hasRoadCard;

    public int brickAmount = 0;
    public int grainAmount = 0;
    public int lumberAmount = 0;
    public int woolAmount = 0;
    public int oreAmount = 0;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.resources = new ArrayList();
        this.isActive = false;
        this.numCities = 0;
        this.numRoads = 0;
        this.numSettlements = 0;
        this.victoryPoints = 0;
        this.knightCount = 0;
        this.hasKnightCard = false;
        this.hasRoadCard = false;
    }

    public Player(Game.PlayerInfo playerInfo) {
        this.name = playerInfo.playerName;
        this.color = playerInfo.playerColor;
        this.resources = new ArrayList();
    }

    public ResourceCard removeRandomCard() {
        if (this.resources.isEmpty()) {
            return null;
        } else {
            Random r = new Random();
            int toRemove = r.nextInt(this.resources.size());
            ResourceCard c = resources.get(toRemove);
            if(c.getType().equals("Brick")){ brickAmount--;}
            if(c.getType().equals("Grain")){ grainAmount--;}
            if(c.getType().equals("Lumber")){ lumberAmount--;}
            if(c.getType().equals("Wool")){ woolAmount--; }
            if(c.getType().equals("Ore")){ oreAmount--; }
            return (ResourceCard)this.resources.remove(toRemove);
        }
    }

    public void addResourceCard(ResourceCard c) {
        this.resources.add(c);

        if(c.getType().equals("Brick")){ brickAmount++;}
        if(c.getType().equals("Grain")){ grainAmount++;}
        if(c.getType().equals("Lumber")){ lumberAmount++;}
        if(c.getType().equals("Wool")){ woolAmount++; }
        if(c.getType().equals("Ore")){ oreAmount++; }
    }

    public boolean removeResourceCard(String type) {

        for(int i = 0; i < resources.size();i++) {
            ResourceCard c = resources.get(i);
            if (c.getType().equals(type)) {
                this.resources.remove(i);
                if(c.getType().equals("Brick")){ brickAmount--;}
                if(c.getType().equals("Grain")){ grainAmount--;}
                if(c.getType().equals("Lumber")){ lumberAmount--;}
                if(c.getType().equals("Wool")){ woolAmount--; }
                if(c.getType().equals("Ore")){ oreAmount--; }
                return true;
            }
        }

        return false;
    }

    public void clearResources(){
        resources.clear();
        oreAmount = 0;
        woolAmount = 0;
        grainAmount = 0;
        lumberAmount = 0;
        brickAmount = 0;
    }

    public boolean containsAllResources(ArrayList<ResourceCard> resourcesToCheck) {
        ArrayList<ResourceCard> temp = new ArrayList();
        boolean empty = false;
        int i;
        for(i = 0; i < this.resources.size(); ++i) {
            for(int j = 0; j < resourcesToCheck.size(); ++j) {
                if (resourcesToCheck.isEmpty()) {
                    empty = true;
                    break;
                }

                if (((ResourceCard)resourcesToCheck.get(j)).type.equals(((ResourceCard)this.resources.get(i)).type)) {
                    temp.add((ResourceCard)this.resources.remove(i));
                    resourcesToCheck.remove(j);
                    --i;
                    break;
                }
            }

            if (resourcesToCheck.isEmpty()) {
                empty = true;
                break;
            }
        }

        for(i = 0; i < temp.size(); ++i) {
            this.addResourceCard((ResourceCard)temp.get(i));
            resourcesToCheck.add((ResourceCard)temp.get(i));
        }

        return empty;
    }

    public void removeAllResources(ArrayList<ResourceCard> requiredResources) {
        for(ResourceCard c: requiredResources){
            removeResourceCard(c.type);
        }
    }

    public void printResources() {
        String s = "You have the following resource cards: ";

        for(int i = 0; i < this.resources.size(); ++i) {
            s = s + ((ResourceCard)this.resources.get(i)).type + ", ";
        }

        System.out.println(s);
    }

    public void addNonPlayableCard(NonPlayableCard c) {
        this.nCards.add(c);
        if (c.getType().equals("Domain.VictoryPointCard")) {
            ++this.victoryPoints;
        } else if (c.getType().equals("MostRoads")) {
            ++this.victoryPoints;
            ++this.victoryPoints;
        } else if (c.getType().equals("LargestArmy")) {
            ++this.victoryPoints;
            ++this.victoryPoints;
        }

    }

    public boolean removeNonPlayableCard(NonPlayableCard c) {
        if (c.getType().equals("MostRoads")) {
            --this.victoryPoints;
            --this.victoryPoints;
        } else if (c.getType().equals("LargestArmy")) {
            --this.victoryPoints;
            --this.victoryPoints;
        }

        return this.nCards.remove(c);
    }

    public String displayPlayableCards() {
        String s = "Playable Cards: ";

        for(int i = 0; i < this.pCards.size(); ++i) {
            s = s + i + ": " + ((PlayableCard)this.pCards.get(i)).getType();
        }

        return s;
    }
}
