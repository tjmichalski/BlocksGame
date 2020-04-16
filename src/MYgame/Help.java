package MYgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Help extends MouseAdapter{

    Game game; 
    
    public Help(Game game, Handler handler){
        
        this.game = game;
        
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(mouseOver(mx, my, 0, 415, 100, 32)){
            game.gameState = Game.STATE.Menu;
        }
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    
    public void tick(){
        
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }
    
    public void render(Graphics g){
        Font fnt = new Font("Arial", 1, 50);
        Font fnt2 = new Font("Arial", 1, 25);
        Font fnt3 = new Font("Times New Roman", 1, 20);
        
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Help Menu", 195, 100);
        
        g.setFont(fnt2);
        g.drawRect(0, 415, 100, 32);
        g.drawString("Back", 15, 440);
        
        g.setFont(fnt3);
        g.drawString("Use WASD keys to move your character around the field", 85, 200);
        g.drawString("to avoid being hit by enemies!", 190, 225);
        
        
        
    }
    
}
