package MYgame;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{

    private Handler handler;
    public static int spawnChance = 10;
    
    private int timer = 50;
    private int timer2 = 50;
    private Random r = new Random();
    
    public EnemyBoss(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this. handler = handler;
        
        velY = 2;
        velX = 0;
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 96, 96);
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        if(timer <= 0){
            velY = 0;
            timer2--;
        }
        else timer --;
        
        if(timer2 <= 0){
            //initial horizontal movement
            if(velX == 0) velX = 2;
            
            //speeds boss up
            if(velX > 0) velX += 0.005f;
            else if (velX < 0) velX -= 0.005f;
            
            //restricts velocity to -10 -- 10
            velX = Game.clamp(velX, -10, 10);
            
            int spawn = r.nextInt(spawnChance);
            if(spawn == 0) handler.addObject(new EnemyBossBullet((int)x+48, (int)y+48, ID.BasicEnemy, handler));
        }
        if(x <= 0 || x >= Game.WIDTH - 100){
            velX *= -1;
        }
    }

    @Override
    public void render(Graphics g) {
        
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 96, 96);

    }
    
    
    
}