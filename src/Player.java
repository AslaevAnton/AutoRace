import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public static final int MAX_V=50;
    public static final int MAX_TOP=100;
    public static final int MAX_BOTTOM=430;
    Image img = new ImageIcon(getClass().getClassLoader().getResource("res/car_blue.png")).getImage();
    int x=30,
        y=150,
        dy=0,
        v=0,
        dv=0,
        s=0,
        road1=0,
        road2=1200;

    public void move(){
        s+=v;
        v+=dv;
        v=v<=0?0:v;
        v=v>=MAX_V?MAX_V:v;
        y-=dy;
        y=y>=MAX_BOTTOM?MAX_BOTTOM:y;
        y=y<=MAX_TOP?MAX_TOP:y;
        road1-=v;
        road2-=v;
        if(road1<=-1200) {
            road1 = 0;
            road2 = 1200;
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT ){
            //dv=3;
            dv=1;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT ){
            //dv=-3;
            dv=-1;

        }
        if(e.getKeyCode()==KeyEvent.VK_UP ){
            dy=7;

        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN ){
            dy=-7;

        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            //dv = -1;
            dv=0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(x,y,170,70);
    }
}
