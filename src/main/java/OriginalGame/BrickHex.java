package Team7.SettlersOfCatan;

import java.awt.*;

public class BrickHex extends Hex {
    public BrickHex(int index) {
        super(index);
    }

    @Override
    public String getResource() {
        return "Brick";
    }

    @Override
    public Color getColor() {
        return new Color(189, 79, 66);
    }
}
