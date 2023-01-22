/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Lexicon {
    
    protected static HashSet<String> wordSet = new HashSet<>();
    protected static File selectedFile;
    
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
    
    //υλοποιεί την επιλογή αλλαγής αρχείου λέξεων από τον χρήστη
    // Κατά βάση κώδικας από: https://mkyong.com/swing/java-swing-jfilechooser-example/
    public static void LexiconCreator(int a){
        try{
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = jfc.getSelectedFile();
		}
            try (Scanner scanner = new Scanner(selectedFile)) {
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