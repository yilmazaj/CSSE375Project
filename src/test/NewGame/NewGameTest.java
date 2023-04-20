import Team7.SettlersOfCatan.Dice;
import Team7.SettlersOfCatan.Game;
import Team7.SettlersOfCatan.Player;
import Team7.SettlersOfCatan.Presentation.GameBoard;
import Team7.SettlersOfCatan.ResourceCard;
//import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewGameTest {


    private Game game;
    private Dice dice;

    @BeforeEach
    public void setup(){
        game = new Game(2);
        dice = game.dice;
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
            dice.setTotal(i);
            game.handleDiceRoll();
        }
    }
    @Test
    public void testRollForResources2(){
        dice.rollDice();
        dice.setTotal(8);
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
        dice.rollDice();
        dice.setTotal(8);
        int startingResources = game.inTurn.resources.size();

        giveSettlementResources();
        game.buildStructure("Settlement", 10);

        doAllDiceRollValues();
        assertTrue(startingResources < game.inTurn.resources.size());

    }




//    @Test
//    void testActivateRobber2() {
//        Game game = new Game(2);
//        Dice dice = game.dice;
//        dice.rollDice();
//        dice.setTotal(8);
//
//        ResourceCard c1 = new ResourceCard("Brick");
//        ResourceCard c2 = new ResourceCard("Grain");
//        ResourceCard c3 = new ResourceCard("Lumber");
//        ResourceCard c4 = new ResourceCard("Wool");
//        game.inTurn.addResourceCard(c1);
//        game.inTurn.addResourceCard(c2);
//        game.inTurn.addResourceCard(c3);
//        game.inTurn.addResourceCard(c4);
//        game.buildStructure("Settlement", 1);
//
//        ResourceCard c1 = new ResourceCard("Brick");
//        ResourceCard c2 = new ResourceCard("Grain");
//        ResourceCard c3 = new ResourceCard("Lumber");
//        ResourceCard c4 = new ResourceCard("Wool");
//        ResourceCard c5 = new ResourceCard("Ore");
//        ResourceCard c6 = new ResourceCard("Ore");
//        ResourceCard c7 = new ResourceCard("Ore");
//        ResourceCard c8 = new ResourceCard("Grain");
//        ResourceCard c9 = new ResourceCard("Grain");
//        ResourceCard c10 = new ResourceCard("Ore");
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
//        Game game = new Game();
//        int numResources = game.inTurn.resources.size();
//
//        int roll;
//        for(roll = 0; roll < 19; ++roll) {
//            ResourceCard c1 = new ResourceCard("Brick");
//            ResourceCard c2 = new ResourceCard("Grain");
//            ResourceCard c3 = new ResourceCard("Lumber");
//            ResourceCard c4 = new ResourceCard("Wool");
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
//        Game game = new Game();
//        game.buildInitialStructures();
//        ResourceCard c1 = new ResourceCard("Brick");
//        ResourceCard c2 = new ResourceCard("Grain");
//        ResourceCard c3 = new ResourceCard("Lumber");
//        ResourceCard c4 = new ResourceCard("Wool");
//        ResourceCard c5 = new ResourceCard("Ore");
//        ResourceCard c6 = new ResourceCard("Ore");
//        ResourceCard c7 = new ResourceCard("Ore");
//        ResourceCard c8 = new ResourceCard("Grain");
//        ResourceCard c9 = new ResourceCard("Grain");
//        ResourceCard c10 = new ResourceCard("Ore");
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
