package utilities;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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
    private static int sfxCount = 0;


    public static void playSFX(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Clip sound = AudioSystem.getClip();   
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundBus.class.getResource(SFX_PATH + filePath));
        sound.open(audioStream);
        sound.setFramePosition(0);
        sfxCount++;
        
        if (sfxCount > 2) {
        	setVolume(sound, 0.5f);
        } else if (sfxCount > 4) {
        	
        }else {
        	setVolume(sound, 1.0f);
        }
        
        sound.addLineListener(new LineListener() {

            @Override
            public void update(LineEvent event) {
                if(event.getType() == LineEvent.Type.STOP){
                    sound.close();
                    sfxCount--;
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
    
    //0.0f means no volume, 1.0f means full volume
    private static void setVolume(Clip sound, float setVolume) {
    	FloatControl gain = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
    	float volumeRange = gain.getMaximum() - gain.getMinimum();
    	float adjustedVolume = (volumeRange * setVolume) + gain.getMinimum();
    	gain.setValue(adjustedVolume);
    }

    public static void pauseSong(){
        if (currentSong != null && !isPaused){
            isPaused = true;
            currentSong.stop();
        }
    }

    public static void resumeSong(){
        if (currentSong != null && isPaused){
            isPaused = false;
            currentSong.start();
        }
    }
    
    public static void closeCurrentSong() {
    	currentSong.close();
    }
}
