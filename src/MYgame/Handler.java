package MYgame;

import static MYgame.Game.HEIGHT;
import static MYgame.Game.WIDTH;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Handler {
    
    LinkedList<GameObject> object = new LinkedList<>();
    Random r = new Random();

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            
            tempObject.tick();
        }
        
    }
    
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void resetGame(){
        object.clear();
        for(int i = 0; i<10; i++){
                addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, this));
        }
    }

    public void clearMenuParticles(){
        int counter = 0;
        for(int i = 0; i < object.size(); ){
            if(object.get(i).getId() == ID.MenuParticle){
                object.remove(i);
                i = 0;
                System.out.println(counter++);
            }else i++;
        }
    }
    public void clearEnemies(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            
            if(tempObject.getId() == ID.Player){
                object.clear();
                
                addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
            }
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
