package utilites;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class SoundBus {
    //Common SFX

    private static Clip currentSong;
    private static boolean isPaused = false;
    public static int currentSongIndex;


    public static void playSFX(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Clip sound = AudioSystem.getClip();   
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundBus.class.getResource(filePath));
        sound.open(audioStream);
        sound.setFramePosition(0);

        sound.addLineListener(new LineListener() {

            @Override
            public void update(LineEvent event) {
                if(event.getType() == LineEvent.Type.STOP){
                    sound.close();
                }
            }
            
        });

        sound.start();
    }

    public static void playSong(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
    }

    public static void pauseSong(){
        if (currentSong != null){
            isPaused = true;
            currentSong.stop();
        }
    }

    public static void resumeSong(){
        if (currentSong != null){
            isPaused = false;
            currentSong.start();
        }
    }
}
