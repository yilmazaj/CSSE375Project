package Domain;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import Presentation.Intersection;

import javax.swing.*;
import java.util.ArrayList;

public class Robber {
    public Robber() {

    }

    public int moveRobberTile(Game g) {
        JOptionPane.showMessageDialog(null, "Click on a Hex to Move the Robber", "Robber", JOptionPane.INFORMATION_MESSAGE);


        int moveRobber = waitForPlayerHexChoice(g, false);
        while(moveRobber < 0 || moveRobber > 18) {
            JOptionPane.showMessageDialog(null, "Click on a Hex to Move the Robber", "Robber", JOptionPane.INFORMATION_MESSAGE);
            moveRobber = waitForPlayerHexChoice(g, false);
        }
        return moveRobber;
    }

    public int waitForPlayerHexChoice(Game g, boolean testMode) {
        g.gameBuildingHandler.board.enableHexButtons(true);
        int selected = -1;
        while (selected == -1) {
            try {
                if(!testMode)
                    selected = g.gameBuildingHandler.board.getSelectedHex();
                else
                    selected = 0;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        g.gameBuildingHandler.board.enableHexButtons(false);
        return selected;
    }


    //Public for testing
    public void moreThanSevenCards(Game g){
        for(int i = 0; i < g.playerNum; i++) {
            while(g.players[i].resources.size() > 7) {
                g.players[i].removeRandomCard();
            }
        }
    }

    private ArrayList<String> retrieveRobberTargets(Game g, int moveRobber){
        ArrayList<String> names = new ArrayList<String>();
        for(int i = 0; i < 6; i++) {
            Structure s = getStuctureByHex(g, moveRobber, i);
            if(s != null) {
                if(!s.color.equals(g.inTurn.color)) {
                    if(!names.contains(g.getPlayerNameByColor(s.color))) {
                        names.add(g.getPlayerNameByColor(s.color));
                    }
                }
            }
        }
        if(names.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No adjacent players to steal from",
                    "Robber", JOptionPane.INFORMATION_MESSAGE);
            return new ArrayList<String>();
        }
        return names;
    }

    private void handleSteal(Game g, ArrayList<String> names){
        String choose = "Pick one of the following players to steal a random resource from: ";
        for(int i = 0; i < names.size(); i++) {
            choose = choose + names.get(i) + ", ";
        }
        choose = choose.substring(0,choose.length() - 2);
        Player stealFrom = null;
        while(stealFrom == null) {
            String stealName = JOptionPane.showInputDialog(null, choose, "");
            for(int i = 0; i < g.playerNum; i++) {
                if(g.players[i].name.equals(stealName)) {
                    stealFrom = g.players[i];
                    break;
                }
            }
        }
        ResourceCard stolen = stealFrom.removeRandomCard();
        if (stolen != null){
            g.inTurn.addResourceCard(stolen);
        }
        else{
            JOptionPane.showMessageDialog(null, stealFrom.name + " has no resources to steal",
                    "Domain.Robber", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void activateRobber(Game g) {
        moreThanSevenCards(g);
        int moveRobber = moveRobberTile(g);
        g.gameBuildingHandler.board.moveRobber(moveRobber);
        ArrayList<String> names = retrieveRobberTargets(g, moveRobber);
        handleSteal(g, names);
    }

    //For automated testing

    public ArrayList<String> retrieveRobberTargetsWithInput(Game g, int moveRobber){
        ArrayList<String> names = new ArrayList<String>();
        for(int i = 0; i < 6; i++) {
            Structure s = getStuctureByHex(g, moveRobber, i);
            if(s != null) {
                if(!s.color.equals(g.inTurn.color)) {
                    if(!names.contains(g.getPlayerNameByColor(s.color))) {
                        names.add(g.getPlayerNameByColor(s.color));
                    }
                }
            }
        }
        if(names.isEmpty()) {
            return new ArrayList<String>();
        }
        return names;
    }
    public void handleStealWithInput(Game g, Player stealFrom){
        ResourceCard stolen = stealFrom.removeRandomCard();
        if (stolen != null){
            g.inTurn.addResourceCard(stolen);
        }
        else{;
            return;
        }
    }
    public boolean activateRobberWithInputs(Game g, int moveRobber, String stealName) {
        for (int i = 0; i < g.playerNum; i++) {
            while (g.players[i].resources.size() > 7) {
                g.players[i].removeRandomCard();
                //System.out.println(g.players[i].name + " : " + g.players[i].resources.size());
            }
        }
        if (moveRobber < 0 || moveRobber > 18) {
            return false;
        }
        g.gameBuildingHandler.board.moveRobber(moveRobber);
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            Structure s = g.gameBuildingHandler.board.hexes[moveRobber].intersections[i].structure;
            if (s != null) {
                if (!s.color.equals(g.inTurn.color)) {
                    if (!names.contains(g.getPlayerNameByColor(s.color))) {
                        names.add(g.getPlayerNameByColor(s.color));
                    }
                }
            }
        }
        if (names.isEmpty()) {
            return true;
        }
        Player stealFrom = null;
        for (int i = 0; i < g.playerNum; i++) {
            if (g.players[i].name.equals(stealName)) {
                stealFrom = g.players[i];
                break;
            }
        }
        if (stealFrom == null) {
            return false;
        }
        ResourceCard stolen = stealFrom.removeRandomCard();
        if (stolen != null) {
            g.inTurn.addResourceCard(stolen);
        } else {
            return true;
        }
        return true;
    }

    //Public for testing purposes
    public Structure getStuctureByHex(Game g, int hexNum, int intersectionNum){
        if (g.gameBuildingHandler.board.hexes.length < hexNum){
            return null;
        }
        Hex hex = g.gameBuildingHandler.board.hexes[hexNum];
        if (hex == null){
            return null;
        } else if (hex.intersections.length < intersectionNum) {
            return null;
        }
        Intersection intersection = hex.intersections[intersectionNum];
        if (intersection == null){
            return null;
        }
        return intersection.structure;
    }
}
