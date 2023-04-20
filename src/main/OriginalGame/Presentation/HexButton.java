package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//https://forums.codeguru.com/showthread.php?478270-Hexagonal-Buttons
/**
 * A six sided toggle button. This is not guaranteed to be a perfect hexagon, it is just guaranteed to have six sides in
 * the form of a hexagon. To be a perfect hexagon the size of this component must have a height to width ratio of
 * 1 to 0.866
 *
 * @author keang
 * @date 5 Jun 2009
 *
 */
public class HexButton extends JButton
{
    private static final long serialVersionUID = 4865976127980106774L;

    private Polygon hexagon = new Polygon();

    private boolean paint = false;

    /**
     * @param arg0
     */
    public HexButton(String arg0)
    {

        super(arg0);

        addMouseListener(new MouseAdapter() {

                             public void mouseEntered(java.awt.event.MouseEvent e) {
                                 paint = hexagon.contains(e.getPoint());
//                                 System.out.println(paint);
                             }

                             public void mouseExited(java.awt.event.MouseEvent e) {
//                                 System.out.println("What");
                                 paint = hexagon.contains(e.getPoint());
//                                 System.out.println(paint);
                             }
                         });
//        addMouseMotionListener(new MouseMotionListener() {
//
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                paint = hexagon.contains(e.getPoint());
//                System.out.println(paint);
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                paint = hexagon.contains(e.getPoint());
//                System.out.println(paint);
//            }
//
//    });
    }

    @Override
    public boolean contains(Point p)
    {
        return hexagon.contains(p);
    }

    @Override
    public boolean contains(int x, int y)
    {
        return hexagon.contains(x, y);
    }

    @Override
    public void setSize(Dimension d)
    {
        super.setSize(d);
        calculateCoords();
    }

    @Override
    public void setSize(int w, int h)
    {
        super.setSize(w, h);
        calculateCoords();
    }

    @Override
    public void setBounds(int x, int y, int width, int height)
    {
        super.setBounds(x, y, width, height);
        calculateCoords();
    }

    @Override
    public void setBounds(Rectangle r)
    {
        super.setBounds(r);
        calculateCoords();
    }

    @Override
    protected void processMouseEvent(MouseEvent e)
    {
        if ( contains(e.getPoint()) )
            super.processMouseEvent(e);
    }

    private void calculateCoords()
    {
        int w = getWidth()-1;
        int h = getHeight()-1;

        int ratio = (int)(h*.25);
        int nPoints = 6;
        int[] hexX = new int[nPoints];
        int[] hexY = new int[nPoints];

        hexX[0] = w/2;
        hexY[0] = 0;
        hexX[1] = w;
        hexY[1] = ratio;
        hexX[2] = w;
        hexY[2] = h - ratio;
        hexX[3] = w/2;
        hexY[3] = h;
        hexX[4] = 0;
        hexY[4] = h - ratio;
        hexX[5] = 0;
        hexY[5] = ratio;

        hexagon = new Polygon(hexX, hexY, nPoints);
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        setOpaque(false);
        super.paintComponent(g); //This maybe should be commented out
//        setOpaque(true);
    }

    @Override
    protected void paintBorder(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(getBackground());
        if (paint)
        {
//            System.out.println("NOTHERE");
            g.setColor(Color.blue);
        }
        else
        {
//            System.out.println("HERE");
            g.setColor(Color.black);
        }

//

        g2.setStroke(new BasicStroke(8));
        g.drawPolygon(hexagon);
    }

}