package Domain;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class SpecialtyCard extends NonPlayableCard {

    private String type;

    public SpecialtyCard(String type) { this.type = type; }

    public int getPointValue() {
        return 2;
    }

    public String getType() { return this.type; }
}


