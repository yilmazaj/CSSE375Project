package Domain;

import Presentation.GameBoard;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class GameBuildingHandler implements Serializable {
    public GameBoard board;
    private boolean testMode;

    public GameBuildingHandler(boolean testMode) {
        this.board = new GameBoard();
        this.testMode = testMode;
    }

    public boolean buildRoad(int i1, int i2, Player inTurn) {
        Road temp = board.getRoadByIntersections(i1, i2);
        ArrayList<ResourceCard> requiredResources = new ArrayList<ResourceCard>();
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Lumber");
        requiredResources.add(c1);
        requiredResources.add(c2);
        if (temp == null) {
            if(!this.testMode)
                JOptionPane.showMessageDialog(null, "There is no path between those two intersections", "Failed to build road", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (temp.color != null) {
            if(!this.testMode)
                JOptionPane.showMessageDialog(null, "There is already a road between those two intersections", "Failed to build road", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ((board.intersections[i1].structure != null && board.intersections[i1].structure.color.equals(inTurn.color))
                || (board.intersections[i2].structure != null && board.intersections[i2].structure.color.equals(inTurn.color))) {
            if (inTurn.containsAllResources(requiredResources)) {
                board.roads[temp.indexNum].activateRoad(inTurn.color);
                board.intersections[i1].updateRoad(i1, i2, board.roads[temp.indexNum]);
                board.intersections[i2].updateRoad(i2, i1, board.roads[temp.indexNum]);
                inTurn.numRoads++;
                inTurn.removeAllResources(requiredResources);
                // AJ CODE
                board.invalidate();
                board.roads[temp.indexNum].color = inTurn.color;
                board.repaint();
                // AJ CODE
                return true;
            } else {
                if(!this.testMode)
                    JOptionPane.showMessageDialog(null, "Player does not have the brick and lumber to build a road", "Failed to build road", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        if (board.intersections[i1].structure == null || board.intersections[i2].structure == null) {
            if (board.intersections[i1].containsRoad(inTurn.color) || board.intersections[i2].containsRoad(inTurn.color)) {
                if (inTurn.containsAllResources(requiredResources)) {
                    board.roads[temp.indexNum].activateRoad(inTurn.color);
                    board.intersections[i1].updateRoad(i1, i2, board.roads[temp.indexNum]);
                    board.intersections[i2].updateRoad(i2, i1, board.roads[temp.indexNum]);
                    inTurn.numRoads++;
                    inTurn.removeAllResources(requiredResources);
                    // AJ CODE
                    board.invalidate();
                    board.roads[temp.indexNum].color = inTurn.color;
                    board.repaint();
                    // AJ CODE
                    return true;
                } else {
                    if(!this.testMode)
                        JOptionPane.showMessageDialog(null, "Player does not have the brick and lumber to build a road", "Failed to build road", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                if(!this.testMode)
                    JOptionPane.showMessageDialog(null, "New road connects to no friendly structures or roads", "Failed to build road", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (!board.intersections[i1].structure.color.equals(inTurn.color) && !board.intersections[i1].structure.color.equals(inTurn.color)) {
            if(!this.testMode)
                JOptionPane.showMessageDialog(null, "Can't place a road between two enemy structures", "Failed to build road", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;

    }

    public boolean buildStructure(String type, int intersection, Player inTurn) {
        if (intersection < 0 || intersection > 53) {
            if(!this.testMode)
                JOptionPane.showMessageDialog(null, "Invalid intersection number entered", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Structure s = null;
        ArrayList<ResourceCard> requiredResources = new ArrayList<ResourceCard>();
        if (type.equals("Settlement") || type.equals("settlement")) {
            if (board.intersections[intersection].structure == null) {
                if (inTurn.numSettlements < 5) {
                    ResourceCard c1 = new ResourceCard("Brick");
                    ResourceCard c2 = new ResourceCard("Grain");
                    ResourceCard c3 = new ResourceCard("Lumber");
                    ResourceCard c4 = new ResourceCard("Wool");
                    requiredResources.add(c1);
                    requiredResources.add(c2);
                    requiredResources.add(c3);
                    requiredResources.add(c4);
                    if (inTurn.containsAllResources(requiredResources)) {
                        s = new Structure(inTurn.color, "Settlement");
                        inTurn.numSettlements++;
                        board.intersections[intersection].structure = s;
                        s.hexes = board.intersections[intersection].hexes;
                        inTurn.removeAllResources(requiredResources);
                        // AJ CODE
                        board.invalidate();
                        board.intersectionPoints[intersection].color = inTurn.color;
                        board.repaint();
                        // AJ CODE
                        return true;
                    } else {
                        if(!this.testMode)
                            JOptionPane.showMessageDialog(null, "Player does not have the brick, grain, lumber, and wool to build a settlement", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    if(!this.testMode)
                        JOptionPane.showMessageDialog(null, "Player can not place any more settlements", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                if(!this.testMode)
                    JOptionPane.showMessageDialog(null, "Intersection already has a structure", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else if (type.equals("City") || type.equals("city")) {
            if (board.intersections[intersection].structure != null &&
                    board.intersections[intersection].structure.color.equals(inTurn.color) &&
                    board.intersections[intersection].structure.getType().equals("Settlement")) {
                if (inTurn.numCities < 4) {
                    ResourceCard c1 = new ResourceCard("Ore");
                    ResourceCard c2 = new ResourceCard("Ore");
                    ResourceCard c3 = new ResourceCard("Ore");
                    ResourceCard c4 = new ResourceCard("Grain");
                    ResourceCard c5 = new ResourceCard("Grain");
                    requiredResources.add(c1);
                    requiredResources.add(c2);
                    requiredResources.add(c3);
                    requiredResources.add(c4);
                    requiredResources.add(c5);
                    if (inTurn.containsAllResources(requiredResources)) {
                        s = new Structure(inTurn.color, "City");
                        inTurn.numCities++;
                        board.intersections[intersection].structure = s;
                        s.hexes = board.intersections[intersection].hexes;
                        inTurn.removeAllResources(requiredResources);
                        return true;
                    } else {
                        if(!this.testMode)
                            JOptionPane.showMessageDialog(null, "Player does not have the 3 ore and 2 grain to build a city", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    if(!this.testMode)
                        JOptionPane.showMessageDialog(null, "Player can not place any more cities", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                if(!this.testMode)
                    JOptionPane.showMessageDialog(null, "Player must already have a settlement at this intersection", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } else {
            if(!this.testMode)
                JOptionPane.showMessageDialog(null, "Invalid Structure name entered", "Failed to build structure", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public int waitForPlayerIntersectionChoice() {
        board.enableIntersectionButtons(true);
        int selected = -1;
        while (selected == -1) {
            try {
                if(!this.testMode)
                    selected = board.getSelectedIntersection();
                else
                    selected = 0;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        board.enableIntersectionButtons(false);
        return selected;
    }

    public void buildRoadsUI(Player inTurn) {
        JOptionPane.showMessageDialog(null, "Click two intersections for your first road!", inTurn.name + "'s turn!", JOptionPane.INFORMATION_MESSAGE);
        int road1Int1 = waitForPlayerIntersectionChoice();
        int road1Int2 = waitForPlayerIntersectionChoice();
        while (!buildRoad(road1Int1, road1Int2, inTurn)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid set of intersections for your road", "Invalid road", JOptionPane.ERROR_MESSAGE);
            road1Int1 = waitForPlayerIntersectionChoice();
            road1Int2 = waitForPlayerIntersectionChoice();
        }
        JOptionPane.showMessageDialog(null, "Click two intersections for your second road!", inTurn.name + "'s turn!", JOptionPane.INFORMATION_MESSAGE);
        int road2Int1 = waitForPlayerIntersectionChoice();
        int road2Int2 = waitForPlayerIntersectionChoice();
        while (!buildRoad(road2Int1, road2Int2, inTurn)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid set of intersections for your road", "Invalid road", JOptionPane.ERROR_MESSAGE);
            road2Int1 = waitForPlayerIntersectionChoice();
            road2Int2 = waitForPlayerIntersectionChoice();
        }
    }

    public ArrayList<Structure> getStructuresOnRolledHexes(int total) {
        return board.getStructuresOnRolledHexes(total);
    }

    public void buildStage(Player inTurn) {
//        int answer = JOptionPane.showConfirmDialog(null, "Would you like to build?", "Build?", JOptionPane.YES_NO_OPTION);
        int answer = JOptionPane.YES_OPTION;
        while (answer == JOptionPane.YES_OPTION) {
            String s = JOptionPane.showInputDialog(null, "Structure or Road?", "");
            if (s.equals("Structure")) {
                String type = JOptionPane.showInputDialog(null, "Settlement or City?", "");
                JOptionPane.showMessageDialog(null, "What intersection should this be placed at?", "Intersection", JOptionPane.INFORMATION_MESSAGE);
                int i = waitForPlayerIntersectionChoice();
                buildStructure(type, i, inTurn);
            } else if (s.equals("Road")) {
                JOptionPane.showMessageDialog(null, "Select two intersections for the road", "Intersection", JOptionPane.INFORMATION_MESSAGE);
                int i1 = waitForPlayerIntersectionChoice();
                int i2 = waitForPlayerIntersectionChoice();
                buildRoad(i1, i2, inTurn);
            } else {
                JOptionPane.showMessageDialog(null, "You must input either Structure or Road", "Invalid input!", JOptionPane.ERROR_MESSAGE);
            }
            answer = JOptionPane.showConfirmDialog(null, "Would you like to build more?", "Build?", JOptionPane.YES_NO_OPTION);
        }
    }
}