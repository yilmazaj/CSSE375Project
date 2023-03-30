package Team7.SettlersOfCatan;

import java.awt.*;

public class OreHex extends Hex {
    public OreHex(int index) {
        super(index);
    }

    @Override
    public String getResource() {
        return "Ore";
    }

    @Override
    public Color getColor() {
        return new Color(169, 165, 169);
    }
}
