/*
       Ονοματεπώνυμο                  ΑΜ
    Δημήτρης Καραγεώργος        321/2017071
    Αναστάσιος Κουτσώνης        321/2018106
 */

//Εισαγωγή των απαραίτητων πακέτων

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.Random;
 
public class Play extends JFrame {
    
    Letter[] letterArray;
    int[] letterPool;
    //letterArray = new Letter[24];
    
    
    
    
    //Όλα τα στοιχεία που απαρτίζουν το γραφικό σώμα της εφαρμογής
    private final JMenuBar menuBar;
    private final JMenu menu,tools;
    private final JMenuItem newGame, endGame, changeProfile, settings, 
            searchWordFile, exit;
    
    private final JMenuItem help,about;
    private final JPanel infoPanel, mainCenterPanel, selectionandProgressPanel,
            gamePanel, selectionsPanel, progressPanel, messagePanel;
    
    private JLabel infoLabel;
    
    private final int gameRows = 8;
    private final int gameCols = 8;
    private final int NUM = gameRows * gameCols;
    private JPanel[] charPanels = new JPanel[NUM];
    
    Play(Profile profile,int StartGame){
        
        //Βασικά χαρακτηριστικά και λειτουργίες του Frame
        this.setTitle("Το Μονοπάτι των Λέξεων"); //Όνομα εφαρμογής -- work in progress
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        this.setSize(800,800);
        this.setLayout(new BorderLayout(10,10));
        
        //Αρχικοποίηση του πίνακα γραμμάτων με τα γράμματα και τις τιμές τους
        InitLetterArray();
        
        //Προσθήκη του logo της εφαρμογής
        ImageIcon image = new ImageIcon("images/logo.png"); //via https://www.freepik.com
        this.setIconImage(image.getImage());
   
        //Δημιουργία των Panel
        infoPanel= new JPanel();
        mainCenterPanel = new JPanel();
        gamePanel = new JPanel();
        selectionandProgressPanel = new JPanel();
        selectionsPanel= new JPanel();
        progressPanel = new JPanel();
        messagePanel = new JPanel();
        
        //Ορισμούς του layout κάθε panel
        mainCenterPanel.setLayout(new GridLayout(1,2));
        selectionandProgressPanel.setLayout(new GridLayout(2,1));
        gamePanel.setLayout(new GridLayout(gameRows,gameCols,6,6));
        infoPanel.setLayout(new BorderLayout());
        selectionsPanel.setLayout(new BorderLayout());
        progressPanel.setLayout(new BorderLayout());
        messagePanel.setLayout(new BorderLayout());
        
        //Προσωρινή προσθήκη label στα panel για να αναγνωρίζουμε την λειτουργία
        //τους
        infoLabel=new JLabel("Νίκες: "+profile.showWins()+"  Ήττες: "+profile.showLosses()
            +"  Σερί: "+profile.showStreak());
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setVerticalAlignment(JLabel.CENTER);
        JLabel label2=new JLabel("GAME");
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        JLabel label3=new JLabel("SELECTIONS");
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalAlignment(JLabel.CENTER);
        JLabel label4=new JLabel("PROGRESS");
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        JLabel label5=new JLabel("MESSAGES");
        label5.setHorizontalAlignment(JLabel.CENTER);
        label5.setVerticalAlignment(JLabel.CENTER);
        
        //Προσωρινή προσθήκη διακριτικών label στα panel
        infoPanel.add(infoLabel);
       // gamePanel.add(label2);
        selectionsPanel.add(label3);
        progressPanel.add(label4);
        messagePanel.add(label5);
        
         // Ορισμός χρώματος και μεγέθους στα panel για διακριτικούς λόγους

        //info Panel
        infoPanel.setBackground(Color.yellow);
        infoPanel.setPreferredSize(new Dimension(100,40));
        
        //mainCenterPanel Panel ( Υπερ panel των game Panel και selectionandProgress Panel )
        mainCenterPanel.setPreferredSize(new Dimension(100,40));
        
        //message Panel
        messagePanel.setBackground(Color.MAGENTA);
        messagePanel.setPreferredSize(new Dimension(100,40));

        //Game Panel
        //gamePanel.setBackground(Color.red);
        gamePanel.setPreferredSize(new Dimension(600,40));
        
        //selectionandProgress Panel ( Υπερ panel των selection panel και progress panel )
        selectionandProgressPanel.setPreferredSize(new Dimension(600,40));
        
        //Selection Panel
        selectionsPanel.setBackground(Color.green);
        selectionsPanel.setPreferredSize(new Dimension(100,40));
        
        //Progress Panel
        progressPanel.setBackground(Color.cyan);
        progressPanel.setPreferredSize(new Dimension(100,40));
        
       
        //Εισαγωγή των βασικών πάνελ στο JFrame
        this.add(infoPanel,BorderLayout.NORTH);
        this.add(mainCenterPanel);
        this.add(messagePanel,BorderLayout.SOUTH);
        
        //Εισαγωγή των υπο πάνελ στο master panel τους
        mainCenterPanel.add(gamePanel);
        mainCenterPanel.add(selectionandProgressPanel);
        selectionandProgressPanel.add(selectionsPanel);
        selectionandProgressPanel.add(progressPanel);
        
        //Δημιουργία του Menu Bar
        menuBar = new JMenuBar();
        
  
        //Δημιουργία του Μενού
        menu = new JMenu("Μενού");
        ImageIcon settingsImage = new ImageIcon("images/menu.png"); //via https://www.flaticon.com/authors/mayor-icons
        menu.setIcon(settingsImage);
        
        //Επιλογή Νέο Παιχνίδι
        newGame = new JMenuItem("Νέο Παιχνίδι",new ImageIcon("images/replay.png")); //via https://www.flaticon.com/authors/pixel-perfect
        menu.add(newGame);
        
        //Επιλογή Ακύρωση/Τερματισμός παιχνιδιού
        endGame = new JMenuItem("Ακύρωση/Τερματισμός παιχνιδιού",new ImageIcon("images/terminategame.png")); //via https://www.flaticon.com/authors/sumberrejeki
        menu.add(endGame);
        
         //Επιλογή Αλλαγή Προφίλ
        changeProfile = new JMenuItem("Αλλαγή Προφίλ",new ImageIcon("images/user.png")); //via https://www.freepik.com
        menu.add(changeProfile);
        
         //Επιλογή Ρυθμίσεις
        settings = new JMenuItem("Ρυθμίσεις",new ImageIcon("images/settings.png")); //via https://www.flaticon.com/authors/juicy-fish
        menu.add(settings);
        
        //Επιλογή Αναζήτηση αρχείου λέξεων
        searchWordFile = new JMenuItem("Αναζήτηση αρχείου λέξεων",new ImageIcon("images/search.png")); //via https://www.flaticon.com/authors/dimitry-miroliubov
        menu.add(searchWordFile);
        
        //Επιλογή Αναζήτηση αρχείου λέξεων
        exit = new JMenuItem("Έξοδος",new ImageIcon("images/exit.png")); //via https://www.flaticon.com/authors/wr-graphic-garage
        menu.add(exit);
        
        //Προσθήκη του Μενού στο menu bar
        menuBar.add(menu);
        
        //Δημιουργία του Μενού Εργαλεία
        tools = new JMenu("Εργαλεία");
        
        //Επιλογή Βοήθεια
        help = new JMenuItem("Βοήθεια",new ImageIcon("images/help.png")); //via  https://www.flaticon.com/authors/ilham-fitrotul-hayat
        tools.add(help);
        
        //Επιλογή Πληροφορίες
        about = new JMenuItem("Πληροφορίες",new ImageIcon("images/info.png")); //via  https://www.flaticon.com/authors/roundicons
        tools.add(about);
        
        menuBar.add(tools);
        this.setJMenuBar(menuBar);
  
        //this.pack();
        this.setVisible(true);
        
        if(StartGame==1){
            NewGame();
        }
    }
    
