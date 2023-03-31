import Team7.SettlersOfCatan.Player;
import Team7.SettlersOfCatan.PlayerStatsGUI;
import Team7.SettlersOfCatan.PlayersStatsGUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsTest {

    @Test
    public void testPlayerStatsGUIUpdate(){
        Player[] players = new Player[3];
        players[0] = new Player("Jerry", Color.BLACK);

        PlayerStatsGUI pSG = new PlayerStatsGUI(players[0]);

        players[0].brickAmount = 3;

        pSG.updateResourcesView();

        JLabel label = (JLabel) pSG.resourceDisplayPanel.getComponent(0);

        assertEquals(label.getText(), "Brick: 3");
    }

}
