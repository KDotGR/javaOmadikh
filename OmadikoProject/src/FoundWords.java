/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */


import java.util.HashSet;

public class FoundWords {
    
    protected int numberOfWordsFound;
    protected int pointsGathered;
    protected HashSet<String> FoundWordsSet;
    
    FoundWords(){
 
            FoundWordsSet = new HashSet<String>();
            numberOfWordsFound =0;
            pointsGathered=0;
        }
    protected void foundWord(String word, int points){
        FoundWordsSet.add(word);
        numberOfWordsFound++;
        pointsGathered = points;
    }
    
    protected boolean checkIfWordFound(String word){
        return FoundWordsSet.contains(word);
    }
}
