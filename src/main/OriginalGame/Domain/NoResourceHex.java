package Domain;

import java.awt.*;

public class NoResourceHex extends Hex {
    public NoResourceHex(int i) {
        super(i);
    }

    @Override
    public String getResource(){
        return "None";
    }

    @Override
    public Color getColor() {
        return new Color(255, 255, 240);
    }
}
