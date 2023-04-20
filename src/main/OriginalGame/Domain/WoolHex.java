package Domain;

import Domain.Hex;

import java.awt.*;

public class WoolHex extends Hex {
    public WoolHex(int index) {
        super(index);
    }

    @Override
    public String getResource() {
        return "Wool";
    }

    @Override
    public Color getColor() {
        return new Color(136, 239, 167);
    }
}
