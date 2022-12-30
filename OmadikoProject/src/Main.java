/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        //Μουσική εκκίνησης
        File music1 = new File("sounds/music/ladyOfThe80s.wav"); //
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(music1);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        
        HashSet<String> wordSet = new HashSet<String>();
        Scanner scanner = new Scanner(System.in);
        
        //δημιουργία του JFrame
        new ProfileChooser();
    }  
     
}
