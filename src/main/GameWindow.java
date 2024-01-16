package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

// Arquivo de configuração da janela que abre ao roda o jogo
public class GameWindow extends JFrame{
   

    public GameWindow(GamePanel gamePanel){
        JFrame jframe = new JFrame();
        jframe.setTitle("As Aventuras de Pog 2");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // TODO Auto-generated method stub
                System.out.println("Bem vindo");
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                // TODO Auto-generated method stub
                gamePanel.windowLostFocus();
            }
            
        });
    }

 
}
