package MYgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    
    public static final long serialVersiionUID = 1550691097823471818L;
    public static boolean paused = false; 
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 *9;
    
    private Thread thread;
    private boolean running = false;
    private boolean check = false;
    
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Help help;
    private End end;
    private Select select;
    private Upgrades upgrades;
    private Difficulty difficulty;
    
    public enum Difficulty{
        Easy,
        Medium,
        Hard,
    };
    
    public enum STATE{
      Menu,
      End,
      Game,
      Select,
      Upgrades,
      Help,
    };
    
    public STATE gameState = STATE.Menu;
    
    public Game(){
       
        
        hud = new HUD();        
        handler = new Handler();
        menu = new Menu(this, handler);
        help = new Help(this, handler);
        end = new End(this, handler);
        
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseListener(help);
        this.addMouseListener(end);
        
        new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
        
        
        spawner = new Spawn(handler, hud);
        upgrades = new Upgrades(handler, this, hud, spawner);
        select = new Select(handler, this, spawner);
        r = new Random();
        this.addMouseListener(upgrades);
        
        for(int i = 0; i<10; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
        }
    }
    
    @Override
    public void run() {
        //makes it so you dont have to click on window before enabling controls
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
        
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
                
        if(!paused){
            if(gameState == STATE.Game){
                hud.tick();
                spawner.tick();
                handler.tick();
                
                if(hud.HEALTH <= 0 ){
                    handler.resetGame();
                    gameState = STATE.End;
                }
            }else if(gameState == STATE.Menu || gameState == STATE.Select){
                handler.tick();
            }
            else if(gameState == STATE.Help){
                help.tick();
                handler.tick();
            }else if(gameState == STATE.End){
                end.tick();
                handler.tick();
            }else if(gameState == STATE.Upgrades){
                handler.tick();
            }
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(2);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        if(paused){
            g.drawString("PAUSED", 165, 64);
        }
        
        if(gameState == STATE.Game){
            hud.render(g);
            this.removeMouseListener(select);
            check = false;
        }else if(gameState == STATE.Menu){
            menu.render(g);
        }else if(gameState == STATE.Help){
            help.render(g);
        }else if(gameState == STATE.End){
            end.render(g);
        }else if(gameState == STATE.Select){
            select.render(g);
            if(!check){
                this.addMouseListener(select);
                check = true;
            }
        }else if(gameState == STATE.Upgrades){
            upgrades.render(g);
        }
        
        g.dispose();
        bs.show();
    }
    
    public void partialReset(){
        hud.partialReset();
        spawner.setScoreKeep(0);
    }
    
    public void completeReset(){
            hud.reset();
            spawner.setScoreKeep(0);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public double getScore(){
        return hud.getScore();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    
    
    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }
        else if (var <= min){
            return var = min;
        }
        else
            return var;
    }
    
    public static void main (String args[]){
        new Game();
    }
    
}
