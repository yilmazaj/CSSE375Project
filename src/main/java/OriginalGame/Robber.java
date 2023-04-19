//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import Team7.SettlersOfCatan.Presentation.Intersection;

import javax.swing.*;
import java.util.ArrayList;

public class Robber {
    public Robber() {

    }

    public void activateRobber(Game g) {
        for(int i = 0; i < g.playerNum; i++) {
            while(g.players[i].resources.size() > 7) {
                g.players[i].removeRandomCard();
                //System.out.println(g.players[i].name + " : " + g.players[i].resources.size());
            }
        }
        int moveRobber = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose a hex to move the robber to", ""));
        while(moveRobber < 0 || moveRobber > 18) {
            moveRobber = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter a valid hex number", ""));
        }
        g.board.moveRobber(moveRobber);
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
            JOptionPane.showMessageDialog(null, "No adjacent players to steal from", "Robber", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
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
            JOptionPane.showMessageDialog(null, stealFrom.name + " has no resources to steal", "Robber", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //For automated testing
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
        g.board.moveRobber(moveRobber);
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            Structure s = g.board.hexes[moveRobber].intersections[i].structure;
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
        if (g.board.hexes.length < hexNum){
            return null;
        }
        Hex hex = g.board.hexes[hexNum];
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
