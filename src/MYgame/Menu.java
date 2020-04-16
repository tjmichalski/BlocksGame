package MYgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter{
    
    private Game game; 
    private Handler handler;
    private Random r = new Random();
    
    public Menu(Game game, Handler handler){
        this.game = game; 
        this.handler = handler;
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == Game.STATE.Menu){
            //play
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = Game.STATE.Select;
            }else if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(0);
            }
            else if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = Game.STATE.Help;
            }
//            else if(mouseOver(mx, my, 245, 85, 130, 40)){
//                game.gameState = Game.STATE.Upgrades;
//            }
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
    
    public void render(Graphics g){
        
        if(game.gameState == game.gameState.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 245, 80);
            
            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 280, 193);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 280, 293);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 280, 393);
        }
    }

}
