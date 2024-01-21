package soundtrack;

// import java.io.File;
// import java.io.IOException;
// import java.io.InputStream;
import java.io.*;

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
    private static Clip clipDefaultSounds;
    public static Clip clipBGSound;
    private static FloatControl volumeControl;
    private static boolean canPlayBGSound;
    public static void playSound(String soundFilePath) {
        try {
            ClassLoader classLoader = SoundHandler.class.getClassLoader();

            InputStream soundFile = classLoader.getResourceAsStream(soundFilePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new BufferedInputStream(soundFile));
            clipDefaultSounds = AudioSystem.getClip();
            clipDefaultSounds.open(audioInput);
            // Obter o controle de volume
            volumeControl = (FloatControl) clipDefaultSounds.getControl(FloatControl.Type.MASTER_GAIN);
            clipDefaultSounds.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playBackgourndSound(){
        if(canPlayBGSound && clipBGSound == null){
            playBGLoopingSound("background_music.wav");
        }
    }
    private static void playBGLoopingSound(String soundFilePath) {

        try {
            ClassLoader classLoader = SoundHandler.class.getClassLoader();

            InputStream soundFile = classLoader.getResourceAsStream(soundFilePath);
            
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new BufferedInputStream(soundFile));
            clipBGSound = AudioSystem.getClip();
            clipBGSound.open(audioInput);

            // Obter o controle de volume
            volumeControl = (FloatControl) clipBGSound.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-20.0f);
            // Adicionar um LineListener para reiniciar o som quando terminar
            clipBGSound.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        event.getLine().close();
                        clipBGSound = null;
                        playBGLoopingSound(soundFilePath);
                    }
                }
            });

            clipBGSound.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void stopBackgroundSound() {
        if(clipBGSound != null && clipBGSound.isRunning()){
            clipBGSound.stop();
            clipBGSound.close();
            clipBGSound = null;
        }

    }

    public static void enableToPlayBGSound(boolean bool){
        canPlayBGSound = bool;
    }

}
