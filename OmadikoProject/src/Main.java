/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Main {
    
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        //Μουσική εκκίνησης
        File music1 = new File("sounds/music/ladyOfThe80s.wav"); //
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(music1);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        
        
        
        //δημιουργία του JFrame
        new ProfileChooser();
        new MyFrame();
        
        
    }  
    
    
}
