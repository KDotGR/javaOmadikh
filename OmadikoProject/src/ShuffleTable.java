/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShuffleTable extends Shuffler{
    private static int counter;
    
    public static int[] tableShuffle(){
        int[] newNumbers = new int[64];
        List<Integer> columnList = Arrays.stream(newNumbers).boxed().collect(Collectors.toList());
     Collections.shuffle(columnList);
     newNumbers = columnList.stream().mapToInt(Integer::intValue).toArray();
     
     return newNumbers;
    }
    
    public static int returnCounter(){
        return counter;
    }
    
     public static void updateCounter(){
        counter++;
    }
    
    public static void initCounter(){
        counter=0;
    }
}
