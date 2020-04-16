package MYgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
    
    Random r = new Random();
    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
      
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }
    
    @Override
    public void tick() {

        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH-38);
        y = Game.clamp(y, 0, Game.HEIGHT-60);
        
        collision();
    }
    
    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){
                //temp object is now basic enemy
                if(getBounds().intersects(tempObject.getBounds())){
                    //actions when collision detected
                    HUD.HEALTH -= 2;
                    
                }
            }
            
        }
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX, float velY) {
        this.velX = velX;
        this.velY = velY;
    }

    public float getVelY() {
        return velY;
    }
    

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);

    }
    
    
    
    
    
}
