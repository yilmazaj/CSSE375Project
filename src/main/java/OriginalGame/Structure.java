//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Structure extends Buildable {
    public Color color;
    protected Hex[] hexes;
    private ImageIcon structureIcon;

    private String type;

    public Structure(Color s, String type) {
        this.color = s;
        this.type = type;
        this.hexes = new Hex[3];
        this.structureIcon = new ImageIcon("images/Icons/"+this.getType()+"Icon.png");
    }

    public String getType(){ return this.type; }

    public void drawIcon(Graphics2D g2, Point2D.Double point) {
        structureIcon.paintIcon(new Canvas(), g2, (int)Math.round(point.getX()),
                                (int)Math.round(point.getY()));
    }
}




