package main;

import soundtrack.SoundHandler;

public class Game implements Runnable {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;


    private final int FPS_SET = 120;
    private final int UPS_SET = 200;




    public Game(){
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        
        this.gamePanel.requestFocus(); // faz com que o Panel ( o que está dentro da janela ) receba os inputs do usuário
        startGameLoop();
    }

    public void update(){
        gamePanel.updateGame();
    }
    @Override  
    public void run(){
        
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        
        int updates = 0;
        int frames = 0;

        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;

        while(true){
    
            
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;


            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >=1){
                gamePanel.repaint();
                frames++;
                deltaF--;
            }


            
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
               System.out.println("Fps: " + frames + "| UPS: "+ updates);
                frames = 0;
                updates = 0;
            }
        }

       
      

    }

    private void startGameLoop(){
        this.gameThread = new Thread(this);
        gameThread.start();
    }
    
}
