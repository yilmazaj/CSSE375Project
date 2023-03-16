//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import java.awt.Color;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameBoardTest {
    GameBoardTest() {
    }

    @Test
    void testConstructEmptyBoard() {
        GameBoard board = new GameBoard();
        Assertions.assertEquals(board.hexes.length, 19);
        Assertions.assertEquals(board.intersections.length, 54);
        Assertions.assertEquals(board.roads.length, 72);
    }

    @Test
    void testPlaceHexes() {
        GameBoard board = new GameBoard();
        board.placeHexes();

        for(int i = 0; i < 19; ++i) {
            Assertions.assertFalse(board.hexes[i].equals((Object)null));
        }

    }

    @Test
    void testPlaceHexesNumbered() {
        GameBoard board = new GameBoard();
        int[] nums = new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12, -1, -2};
        Hex[] var6;
        int var5 = (var6 = board.hexes).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            Hex h = var6[var4];
            int index = 0;
            int[] var11 = nums;
            int var10 = nums.length;

            for(int var9 = 0; var9 < var10; ++var9) {
                int i = var11[var9];
                if (h.getNumber() == i) {
                    nums[index] = 0;
                    break;
                }

                if (i == -2) {
                    Assertions.fail("Too much of one number");
                }

                ++index;
            }
        }

    }

    @Test
    void testMixHexes() {
        GameBoard board = new GameBoard();
        int[] nums = new int[]{2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12, -1};
        int count = 0;

        for(int i = 0; i < 19; ++i) {
            if (board.hexes[i].getNumber() == nums[i]) {
                ++count;
            }
        }

        if (count == 19) {
            Assertions.fail("Nothing is mixed up");
        }

    }

    @Test
    void testHexResources() {
        GameBoard board = new GameBoard();
        int grain = 0;
        int brick = 0;
        int wool = 0;
        int lumber = 0;
        int ore = 0;
        int none = 0;

        for(int i = 0; i < 19; ++i) {
            if (board.hexes[i].getResource().equals("Grain")) {
                ++grain;
            } else if (board.hexes[i].getResource().equals("Wool")) {
                ++wool;
            } else if (board.hexes[i].getResource().equals("Lumber")) {
                ++lumber;
            } else if (board.hexes[i].getResource().equals("Brick")) {
                ++brick;
            } else if (board.hexes[i].getResource().equals("Ore")) {
                ++ore;
            } else if (board.hexes[i].getResource().equals("None")) {
                ++none;
            }
        }

        Assertions.assertEquals(4, grain);
        Assertions.assertEquals(4, wool);
        Assertions.assertEquals(4, lumber);
        Assertions.assertEquals(3, brick);
        Assertions.assertEquals(3, ore);
        Assertions.assertEquals(1, none);
    }

    @Test
    void testSetIntersectionsHexes() {
        GameBoard board = new GameBoard();

        int i;
        for(i = 0; i < 54; ++i) {
            Assertions.assertFalse(board.intersections[i].equals((Object)null));
            Assertions.assertFalse(board.intersections[i].hexes[0].equals((Object)null));
        }

        for(i = 0; i < 19; ++i) {
            Assertions.assertFalse(board.hexes[i].intersections[0].equals((Object)null));
            Assertions.assertFalse(board.hexes[i].intersections[1].equals((Object)null));
            Assertions.assertFalse(board.hexes[i].intersections[2].equals((Object)null));
            Assertions.assertFalse(board.hexes[i].intersections[3].equals((Object)null));
            Assertions.assertFalse(board.hexes[i].intersections[4].equals((Object)null));
            Assertions.assertFalse(board.hexes[i].intersections[5].equals((Object)null));
        }

    }

    @Test
    void testSetRoads() {
        GameBoard board = new GameBoard();

        int i;
        for(i = 0; i < 72; ++i) {
            Assertions.assertFalse(board.roads[i].equals((Object)null));
        }

        for(i = 0; i < 54; ++i) {
            Assertions.assertFalse(board.intersections[i].roads[0].equals((Object)null));
        }

    }

    @Test
    void testInitializeRobber() {
        GameBoard board = new GameBoard();
        Assertions.assertTrue(board.hexes[board.findRobberIndex()].getResource().equals("None"));
    }

    @Test
    void testMoveRobber() {
        GameBoard board = new GameBoard();
        board.moveRobber(10);
        Assertions.assertEquals(board.findRobberIndex(), 10);
    }

    @Test
    void testAddStructure() {
        GameBoard board = new GameBoard();
        Structure city = new City(Color.RED);
        Structure settlement = new Settlement(Color.BLUE);
        board.roads[0].color = Color.RED;
        board.roads[24].color = Color.BLUE;
        Assertions.assertTrue(board.addStructure(city, 0));
        Assertions.assertTrue(board.addStructure(settlement, 18));
        Assertions.assertFalse(board.addStructure(settlement, 0));
        Assertions.assertFalse(board.addStructure(settlement, 5));
    }

    @Test
    void testGetRoadByIntersections() {
        GameBoard board = new GameBoard();
        Assertions.assertTrue(board.getRoadByIntersections(0, 1) != null);
        Assertions.assertTrue(board.getRoadByIntersections(1, 0) != null);
        Assertions.assertTrue(board.getRoadByIntersections(0, 30) == null);
        Assertions.assertTrue(board.getRoadByIntersections(-1, 0) == null);
    }

    @Test
    void testGetStructuresOnRolledHexes() {
        GameBoard board = new GameBoard();
        int roll = board.hexes[0].getNumber();
        Structure s1 = new Settlement(Color.BLUE);
        Structure s2 = new City(Color.RED);
        Structure s3 = new City(Color.RED);
        board.hexes[0].intersections[0].structure = s1;
        board.hexes[0].intersections[1].structure = s2;
        board.hexes[0].intersections[2].structure = s3;
        ArrayList<Structure> structures = board.getStructuresOnRolledHexes(roll);
        Assertions.assertEquals(structures.size(), 3);
        Assertions.assertTrue(structures.contains(s1));
        Assertions.assertTrue(structures.contains(s2));
        Assertions.assertTrue(structures.contains(s3));
    }
}
