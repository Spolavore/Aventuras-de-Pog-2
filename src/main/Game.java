package main;

public class Game implements Runnable {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;

    private final int FPS_SET = 120;

    public Game(){
        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow(gamePanel);
        this.gamePanel.requestFocus(); // faz com que o Panel ( o que está dentro da janela ) receba os inputs do usuário
        startGameLoop();
    }


    @Override  
    public void run(){
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long systemTime = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while(true){
            
            systemTime = System.nanoTime(); 
            if (systemTime - lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame = systemTime;
                frames++;
            }
            
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("Fps: " + frames);
                frames = 0;
            }
        }

       
      

    }

    private void startGameLoop(){
        this.gameThread = new Thread(this);
        gameThread.start();
    }
    
}
