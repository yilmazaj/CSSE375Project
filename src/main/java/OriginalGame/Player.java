//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    public String name;
    public Color color;
    protected ArrayList<ResourceCard> resources;
    protected boolean isActive;
    protected int numSettlements;
    protected int numCities;
    protected int numRoads;
    protected int victoryPoints;
    protected ArrayList<NonPlayableCard> nCards = new ArrayList();
    protected ArrayList<PlayableCard> pCards = new ArrayList();
    protected int knightCount;
    protected boolean hasKnightCard;
    protected boolean hasRoadCard;

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
            return (ResourceCard)this.resources.remove(toRemove);
        }
    }

    public void addResourceCard(ResourceCard c) {
        this.resources.add(c);
    }

    public boolean removeResourceCard(String type) {
        for(int i = 0; i < this.resources.size(); ++i) {
            if (((ResourceCard)this.resources.get(i)).type.equals(type)) {
                this.resources.remove(i);
                return true;
            }
        }

        return false;
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
        for(int i = 0; i < requiredResources.size(); ++i) {
            this.removeResourceCard(((ResourceCard)requiredResources.get(i)).type);
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
        if (c.getType().equals("VictoryPointCard")) {
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
