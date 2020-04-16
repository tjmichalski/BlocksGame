package MYgame;

import java.util.Random;

public class Spawn {
    
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int speed1, speed2 = 9;
    private int size1;
    
    private int scoreKeep = 0;
    
    public Spawn(Handler handler, HUD hud){
        
        this.handler = handler;
        this.hud = hud;
        
    }
    public void setSize1(int size){
        size1 = size;
    }
    public void setSpeed1(int speed){
        speed1 = speed;
    }
    public int getSpeed1(){
        return speed1;
    }
    public int getSpeed2() {
        return speed2;
    }

    public void setSpeed2(int speed2) {
        this.speed2 = speed2;
    }
    
    
    public void tick(){
        
        scoreKeep++;
        
        if(scoreKeep >= 100){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            
            if(hud.getLevel() == 2){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, speed1));
            }
            else if(hud.getLevel() == 3){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, speed1));
            }
            else if(hud.getLevel() == 4){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler, size1, speed2));
            }
            else if(hud.getLevel() == 5){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.SmartEnemy, handler));
            }
            else if(hud.getLevel() == 6){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler, size1, speed2));
            }
            else if(hud.getLevel() == 7){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.FastEnemy, handler, size1, speed2));
            }
            else if(hud.getLevel() == 10){
                handler.clearEnemies();
                handler.addObject(new EnemyBoss((Game.WIDTH/2)-48, -120, ID.EnemyBoss, handler));
            }
        }
    }
    
    public void setScoreKeep(int scoreKeep){
        this.scoreKeep = scoreKeep;
    }
    
}
