
package MYgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Upgrades extends MouseAdapter{
    
    private Handler handler;
    private Game game;
    private HUD hud;
    private Spawn spawner;
    private Random r;
    
    public Upgrades(Handler handler, Game game, HUD hud, Spawn spawner){
        
        this.spawner = spawner;
        this.hud = hud;
        this.handler = handler;
        this.game = game;
        r = new Random();
    }
    
    public void render(Graphics g){
        Font fnt = new Font("Arial", 1, 25);
        Font fnt2 = new Font("Arial", 1, 20);
        Font fnt3 = new Font("Arial", 1, 15);
        
        g.setFont(fnt3);
        
        g.drawString("Your total points remaining: " + hud.getScore(), 200, 25);
        
        g.fillRect(43, 50, 245, 80);        
        g.fillRect(338, 50, 245, 80);
        g.fillRect(43, 185, 245, 80);
        g.fillRect(43, 320, 245, 80);
        g.fillRect(338, 185, 245, 80);
        g.fillRect(338, 320, 245, 80);
        
        g.setFont(fnt);
        g.setColor(Color.black);
        g.drawString("Extra Health", 90, 85);
        g.drawString("Point Multiplier", 370, 85);
        g.drawString("Faster Movement", 60, 220);
        g.drawString("Time Slow - X", 395, 220);
        g.drawString("Boss Ammo", 90, 355);
        g.drawString("Continue Game", 370, 365);
        
        g.setFont(fnt2);
        if(hud.MAXHEALTH < 300) g.drawString("200 Points", 115, 110);
        else if(hud.MAXHEALTH == 300) g.drawString("Complete", 115, 110);
        g.drawString("200 Points", 415, 110);
        g.drawString("200 Points", 115, 245);
        g.drawString("200 Points", 415, 245);
        g.drawString("200 Points", 115, 380);
    }
    
     @Override
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == Game.STATE.Upgrades && hud.getScore() > 200){
            if(mouseOver(mx, my, 43, 50, 245, 80) && hud.MAXHEALTH < 300){
               
               hud.setScore(hud.getScore() - 200);
               hud.MAXHEALTH += 20;
                
            }else if(mouseOver(mx, my, 338, 50, 245, 80)){
               hud.setScore(hud.getScore() - 200);
               hud.scoreMultiplier += .1;
            }else if(mouseOver(mx, my, 43, 185, 245, 80)){
                hud.setScore(hud.getScore() - 200);
                KeyInput.vel += 1;
            }else if(mouseOver(mx, my, 43, 320, 245, 80)){
                hud.setScore(hud.getScore() - 200);
                EnemyBoss.spawnChance += 1;
            }
        }if(game.gameState == Game.STATE.Upgrades){
            if(mouseOver(mx, my, 338, 320, 245, 80)){
                if(game.getDifficulty() == Game.Difficulty.Easy){
                    //easy
                    game.partialReset();
                    handler.clearMenuParticles();
                    handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, 3));
                    game.gameState = Game.STATE.Game;
                    spawner.setSpeed1(3);   
                    spawner.setSize1(16);
                }else if(game.getDifficulty() == Game.Difficulty.Medium){
                    //medium
                    game.partialReset();
                    handler.clearMenuParticles();
                    handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, 6));
                    game.gameState = Game.STATE.Game;
                    spawner.setSpeed1(6);
                    spawner.setSize1(16);
                }
                else if(game.getDifficulty() == Game.Difficulty.Hard){
                    //hard
                    game.partialReset();
                    handler.clearMenuParticles();
                    handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, 6));
                    game.gameState = Game.STATE.Game;
                    spawner.setSpeed1(6);
                    spawner.setSize1(30);
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }
}
