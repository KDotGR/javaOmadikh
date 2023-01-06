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
    
    protected static HashSet<String> wordSet = new HashSet<>();
    
    public static void LexiconCreator(){
        try{
            Scanner scanner = new Scanner(new  File("words.txt"));
                
                    System.out.println("ss5");
                    while(scanner.hasNext()){
                        //System.out.println("ss4");
                        wordSet.add(scanner.nextLine());
                        //System.out.println("ss3");
                    }
                    scanner.close();
                  
 
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