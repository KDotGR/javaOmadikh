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
        File music1 = new File("sounds/music/modernRnbCuriosity.wav"); // Sound Effect from Pixabay 
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(music1);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        
        //Δημιουργία Λεξικού Παιχνιδιού
        Lexicon.LexiconCreator();
        
        //δημιουργία του JFrame
        new ProfileChooser();
        
        
    }  
     
}
