//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

import java.awt.Color;

public class Road extends Buildable {
    protected int intersection1Num;
    protected int intersection2Num;
    protected int indexNum;
    public Color color;

    public Road(int i1, int i2, int index) {
        this.intersection1Num = i1;
        this.intersection2Num = i2;
        this.indexNum = index;
        this.color = null;
    }

    public void activateRoad(Color color) {
        this.color = color;
    }

    public boolean isBetweenIntersections(int i1, int i2) {
        if (this.intersection1Num == i1) {
            if (this.intersection2Num == i2) {
                return true;
            }
        } else if (this.intersection1Num == i2 && this.intersection2Num == i1) {
            return true;
        }

        return false;
    }
}
