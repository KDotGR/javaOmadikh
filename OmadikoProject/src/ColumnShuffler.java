import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
*/


//Επιστρέφει shuffled πίνακα των στοιχείων
public class ColumnShuffler extends Shuffler{
    private static int counter;
    
    //Φτιάχνει έναν πίνακα με τους αριθμούς των θέσεων της κάθε στήλης,
    //τον μετατρέπει σε λίστα, τον κάνει και shuffle και τέλος τον μετατρέπει
    //ξανά σε πίνακα και τον επιστρέφει με τις shuffled τιμές
    public static int[] columnShuffle(int column){
       int[] newNumbers = new int[8];
     for(int i=0; i<8; i++)
         newNumbers[i] = i*8+column;
     List<Integer> columnList = Arrays.stream(newNumbers).boxed().collect(Collectors.toList());
     Collections.shuffle(columnList);
     newNumbers = columnList.stream().mapToInt(Integer::intValue).toArray();
     
     return newNumbers;
    }
    
    public static int returnCounter(){
        return counter;
    }
    
    public static void initCounter(){
        counter=0;
    }
    
    public static void updateCounter(){
        counter++;
    }
}