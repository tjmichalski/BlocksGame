package MYgame;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

    private Handler handler;
    
    public BasicEnemy(int x, int y, ID id, Handler handler, int speed){
        super(x, y, id);
        this. handler = handler;
        
        velY = speed;
        velX = speed;
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
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));

    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);

    }
    
    
    
    
    
}
