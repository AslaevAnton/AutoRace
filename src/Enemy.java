import javax.swing.*;
import java.awt.*;

public class Enemy {
    int x,
        y,
        v;
    Image img = new ImageIcon(getClass().getClassLoader().getResource("res/car_orange.png")).getImage();
    Road road;

    public Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }
    public void move(){
        x=x-road.player.v+v;
    }
    public Rectangle getRectangle(){
        return new Rectangle(x+5, y+10, 175, 60);
    }
}
