/*
        Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

//Αποθηκεύει το προφίλ του παίκτη
public class Profile {
    private String name;
    private int wins;
    private int losses;
    private int streak;
    
    Profile(String name){
        this.name = name;
        wins = 0;
        losses = 0;
        streak = 0;
    }
    
    public int showWins(){
        return wins;
    }
    
    public int showLosses(){
        return losses;
    }
    
    public int showStreak(){
        return streak;
    }
    
    protected void newWin(){
        wins++;
        streak++;
    }
    
    protected void newLoss(){
        losses++;
        streak = 0;
    }
    
}
