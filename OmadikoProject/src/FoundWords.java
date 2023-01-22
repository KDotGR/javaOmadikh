/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */


import java.util.HashSet;

//Αποθηκεύει τα δεδομένα των λέξεων που έχουν βρεθεί
public class FoundWords {
    
    protected int numberOfWordsFound;
    protected int pointsGathered;
    protected HashSet<String> FoundWordsSet;
    
    FoundWords(){
 
            FoundWordsSet = new HashSet<>();
            numberOfWordsFound =0;
            pointsGathered=0;
            System.out.println(numberOfWordsFound);
        }
    protected void foundWord(String word, int points){
        FoundWordsSet.add(word);
        numberOfWordsFound++;
        pointsGathered = pointsGathered+points;
    }
    
    protected boolean checkIfWordFound(String word){
        return FoundWordsSet.contains(word);
    }
    
    protected int returnPointsGathered(){
        return pointsGathered;
    }
    
    public int returnNumberOfWordsFound(){
        return numberOfWordsFound;
    }
}
