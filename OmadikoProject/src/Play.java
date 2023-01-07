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
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
 
public final class Play extends JFrame{
    
    static Letter[] letterArray;
    
    private Score score;
    
    //Όλα τα στοιχεία που απαρτίζουν το γραφικό σώμα της εφαρμογής
    private final JMenuBar menuBar;
    private final JMenu menu,tools;
    private final JMenuItem newGame, endGame, changeProfile, settings, 
            searchWordFile, exit;
    
    private final JMenuItem help,about;
    private final JPanel infoPanel, mainCenterPanel,SendButtonSelandProgPanel, selectionandProgressPanel,
            gamePanel, selectionsPanel, progressPanel;
    
    private static JPanel messagePanel;
    
    private final JLabel infoLabel;
    private static JLabel messageLabel;
    private JLabel wordsFoundLabel;
    
    private static JButton sendWord;
    private static JProgressBar progressBar;
    private static final int gameRows = 8;
    private static final int gameCols = 8;
    private static final int NUM = gameRows * gameCols;
    private static final JPanel[] charPanels = new JPanel[NUM];
    private static final JLabel[] charLabels = new JLabel[NUM];
    private static final JLabel[] pointLabels = new JLabel [NUM];
    
    private static final LetterPanel[] letterPanels = new LetterPanel [NUM];
    
    Play(Profile profile,int StartGame){
        
        //Βασικά χαρακτηριστικά και λειτουργίες του Frame
        this.setTitle("Το Μονοπάτι των Λέξεων"); //Όνομα εφαρμογής
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); //Ξεκινάει σε fullscreen
       
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
        SendButtonSelandProgPanel = new JPanel();
        selectionandProgressPanel = new JPanel();
        selectionsPanel= new JPanel();
        progressPanel = new JPanel();
        messagePanel = new JPanel();
        
        //Ορισμούς του layout κάθε panel
        mainCenterPanel.setLayout(new GridLayout(1,2));
        
        SendButtonSelandProgPanel.setLayout(new BorderLayout());
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
        
        sendWord = new JButton("Υποβολή"); 
        sendWord.setFocusable(false);
        progressBar = new JProgressBar(0,200);
        progressBar.setStringPainted(true);
        updateProgressBar(0);
        progressBar.setFont(new Font("Verdana",Font.BOLD,25));
        progressBar.setForeground(Color.CYAN);
        progressBar.setBackground(Color.BLACK);
        
        
        JLabel label4=new JLabel("PROGRESS");
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        messageLabel = new JLabel("MESSAGES");
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        
        //Προσωρινή προσθήκη διακριτικών label στα panel
        infoPanel.add(infoLabel);
        //progressPanel.add(wordsFoundLabel);
        SendButtonSelandProgPanel.add(sendWord,BorderLayout.WEST);
        progressPanel.add(progressBar,BorderLayout.SOUTH);
        messagePanel.add(messageLabel);
        
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
        mainCenterPanel.setBackground(Color.GRAY);
        mainCenterPanel.add(SendButtonSelandProgPanel);
        SendButtonSelandProgPanel.add(selectionandProgressPanel);
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
        
        this.setBackground(Color.GRAY);
        //this.pack();
        this.setVisible(true);
        
