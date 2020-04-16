package MYgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    private Game game;
    public static int vel = 5;
    
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game; 
        
        for (int i = 0; i <keyDown.length; i++){
            keyDown[i] = false;
            System.out.println(keyDown[i]);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();     
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player){               
                
                if(key == KeyEvent.VK_W){
                    tempObject.setVelY(-vel);
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(vel);
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setVelX(-vel);
                    keyDown[3] = true;
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelY(vel);
                    keyDown[1] = true;
                }
            }
        }
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if(game.gameState == game.gameState.Game){
            
            if(key == KeyEvent.VK_P){
                if(Game.paused) Game.paused = false;
                else Game.paused = true;
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
        
            if(tempObject.getId() == ID.Player){
                
                if(key == KeyEvent.VK_W) keyDown[0] = false;
                else if(key == KeyEvent.VK_S) keyDown[1] = false;
                else if(key == KeyEvent.VK_A) keyDown[3] = false;
                else if(key == KeyEvent.VK_D) keyDown[2] = false;
                
                //vertical movement              
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
                
            }
            
        }
    }
    
}
