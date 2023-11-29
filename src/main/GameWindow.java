package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
