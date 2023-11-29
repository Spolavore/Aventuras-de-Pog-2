package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Spring;

import utils.Constants.Sprites;




public abstract class AssetsHandler {
    

    public static BufferedImage LoadAssets(String assetPath){
          InputStream is = AssetsHandler.class.getResourceAsStream(assetPath);
      
          System.out.println(is);
          BufferedImage img = null;
        try {

            img = ImageIO.read(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
        } finally {
            try {
                
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return img;
    }
}
