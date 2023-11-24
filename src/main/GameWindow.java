package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
    private int  windowWidth = 400;
    private int windowHeight = 400;

    public GameWindow(GamePanel gamePanel){
        JFrame jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null); // coloca o janela no centro da tela do usuário
        jframe.setResizable(false);
        jframe.pack();

        jframe.setVisible(true);

    }

 
}
