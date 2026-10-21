package utilites;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class SoundBus {
    //Common SFX
	private static final String SFX_PATH = "../Assets/sfx/";
	private static final String SONG_PATH = "../Assets/songs/";

    private static Clip currentSong;
    private static boolean isPaused = false;
    public static int currentSongIndex;


    public static void playSFX(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Clip sound = AudioSystem.getClip();   
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundBus.class.getResource(SFX_PATH + filePath));
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

    public static void playSong(String filePath, boolean loop) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        if (currentSong != null) {
        	currentSong.close();
        }
        currentSong = AudioSystem.getClip();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundBus.class.getResource(SONG_PATH + filePath));
        currentSong.open(audioStream);
        currentSong.setFramePosition(0);
        currentSong.start();
        
        if (!loop) {
        	currentSong.loop(0);
        } else {
        	currentSong.loop(Clip.LOOP_CONTINUOUSLY);
        }
        
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
    
    public static void closeCurrentSong() {
    	currentSong.close();
    }
}
