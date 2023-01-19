
import java.util.Random;


public class RowShuffler extends Shuffler{
    protected static int[] newNumbers;
    
    public void rowShuffle(int row){
        
        Random rand = new Random();
        switch (row) {
            case 0:
                doTheShuffle(0,8);
                break;
            case 1:
                doTheShuffle(8,16);
                break;
            case 2:
                doTheShuffle(16,24);
                break;
            case 3:
                doTheShuffle(24,32);
                break;
            case 4:
                doTheShuffle(32,40);
                break;
            case 5:
                doTheShuffle(40,48);
                break;
            case 6:
                doTheShuffle(48,56);
                break;
            default:
                doTheShuffle(56,64);
                break;
        }
    }
    
    public void doTheShuffle(int min, int max){
        newNumbers = new int[8];
        Random rand = new Random();
        int num;
        for(int i=min; i<max; i++){
            num=rand.nextInt(max)+min;
        
            while(newNumbers[num%8]!=-1){
                num=rand.nextInt(max)+min;    
            }
            newNumbers[num%8] = num;
        }
    }
}
                                                                              