    public void NewGame(){
        
         Border br = BorderFactory.createLineBorder(Color.black);
         
        for(int i=0; i < NUM; i++){
            
            charPanels[i] = new JPanel();
            charPanels[i].setBorder(br);
           
            Character chr=RandomLetterChooser().ReturnLetter();
            String str=Character.toString(chr);
            
            charPanels[i].add(new JLabel(str));
            gamePanel.add(charPanels[i]);
        }
        
    }
    
    protected void InitLetterArray(){
        
        letterArray = new Letter[24];
        //Πίνακας Γραμμάτων
        letterArray[0] = new Letter('Α',1);
        letterArray[1] = new Letter('Β',8);
        letterArray[2] = new Letter('Γ',4);
        letterArray[3] = new Letter('Δ',4);
        letterArray[4] = new Letter('Ε',1);
        letterArray[5] = new Letter('Ζ',8);
        letterArray[6] = new Letter('Η',1);
        letterArray[7] = new Letter('Θ',8);
        letterArray[8] = new Letter('Ι',1);
        letterArray[9] = new Letter('Κ',2);
        letterArray[10] = new Letter('Λ',3);
        letterArray[11] = new Letter('Μ',3);
        letterArray[12] = new Letter('Ν',1);
        letterArray[13] = new Letter('Ξ',10);
        letterArray[14] = new Letter('Ο',1);
        letterArray[15] = new Letter('Π',2);
        letterArray[16] = new Letter('Ρ',2);
        letterArray[17] = new Letter('Σ',1);
        letterArray[18] = new Letter('Τ',1);
        letterArray[19] = new Letter('Υ',2);
        letterArray[20] = new Letter('Φ',8);
        letterArray[21] = new Letter('Χ',10);
        letterArray[22] = new Letter('Ψ',10);
        letterArray[23] = new Letter('Ω',3);
    }
    
