import Domain.Dice;
import Domain.Game;
import Domain.GameTest;
import Domain.ResourceCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewGameTest {


    private GameTest game;

    @BeforeEach
    public void setup(){
        game = new GameTest(2);
    }

    private void giveSettlementResources(){
        ResourceCard c1 = new ResourceCard("Brick");
        ResourceCard c2 = new ResourceCard("Grain");
        ResourceCard c3 = new ResourceCard("Lumber");
        ResourceCard c4 = new ResourceCard("Wool");
        game.inTurn.addResourceCard(c1);
        game.inTurn.addResourceCard(c2);
        game.inTurn.addResourceCard(c3);
        game.inTurn.addResourceCard(c4);
    }

    private void doAllDiceRollValues(){
        for(int i = 1; i < 15; i++){
            if (i == 7) continue;
            game.simulateHandleDiceRoll(i);
        }
    }
    @Test
    public void testRollForResources2(){
        int startingResources = game.inTurn.resources.size();

        for(int i = 10; i< 14; i= i+2){
            giveSettlementResources();
            game.buildStructure("Settlement", i);
        }

        assertEquals(game.inTurn.resources.size(),startingResources);
        doAllDiceRollValues();
        assertTrue(startingResources < game.inTurn.resources.size());

    }

    @Test
    public void testRollForResources1(){
        int startingResources = game.inTurn.resources.size();

        giveSettlementResources();
        game.buildStructure("Settlement", 10);

        doAllDiceRollValues();
        assertTrue(startingResources < game.inTurn.resources.size());

    }




//    @Test
//    void testActivateRobber2() {
//        Domain.Game game = new Domain.Game(2);
//        Domain.Dice dice = game.dice;
//        dice.rollDice();
//        dice.setTotal(8);
//
//        Domain.ResourceCard c1 = new Domain.ResourceCard("Brick");
//        Domain.ResourceCard c2 = new Domain.ResourceCard("Grain");
//        Domain.ResourceCard c3 = new Domain.ResourceCard("Lumber");
//        Domain.ResourceCard c4 = new Domain.ResourceCard("Wool");
//        game.inTurn.addResourceCard(c1);
//        game.inTurn.addResourceCard(c2);
//        game.inTurn.addResourceCard(c3);
//        game.inTurn.addResourceCard(c4);
//        game.buildStructure("Settlement", 1);
//
//        Domain.ResourceCard c1 = new Domain.ResourceCard("Brick");
//        Domain.ResourceCard c2 = new Domain.ResourceCard("Grain");
//        Domain.ResourceCard c3 = new Domain.ResourceCard("Lumber");
//        Domain.ResourceCard c4 = new Domain.ResourceCard("Wool");
//        Domain.ResourceCard c5 = new Domain.ResourceCard("Ore");
//        Domain.ResourceCard c6 = new Domain.ResourceCard("Ore");
//        Domain.ResourceCard c7 = new Domain.ResourceCard("Ore");
//        Domain.ResourceCard c8 = new Domain.ResourceCard("Grain");
//        Domain.ResourceCard c9 = new Domain.ResourceCard("Grain");
//        Domain.ResourceCard c10 = new Domain.ResourceCard("Ore");
//        game.inTurn.addResourceCard(c1);
//        game.inTurn.addResourceCard(c2);
//        game.inTurn.addResourceCard(c3);
//        game.inTurn.addResourceCard(c4);
//        game.inTurn.addResourceCard(c5);
//        game.inTurn.addResourceCard(c6);
//        game.inTurn.addResourceCard(c7);
//        game.inTurn.addResourceCard(c8);
//        game.inTurn.addResourceCard(c9);
//        game.inTurn.addResourceCard(c10);
//        int indexBefore = game.board.findRobberIndex();
//        game.activateRobber();
//        assertTrue(game.inTurn.resources.size() < 9);
//    }

//    @Test
//    void testRollForResources() {
//        Domain.Game game = new Domain.Game();
//        int numResources = game.inTurn.resources.size();
//
//        int roll;
//        for(roll = 0; roll < 19; ++roll) {
//            Domain.ResourceCard c1 = new Domain.ResourceCard("Brick");
//            Domain.ResourceCard c2 = new Domain.ResourceCard("Grain");
//            Domain.ResourceCard c3 = new Domain.ResourceCard("Lumber");
//            Domain.ResourceCard c4 = new Domain.ResourceCard("Wool");
//            game.inTurn.addResourceCard(c1);
//            game.inTurn.addResourceCard(c2);
//            game.inTurn.addResourceCard(c3);
//            game.inTurn.addResourceCard(c4);
//            game.buildStructure("Settlement", roll);
//            --game.inTurn.numSettlements;
//        }
//
//        roll = game.handleDiceRoll();
//        if (roll != 7) {
//            assertTrue(numResources < game.inTurn.resources.size());
//        }
//
//    }

//    @Test
//    void testActivateRobber() {
//        Domain.Game game = new Domain.Game();
//        game.buildInitialStructures();
//        Domain.ResourceCard c1 = new Domain.ResourceCard("Brick");
//        Domain.ResourceCard c2 = new Domain.ResourceCard("Grain");
//        Domain.ResourceCard c3 = new Domain.ResourceCard("Lumber");
//        Domain.ResourceCard c4 = new Domain.ResourceCard("Wool");
//        Domain.ResourceCard c5 = new Domain.ResourceCard("Ore");
//        Domain.ResourceCard c6 = new Domain.ResourceCard("Ore");
//        Domain.ResourceCard c7 = new Domain.ResourceCard("Ore");
//        Domain.ResourceCard c8 = new Domain.ResourceCard("Grain");
//        Domain.ResourceCard c9 = new Domain.ResourceCard("Grain");
//        Domain.ResourceCard c10 = new Domain.ResourceCard("Ore");
//        game.inTurn.addResourceCard(c1);
//        game.inTurn.addResourceCard(c2);
//        game.inTurn.addResourceCard(c3);
//        game.inTurn.addResourceCard(c4);
//        game.inTurn.addResourceCard(c5);
//        game.inTurn.addResourceCard(c6);
//        game.inTurn.addResourceCard(c7);
//        game.inTurn.addResourceCard(c8);
//        game.inTurn.addResourceCard(c9);
//        game.inTurn.addResourceCard(c10);
//        int indexBefore = game.board.findRobberIndex();
//        game.activateRobber();
//        assertTrue(game.inTurn.resources.size() < 9);
//    }
}
