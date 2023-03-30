package Team7.SettlersOfCatan;

import java.awt.*;

public class GrainHex extends Hex {
    public GrainHex(int index) {
        super(index);
    }

    @Override
    public String getResource() {
        return "Grain";
    }

    @Override
    public Color getColor() {
        return new Color(250, 233, 144);
    }
}