    /*Επιστρέφει ένα τυχαίο γράμμα
     
     Η επιλογή του γράμματος γίνεται με τον εξης τρόπο αναλογικά με την πιθανότητα
     ύπαρξης ενός γράμματος μέσα σε μια τυχαία λέξη :
    
     ---- Παράγεται ένας τυχαίος αριθμός από το 1-100. Επιστρέφεται η αντίστοιχη
    λέξη σύμφωνα με το ακόλουθο πινακάκι:
    
    ΤΥΧΑΙΟΣ ΑΡΙΘΜΟΣ   ΕΠΙΣΤΡΕΦΟΜΕΝΗ ΤΙΜΗ
         1-11       ->      Α  (11%)
           12       ->      Β  (1%)
           13       ->      Γ  (1%)
        14-15       ->      Δ  (2%)
        16-24       ->      Ε  (9%)
           25       ->      Ζ  (1%)
        26-30       ->      Η  (5%)
           31       ->      Θ  (1%)
        32-40       ->      Ι  (9%)
        41-44       ->      Κ  (4%)
        45-46       ->      Λ  (2%)
        47-49       ->      Μ  (3%)
        50-55       ->      Ν  (6%)
           56       ->      Ξ  (1%)
        57-67       ->      Ο  (10%)
        68-71       ->      Π  (4%)
        72-75       ->      Ρ  (4%)
        76-83       ->      Σ  (8%)
        84-91       ->      Τ  (8%)
        92-95       ->      Υ  (4%)
           96       ->      Φ  (1%)
           97       ->      Χ  (1%)
           98       ->      Ψ  (1%)
        99-100      ->      Ω  (2%)
        
    */
    protected Letter RandomLetterChooser(){
        Random rand = new Random();
        int randNum = rand.nextInt(100)+1;
        
        // 1-11       ->      Α  (11%)
        if(randNum<=11)
            return letterArray[0];
        
        // 12       ->      Β  (1%)
        else if(randNum==12)
            return letterArray[1];
        
        // 13       ->      Γ  (1%)
        else if(randNum==13)
            return letterArray[2];
        
        // 14-15       ->      Δ  (2%)
        else if(randNum==14 || randNum==15)
            return letterArray[3];
        
        // 16-24       ->      Ε  (9%)
        else if(randNum>=16 && randNum<=24)
            return letterArray[4];
        
        // 25       ->      Ζ  (1%)
        else if(randNum==25)
            return letterArray[5];
        
        // 26-30       ->      Η  (5%)
        else if(randNum>=26 && randNum<=30)
            return letterArray[6];
        
        // 31       ->      Θ  (1%)
        else if(randNum==31)
            return letterArray[7];
        
        // 32-40       ->      Ι  (9%)
        else if(randNum>=32 && randNum<=40)
            return letterArray[8];
        
        // 41-44       ->      Κ  (4%)
        else if(randNum>=41 && randNum<=44)
            return letterArray[9];
        
        // 45-46       ->      Λ  (2%)
        else if(randNum==45 || randNum==46)
            return letterArray[10];
        
        // 47-49       ->      Μ  (3%)
        else if(randNum>=47 && randNum<=49)
            return letterArray[11];
        
        // 50-55       ->      Ν  (6%)
        else if(randNum>=50 && randNum<=55)
            return letterArray[12];
        
        // 56       ->      Ξ  (1%)
        else if(randNum==56)
            return letterArray[13];
        
        // 57-67       ->      Ο  (10%)
        else if(randNum>=57 && randNum<=67)
            return letterArray[14];
        
        // 68-71       ->      Π  (4%)
        else if(randNum>=68 && randNum<=71)
            return letterArray[15];
        
        // 72-75       ->      Ρ  (4%)
        else if(randNum>=72 && randNum<=75)
            return letterArray[16];
        
        // 76-83       ->      Σ  (8%)
        else if(randNum>=76 && randNum<=83)
            return letterArray[17];
        
        // 84-91       ->      Τ  (8%)
        else if(randNum>=84 && randNum<=91)
            return letterArray[18];
        
        // 92-95       ->      Υ  (4%)
        else if(randNum>=92 && randNum<=95)
            return letterArray[19];
        
        // 96       ->      Φ  (1%)
        else if(randNum==96)
            return letterArray[20];
        
        // 97       ->      Χ  (1%)
        else if(randNum==97)
            return letterArray[21];
        
        // 98       ->      Ψ  (1%)
        else if(randNum==98)
            return letterArray[22];
        
        //99-100      ->      Ω  (2%)
        else if(randNum==99 || randNum==100)
            return letterArray[23];
        
        return null;
    }
}
