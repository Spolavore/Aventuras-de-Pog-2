package main;

public class Game {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); // faz com que o Panel ( o que está dentro da janela ) receba os inputs do usuário
    }
}
