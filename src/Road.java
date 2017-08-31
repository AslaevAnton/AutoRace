import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class Road extends JPanel implements ActionListener, Runnable {
    //Панель окна

    Image img = new ImageIcon(getClass().getClassLoader().getResource("res/bg_road.jpg")).getImage();
    Player player = new Player();
    Timer mainTimer = new Timer(20, this); //Запускет actionPerform каждые 20 милисекунд
    Thread traffic = new Thread(this); //поток создающий соперников, нужен переопределить метод Run текущего экземпляра

    List <Enemy> enemies = new ArrayList<Enemy>(); //Коллекция машин траффика

    public Road(){
        mainTimer.start();
        traffic.start(); //Запускает поток
        addKeyListener(new MyKeyAdaptor());
        setFocusable(true);

    }
    public void paint(Graphics g){
        g=(Graphics2D)g;
        g.drawImage(img, player.road1,0, null);
        g.drawImage(img, player.road2,0, null);
        ((Graphics2D) g).drawImage(player.img, player.x, player.y, null);

        double v=(200/Player.MAX_V)*player.v;
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Скорость: "+v+" км/ч. Расстояние: "+player.s/100+"километров", 100, 80);

        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if(e.x<=-100)
                i.remove();;
            e.move();
            ((Graphics2D) g).drawImage(e.img, e.x, e.y, null);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        testCollisionCar();
        testWin();
        //System.out.println(enemies.size());
    }

    private void testWin() {
        if(player.s>50000){
            JOptionPane.showMessageDialog(null, "Победа!!");
            System.exit(0);
        }
    }

    private void testCollisionCar() {
        //Проверка столкновнения
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()){
            Enemy e = i.next();
            if(player.getRectangle().intersects(e.getRectangle())){
                JOptionPane.showMessageDialog(null, "Потрачено!!");
                System.exit(1);
            }

        }
    }

    @Override
    public void run() {
    // Бесконечно создаёт траффик
        while (true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(3000));
                if(enemies.size()<4)
                    enemies.add(new Enemy(1200, random.nextInt(350)+100, random.nextInt(30)+20, this)); // Добавляем машину в коллецию
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdaptor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