        if(StartGame==1){
            NewGame();
        }
    }
    
    protected void NewGame(){
        
        //Διαμόρφωση πλαισίου πάνελ-γραμμάτων παιχνιδιού
         Border br = BorderFactory.createLineBorder(Color.black);
         
         FoundWords a = new FoundWords();
         Score.resetBlue();
         wordsFoundLabel=new JLabel("Λέξεις που βρέθηκαν: "+a.returnNumberOfWordsFound());
         wordsFoundLabel.setFont(new Font("Sans",Font.PLAIN,30));
         wordsFoundLabel.setHorizontalAlignment(JLabel.CENTER);
         wordsFoundLabel.setVerticalAlignment(JLabel.CENTER);
         progressPanel.add(wordsFoundLabel);
         
         //Κουμπί υποβολής λέξης
         sendWord.addActionListener((ActionEvent e) -> {
             LetterPanel.changePrevButton(-1);
             
             //Περίπτωση όπου η λέξη έχει ήδη βρεθεί
             if(a.checkIfWordFound(Score.returnWord()) == true){
                 displayMessage(0,"Αυτή η λέξη έχει βρεθεί ήδη!");
                 Score.resetWord();
                 Score.resetScore();
                 ResetPanels();
             }
             
             else{
                 //Περίπτωση όπου η λέξη υπάρχει
                 if(Lexicon.doesWordExist(Score.returnWord()) == true){
                     try {
                         SoundEffects.correctWord();
                     } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                         Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     if(Score.returnBlue()==true){
                         displayMessage(3,"Συγχαρητήρια! Βρήκες την λέξη "
                        +Score.returnWord()+" και έκανες "+Score.returnBlueScore() +" βήματα!");
                         a.foundWord(Score.returnWord(), 2*Score.returnScore()); //προσθήκη της λέξης στο σετ με αυτές που έχουν βρεθεί
                     }
                     else{
                         displayMessage(3,"Συγχαρητήρια! Βρήκες την λέξη "
                        +Score.returnWord()+" και έκανες "+Score.returnScore() +" βήματα!");
                         a.foundWord(Score.returnWord(), Score.returnScore()); //προσθήκη της λέξης στο σετ με αυτές που έχουν βρεθεί
                     }
                     
                     
                     
                    updateProgressBar(a.returnPointsGathered()); //update στο progressbar
                    updateWordsFoundLabel(a.returnNumberOfWordsFound());
                     
                 }
                 //Περίπτωση όπου η λέξη δεν υπάρχει
                 else{
                     try {
                         SoundEffects.wrongWord();
                     } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
                         Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     displayMessage(1,"Δεν υπάρχει η λέξη "+Score.returnWord());
                 }
                 
                 //reset των Πάνελ και των μεταβλητών που κρατάνε τα δεδομένα
                 //της λέξης που σχηματίζεται
                 Score.resetScore();
                 Score.resetWord();
                 Score.resetBlue();
                 ResetPanels();
             }
         });
         //Τυχαία επιλογή αριθμού από μπαλαντέρ στο παιχνίδι
         int[] placeOfWilds;
         placeOfWilds = new int[returnRandom(0,4)];
         
         //Τυχαία επιλογή θέσης μπαλαντέρ
         for(int i=0; i < placeOfWilds.length; i++)
             placeOfWilds[i]=returnRandom(0,NUM-1);
         
         
         //Τυχαία επιλογή αριθμού γραμμάτων με κόκκινο φόντο στο παιχνίδι
         int[] placeOfRedLetters;
         placeOfRedLetters = new int[returnRandom(0,2)];
         
         //Τυχαία επιλογή θέσης γραμμάτων με κόκκινο φόντο στο παιχνίδι
         for(int i=0; i < placeOfRedLetters.length; i++ )
             placeOfRedLetters[i] = returnRandom(0,NUM-1,placeOfWilds);
         
         //Tυχαία επιλογή αριθμού γραμμάτων με μπλε φόντο στο παιχνίδι
         int[] placeOfBlueLetters;
         placeOfBlueLetters = new int[returnRandom(0,3)];
         
         //Τυχαία επιλογή θέσης γραμμάτων με μπλε φόντο στο παιχνίδι
         for(int i=0; i < placeOfBlueLetters.length; i++)
             placeOfBlueLetters[i] = returnRandom(0,NUM-1,placeOfWilds,placeOfRedLetters);
         
         //Αρχικοποίηση του PrevButton σε -1
         LetterPanel.changePrevButton(-1);
         
         // --------------------- Δημιουργία των Panel ------------------------
         
         int keeper1; //Αποθηκεύει αν το panel έχει ειδική κατάσταση ή όχι
         
         for(int i=0; i < NUM; i++){
            
            charPanels[i] = new JPanel();
            charPanels[i].setLayout(new GridLayout(1,4));
            charPanels[i].setBorder(br); //Προσθήκη border στα κουμπιά-πανελ
           
            //Απόκτηση τυχαίου γράμματος
            Letter chosenLetter = RandomLetterChooser();
            
            keeper1 = 0;
            
            
            //Αν το panel έχει επιλεχθεί για μπαλαντέρ, κάνε το πάνελ μπαλαντέρ
            for(int l=0; l < placeOfWilds.length; l++){
                if(placeOfWilds[l] == i){
                    letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(),chosenLetter.ReturnLetterPoints(),4);
                    keeper1 = 1;
                    break;
                }       
            }
            
            if(keeper1 == 0){
                //Αν το πάνελ έχει επιλεχθεί για κόκκινο, κάνε το πάνελ κόκκινο
                for(int l=0; l< placeOfRedLetters.length; l++){
                    if(placeOfRedLetters[l] == i){
                        letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(), chosenLetter.ReturnLetterPoints(),2);
                        keeper1 = 1;
                        break;
                    }
                }
            }
            
            if(keeper1 == 0){
                //Αν το πάνελ έχει επιλεχθεί για μπλε, κάνε το πάνελ μπλε
                for(int l=0; l< placeOfBlueLetters.length; l++){
                    if(placeOfBlueLetters[l] == i){
                        letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(), chosenLetter.ReturnLetterPoints(),3);
                        
                        keeper1 = 1;
                        break;
                    }
                }
            }
            
            if(keeper1 == 0){
                //Αν το πάνελ δεν έχει επιλεχθεί για ειδική λειτουργία, κάντο άσπρο
                letterPanels[i] = new LetterPanel(i,chosenLetter.ReturnLetter(), chosenLetter.ReturnLetterPoints(),1);
                
            }
       
            charLabels[i] = new JLabel(letterPanels[i].displayLetter());
            charLabels[i].setFont(new Font("Serif",Font.PLAIN, 32));
            charLabels[i].setHorizontalAlignment(JLabel.CENTER);
            pointLabels[i] = new JLabel(letterPanels[i].displayPoints());
            
            if(letterPanels[i].returnType() !=4){
                pointLabels[i] = new JLabel(letterPanels[i].displayPoints());
                pointLabels[i].setFont(new Font("Serif",Font.PLAIN, 14));
                
                charPanels[i].add(new JLabel());
                charPanels[i].add(charLabels[i]);
                charPanels[i].add(pointLabels[i]);
                charPanels[i].add(new JLabel());
            }
            else
                charPanels[i].add(charLabels[i]);
            
            //Χρωματίζει τα panel ανάλογα την λειτουργία τους 
             switch (letterPanels[i].returnType()) {
                 case 2 -> charPanels[i].setBackground(Color.red);
                 case 3 -> charPanels[i].setBackground(Color.CYAN);
                 case 4 -> charPanels[i].setBackground(Color.MAGENTA);
                 default -> { charPanels[i].setBackground(Color.WHITE);
                 }
             }
            
            //Κάνει τα panel clickable
            charPanels[i].addMouseListener(new LetterPanelListener(letterPanels[i]));
            gamePanel.add(charPanels[i]);
            
        }
        gamePanel.setBackground(Color.GRAY);
    }
    
    //Κάνει reset τα Panels στο σωστό τους χρώμα ( για χρήση μετά από λάθος επιλογή )
    protected static void ResetPanels(){
        
        System.out.println(LetterPanel.returnPrevButton());
        for(int i=0; i < NUM; i++){
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            switch (letterPanels[i].returnType()) {
                 case 2 -> charPanels[i].setBackground(Color.red);
                 case 3 -> charPanels[i].setBackground(Color.CYAN);
                 case 4 -> charPanels[i].setBackground(Color.MAGENTA);
                 default -> { charPanels[i].setBackground(Color.WHITE);
                 }
            }
        }
    }
    
    //Κάνει reset τα Panels στο σωστό τους χρώμα μετά από αλλαγή μπαλαντέρ
    protected static void ResetPanels(int exWild){
        
        for(int i=0; i < NUM; i++){
            charLabels[i].setText(letterPanels[i].displayLetter());
            pointLabels[i].setText(letterPanels[i].displayPoints());
            
            //Δημιουργία των Label που δεν υπήρχαν προηγουμένως στο panel
            //επειδή ήταν Μπαλαντέρ
            if(i==exWild){
                charPanels[i].remove(charLabels[i]);
                 pointLabels[i] = new JLabel(letterPanels[i].displayPoints());
                pointLabels[i].setFont(new Font("Serif",Font.PLAIN, 14));
                
                charPanels[i].add(new JLabel());
                charPanels[i].add(charLabels[i]);
                charPanels[i].add(pointLabels[i]);
                charPanels[i].add(new JLabel());
            }
            switch (letterPanels[i].returnType()) {
                 case 2 -> charPanels[i].setBackground(Color.red);
                 case 3 -> charPanels[i].setBackground(Color.CYAN);
                 case 4 -> charPanels[i].setBackground(Color.MAGENTA);
                 default -> { charPanels[i].setBackground(Color.WHITE);
                 }
            }
        }
    }
    
    //JOptionPane για επιλογή γράμματος από μπαλαντέρ
    protected static int WildLetterChooser(){
       
        String[] options = {"Α", "Β","Γ","Δ","Ε","Ζ","Η","Θ","Ι","Κ","Λ","Μ","Ν",
            "Ξ","Ο","Π","Ρ","Σ","Τ","Υ","Φ","Χ","Ψ","Ω"};
        return JOptionPane.showOptionDialog(null,
                "Διάλεξε το γράμμα που θα αντικαταστήσει τον μπαλαντέρ",
                "Μπαλαντέρ",JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/joker.png"), options , null); // via https://www.flaticon.com/authors/smashicons

    }
    
    //Αρχικοποιεί τον πίνακα γραμμάτων με αντικείμενα γραμμάτων
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
     
        int randNum = returnRandom(1,100);
        
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
    
    //Επιστρέφει τυχαίο αριθμό από min έως max
    protected int returnRandom(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max)+min;
    }
    
    // -------------------- Overload στην returnRandom --------------------
    //Επιστρέφει τυχαίο αριθμό από min έως max εκτός από τους αριθμούς που
    //περιέχονται στον πίνακα exception
    protected int returnRandom(int min, int max, int[] exception){
        Random rand = new Random();
        int keeper = 1;
        int randNum =0;
        while(keeper == 1){
            keeper = 0;
            randNum = rand.nextInt(max)+min;
            //Αν ο τυχαίος αριθμός randNum περιλαμβάνεται στον exception μένει 
            //στην while
            for(int i=0; i < exception.length; i++){
                if(randNum == exception[i])
                    keeper = 1;
            }
        }
        
        return randNum;
    }
    
    // -------------------- Overload στην returnRandom --------------------
    //Επιστρέφει τυχαίο αριθμό από min έως max εκτός από τους αριθμούς που
    //περιέχονται στους πίνακες exception
    protected int returnRandom(int min, int max, int[] exception1, int[] exception2){
        Random rand = new Random();
        int keeper = 1;
        int randNum=0;
        while(keeper == 1){
            keeper = 0;
            randNum = rand.nextInt(max)+min;
            //Αν ο τυχαίος αριθμός randNum περιλαμβάνεται στον exception1 
            //μένει στην while
            for(int i=0; i < exception1.length; i++){
                if(randNum == exception1[i])
                    keeper = 1;
            }
            //Αν ο τυχαίος αριθμός randNum περιλαμβάνεται στον exception2 
            //μένει στην while
            for(int i=0; i < exception2.length; i++){
                if(randNum == exception2[i])
                    keeper = 1;
            }
        }
        
        return randNum;
    }
    
    //Επιστρέφει ένα συγκεκριμένο αντικείμενο από τον πίνακα γραμμάτων 
    // --getter για χρήση από την κλάση LetterPanelListener
    protected static Letter ReturnLetterArray(int n){
        return letterArray[n];
    }
    
    //Εμφανίζει μήνυμα στον παίκτη στο κάτω μέρος της οθόνης
    protected static void displayMessage(int color, String message){
        messageLabel.setText(message);
        switch (color) {
            case 0 -> messagePanel.setBackground(Color.gray);
            case 1 -> messagePanel.setBackground(Color.red);
            default -> messagePanel.setBackground(Color.green);
        }
    }
    
    //Ενημερώνει το progressbar με την τιμή value
    protected static void updateProgressBar(int value){
        progressBar.setValue(value);
        progressBar.setString("Βήματα: "+value+"/200");
    }
    
    //Ενημερώνει το πόσες λέξεις έχουν βρεθεί
    protected void updateWordsFoundLabel(int value){
        wordsFoundLabel.setText("Λέξεις που βρέθηκαν: "+value);
    }
}
