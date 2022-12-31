/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Lexicon {
    
    protected HashSet<Word> wordSet;
    
    Lexicon(){
            try{
            wordSet = new HashSet<Word>();
            File wordFile = new File("words.txt");
            Scanner scanner = new Scanner(wordFile);
            while(scanner.hasNextLine()){
                wordSet.add(new Word(scanner.nextLine()));
                }
            scanner.close();
            System.out.println("is all good!");
        }
        catch (FileNotFoundException e){
            System.out.println("File was not found");
            e.printStackTrace();
        }
    }
}