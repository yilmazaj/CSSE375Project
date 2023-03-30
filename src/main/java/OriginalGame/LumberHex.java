package Team7.SettlersOfCatan;

import java.awt.*;

public class LumberHex extends Hex {
    public LumberHex(int index) {
        super(index);
    }

    @Override
    public String getResource() {
        return "Lumber";
    }

    @Override
    public Color getColor() {
        return new Color(57, 98, 48);
    }
}
