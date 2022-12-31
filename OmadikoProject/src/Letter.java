/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
*/

public class Letter {
    private int points;
    private char letter;
    
    Letter(char letter, int points){
        this.letter=letter;
        this.points=points;
    }
    
    public char ReturnLetter(){
        return letter;
    }
}
