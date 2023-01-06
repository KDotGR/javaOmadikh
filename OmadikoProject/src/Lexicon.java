/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Lexicon {
    
    protected static HashSet<String> wordSet = new HashSet<>();
    
    public static void LexiconCreator(){
        try{
            try (Scanner scanner = new Scanner(new  File("words.txt"))) {
                while(scanner.hasNext()){
                    wordSet.add(scanner.nextLine());
                }
            }
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("File was not found");
            }
    }
    
    public static boolean doesWordExist(String word){
        return wordSet.contains(word);
    }
}