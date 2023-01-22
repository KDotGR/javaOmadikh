/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

public class LineDeleter extends Shuffler{
    private static int counter;
    
     public static void updateCounter(){
        counter++;
    }
    
    public static void initCounter(){
        counter=0;
    }
    
    public static int returnCounter(){
        return counter;
    }
}
