/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

public class Score {
    private static int score;
    private static String word;
    private static boolean blue;
    
    public static void addScore(int points){
        score = score + points;
    }
    
    public static void resetScore(){
        score=0;
    }
    
    public static void addChar(String s){
        if(word==null)
            word = s;
        else
            word=word+s;
    }
    
    public static void addBlue(){
        blue = true;
    }
    
    public static void resetWord(){
        word = null;
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
    
    public static boolean returnBlue(){
        return blue;
    }
}
