package MYgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
    
    private Handler handler;
    private int size;
    private int speed;
    
    public FastEnemy(int x, int y, ID id, Handler handler, int size, int speed){
        super(x, y, id);
        this. handler = handler;
        this.speed = speed;
        this.size = size;
        velY = speed;
        velX = 2;
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if(y <= 0 || y >= Game.HEIGHT -45){
            velY *= -1;
        }
        if(x <= 0 || x >=Game.WIDTH-20){
            velX *= -1;
        }
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, size, size, 0.03f, handler));

    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.CYAN);
        g.fillRect((int)x, (int)y, size, size);
    }
    
}
