/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

public class Word {
    private int points;
    private String word;
    
    Word(String word){
        this.word = word;
        points = 0;
        
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i)=='Α')
                points=points+1;
            else if(word.charAt(i)=='Β')
                points=points+8;
            else if(word.charAt(i)=='Γ')
                points=points+4;
            else if(word.charAt(i)=='Δ')
                points=points+4;
            else if(word.charAt(i)=='Ε')
                points=points+1;
            else if(word.charAt(i)=='Ζ')
                points=points+8;
            else if(word.charAt(i)=='Η')
                points=points+1;
            else if(word.charAt(i)=='Θ')
                points=points+8;
            else if(word.charAt(i)=='Ι')
                points=points+1;
            else if(word.charAt(i)=='Κ')
                points=points+2;
            else if(word.charAt(i)=='Λ')
                points=points+3;
            else if(word.charAt(i)=='Μ')
                points=points+3;
            else if(word.charAt(i)=='Ν')
                points=points+1;
            else if(word.charAt(i)=='Ξ')
                points=points+10;
            else if(word.charAt(i)=='Ο')
                points=points+1;
            else if(word.charAt(i)=='Π')
                points=points+2;
            else if(word.charAt(i)=='Ρ')
                points=points+2;
            else if(word.charAt(i)=='Σ')
                points=points+1;
            else if(word.charAt(i)=='Τ')
                points=points+1;
            else if(word.charAt(i)=='Υ')
                points=points+2;
            else if(word.charAt(i)=='Φ')
                points=points+8;
            else if(word.charAt(i)=='Χ')
                points=points+10;
            else if(word.charAt(i)=='Ψ')
                points=points+10;
            else if(word.charAt(i)=='Ω')
                points=points+3;
            else
                System.out.println("There was a problem saving the word"+word);
            
        }
    }
    
    
}
