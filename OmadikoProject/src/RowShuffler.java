/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */
import java.util.Random;

//Κλάση που υλοποιεί το shuffle γραμμής
public class RowShuffler extends Shuffler{
    //public int[] newNumbers;
    private static int counter;
    
    public static int[] rowShuffle(int row) {
        
        
        int[] newNumbers = new int[8];
        for(int i=0; i<newNumbers.length; i++)
            newNumbers[i]=-1;
        switch (row) {
            case 0:
                doTheShuffle(0,8,newNumbers);
                break;
            case 1:
                doTheShuffle(8,16,newNumbers);
                break;
            case 2:
                doTheShuffle(16,24,newNumbers);
                break;
            case 3:
                doTheShuffle(24,32,newNumbers);
                break;
            case 4:
                doTheShuffle(32,40,newNumbers);
                break;
            case 5:
                doTheShuffle(40,48,newNumbers);
                break;
            case 6:
                doTheShuffle(48,56,newNumbers);
                break;
            default:
                doTheShuffle(56,64,newNumbers);
                break;
        }
        return newNumbers;
    }
    
    //Επιστρέφει πίνακα 8 θέσεων με τυχαίες τις θέσεις των γραμμάτων
    public static int[] doTheShuffle(int min, int max,int[] newNumbers){
        
        Random rand = new Random();
        int num;
        for(int i=min; i<max; i++){
            num=rand.nextInt(max)+min;
        
            while(newNumbers[num%8]!=-1){
                num=rand.nextInt(max)+min;    
            }
            newNumbers[num%8] = i;
        }
        return newNumbers;
    }
    
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
                                                                              