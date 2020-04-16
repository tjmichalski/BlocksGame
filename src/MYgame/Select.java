package MYgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Select extends MouseAdapter{
    
    private Handler handler;
    private Game game; 
    private Random r;
    private Spawn spawner;
    
    public Select(Handler handler, Game game, Spawn spawner){
        this.handler = handler;
        this.game = game;
        this.spawner = spawner;
        r = new Random();
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == Game.STATE.Select){
            //select
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                //easy
                handler.clearMenuParticles();
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, 3));
                game.gameState = Game.STATE.Game;
                spawner.setSpeed1(3);   
                spawner.setSize1(16);
                game.setDifficulty(Game.Difficulty.Easy);
            }else if(mouseOver(mx, my, 210, 250, 200, 64)){
                //medium
                handler.clearMenuParticles();
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, 6));
                game.gameState = Game.STATE.Game;
                spawner.setSpeed1(6);
                spawner.setSize1(16);
                game.setDifficulty(Game.Difficulty.Medium);
            }
            else if(mouseOver(mx, my, 210, 350, 200, 64)){
                //hard
                handler.clearMenuParticles();
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler, 6));
                game.gameState = Game.STATE.Game;
                spawner.setSpeed1(6);
                spawner.setSize1(30);
                game.setDifficulty(Game.Difficulty.Hard);
            }
        }
//        handler.clearMenuParticles();
//        handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
//        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 38), r.nextInt(Game.HEIGHT - 60), ID.BasicEnemy, handler));
//        game.gameState = Game.STATE.Game;
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            }else return false;
        }else return false;
    }
    
    public void render(Graphics g){
        if(game.gameState == game.gameState.Select){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select a difficult", 200, 80);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Easy", 280, 193);

            g.drawRect(210, 250, 200, 64);
            g.drawString("Medium", 255, 293);

            g.drawRect(210, 350, 200, 64);
            g.drawString("Hard", 280, 393);
        }
    }
    
}
