package Domain;

import javax.swing.*;
import java.awt.*;

public class BoardBuilder {
    public BoardBuilder(){

    }
    public Hex createHex(){
        if(!GraphicsEnvironment.isHeadless()){
            String resource = JOptionPane.showInputDialog(null, "Enter resource type (Grain, Wool, Lumber, Brick, Ore, None)");
            return createHexOfType(resource);
        } else {
            return createHexOfType("grain");
        }
    }

    public Hex createHexOfType(String resource) {
        if(resource == null){
            return null;
        } else if(resource.toLowerCase().equals("none")){
            return new NoResourceHex(7);
        }
        return createNumberedHexOfType(resource);
    }

    private Hex createNumberedHexOfType(String resource) {
        int hexNum = getManualHexNumber();
        return createHexOfTypeAndNumber(resource, hexNum);
    }

    public Hex createHexOfTypeAndNumber(String resource, int hexNum) {
        if(hexNum == 0){
            return null;
        } else if(resource.toLowerCase().equals("grain")){
            return new GrainHex(hexNum);
        } else if(resource.toLowerCase().equals("wool")){
            return new WoolHex(hexNum);
        } else if(resource.toLowerCase().equals("lumber")){
            return new LumberHex(hexNum);
        } else if(resource.toLowerCase().equals("brick")){
            return new BrickHex(hexNum);
        } else if(resource.toLowerCase().equals("ore")){
            return new OreHex(hexNum);
        } else {
            return null;
        }
    }

    public String showManualHexNumberUI(){
        if(!GraphicsEnvironment.isHeadless()){
            return JOptionPane.showInputDialog(null, "Enter this hex's number (2-12 and can't be 7)", "2");
        } else {
            return "2";
        }
    }

    public int validateManualHexNumberString(String numberStr){
        if(numberStr == null){
            return 0;
        }
        try{
            int number = Integer.parseInt(numberStr);
            if(number < 2 || number > 12 || number == 7){
                return -1;
            }
            return number;
        }catch(Exception e){
            return -1;
        }
    }

    public int getManualHexNumber(){
        String numberStr = showManualHexNumberUI();
        int number = validateManualHexNumberString(numberStr);
        if(number < 0){
            JOptionPane.showMessageDialog(null, "Hex numbers must be from 2-12 and can not be 7", "Invalid number", JOptionPane.INFORMATION_MESSAGE);
            return getManualHexNumber();
        }
        return number;
    }
}
