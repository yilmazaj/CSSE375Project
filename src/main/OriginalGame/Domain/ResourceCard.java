package Domain;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class ResourceCard extends NonPlayableCard {

    protected String type;

    public ResourceCard(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public int getPointValue() {
        return 0;
    }
}
