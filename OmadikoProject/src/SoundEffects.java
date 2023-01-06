
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

public class SoundEffects {
    
    public static void clickSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        File clickSound = new File("sounds/soundEffects/uiClick.wav"); // Sound effect from Pixabay
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(clickSound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    
    public static void wrongChoiceSound()throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        File clickSound = new File("sounds/soundEffects/deny.wav"); // Sound effect from Pixabay
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(clickSound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    
    public static void wrongWord()throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        File clickSound = new File("sounds/soundEffects/wrongWord.wav"); // Sound effect from Pixabay
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(clickSound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
    
    public static void correctWord()throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        File clickSound = new File("sounds/soundEffects/correctWord.wav"); // Sound effect from Pixabay
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(clickSound);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
