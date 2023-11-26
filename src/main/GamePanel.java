package main;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utils.Player;
import utils.Constants.Directions;
import utils.Constants.Directions.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class GamePanel extends JPanel {
    private Player player;
    private MouseInputs mouseInputs;
    private int xPosition = 0, yPosition = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int playerAniTick, playerAniIndex, playerAniSpeed = 10;
    

    // As animações possuem 7 tipos, cada uma é uma linha da matriz animations
    // algumas animações possuem apenas 5 sprites, enquanto outras 8. Esses números
    // referem-se às colunas da matriz citado
    // Para saber todas as animações possíveis ir res/char_blue.png
    
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        this.player = new Player(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this, this.player));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

   

    private void loadAnimations() {        
        animations = new BufferedImage[6][8];
        // Pega-se uma subimagem da imagem original (res/char_blue.png)
        // recortando conforme a imagem e colocando as subimagens separada
        // na matriz animations
        for (int i = 0; i < 6; i++){
            for(int j = 0 ; j < 8; j++){
                animations[i][j] = img.getSubimage(j*56, i*56, 56, 56);

            }
        }
      
}
    


    public void updateGame(){
        updateAnimationTick();
        updatePosition();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
       
        g.drawImage(animations[player.getTypeOfAnimation()][playerAniIndex], (int)xPosition,(int)yPosition, 128, 128,null);
       
        
    }

    private void updatePosition() {

        if(player.isMoving()){
            int playerDirection = player.getDirection();
            if(playerDirection ==  Directions.DOWN){
                yPosition +=1;
            }
            else if (playerDirection == Directions.UP){
                yPosition -= 1;
            }
            else if (playerDirection == Directions.RIGHT){
                xPosition += 1;
            } else{
                xPosition -=1;
            }
        }
      
    }



    private void updateAnimationTick() {
        // Numeros do vetor referem-se a quantas sprites tem cada animação
        // da char_blue.png, ou seja, quantas imagens tem disponiveis para cada
        // tipo de animação
        int animationsMaxIndexes[] = {6,6,8,8,8};

        playerAniTick++;
        if (playerAniTick >= playerAniSpeed){
            playerAniTick = 0;
            playerAniIndex+=1;
            if(playerAniIndex >= animationsMaxIndexes[player.getTypeOfAnimation()]){
                playerAniIndex = 0;
            }
        }
    }



    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

   
     private void importImg() {
        
        InputStream is = getClass().getResourceAsStream("/char_blue.png");
        try {
            
            img = ImageIO.read(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        } finally{
            try{
                is.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

   public void setImgPosition(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
   }




}
