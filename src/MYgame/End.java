package MYgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class End extends MouseAdapter{
    
    Handler handler;
    Game game;
    
    public End(Game game, Handler handler){
        this.handler = handler;
        this.game = game;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == Game.STATE.End){
            //play
            if(mouseOver(mx, my, 210, 220, 200, 64)){
                game.gameState = Game.STATE.Menu;
                game.completeReset();
            }else if(mouseOver(mx, my, 320, 220, 200, 64)){
                System.exit(0);
            }else if(mouseOver(mx, my, 245, 140, 130, 40)){
                game.gameState = Game.STATE.Upgrades;
            }
        }
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }
    
    public void render(Graphics g){
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 20);
        Font fnt3 = new Font("Times New Roman", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Game Over", 175, 80);
        
        g.setFont(fnt2);
        g.drawString("You lost with a score of: " + (int)game.getScore(), 185, 110);
        
        g.setFont(fnt3);
        g.drawRect(245, 140, 130, 40);
        g.drawString("Upgrades", 250, 170);
        
        g.drawRect(210, 220, 200, 64);
        g.drawString("Main Menu", 240, 260);
       
        g.drawRect(210, 320, 200, 64);
        g.drawString("Quit", 280, 360);
    }
    
    public void tick(){
        
    }
    
}
