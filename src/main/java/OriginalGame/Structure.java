//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import java.awt.Color;

public abstract class Structure extends Buildable {
    protected Color color;
    protected Hex[] hexes;

    public Structure(Color s) {
        this.color = s;
        this.hexes = new Hex[3];
    }

    public abstract String getType();
}
