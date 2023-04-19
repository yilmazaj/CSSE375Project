package Team7.SettlersOfCatan.Presentation;

import java.awt.*;

public class GraphicsWithIndex{
    private Graphics2D g2;
    private int position;
    public GraphicsWithIndex(Graphics2D g2, int position){
        this.g2 = g2;
        this.position = position;
    }

    public void setNewPosition(int newPosition){
        this.position = newPosition;
    }

    public Graphics2D getGraphics(){
        return g2;
    }

    public int getPosition(){
        return position;
    }
    public GraphicsWithIndex initGraphicsWithIndex(Graphics2D g2, int position){
        return new GraphicsWithIndex(g2, position);
    }
}
