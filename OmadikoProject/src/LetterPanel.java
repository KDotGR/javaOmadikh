/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

public class LetterPanel {
    protected int position;
    protected char letter;
    protected int points;
    protected int type; // 1 = Άσπρο, 2 = Κόκκινο, 3 = Μπλε, 4 = Μπαλαντέρ
    
    //constructor για αρχικοποίηση των αντικειμένων
    LetterPanel(int position, char letter, int points, int type){
        
        this.position = position;
        
        if(type == 1 || type == 2 || type == 3)
            this.letter = letter;
        
        if(type == 4)
            this.letter = '?';
        
        if(type == 1 || type == 3)
            this.points = points;
        
        if(type == 2)
            this.points = points*2;
        
        if(type == 4)
            this.points = 0;
        this.type = type;
    }
    
    //Συνάρτηση αντικατάστασης του αντικειμένου μπαλαντέρ με τα στοιχεία του
    // γράμματος που επέλεξε ο χρήστης
    protected void WildChanger(Letter newLetter){
        letter = newLetter.ReturnLetter();
        points = newLetter.ReturnLetterPoints();
        type = 1;
    }
    
    public String displayLetter(){
        String ltr = Character.toString(letter);
        return ltr;
    }
    
    public String displayPoints(){
        String pnts = Integer.toString(points);
        return pnts;
    }
    
    public int returnType(){
        return type;
    }
}
