//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Team7.SettlersOfCatan;

public class ResourceCard extends NonPlayableCard {
    protected String type;
    int pointValue = 0;

    public ResourceCard(String t) {
        this.type = t;
    }

    public String getType() {
        return this.type;
    }

    public int getPointValue() {
        return this.pointValue;
    }
}
