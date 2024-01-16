package soundtrack;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * Classe responsável pelo controle de todos os sons do jogo
 * 
 */
public class SoundHandler {
    private static Clip clip;
    private static FloatControl volumeControl;
    private static Clip backgroundSound = null;
    public static void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            // Obter o controle de volume
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playBackgourndSound(){
        playBGLoopingSound("sounds/background_music.wav");
    }
    private static void playBGLoopingSound(String soundFilePath) {

        try {

            File soundFile = new File(soundFilePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);

            // Obter o controle de volume
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-20.0f);
            // Adicionar um LineListener para reiniciar o som quando terminar
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close();
                        playBGLoopingSound(soundFilePath);
                    }
                }
            });

            backgroundSound = clip;
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void stopBackgroundSound() {
        if(backgroundSound != null && backgroundSound.isRunning()){
            backgroundSound.stop();
            backgroundSound.close();
        }
    }

}
