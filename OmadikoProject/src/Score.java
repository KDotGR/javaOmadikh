
import java.util.ArrayList;

/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

public class Score {
    private static int score;
    private static String word;
    private static boolean blue;
    
    private static ArrayList <Integer> wordPosition;
    
    public static void initArrayList(){
       wordPosition = new ArrayList<>();
    }
    
    public static void addScore(int points){
        score = score + points;
    }
    
    public static void addChar(String s,int pos){
        if(word==null)
            word = s;
       
        else
            word=word+s;
        
        wordPosition.add(pos);
    }
    
    public static void addBlue(){
        blue = true;
    }
    
    public static void resetWord(){
        score = 0;
        word = null;
        wordPosition.clear();
        blue = false;
    }
    
    public static void resetBlue(){
        blue = false;
    }
    
    public static String returnWord(){
        return word;
    }
    
    public static int returnScore(){
        return score;
    }
    
    public static int returnBlueScore(){
        return (score*2);
    }
    
    public static boolean returnBlue(){
        return blue;
    }
    
    public static boolean returnWordPositionisEmpty(){
        return wordPosition.isEmpty();
    }
    
    public static int returnWordPositionSize(){
        return wordPosition.size();
    }
    
    public static int returnWordPositionElement(int n){
        return wordPosition.get(n);
    }
    
}
