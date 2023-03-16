//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import Team7.SettlersOfCatan.*;

import java.awt.Color;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {
    PlayerTest() {
    }

    @Test
    void testInitializedPlayer() {
        String name = "Name";
        Color color = Color.BLACK;
        Player player = new Player(name, color);
        Assertions.assertEquals(player.name, name);
        Assertions.assertEquals(player.color, color);
    }

    @Test
    void testAddResourceCard() {
        Player p = new Player("Player1", Color.WHITE);
        ResourceCard c1 = new ResourceCard("Lumber");
        ResourceCard c2 = new ResourceCard("Brick");
        p.addResourceCard(c1);
        p.addResourceCard(c2);
        Assertions.assertTrue(p.resources.contains(c1));
        Assertions.assertTrue(p.resources.contains(c2));
    }

    @Test
    void testRemoveResourceCard() {
        Player p = new Player("Player1", Color.WHITE);
        ResourceCard c1 = new ResourceCard("Lumber");
        ResourceCard c2 = new ResourceCard("Brick");
        p.addResourceCard(c1);
        p.addResourceCard(c2);
        p.removeResourceCard(c1.getType());
        p.removeResourceCard(c2.getType());
        Assertions.assertFalse(p.resources.contains(c1));
        Assertions.assertFalse(p.resources.contains(c2));
    }

    @Test
    void testContainsAllResources() {
        Player p1 = new Player("Player1", Color.WHITE);
        ResourceCard c1 = new ResourceCard("Lumber");
        ResourceCard c2 = new ResourceCard("Brick");
        ResourceCard c3 = new ResourceCard("Ore");
        ResourceCard c4 = new ResourceCard("Ore");
        p1.addResourceCard(c1);
        p1.addResourceCard(c2);
        p1.addResourceCard(c3);
        p1.addResourceCard(c4);
        Player p2 = new Player("Player2", Color.BLUE);
        p2.addResourceCard(c1);
        p2.addResourceCard(c2);
        p2.addResourceCard(c3);
        Player p3 = new Player("Player3", Color.RED);
        ArrayList<ResourceCard> test = new ArrayList();
        test.add(c1);
        test.add(c2);
        test.add(c3);
        test.add(c4);
        Assertions.assertTrue(p1.containsAllResources(test));
        Assertions.assertFalse(p2.containsAllResources(test));
        Assertions.assertFalse(p3.containsAllResources(test));
    }

    @Test
    void testRemoveAllResources() {
        Player p = new Player("Player1", Color.WHITE);
        ResourceCard c1 = new ResourceCard("Lumber");
        ResourceCard c2 = new ResourceCard("Brick");
        ArrayList<ResourceCard> rCards = new ArrayList();
        ResourceCard c3 = new ResourceCard("Lumber");
        ResourceCard c4 = new ResourceCard("Brick");
        rCards.add(c3);
        rCards.add(c4);
        p.addResourceCard(c1);
        p.addResourceCard(c2);
        p.removeAllResources(rCards);
        Assertions.assertFalse(p.resources.contains(c1));
        Assertions.assertFalse(p.resources.contains(c2));
    }

    @Test
    void testAddNonPlayableCard() {
        Player p = new Player("Player1", Color.WHITE);
        NonPlayableCard c1 = new VictoryPointCard();
        NonPlayableCard c2 = new MostRoads();
        NonPlayableCard c3 = new LargestArmy();
        p.addNonPlayableCard(c1);
        p.addNonPlayableCard(c2);
        p.addNonPlayableCard(c3);
        Assertions.assertEquals(p.victoryPoints, 5);
        Assertions.assertEquals(p.nCards.size(), 3);
    }

    @Test
    void testRemoveNonPlayableCard() {
        Player p = new Player("Player1", Color.WHITE);
        NonPlayableCard c1 = new VictoryPointCard();
        NonPlayableCard c2 = new MostRoads();
        p.addNonPlayableCard(c1);
        p.addNonPlayableCard(c2);
        p.removeNonPlayableCard(c2);
        Assertions.assertEquals(p.victoryPoints, 1);
        Assertions.assertEquals(p.nCards.size(), 1);
    }
}
