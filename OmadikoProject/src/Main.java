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
        
        Letter alpha = new Letter('Α',1);
        Letter beta = new Letter('Β',8);
        Letter gamma = new Letter('Γ',4);
        Letter delta = new Letter('Δ',4);
        Letter epsilon = new Letter('Ε',1);
        Letter zeta = new Letter('Ζ',8);
        Letter ita = new Letter('Η',1);
        Letter theta = new Letter('Θ',8);
        Letter iota = new Letter('Ι',1);
        Letter kappa = new Letter('Κ',2);
        Letter lamda = new Letter('Λ',3);
        Letter mi = new Letter('Μ',3);
        Letter ni = new Letter('Ν',1);
        Letter ksi = new Letter('Ξ',10);
        Letter omicron = new Letter('Ο',1);
        Letter pi = new Letter('Π',2);
        Letter ro = new Letter('Ρ',2);
        Letter sigma = new Letter('Σ',1);
        Letter taf = new Letter('Τ',1);
        Letter ypsilon = new Letter('Υ',2);
        Letter fi = new Letter('Φ',8);
        Letter xi = new Letter('Χ',10);
        Letter psi = new Letter('Ψ',10);
        Letter omega = new Letter('Ω',3);
        
        
        HashSet<String> wordSet = new HashSet<String>();
        Scanner scanner = new Scanner(System.in);
        
        //δημιουργία του JFrame
        new ProfileChooser();
        new Lexicon();
    }  
     
}
