package MYgame;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
 
    public static float MAXHEALTH = 100;
    public static float HEALTH = 100;
    public double scoreMultiplier = 1;
    
    private float greenValue = 255; 
    private double score = 0; 
    private int level = 1;
    
    
    public void tick(){
        
        HEALTH = Game.clamp(HEALTH, 0.0f, MAXHEALTH);
        greenValue = HEALTH*2;
        greenValue = Game.clamp(greenValue, 0.0f, 255.0f);
                
        score = score + scoreMultiplier;   
    }
    
    public void partialReset(){
        level = 1;
        HEALTH = MAXHEALTH;
    }
    
    public void reset(){
        score = 0;
        level = 1; 
        HEALTH = 100;
        MAXHEALTH = 100;
    }
    
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, (int)MAXHEALTH*2, 32);
        g.setColor(new Color(75, (int)greenValue, 0));
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, (int)MAXHEALTH*2, 32);
        
        g.drawString("Score: " + (int)score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        
    }
    
    public int getLevel(){
        return level;
    }
    
    public void setLevel(int level){
        this.level = level;
    }
    
    public void setScore(double score){
        this.score = score;
    }
    
    public double getScore(){
        return score;
    }
    
}
